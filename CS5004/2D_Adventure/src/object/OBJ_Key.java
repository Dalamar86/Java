package object;

import enums.ObjectType;
import main.*;

/**
 * Key lets player remove door obstacles. Removed from inventory once used, i.e. single use.
 * 
 * @author white
 *
 */
public final class OBJ_Key extends SuperObject {
	
	/**
	 * Creates an instance of the key object.
	 * 
	 * @param gp (GamePanel) Current game panel
	 */
	public OBJ_Key(GamePanel gp) {
		super(gp, "Key");
		setType(ObjectType.KEY);
		this.setDescription("[" + getName() + "]\nThis unassuming key opens things.");
	}
}
