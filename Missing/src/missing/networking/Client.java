/* File: Client.java
 * 
 * Authors			ID
 * Edward Kelly 	300334192
 * 
 * Date				Author			Modification
 * 19 Sep 16		Edward Kelly	created class
 * 24 Sep 16		Edward Kelly	added vControl setting up
 * 28 Sep 16		Edward Kelly	fixed minimize bug
 * 28 Sep 16		Edward Kelly	integrated GameView
 * 29 Sep 16		Edward Kelly	now receives instructions instead of game
 * 30 Sep 16		Edward Kelly	now sends player image number
 * 2 Oct 16			Edward Kelly	disconnects handled
 * 3 Oct 16			Edward Kelly	implemented dieing
 * 10 Oct 16		Edward Kelly	fixed health difference bug between clients
 * 10 Oct 16		Edward Kelly	added sendUseItem and bag/pocket transfers
 */
package missing.networking;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

import missing.game.Game;
import missing.game.characters.Merchant;
import missing.game.characters.Player;
import missing.game.items.nonmovable.Shop;
import missing.game.items.nonmovable.Shop.ShopType;
import missing.game.items.movable.Craftable;
import missing.game.items.movable.Food;
import missing.game.items.movable.Food.FoodType;
import missing.game.items.movable.Movable;
import missing.game.items.movable.Tool;
import missing.game.items.movable.Tool.ToolType;
import missing.game.world.nodes.WorldTile.Pile;
import missing.game.world.nodes.WorldTile.TileObject;
import missing.game.world.nodes.WorldTile.TileObject.Direction;
import missing.helper.GameException;
import missing.helper.SignalException;
import missing.ui.controller.VControl;
import missing.ui.views.ShopView;

/**
 * The Client is responsible for sending inputs from the player to the server
 * and providing an instance of the game to the view package to be displayed to
 * the player.
 *
 */
public class Client extends Thread implements KeyListener {
	/** The socket representing this client */
	private Socket socket;
	/** Receives info from server */
	private ObjectInputStream in;
	/** Sends info to server */
	private PrintWriter out;
	/** Reference to the game sent by server. */
	private Game game;
	/** Unique ID representing player/client. If 0, the client is the host */
	private int clientID;
	/**
	 * VControl responsible for displaying game and taking inputs from player
	 */
	private VControl vControl;
	/** Name of player for this client */
	private String playerName;
	/** Number that represents player image for this client */
	private int imageNumber;

	public Client(Socket s, VControl vControl, String playerName, int imageNumber) {
		this.socket = s;
		this.vControl = vControl;
		this.playerName = playerName;
		this.imageNumber = imageNumber;
		vControl.setClient(this);
	}

	// Getters and Setters

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public int getClientID() {
		return clientID;
	}

	public void setClientID(int clientID) {
		this.clientID = clientID;
	}

	// Methods

