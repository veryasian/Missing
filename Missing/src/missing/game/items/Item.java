/* File: Item.java
 * 
 * Authors			ID
 * Edward Kelly 	300334192
 * Chris Rabe		300334207
 * 
 * Date				Author			Modification
 * 7 Sep 16			Edward Kelly	created item class
 * 8 Sep 16			Chris Rabe		made item implement TileObject
 * 8 Sep 16			Edward Kelly	made Item implement TileObject
 */
package missing.game.items;

import java.awt.Point;
import java.io.Serializable;

import missing.game.world.nodes.WorldTile.TileObject;

/**
 * Represents all the interactable and non-interactable objects inside the game
 * world. This should be extended to a more specialised subclass.
 *
 */
@SuppressWarnings("serial")
public abstract class Item implements TileObject, Serializable {

	/** name of item, used when displaying details of item */
	protected String name;
	/** description of item, used when displaying details of item */
	protected String description;
	/** represents the portion of the world which the item is located in */
	protected Point worldLocation;
	/** represents the tile this item is located on inside the worldLocation */
	protected Point tileLocation;
	/** represents the direction which the item is facing */
	protected Direction orientation;
	/** represents the direction which the item can be approached from */
	protected Direction approach;

	/**
	 * Create an Instance of an Item, given its location.
	 * 
	 * @param name
	 *            name of the item
	 * @param description
	 *            describes what the item is
	 * @param worldLocation
	 *            the section of the world this item is located in
	 * @param tileLocation
	 *            the tile in the worldLocation in which the item is located
	 */
	public Item(String name, String description, Point worldLocation, Point tileLocation) {
		this.name = name;
		this.description = description;
		this.worldLocation = worldLocation;
		this.tileLocation = tileLocation;
		this.orientation = Direction.SOUTH;
		this.approach = Direction.ALL;
	}

	// Getters and Setters

	@Override
	public Direction getOrientation() {
		return orientation;
	}

	@Override
	public Direction getApproach() {
		return approach;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Point getTileLocation() {
		return tileLocation;
	}

	public void setTileLocation(Point tileLocation) {
		this.tileLocation = tileLocation;
	}

	public Point getWorldLocation() {
		return worldLocation;
	}

	public void setWorldLocation(Point worldLocation) {
		this.worldLocation = worldLocation;
	}

	// Overridden Object methods

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return name + ", " + description;
	}
}
