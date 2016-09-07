/* File: Health.java
 * 
 * Authors:
 * Linus Go		300345571
 * 
 * Date			Author			Modification
 * 7 Sep 16		Linus Go		created Health.java
 */
package missing.game.items.movable;
import java.awt.Point;
/**
 * This class represents Consumable Items that can give health to a player.
 *
 */
public class Health extends Consumable {
	
	/** Dictates how much this Health item regenerates the player by.*/
	protected int REGEN_AMOUNT; 
	
	/**
	 * Construct a new Instance of a Health class.
	 * @param name of item
	 * @param description of Item
	 * @param worldLocation
	 * @param tileLocation
	 * @param amount
	 * @param size
	 */
	public Health(String name, String description, Point worldLocation, Point tileLocation, int amount, int size, int REGAMOUNT) {
		super(name, description, worldLocation, tileLocation, amount, size);
		this.REGEN_AMOUNT = REGAMOUNT;
	}
	
	/**
	 * Returns the REGEN_AMOUNT for this health object.
	 * @return
	 */
	protected int getRegenAmount(){
		return this.REGEN_AMOUNT;
	}
	
	
	
}