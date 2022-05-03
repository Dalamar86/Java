package monster;

import enums.FSM;
import enums.ObjectType;
import main.GamePanel;

/**
 * Green Slime monster attacks by hitting character during random movements.  Actively moves
 * away from player when het.
 * 
 * @author Robert Wilson
 *
 */
public final class MON_GreenSlime extends SuperMonster {

	/**
	 * Creates an instance of the green slime monster.
	 * 
	 * @param gp (GamePanel) Current game panel
	 */
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
		
		// Hit box
		getSolidArea().x = 3;
		getSolidArea().y = 18;
		getSolidArea().width = 42;
		getSolidArea().height = 30;
		setSolidAreaDefaultX(getSolidArea().x);
		setSolidAreaDefaultY(getSolidArea().y);
		
		// buffer monster images
		getImage();
	}

	//#####################################################################
	// 								Setup
	//#####################################################################
	
	/**
	 * Load image paths and send to get scaled and add the buffered images.
	 */
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
