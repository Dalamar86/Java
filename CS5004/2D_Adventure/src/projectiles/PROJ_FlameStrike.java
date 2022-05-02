package projectiles;

import enums.ObjectType;
import main.GamePanel;

public class PROJ_FlameStrike extends SuperProjectile {

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
	
	@Override
	public int attack() {
		return attack;
	}
}
