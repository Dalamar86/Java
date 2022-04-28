package object;

import main.GamePanel;
import main.ObjectType;

public class OBJ_Axe extends SuperObject {

	public OBJ_Axe(GamePanel gp) {
		super(gp, "Axe");
		setType(ObjectType.WEAPON);
		this.setAttackValue(2);
		attackArea.width = 30;
		attackArea.height = 30;
		this.setDescription("[" + getName() + "]\nUsually used for cutting wood.\nI'm sure you will put it to good use.");
	}
}