package object;

import enums.ObjectType;
import main.GamePanel;

/**
 * Axe object, does more damage but has a smaller hit box.  
 * 
 * @author Robert Wilson
 *
 */
public final class OBJ_Axe extends SuperObject {
	
	/**
	 * Creates an instance of the axe object.
	 * 
	 * @param gp (GamePanel) Current game panel
	 */
	public OBJ_Axe(GamePanel gp) {
		super(gp, "Axe");
		setType(ObjectType.WEAPON);
		this.setAttackValue(2);
		getAttackArea().width = 30;
		getAttackArea().height = 30;
		this.setDescription("[" + getName() + "]\nUsually used for cutting wood.\nI'm sure you will put it to good use.");
	}
}