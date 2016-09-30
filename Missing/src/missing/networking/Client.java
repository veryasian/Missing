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
 */
package missing.networking;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

import missing.game.Game;
import missing.game.world.nodes.WorldTile.TileObject.Direction;
import missing.helper.GameException;
import missing.helper.SignalException;
import missing.ui.assets.GGame;
import missing.ui.controller.VControl;

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
				vControl.setGGame(new GGame(game, vControl.getView(vControl.getGameView())));
				vControl.changeView(vControl.getGameView());
				vControl.requestFocusInWindow();

			} catch (GameException e) {
				// TODO forward to controller
				e.printStackTrace();
			}

			// listen for updates from server
			while (true) {
				Object input = in.readObject();
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
							if (clientID == movingPlayer){
								vControl.displayGameException(e.getMessage());
							}
						}
					} else if (input.equals("turn")) {
						try {
							game.turnPlayer(movingPlayer, direction);
						} catch (GameException e) {
							if (clientID == movingPlayer){
								vControl.displayGameException(e.getMessage());
							}
						}
					} else if (input.equals("perform")) {
						// only receive actions from other players
						// actions for this player handled locally in handleAction
						try {
							game.performAction(movingPlayer);
						} catch (GameException | SignalException e) {
							// do nothing, message only displayed for
							// player that performed he action
						}
					} else if (input.equals("disconnect")) {
						game.convertPlayerToPile(movingPlayer);
						System.out.println(movingPlayer + " disconnected");
					}
					try {
						vControl.updateGGame(game);
					} catch (GameException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					vControl.repaint();
				}
			}
			socket.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Server has gone offline");
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Direction moveDirection = null;
		int key = e.getKeyCode();
		switch(key){
			case KeyEvent.VK_W :
				moveDirection = Direction.NORTH;
				break;
			case KeyEvent.VK_S :
				moveDirection = Direction.SOUTH;
				break;
			case KeyEvent.VK_A :
				moveDirection = Direction.WEST;
				break;
			case KeyEvent.VK_D :
				moveDirection = Direction.EAST;
				break;
			case KeyEvent.VK_Q :
				out.println("Q");
				break;
			case KeyEvent.VK_E :
				out.println("E");
				break;
			case KeyEvent.VK_F :
				handleAction();
				break;
		}
		if (moveDirection != null) {
			try {
				if (game.validMove(clientID, moveDirection)) {
					out.println(moveDirection.toString());
				}
			} catch (GameException e1) {
				vControl.displayGameException(e1.getMessage());
			}
		}
	}
	
	private void handleAction(){
		try {
			game.performAction(clientID);
			out.println("F");
			vControl.updateGGame(game);
			vControl.repaint();
		} catch(SignalException | GameException e){
			if (e.getClass() == SignalException.class){
				if (e.getMessage().equals("CONTAINER")){
					vControl.displayContainerItems();
				} else if (e.getMessage().equals("PILE")){
					vControl.displayPileItems();
				} else if (e.getMessage().equals("TRADE")){
					//TODO: get other player from exception
					// request trade with other player
				}
			} else if(e.getClass() == GameException.class){
				vControl.displayGameException(e.getMessage());
			}
		}
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}