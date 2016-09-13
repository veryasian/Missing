/*	File: WorldNode.java
 * 	Author
 * 	Chris Rabe		300334207
 *  Linus Go		300345571
 * 	Date			Author				Changes
 * 	8 Sep 16		Chris Rabe			create worldNode class
 * 	8 Sep 16		Linus Go			filled the class and added fields.
 * 	8 Sep 16		Chris Rabe			removed some fields and constructor
 */

package missing.game.world.nodes;

import java.awt.Point;
import java.util.List;

import missing.game.items.Item;

/**
 * World node contains various types of world tiles. It should also contain a
 * list of movable and nonMovable objects because this information will be
 * needed when managing server.
 */
public class WorldNode {
	/** Represents the maximum number of neighbours of this node */
	private final int NEIGHBOURS = 4;
	/**
	 * Represents the neighbouring nodes.
	 * 
	 * <pre>
	 * 0 : north, 1 : south, 2 : east, 3 : west
	 * </pre>
	 */
	private WorldNode[] neighbours;
	/** The location of this World Node on the world. */
	private Point gameLocation;
	/** The World Tile 2D array. Each world node has these. */
	private WorldTile[][] worldTiles;

	private List<Item> items; // TODO Use these to initialise world

	public WorldNode(Point location, WorldTile[][] worldTiles) {
		this.gameLocation = location;
		this.worldTiles = worldTiles;
		this.neighbours = new WorldNode[NEIGHBOURS];
	}

	/* Getters and Setters. */
	public WorldTile[][] getWorldTiles() {
		return worldTiles;
	}

	public WorldNode getNorth() {
		return neighbours[0];
	}

	public WorldNode getSouth() {
		return neighbours[1];
	}

	public WorldNode getEast() {
		return neighbours[2];
	}

	public WorldNode getWest() {
		return neighbours[3];
	}

	public void setNorth(WorldNode node) {
		neighbours[0] = node;
	}

	public void setSouth(WorldNode node) {
		neighbours[1] = node;
	}

	public void setEast(WorldNode node) {
		neighbours[2] = node;
	}

	public void setWest(WorldNode node) {
		neighbours[3] = node;
	}

	public Point getGameLocation() {
		return this.gameLocation;
	}

	public void setGameLocation(Point gameLocation) {
		this.gameLocation = gameLocation;
	}
}
