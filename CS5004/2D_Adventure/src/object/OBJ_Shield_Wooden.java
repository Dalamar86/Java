package object;

import main.GamePanel;
import main.ObjectType;

public class OBJ_Shield_Wooden extends SuperObject {

	public OBJ_Shield_Wooden(GamePanel gp) {
		super(gp, "shield_wood");
		setType(ObjectType.SHIELD);
		this.setDefenseValue(1);
		this.setDescription("[" + getName() + "]\nA piece of bark pulled from a tree \nnear your house.");
	}
}
