package monster;

import java.util.Random;

import entity.*;
import main.GamePanel;
import main.ObjectType;

public class MON_GreenSlime extends SuperMonster {

	public MON_GreenSlime(GamePanel gp) {
		super(gp);
		
		setType(ObjectType.MONSTER);
		name = "Green Slime";
		speed = 1;
		maxLife = 4;
		life = maxLife;
		attack = 3;
		defense = 0;
		exp = 2;
		
		solidArea.x = 3;
		solidArea.y = 18;
		solidArea.width = 42;
		solidArea.height = 30;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage();
	}

	public void getImage() {
		
		up1 = setup("/monster/greenslime_down_1");
		up2 = setup("/monster/greenslime_down_2");
		down1 = setup("/monster/greenslime_down_1");
		down2 = setup("/monster/greenslime_down_2");
		left1 = setup("/monster/greenslime_down_1");
		left2 = setup("/monster/greenslime_down_2");
		right1 = setup("/monster/greenslime_down_1");
		right2 = setup("/monster/greenslime_down_2");
	}
	
	public void setAction() {
		actionTimeCounter ++;
		
		if(actionTimeCounter == 120) {
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
	
	@Override
	public void damageReaction() {
		
		actionTimeCounter = 0;
		direction = gp.player.direction;
	}
}
