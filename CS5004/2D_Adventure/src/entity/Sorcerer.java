package entity;

import enums.ObjectType;
import main.GamePanel;
import main.KeyHandler;
import object.OBJ_Key;
import object.OBJ_PotionBlue;
import object.OBJ_PotionRed;
import projectiles.PROJ_FlameStrike;

public final class Sorcerer extends Player{

	public Sorcerer(GamePanel gp, KeyHandler keyH) {
		super(gp, keyH);
		
	}

	@Override
	public void setDefaultValues() {
		setWorldX(gp.tileSize * 23);
		setWorldY(gp.tileSize * 21);
		
		setType(ObjectType.PLAYER);
		setSpeed(4);
		setSpeeddiag(getSpeed() - 1);
		setDirection("down");
		
		// Player status
		setLevel(1);
		setMaxLife(6);
		setLife(getMaxLife());
		setStrength(1);
		setDexterity(1);
		setExp(0);
		setNextLevelExp(5);
		setCoin(0);
		//setCurrentWeapon(new OBJ_Sword_Normal(gp));
		//setCurrentShield(new OBJ_Shield_Wooden(gp));
		setProjectile(new PROJ_FlameStrike(gp));
		setAttack(getAttack());
		setDefense(getDefense());
	}
	
	@Override
	protected void setItems() {
		//getInventory().add(getCurrentWeapon());
		//getInventory().add(getCurrentShield());
		getInventory().add(new OBJ_Key(gp));
		setHasKey(getHasKey() + 1);
		getInventory().add(new OBJ_PotionRed(gp));
		getInventory().add(new OBJ_PotionBlue(gp));
	}
}
