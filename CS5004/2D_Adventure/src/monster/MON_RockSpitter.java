package monster;

import enums.ObjectType;
import main.GamePanel;

/**
 * Rock spitter moves in random directions and then spits out a rock projectile.  
 * Will not react to player interaction.
 * 
 * @author Robert Wilson
 *
 */
public final class MON_RockSpitter extends SuperMonster {

	/**
	 * Creates an instance of the rock spitter monster.
	 * 
	 * @param gp (GamePanel) Current game panel
	 */
	public MON_RockSpitter(GamePanel gp) {
		super(gp);

		setType(ObjectType.MONSTER);
		setName("Rock Spitter");
		setSpeed(2);
		maxLife = 2;
		life = maxLife;
		attack = 2;
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
		up1 = setup("/monster/rock_spitter_up_1");
		up2 = setup("/monster/rock_spitter_up_2");
		up3 = setup("/monster/rock_spitter_up_3");
		down1 = setup("/monster/rock_spitter_down_1");
		down2 = setup("/monster/rock_spitter_down_2");
		down3 = setup("/monster/rock_spitter_down_3");
		left1 = setup("/monster/rock_spitter_left_1");
		left2 = setup("/monster/rock_spitter_left_2");
		right1 = setup("/monster/rock_spitter_right_1");
		right2 = setup("/monster/rock_spitter_right_2");
		
		attackDown1 = setup("/Monster/rock_spitter_down_attack_1");
		attackDown2 = setup("/Monster/rock_spitter_down_attack_2");
		attackLeft1 = setup("/Monster/rock_spitter_left_attack_1");
		attackLeft2 = setup("/Monster/rock_spitter_left_attack_2");
		attackRight1 = setup("/Monster/rock_spitter_right_attack_1");
		attackRight2 = setup("/Monster/rock_spitter_right_attack_2");
	}
	
	//#####################################################################
	// 								Components
	//#####################################################################
}
