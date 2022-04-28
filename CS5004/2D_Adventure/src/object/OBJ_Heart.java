package object;

import main.GamePanel;

public class OBJ_Heart extends SuperObject {

	public OBJ_Heart(GamePanel gp) {
		super(gp, "Heart_full");
		addImage("Heart_half", "Heart_blank");
		this.setDescription("[" + getName() + "]\nA symbol of your vitality.");
	}
}