	public void run() {
		System.out.println("Client running");
		try {
			// setup input and output streams to server
			in = new ObjectInputStream(socket.getInputStream());
			out = new PrintWriter(socket.getOutputStream(), true);

			// wait for request for name
			String request = (String) in.readObject();
			if (request.equals("name")) {
				out.println(playerName);
				out.println(imageNumber);
			}

			// Set initial game state
			clientID = (int) in.readObject();
			vControl.setPlayerID(clientID);
			game = (Game) in.readObject();
			try {
				vControl.addKeyListener(this);
				vControl.setGGame(game);
				vControl.changeView(vControl.getGameView());
				vControl.requestFocusInWindow();

			} catch (GameException e) {
			}

			// listen for updates from server
			boolean playerDied = false;
			while (true) {
				Object input = null;
				try {
					input = in.readObject();
				} catch (EOFException e) {
					vControl.displayException("Disconnected from host");
					vControl.dispose();
					new VControl();
				}
				if (input == null) {
					break;
				}
				if (input.getClass() == String.class) {
					// received instruction
					// player to be changed
					int movingPlayer = (Integer) (in.readObject());
					Direction direction = (Direction) (in.readObject());
					if (input.equals("move")) {
						try {
							if (game.validMove(movingPlayer, direction)) {
								game.movePlayer(movingPlayer, direction);
							}
						} catch (GameException e) {
							if (clientID == movingPlayer) {
								vControl.displayException(e.getMessage());
							}
						}
					} else if (input.equals("turn")) {
						try {
							game.turnPlayer(movingPlayer, direction);
						} catch (GameException e) {
							if (clientID == movingPlayer) {
								vControl.displayException(e.getMessage());
							}
						}
					} else if (((String)input).contains("perform")) {
						// only receive actions from other players
						// actions for this player handled locally in
						// handleAction
						String randomItem = ((String)input).split(" ")[1];
						try {
							game.performAction(movingPlayer);
							if (!randomItem.equals("NONE")) {
								// didnt generate random item
								game.getAvatars()[movingPlayer]
										.addToPocket(new Food(null, null, Food.FoodType.valueOf(randomItem)));
							}
						} catch (GameException e) {
							// ignore it
						} catch (SignalException e1) {
							if (e1.getMessage().contains("DEAD")) {
								String[] msg = e1.getMessage().split(" ");
								int id = Integer.parseInt(msg[1]);
								if (clientID == id) {
									playerDied = true;
								}
							} else if (e1.getMessage().contains("SHOP")) {
								game.forceRemovePlayer(movingPlayer);
							} else if (!e1.getMessage().equals(randomItem)) {
								if (randomItem.equals("NONE")) {
									// random item generated when shouldn't of
									try {
										game.getAvatars()[movingPlayer].removeGivenAmountFromPocket(
												new Food(null, null, Food.FoodType.valueOf(e1.getMessage())));
									} catch (GameException e) {
									}
								}
							}
						}
					} else if (input.equals("disconnect")) {
						// a player disconnected
						if (!game.getAvatars()[movingPlayer].isDead()) {
							game.getAvatars()[movingPlayer].setDead(true);
							game.convertPlayerToPile(movingPlayer);
						}
						vControl.displayTimedMessage(game.getAvatars()[movingPlayer].getName() + " disconnected");

					} else if (((String) input).contains("craft")) {
						String itemType = ((String) input).split(" ")[1];
						try {
							Tool tool = Craftable.createTool(ToolType.valueOf(itemType),
									game.getAvatars()[movingPlayer]);
							game.getAvatars()[movingPlayer].addToPocket(tool);
						} catch (GameException e) {
							// already handled by player crafting item
						}
					} else if (((String) input).contains("exit")) {
						int id = Integer.valueOf(((String) input).split(" ")[1]);
						game.forceEnterPlayer(id);
					} else if (((String) input).contains("use")) {
						String foodType = ((String) input).split(" ")[1];
						Food food = null;
						Player player = game.getAvatars()[movingPlayer];
						for (Movable item : player.getPocket().getItems()){
							if (item instanceof Food){
								if (((Food)item).getFoodType().toString().equals(foodType)){
									food = (Food) item;
								}
							}
						}
						try {
							food.use(game.getAvatars()[movingPlayer]);
						} catch (GameException e) {
							if (e.getMessage().equals("Pocket is empty.")) {
								// this client version of player doesnt have the
								// random item eg apple
							} else if (e.getMessage().equals("Item not found.")) {
								// this client version of player doesnt have the
								// random item eg apple
							} 
						}
					} else if (((String) input).contains("sell")) {
						String itemName = ((String) input).split(" ")[1];
						String shopType = ((String) input).split(" ")[2];
						Player player = game.getAvatars()[movingPlayer];
						Movable selling = null;
						for (Movable item : player.getPocket().getItems()){
							if (itemName.equals(item.toString())){
								selling = item;
							}
						}
						if (selling == null){
							for (Movable item : player.getBag().getItems()){
								if (itemName.equals(item.toString())){
									selling = item;
								}
							}
						}
						try {
							new Merchant(null,null,ShopType.valueOf(shopType)).sellItem(player, selling);
						} catch (GameException e) {
						}
						
					} else if (((String) input).contains("buy")) {
						String itemName = ((String) input).split(" ")[1];
						String shopType = ((String) input).split(" ")[2];
						Player player = game.getAvatars()[movingPlayer];
						try {
							new Merchant(null,null,ShopType.valueOf(shopType)).buyItem(player, itemName);
						} catch (GameException e) {
						}
						
					} else if (((String) input).contains("pilepickup")) {
						String itemName = ((String) input).split(" ")[1];
						Pile pile = null;
						Movable selectedItem = null;
						try {
							pile = (Pile) game.getObjectInFront(movingPlayer);
							for (TileObject pileItem : pile.getItems()) {
								if (pileItem.toString().equals(itemName)) {
									selectedItem = (Movable) pileItem;
									break;
								}
							}
							game.getAvatars()[movingPlayer].addToPocket(selectedItem);
						} catch (GameException e) {
						}
						pile.getItems().remove(selectedItem);
					} else if (((String) input).contains("transfer")) {
						String to = ((String) input).split(" ")[1];
						String itemName = ((String) input).split(" ")[2];
						Player player = game.getAvatars()[movingPlayer];
						if (to.equals("bag")) {
							Movable movingItem = null;
							for (Movable pocketItem : player.getPocket().getItems()) {
								if (pocketItem.toString().equals(itemName)) {
									movingItem = pocketItem;
									break;
								}
							}
							try {
								player.removeFromPocket(movingItem);
								player.addToBag(movingItem);
							} catch (GameException e) {
							}

						} else if (to.equals("pocket")) {
							Movable movingItem = null;
							for (Movable bagItem : player.getBag().getItems()) {
								if (bagItem.toString().equals(itemName)) {
									movingItem = bagItem;
									break;
								}
							}
							try {
								player.getBag().removeItem(movingItem);
								player.addToPocket(movingItem);
							} catch (GameException e) {
							}

						}
					} else if (((String) input).contains("drop")) {
						String itemName = ((String) input).split(" ")[1];
						Player player = game.getAvatars()[movingPlayer];
						Movable movingItem = null;
						for (Movable pocketItem : player.getPocket().getItems()) {
							if (pocketItem.toString().equals(itemName)) {
								movingItem = pocketItem;
								break;
							}
						}
						try {
							game.placeDroppedItem(movingItem, movingPlayer);
						} catch (GameException e) {
							// Ignore
						}
					}
					try {
						vControl.updateGGame(game);
					} catch (GameException e) {
					}
					vControl.repaint();
					if (playerDied) {
						vControl.displayDead();
						playerDied = false;
					}
				}
			}
			socket.close();
		} catch (IOException | ClassNotFoundException e) {
			if (e.getClass() == SocketException.class) {
				// host disconnected, send to main menu
				vControl.displayException("Disconnected from host");
				// vControl.reset();
				vControl.dispose();
				new VControl();
			} else {
			}
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Direction moveDirection = null;
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_W:
			moveDirection = Direction.NORTH;
			break;
		case KeyEvent.VK_S:
			moveDirection = Direction.SOUTH;
			break;
		case KeyEvent.VK_A:
			moveDirection = Direction.WEST;
			break;
		case KeyEvent.VK_D:
			moveDirection = Direction.EAST;
			break;
		case KeyEvent.VK_Q:
			out.println("Q");
			break;
		case KeyEvent.VK_E:
			out.println("E");
			break;
		case KeyEvent.VK_F:
			handleAction();
			break;
		}
		if (moveDirection != null) {
			try {
				if (game.validMove(clientID, moveDirection)) {
					out.println(moveDirection.toString());
				}
			} catch (GameException e1) {
				vControl.displayException(e1.getMessage());
			}
		}
	}

