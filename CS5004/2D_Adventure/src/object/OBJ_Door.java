package object;

import main.*;

/**
 * Door bars entry to certain areas of the map unless player has a key to open them.
 * 
 * @author Robert Wilson
 *
 */
public final class OBJ_Door extends SuperObject {

	/**
	 * Creates an instance of the door object.
	 * 
	 * @param gp (GamePanel) Current game panel
	 */
	public OBJ_Door(GamePanel gp) {
		super(gp, "Door");
		this.setDescription("[" + getName() + "]\nA way of keeping unwanted company out.?nI wonder why it won't let you in.");
		setCollision(true);
	}
}
