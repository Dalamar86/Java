package object;

import main.*;

public class OBJ_Key extends SuperObject {
	
	public OBJ_Key(GamePanel gp) {
		super(gp, "Key");
		setType(ObjectType.KEY);
		this.setDescription("[" + getName() + "]\nThis unassuming key opens things.");
	}
}
