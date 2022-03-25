package object;

import main.GamePanel;

public class OBJ_Axe extends SuperObject {

	public OBJ_Axe(GamePanel gp) {
		super(gp, "Axe");
		setType(EntityType.WEAPON);
		this.attackValue = 2;
		attackArea.width = 30;
		attackArea.height = 30;
		this.setDescription("[" + name + "]\nUsually used for cutting wood.\nI'm sure you will put it to good use.");
	}
}