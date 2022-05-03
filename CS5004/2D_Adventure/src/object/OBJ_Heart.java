package object;

import main.GamePanel;

/**
 * Heart represents a players health.   One heart for every two points of health.
 * 
 * @author Robert Wilson
 *
 */
public final class OBJ_Heart extends SuperObject {

	/**
	 * Creates an instance of the heart object.
	 * 
	 * @param gp (GamePanel) Current game panel
	 */
	public OBJ_Heart(GamePanel gp) {
		super(gp, "Heart_full");
		addImage("Heart_half", "Heart_blank");
		this.setDescription("[" + getName() + "]\nA symbol of your vitality.");
	}
}
