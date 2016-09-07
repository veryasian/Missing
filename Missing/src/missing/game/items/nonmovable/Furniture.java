/*	File: Furniture.java
 * 	
 * 	Authors:			ID
 *	Casey Huang			300316284
 * 	Date				Author					Changes
 *	7 Sep 16 			Casey Huang				Created Furniture class and added javaDoc comments
 */
package missing.game.items.nonmovable;

import java.awt.Point;

/**
 * Represents a furniture item that a player can interact with.
 */
public abstract class Furniture extends Interactable{
	/**
	 * Creates an instance of a Funiture item.
	 * @param name
	 * @param description
	 * @param worldLocation
	 * @param tileLocation
	 */
	public Furniture(String name, String description, Point worldLocation, Point tileLocation) {
		super(name, description, worldLocation, tileLocation);
	}
	
	/**
	 * For player to perform an action on this Interactable item.
	 */
	public abstract void performAction();
}
