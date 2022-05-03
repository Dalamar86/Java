package projectiles;

import enums.ObjectType;
import main.GamePanel;

/**
 * FlameStrike projectile is a make spell which can be used by both player and monster.  
 * 
 * @author Robert Wilson
 *
 */
public final class PROJ_FlameStrike extends SuperProjectile {

	/**
	 * Creates an instance of the flame strike projectile.
	 * 
	 * @param gp (GamePanel) Current game panel
	 */
	public PROJ_FlameStrike(GamePanel gp) {
		super(gp);
		
		setName("Flame Strike");
		setSpeed(5);
		setSpeeddiag(7);
		setType(ObjectType.PROJECTILE);
		maxLife = 80;
		life = maxLife;
		setAttackSpeed(30);
		setAttack(2);
		useCost = 1;
		setAlive(false);
		
		getImage();
	}

	/**
	 * Load image paths and send to get scaled and add the buffered images.
	 */
	public void getImage() {
		up1 = setup("/projectiles/flame_strike_up_1");
		up2 = setup("/projectiles/flame_strike_up_1");
		down1 = setup("/projectiles/flame_strike_down_1");
		down2 = setup("/projectiles/flame_strike_down_1");
		left1 = setup("/projectiles/flame_strike_left_1");
		left2 = setup("/projectiles/flame_strike_left_1");
		right1 = setup("/projectiles/flame_strike_right_1");
		right2 = setup("/projectiles/flame_strike_right_1");
	}
}
