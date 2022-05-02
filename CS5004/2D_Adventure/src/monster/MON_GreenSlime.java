package monster;

import java.util.Random;

import enums.FSM;
import enums.ObjectType;
import main.GamePanel;

public final class MON_GreenSlime extends SuperMonster {

	public MON_GreenSlime(GamePanel gp) {
		super(gp);
		
		setType(ObjectType.MONSTER);
		setName("Green Slime");
		setSpeed(1);
		maxLife = 4;
		life = maxLife;
		attack = 3;
		defense = 0;
		exp = 2;
		
		getSolidArea().x = 3;
		getSolidArea().y = 18;
		getSolidArea().width = 42;
		getSolidArea().height = 30;
		setSolidAreaDefaultX(getSolidArea().x);
		setSolidAreaDefaultY(getSolidArea().y);
		
		getImage();
	}

	//#####################################################################
	// 								Setup
	//#####################################################################
	
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
		if(actionTimeCounter == 60) {
			setDirection(gp.player.getDirection());
			actionTimeCounter ++;
		} else {
			setFsm(FSM.IDLE);
		}
	}
}
