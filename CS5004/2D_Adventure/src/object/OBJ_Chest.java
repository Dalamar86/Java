package object;

import main.*;

/**
 * Chest object, creates an chest with loot. Ends the debug round when found and opened.
 * 
 * @author Robert Wilson
 *
 */
public final class OBJ_Chest extends SuperObject {

	/**
	 * Creates an instance of the chest object.
	 * 
	 * @param gp (GamePanel) Current game panel
	 */
	public OBJ_Chest(GamePanel gp) {
		super(gp, "Chest");
		this.setDescription("[" + getName() + "]\nContains treasures beyond your imagination.\nThat is, until you open it.");
		setCollision(true);
	}
}
