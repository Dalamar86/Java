package monster;

import java.util.Random;

import main.GamePanel;
import main.ObjectType;

public class MON_GreenSlime_Boss extends SuperMonster {

	public MON_GreenSlime_Boss(GamePanel gp) {
		super(gp);

		setType(ObjectType.MONSTER);
		setName("Green Slime");
		setSpeed(1);
		maxLife = 10;
		life = maxLife;
		attack = 5;
		defense = 2;
		exp = 5;
		scale = 1.35;
		
		getSolidArea().x = 3;
		getSolidArea().y = (int) (18*scale);
		getSolidArea().width = (int) (42*scale);
		getSolidArea().height = (int) (30*scale);
		setSolidAreaDefaultX(getSolidArea().x);
		setSolidAreaDefaultY(getSolidArea().y);
		
		getImage();
	}
	
	//#####################################################################
	// 								Setup
	//#####################################################################
	
	public void getImage() {
		int width = (int)(getSolidArea().width*scale*1.1);
		int height = (int)(getSolidArea().height*scale*1.1);
		up1 = setup("/monster/redslime_down_1", width, height);
		up2 = setup("/monster/redslime_down_2", width, height);
		down1 = setup("/monster/redslime_down_1", width, height);
		down2 = setup("/monster/redslime_down_2", width, height);
		left1 = setup("/monster/redslime_down_1", width, height);
		left2 = setup("/monster/redslime_down_2", width, height);
		right1 = setup("/monster/redslime_down_1", width, height);
		right2 = setup("/monster/redslime_down_2", width, height);
	}
	
	//#####################################################################
	// 								Components
	//#####################################################################
	
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
	
	@Override
	public void damageReaction() {
		
		actionTimeCounter = 0;
		setDirection(gp.player.getDirection());
	}
}
