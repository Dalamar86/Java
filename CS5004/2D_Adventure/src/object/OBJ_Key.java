package object;

import enums.ObjectType;
import main.*;

public final class OBJ_Key extends SuperObject {
	
	public OBJ_Key(GamePanel gp) {
		super(gp, "Key");
		setType(ObjectType.KEY);
		this.setDescription("[" + getName() + "]\nThis unassuming key opens things.");
	}
}
