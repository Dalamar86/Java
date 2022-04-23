package object;

import main.GamePanel;
import main.ObjectType;

public class OBJ_Shield_Wooden extends SuperObject {

	public OBJ_Shield_Wooden(GamePanel gp) {
		super(gp, "shield_wood");
		setType(ObjectType.SHIELD);
		this.defenseValue = 1;
		this.setDescription("[" + name + "]\nA piece of bark pulled from a tree \nnear your house.");
	}
}
