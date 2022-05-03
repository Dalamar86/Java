package npc;
import gameobject.*;
import main.GamePanel;

/**
 * Non-Player Character gameObject parent wrapper.Holds specific 
 * methods for updating and controlling npcs.
 * 
 * @author Robert Wilson
 *
 */
public abstract class SuperNPC extends GameObject {

	/**
	 * Super constructor of npc children.
	 * 
	 * @param gp (GamePanel) Current game panel
	 */
	public SuperNPC(GamePanel gp) {
		super(gp);
	}

}
