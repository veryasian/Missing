/*	File: GameAssets.java
 * 	Author
 * 	Chris Rabe		300334207
 * 	Casey Huang		300316284
 * 
 * 	Date			Author				Changes
 * 	14 Sep 16		Chris Rabe			created GameAssets.java
 * 	15 Sep 16		Chris Rabe			moved loading the node files here
 * 	16 Sep 16		Casey Huang			Updated class with new images added
 * 	19 Sep 16		Casey Huang			Added all the other item images
 *  27 Sep 16       Casey Huang			Added more images
 *  27 Sep 16		Linus Go			added player images.
 *  27 Sep 16		Casey Huang			Added girl player images.
 *  1 Oct 16		Linus Go			Added tombstone images.
 *  3 Oct 16		Casey Huang			Added pileofitems image.
 *  6 Oct 16 		Casey Huang			Added logo images and button image
 *  7 Oct 16		Casey Huang			Added server/connecting background
<<<<<<< HEAD
 *  9 Oct 16		Edward Kelly		Added crafting background
=======
 *  09 Oct 16		Casey Huang			Added new fonts
>>>>>>> branch 'master' of https://gitlab.ecs.vuw.ac.nz/rabechri/Missing.git
 */

package missing.datastorage.assetloader;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import missing.game.items.nonmovable.Shop.ShopType;
import missing.game.world.nodes.WorldTile.TileObject.Direction;

/**
 * This class contains static fields for the images and files and uses methods
 * for retrieving those fields.
 */
public class GameAssets {
	/*Specifies the path for the various storage paths and world-file paths */

	private static final String STORAGE_PATH = "/missing/datastorage";

	// File assets
	private static final String WORLD_FILE_PATH = STORAGE_PATH + "/files/nodes/";

	// Image assets

	private static BufferedImage tallGrassImage;

	private static BufferedImage foodShopImage;

	private static BufferedImage resourceShopImage;

	private static BufferedImage toolsShopImage;

	private static BufferedImage playerImage;

	private static BufferedImage sandImage;

	private static BufferedImage tombStoneImage;

	private static BufferedImage waterImage;

	private static BufferedImage grassImage;

	private static BufferedImage roadImage;

	private static BufferedImage appleImage;

	private static BufferedImage bushImage;

	private static BufferedImage fireplaceImage;

	private static BufferedImage treeImage;

	private static BufferedImage woodImage;

	private static BufferedImage rockImage;

	private static BufferedImage soilImage;

	private static BufferedImage dirtImage;

	private static BufferedImage stoneImage;

	private static BufferedImage axeImage;

	private static BufferedImage pickaxeImage;

	private static BufferedImage shovelImage;

	private static BufferedImage fishingRodImage;

	private static BufferedImage fishingAreaImage;

	private static BufferedImage darkGrassImage;

	private static BufferedImage fishImage;

	private static BufferedImage berriesImage;

	private static BufferedImage pileOfItemsImage;

	private static BufferedImage logoImage;

	private static BufferedImage splashBackgroundImage;

	private static BufferedImage missingLogoImage;

	private static BufferedImage craftingBackgroundImage;

	private static BufferedImage windowBackgroundImage;

	private static BufferedImage itemBackgroundImage;

	private static Image serverBackgroundImage;

	private static ImageIcon buttonImage;

	private static Font customFont;

	private static Font customFont2;


	// Getters for File Assets
	
	/**
	 * Returns the WorldFile txt file associated with a certain x and y coordinate.
	 * For example, getWorldFile(1,1) returns 1,1.txt
	 * @param x - x coordinate
	 * @param y - y coordinate.
	 * @return
	 */
	public static InputStream getWorldFile(int x, int y) {
		return GameAssets.class.getResourceAsStream(WORLD_FILE_PATH + x + "," + y + ".txt");
	}

