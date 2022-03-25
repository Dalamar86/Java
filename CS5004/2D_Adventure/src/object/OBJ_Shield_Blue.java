package object;

import main.GamePanel;

public class OBJ_Shield_Blue extends SuperObject {

	public OBJ_Shield_Blue(GamePanel gp) {
		super(gp, "Shield_blue");
		setType(EntityType.SHIELD);
		this.defenseValue = 2;
		this.setDescription("[" + name + "]\nOld rusty sheild with some blue paint.");
	}
}