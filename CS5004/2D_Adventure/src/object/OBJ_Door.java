package object;

import main.*;

public class OBJ_Door extends SuperObject {

	public OBJ_Door(GamePanel gp) {
		super(gp, "Door");
		this.setDescription("[" + name + "]\nA way of keeping unwanted company out.?nI wonder why it won't let you in.");
		collision = true;
	}
}