	/**
	 * Performs an action in the game. If action needs to be applied to all
	 * clients games the action is sent to the server
	 */
	public void handleAction() {
		try {
			game.performAction(clientID);
			out.println("perform NONE");
			vControl.updateGGame(game);
			vControl.repaint();
		} catch (SignalException | GameException e) {
			if (e.getClass() == SignalException.class) {
				if (e.getMessage().equals("FISH") || e.getMessage().equals("APPLE") || e.getMessage().equals("BERRY")) {
					out.println("perform "+e.getMessage());
					try {
						vControl.updateGGame(game);
					} catch (GameException e1) {
						vControl.displayException(e1.getMessage());
					}
					vControl.repaint();
				} else if (e.getMessage().equals("CONTAINER")) {
					vControl.displayContainerItems();
				} else if (e.getMessage().equals("PILE")) {
					vControl.displayPileItems();
				} else if (e.getMessage().contains("DEAD")) {
					String[] msg = e.getMessage().split(" ");
					int id = Integer.parseInt(msg[1]);
					out.println("perform NONE");
					try {
						vControl.updateGGame(game);
					} catch (GameException e1) {
						vControl.displayException(e1.getMessage());
					}
					vControl.repaint();
					if (id == clientID) {
						// this player died
						vControl.displayDead();
					}
				} else if (e.getMessage().contains("SHOP")) {
					out.println("perform NONE");
					try {
						// first grab the shop in front of the player
						Shop shop = (Shop) game.getObjectInFront(clientID);
						// remove player from the current tile
						game.forceRemovePlayer(clientID);
						// update the shop of the player
						ShopView view = (ShopView) vControl.getView(vControl.getShopView());
						view.setPlayer(game.getAvatars()[clientID]);
						view.updateDisplay(shop);
						vControl.changeView(vControl.getShopView());
					} catch (GameException e1) {
						vControl.displayException(e.getMessage());
					}
				}
			} else if (e.getClass() == GameException.class) {
				vControl.displayException(e.getMessage());
				out.println("perform NONE");
			}
		}
	}

	/**
	 * Disconnects client from server and closes socket
	 */
	public void disconnectClient() {
		try {
			out.println("disconnect");
		} catch (NullPointerException e) {
		}
		try {
			socket.close();
		} catch (IOException e) {
		}
	}
	
	private void update(){
		try {
			vControl.updateGGame(game);
			
		} catch (GameException e) {
		}
		vControl.repaint();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	public void sendCraftedItem(String item) {
		out.println("craft " + item);
		update();
	}

	public void sendExitSignal(int id) {
		game.forceEnterPlayer(id);
		out.println("exit " + id);
		update();
	}

	public void sendUseItem(String foodType) {
		out.println("use " + foodType);
		update();
	}

	public void sendTransferTo(String to, Movable item) {
		out.println("transfer " + to + " " + item.toString());
		update();

	}

	public void sendPilePickUp(String selectedItem) {
		out.println("pilepickup " + selectedItem);
		update();

	}

	public void sendDropItem(Movable item) throws GameException {
		// first send drop intent
		out.println("drop " + item.toString());
		game.placeDroppedItem(item, clientID);
		update();
	}

	public void sendSell(String itemName, String shopType) {
		out.println("sell "+itemName+" "+shopType);
		update();
		
	}

	public void sendBuy(String itemName, String shopType) {
		out.println("buy "+itemName+" "+shopType);
		update();
	}
}