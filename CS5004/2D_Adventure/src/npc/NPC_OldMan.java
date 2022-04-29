package npc;

import java.util.Random;

import main.GamePanel;
import main.ObjectType;

public class NPC_OldMan extends SuperNPC {

	public NPC_OldMan(GamePanel gp) {
		super(gp);
		
		setType(ObjectType.NPC);
		setDirection("down");
		setSpeed(1);
		
		getImage();
		setDialogue();
	}

	//#####################################################################
	// 								Setup
	//#####################################################################
	
	public void getImage() {		
		up1 = setup("/npc/oldman_up_1");
		up2 = setup("/npc/oldman_up_2");
		down1 = setup("/npc/oldman_down_1");
		down2 = setup("/npc/oldman_down_2");
		left1 = setup("/npc/oldman_left_1");
		left2 = setup("/npc/oldman_left_2");
		right1 = setup("/npc/oldman_right_1");
		right2 = setup("/npc/oldman_right_2");
	}
	
	//#####################################################################
	// 								Components
	//#####################################################################
	
	public void setDialogue() {
		dialogues[0] = "Hello, Link";
		dialogues[1] = "So, you've found me!";
		dialogues[2] = "I used to be a great wizard... \ntill I took an arrow to the hat";
		dialogues[3] = "Good luck storming the castle";
	}
	
	public void setAction() {
		actionTimeCounter ++;
		
		if(actionTimeCounter == 120) {
			Random rand = new Random();
			int i = rand.nextInt(100) + 1;
			
			if(i <= 25) {
				setDirection("up");
			} else if(i <= 50) {
				setDirection("down");
			} else if(i <= 75) {
				setDirection("left");
			} else if(i > 75) {
				setDirection("right");
			}
			actionTimeCounter = 0;
		}	
	}
	
	public void speak() {
		super.speak();
		// leaves room for customization
	}
	
	public int takeDamage(int damage) {
		return 0;
	}
}
