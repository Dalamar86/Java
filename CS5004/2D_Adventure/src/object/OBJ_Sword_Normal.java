package object;

import enums.ObjectType;
import main.GamePanel;

/**
 * Normal sword is the basic weapon in the game.  It offers lower attack damage but a larger attack area.
 * 
 * @author Robert Wilson
 *
 */
public final class OBJ_Sword_Normal extends SuperObject {

	/**
	 * Creates an instance of the normal sword object.
	 * 
	 * @param gp (GamePanel) Current game panel
	 */
	public OBJ_Sword_Normal(GamePanel gp) {
		super(gp, "sword_normal");
		setType(ObjectType.WEAPON);
		this.setAttackValue(1);
		getAttackArea().width = 36;
		getAttackArea().height = 36;
		this.setDescription("[" + getName() + "]\nAn old sword you found in the \npalace garbage.");
	}
}
