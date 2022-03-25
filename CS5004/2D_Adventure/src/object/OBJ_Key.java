package object;

import main.*;

public class OBJ_Key extends SuperObject {
	
	public OBJ_Key(GamePanel gp) {
		super(gp, "Key");
		setType(EntityType.KEY);
		this.setDescription("[" + name + "]\nThis unassuming key opens things.");
	}
}
