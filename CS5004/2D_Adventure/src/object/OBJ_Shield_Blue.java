package object;

import enums.ObjectType;
import main.GamePanel;

/**
 * Blue shield is an upgrade for the wooden shield, offering higher defensive capabilities. 
 * 
 * @author Robert Wilson
 *
 */
public final class OBJ_Shield_Blue extends SuperObject {

	/**
	 * Creates an instance of the blue shield object.
	 * 
	 * @param gp (GamePanel) Current game panel
	 */
	public OBJ_Shield_Blue(GamePanel gp) {
		super(gp, "Shield_blue");
		setType(ObjectType.SHIELD);
		this.setDefenseValue(2);
		this.setDescription("[" + getName() + "]\nOld rusty sheild with some blue paint.");
	}
}