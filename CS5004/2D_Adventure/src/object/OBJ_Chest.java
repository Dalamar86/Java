package object;

import main.*;

public class OBJ_Chest extends SuperObject {

	public OBJ_Chest(GamePanel gp) {
		super(gp, "Chest");
		this.setDescription("[" + getName() + "]\nContains treasures beyond your imagination.\nThat is, until you open it.");
		setCollision(true);
	}
}
