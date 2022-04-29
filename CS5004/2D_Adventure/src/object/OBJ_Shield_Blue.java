package object;

import main.GamePanel;
import main.ObjectType;

public class OBJ_Shield_Blue extends SuperObject {

	public OBJ_Shield_Blue(GamePanel gp) {
		super(gp, "Shield_blue");
		setType(ObjectType.SHIELD);
		this.setDefenseValue(2);
		this.setDescription("[" + getName() + "]\nOld rusty sheild with some blue paint.");
	}
}