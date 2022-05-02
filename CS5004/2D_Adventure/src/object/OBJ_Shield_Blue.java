package object;

import enums.ObjectType;
import main.GamePanel;

public final class OBJ_Shield_Blue extends SuperObject {

	public OBJ_Shield_Blue(GamePanel gp) {
		super(gp, "Shield_blue");
		setType(ObjectType.SHIELD);
		this.setDefenseValue(2);
		this.setDescription("[" + getName() + "]\nOld rusty sheild with some blue paint.");
	}
}