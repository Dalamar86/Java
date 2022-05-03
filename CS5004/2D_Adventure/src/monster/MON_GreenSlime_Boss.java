package monster;

import enums.FSM;
import enums.ObjectType;
import gameobject.GameObject;
import main.GamePanel;

/**
 * Green Slime monster boss.  Equipped with a Finite-State machine as its AI.  This creature
 * moves in random directions until player ventures close.  It then follows the player actively 
 * trying to hit them.  Once at half health, this creature will spawn smaller creatures to 
 * aide it. 
 * 
 * @author Robert Wilson
 *
 */
public final class MON_GreenSlime_Boss extends SuperMonster {
	
	/**
	 * Creates an instance of the boss.
	 * 
	 * @param gp (GamePanel) Current game panel
	 */
	public MON_GreenSlime_Boss(GamePanel gp) {
		super(gp);

		setType(ObjectType.MONSTER);
		setName("Green Slime Boss");
		setSpeed(1);
		maxLife = 10;
		life = maxLife;
		attack = 5;
		defense = 2;
		exp = 5;
		scale = 1.35;
		
		// Set hit box
		getSolidArea().x = 3;
		getSolidArea().y = (int) (18*scale);
		getSolidArea().width = (int) (42*scale);
		getSolidArea().height = (int) (30*scale);
		setSolidAreaDefaultX(getSolidArea().x);
		setSolidAreaDefaultY(getSolidArea().y);
		
		// Buffer images
		getImage();
	}
	
	//#####################################################################
	// 								Setup
	//#####################################################################
	
	/**
	 * Load image paths and send to get scaled and add the buffered images.
	 */
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
	// 								Finite-State Machine
	//#####################################################################
	
	@Override
	public void attack() {
		setDirection(getDirectionTo(gp.player));
	}
	
	@Override
	public void damageReaction() {
		setDirection(getDirectionTo(gp.player));
		if(getLife() <= (getMaxLife()/2)) {
			setFsm(FSM.AIDE);
			getFsm().update(this);
		}
	}

	@Override
	public void findAide() {
		// TODO fix bug where monster spawns in trees or water
		int x = (int) (getWorldX()+this.getSolidArea().getMaxX());
		int y = getWorldY()/gp.tileSize;
		
		// Create new monsters
		GameObject monster1 = new MON_GreenSlime(gp);
		monster1.setWorldX(x);
		monster1.setWorldY(getWorldY());
		monster1.setDirection("Right");
		
		// check to make sure something isn't in the way of spawning
		gp.cChecker.checkTile(monster1);
		gp.cChecker.checkPlayer(monster1);
		gp.cChecker.checkEntity(monster1, gp.monster);
		
		if(!monster1.isCollisionOn()) {
			gp.aSetter.addItem(monster1, (x/gp.tileSize)+1, y+1);
		}
		
		gp.addAssetMonster();
	}
}
