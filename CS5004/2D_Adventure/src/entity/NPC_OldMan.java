package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_OldMan extends Entity {

	public NPC_OldMan(GamePanel gp) {
		super(gp);
		direction = "down";
		speed = 1;
		
		getImage();
		setDialogue();
	}

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
	
	public void setDialogue() {
		dialogues[0] = "Hello, Link";
		dialogues[1] = "So, you've found me!";
		dialogues[2] = "I used to be a great wizard... \ntill I took an arrow to the hat";
		dialogues[3] = "Good luck storming the castle";
	}
	
	public void setAction() {
		actionTimeCounter ++;
		
		if(actionTimeCounter%120 == 0) {
			Random rand = new Random();
			int i = rand.nextInt(100) + 1;
			
			if(i <= 25) {
				direction = "up";
			} else if(i <= 50) {
				direction = "down";
			} else if(i <= 75) {
				direction = "left";
			} else if(i > 75) {
				direction = "right";
			}
			actionTimeCounter = 0;
		}	
	}
	
	public void speak() {
		super.speak();
		// leaves room for customization
	}
}
