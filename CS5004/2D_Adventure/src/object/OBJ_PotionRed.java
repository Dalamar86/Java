package object;

import main.GamePanel;

public class OBJ_PotionRed extends SuperObject {

	public OBJ_PotionRed(GamePanel gp) {
		super(gp, "Potion_red");
		this.setDescription("[" + name + "]\nWhat does this mysterious red liquid do?\n I guess we won't know till you try it.");
	}
}
