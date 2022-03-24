package object;

import main.GamePanel;

public class OBJ_Sword_Normal extends SuperObject {

	public OBJ_Sword_Normal(GamePanel gp) {
		super(gp, "sword_normal");
		this.attackValue = 2;
		this.setDescription("[" + name + "]\nAn old sword you found in the \npalace garbage.");
	}
}
