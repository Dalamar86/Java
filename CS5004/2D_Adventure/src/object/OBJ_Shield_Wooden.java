package object;

import enums.ObjectType;
import main.GamePanel;

/**
 * Wooden shield is the base defensive shield of the game, offering minimal protection.
 * 
 * @author Robert Wilson
 *
 */
public final class OBJ_Shield_Wooden extends SuperObject {

	/**
	 * Creates an instance of the wooden shield object.
	 * 
	 * @param gp (GamePanel) Current game panel
	 */
	public OBJ_Shield_Wooden(GamePanel gp) {
		super(gp, "shield_wood");
		setType(ObjectType.SHIELD);
		this.setDefenseValue(1);
		this.setDescription("[" + getName() + "]\nA piece of bark pulled from a tree \nnear your house.");
	}
}
