package object;

import main.*;

/**
 * Boots object, makes the player faster.
 * 
 * @author Robert Wilson
 *
 */
public final class OBJ_Boots extends SuperObject {

	/**
	 * Creates an instance of the boots object.
	 * 
	 * @param gp (GamePanel) Current game panel
	 */
	public OBJ_Boots(GamePanel gp) {
		super(gp, "Boots");
		this.setDescription("[" + getName() + "]\nAnything is better than being barefoot in the woods.");
	}
}
