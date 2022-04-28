package object;

import main.GamePanel;
import main.ObjectType;

public class OBJ_Sword_Normal extends SuperObject {

	public OBJ_Sword_Normal(GamePanel gp) {
		super(gp, "sword_normal");
		setType(ObjectType.WEAPON);
		this.setAttackValue(1);
		attackArea.width = 36;
		attackArea.height = 36;
		this.setDescription("[" + getName() + "]\nAn old sword you found in the \npalace garbage.");
	}
}