	// getters for the image assets
	/**
	 * @return tallGrassImage
	 */
	public static BufferedImage getTallGrassImage() {
		if (tallGrassImage == null) {
			try {
				tallGrassImage = ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/tallgrass.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return tallGrassImage;
	}
	/**
	 * @return foodShopImage
	 */
	public static BufferedImage getFoodShopImage() {
		if (foodShopImage == null) {
			try {
				foodShopImage = ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/foodshop.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return foodShopImage;
	}
	/**
	 * @return resourceShopImage
	 */
	public static BufferedImage getResourceShopImage() {
		if (resourceShopImage == null) {
			try {
				resourceShopImage = ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/resourceshop.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return resourceShopImage;
	}
	/**
	 * 
	 * @return toolsShopImage
	 */
	public static BufferedImage getToolsShopImage() {
		if (toolsShopImage == null) {
			try {
				toolsShopImage = ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/toolsshop.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return toolsShopImage;
	}
	/**
	 * Returns the player image, given the current players index and the direction they are facing.
	 * @param index - of the player
	 * @param direction - of the player
	 * @return - playerImage
	 */
	public static BufferedImage getPlayerImage(int index, String direction) {
		try {
			playerImage = ImageIO.read(GameAssets.class
					.getResource(STORAGE_PATH + "/img/player" + direction + String.valueOf(index) + ".png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		return playerImage;
	}
	/**
	 * @return sandImage
	 */
	public static BufferedImage getSandImage() {
		if (sandImage == null) {
			try {
				sandImage = ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/sand.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sandImage;
	}
	/**
	 * @return tombStoneImage
	 */
	public static BufferedImage getTombStoneImage() {
		if (tombStoneImage == null) {
			try {
				tombStoneImage = ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/tombstone.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return tombStoneImage;
	};
	/**
	 * 
	 * @return waterImage
	 */
	public static BufferedImage getWaterImage() {
		if (waterImage == null) {
			try {
				waterImage = ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/water.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return waterImage;
	}
	/**
	 * @return grassImage
	 */
	public static BufferedImage getGrassImage() {
		if (grassImage == null) {
			try {
				grassImage = ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/grass.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return grassImage;
	}
	/**
	 * 
	 * @return roadImage
	 */
	public static BufferedImage getRoadImage() {
		if (roadImage == null) {
			try {
				roadImage = ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/road.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return roadImage;
	}
	/**
	 * 
	 * @return appleImage
	 */
	public static BufferedImage getAppleImage() {
		if (appleImage == null) {
			try {
				appleImage = ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/apple.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return appleImage;
	}
	/**
	 * 
	 * @return bushImage
	 */
	public static BufferedImage getBushImage() {
		if (bushImage == null) {
			try {
				bushImage = ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/bush.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return bushImage;
	}
	/**
	 * 
	 * @return fireplaceImage
	 */
	public static BufferedImage getFireplaceImage() {
		if (fireplaceImage == null) {
			try {
				fireplaceImage = ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/fireplace.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fireplaceImage;
	}
	/**
	 * Gets the treeImage associated with this number. 
	 * @param num - the number of tree wanted.
	 * @return treeImage
	 */
	public static BufferedImage getTreeImage(int num) {
		String imageNm = "tree" + String.valueOf(num);
		if (treeImage == null) {
			try {
				treeImage = ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/" + imageNm + ".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return treeImage;
	}
	/**
	 * 
	 * @return woodImage
	 */
	public static BufferedImage getWoodImage() {
		if (woodImage == null) {
			try {
				woodImage = ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/wood.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return woodImage;
	}
	/**
	 * 
	 * @return rockImage
	 */
	public static BufferedImage getRockImage() {
		if (rockImage == null) {
			try {
				rockImage = ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/rock.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return rockImage;
	}
	/**
	 * 
	 * @return soilImage
	 */
	public static BufferedImage getSoilImage() {
		if (soilImage == null) {
			try {
				soilImage = ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/soil.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return soilImage;
	}
	/**
	 * 
	 * @return dirtImage
	 */
	public static BufferedImage getDirtImage() {
		if (dirtImage == null) {
			try {
				dirtImage = ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/dirt.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return dirtImage;
	}
	/**
	 * 
	 * @return stoneImage
	 */
	public static BufferedImage getStoneImage() {
		if (stoneImage == null) {
			try {
				stoneImage = ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/stone.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return stoneImage;
	}
	/**
	 * 
	 * @return axeImage
	 */
	public static BufferedImage getAxeImage() {
		if (axeImage == null) {
			try {
				axeImage = ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/axe.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return axeImage;
	}
	/**
	 * 
	 * @return pickaxeImage
	 */
	public static BufferedImage getPickaxeImage() {
		if (pickaxeImage == null) {
			try {
				pickaxeImage = ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/pickaxe.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return pickaxeImage;
	}
	/**
	 * 
	 * @return shovelImage
	 */
	public static BufferedImage getShovelImage() {
		if (shovelImage == null) {
			try {
				shovelImage = ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/shovel.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return shovelImage;
	}
	/**
	 * 
	 * @return fishingRodImage
	 */
	public static BufferedImage getFishingRodImage() {
		if (fishingRodImage == null) {
			try {
				fishingRodImage = ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/fishingrod.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fishingRodImage;
	}
	/**
	 * 
	 * @return fishingAreaImage
	 */
	public static BufferedImage getFishingAreaImage() {
		if (fishingAreaImage == null) {
			try {
				fishingAreaImage = ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/fishingarea.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fishingAreaImage;
	}
	/**
	 * 
	 * @return darkGrassImage
	 */
	public static BufferedImage getDarkGrassImage() {
		if (darkGrassImage == null) {
			try {
				darkGrassImage = ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/darkgrass.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return darkGrassImage;
	}
	/**
	 * 
	 * @return berriesImage
	 */
	public static BufferedImage getBerriesImage() {
		if (berriesImage == null) {
			try {
				berriesImage = ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/berries.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return berriesImage;
	}
	/**
	 * 
	 * @return fishImage
	 */
	public static BufferedImage getFishImage() {
		if (fishImage == null) {
			try {
				fishImage = ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/fish.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fishImage;
	}
	/**
	 * @return windowBackgroundImage
	 */
	public static BufferedImage getWindowBackgroundImage() {
		if (windowBackgroundImage == null) {
			try {
				windowBackgroundImage = ImageIO
						.read(GameAssets.class.getResource(STORAGE_PATH + "/img/windowbackground.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return windowBackgroundImage;
	}
	/**
	 * 
	 * @return craftingBackgroundImage
	 */
	public static BufferedImage getCraftingBackgroundImage() {
		if (craftingBackgroundImage == null) {
			try {
				craftingBackgroundImage = ImageIO
						.read(GameAssets.class.getResource(STORAGE_PATH + "/img/craftingBackground.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return craftingBackgroundImage;
	}
	/**
	 * 
	 * @return pileofItemsImage
	 */
	public static BufferedImage getPileOfItemsImage() {
		if (pileOfItemsImage == null) {
			try {
				pileOfItemsImage = ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/pileofitems.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return pileOfItemsImage;
	}
	/**
	 * 
	 * @return logoImage
	 */
	public static BufferedImage getLogoImage() {
		if (logoImage == null) {
			try {
				logoImage = ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/logo.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return logoImage;
	}
	/**
	 * 
	 * @return splashBackgroundImage
	 */
	public static BufferedImage getSplashBackgroundImage() {
		if (splashBackgroundImage == null) {
			try {
				splashBackgroundImage = ImageIO
						.read(GameAssets.class.getResource(STORAGE_PATH + "/img/splashbackground.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return splashBackgroundImage;
	}
	/**
	 * 
	 * @return missingLogoImage
	 */
	public static BufferedImage getMissingLogoImage() {
		if (missingLogoImage == null) {
			try {
				missingLogoImage = ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/missinglogo.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return missingLogoImage;
	}
	/**
	 * 
	 * @return itemBackgroundImage
	 */
	public static BufferedImage getItemBackgroundImage() {
		if (itemBackgroundImage == null) {
			try {
				itemBackgroundImage = ImageIO
						.read(GameAssets.class.getResource(STORAGE_PATH + "/img/itembackground.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return itemBackgroundImage;
	}
	/**
	 * 
	 * @return serverBackgroundImage
	 */
	public static Image getServerBackgroundImage() {
		if (serverBackgroundImage == null) {
			serverBackgroundImage = new ImageIcon(
					GameAssets.class.getResource(STORAGE_PATH + "/img/serverbackground.gif")).getImage();
		}
		return serverBackgroundImage;
	}
	/**
	 * @return buttonImage
	 */
	public static ImageIcon getButtonImage() {
		if (buttonImage == null) {
			try {
				buttonImage = new ImageIcon(
						ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/button.png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return buttonImage;
	}
	/**
	 * Returns the_dark.ttf as a font object, with a specified size.
	 * @param size - of the font
	 * @return the_dark font
	 */
	public static Font getFont(float size) {
		try {
			customFont = Font.createFont(Font.TRUETYPE_FONT,
					GameAssets.class.getResource(STORAGE_PATH + "/fonts/the_dark.ttf").openStream());
			GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
			genv.registerFont(customFont);
			return customFont.deriveFont(size);
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
		return customFont.deriveFont(size);
	}
	/**
	 * Returns ParmaPetit_Normal.ttf as a font object, with a specified size.
	 * @param size - of the font
	 * @return ParmaPetit_Normal font
	 */
	public static Font getFont2(float size) {
		try {
			customFont2 = Font.createFont(Font.TRUETYPE_FONT,
					GameAssets.class.getResource(STORAGE_PATH + "/fonts/ParmaPetit-Normal.ttf").openStream());
			GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
			genv.registerFont(customFont2);
			return customFont2.deriveFont(size);
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
		return customFont2.deriveFont(size);
	}
	/**
	 * Returns Drifttype.ttf as a font object, with a specified size.
	 * @param size - of the font
	 * @return Drifttype font
	 */
	public static Font getFont3(float size) {
		try {
			customFont2 = Font.createFont(Font.TRUETYPE_FONT,
					GameAssets.class.getResource(STORAGE_PATH + "/fonts/Drifttype.ttf").openStream());
			GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
			genv.registerFont(customFont2);
			return customFont2.deriveFont(size);
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
		return customFont2.deriveFont(size);
	}
	/**
	 * Returns the correct ShopNodeImage given the ShopType and the current Direction.
	 * @param type
	 * @param direction
	 * @return
	 */
	public static BufferedImage getShopNodeImage(ShopType type, Direction direction) {
		try {
			return ImageIO.read(GameAssets.class
					.getResource(String.format("%s/img/%s_%s.png", STORAGE_PATH, type.name(), direction.name())));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
