package projectiles;

import enums.ObjectType;
import gameobject.GameObject;
import main.GamePanel;

/**
 * Projectile gameObject parent wrapper.Holds specific methods for updating and controlling projectiles.
 * 
 * @author Robert Wilson
 *
 */
public abstract class SuperProjectile extends GameObject {
	// caster casting the spell
	private GameObject caster;
	
	/**
	 * Super constructor of projectile children.
	 * 
	 * @param gp (GamePanel) Current game panel
	 */
	public SuperProjectile(GamePanel gp) {
		super(gp);
	}

	/**
	 * sets the origin of the projectile as well as who llaunched it.
	 * 
	 * @param worldX (int) caster's worldX
	 * @param worldY (int) caster's worldY
	 * @param direction (String) caster's direction
	 * @param alive (boolean) determines if the projectile gets drawn
	 * @param caster (gameObject) object responsible for launing the projectile
	 */
	public void set(int worldX, int worldY, String direction, boolean alive, GameObject caster) {
		this.setWorldX(worldX);
		this.setWorldY(worldY);
		this.setDirection(direction);
		this.setAlive(alive);
		this.setCaster(caster);
		this.setLife(getMaxLife());
	}
	
	@Override
	public void update() {
		// reset and check for collision
		setCollisionOn(false);
		if(caster.getType() == ObjectType.PLAYER) {
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			if(monsterIndex != 999) {
				GameObject monster = gp.monster[monsterIndex];
				gp.player.damageMonster(monster, getAttack());
				setAlive(false);
			}
		} else if(caster.getType() == ObjectType.MONSTER) {
			// TODO add implementation for caster other than player
		}
		
		// if collision is False, Player can move
		if(isCollisionOn() == false) {

			switch(getDirection()) {
			case "up":
				setWorldY(getWorldY() - getSpeed());
				break;
			case "upleft":
				setWorldY(getWorldY() - getSpeeddiag());
				setWorldX(getWorldX() - getSpeeddiag());
				break;
			case "upright":
				setWorldY(getWorldY() - getSpeeddiag());
				setWorldX(getWorldX() + getSpeeddiag());
				break;
			case "down":
				setWorldY(getWorldY() + getSpeed());
				break;
			case "downleft":
				setWorldY(getWorldY() + getSpeeddiag());
				setWorldX(getWorldX() - getSpeeddiag());
				break;
			case "downright":
				setWorldY(getWorldY() + getSpeeddiag());
				setWorldX(getWorldX() + getSpeeddiag());
				break;
			case "left":
				setWorldX(getWorldX() - getSpeed());
				break;
			case "right":
				setWorldX(getWorldX() + getSpeed());
				break;
			}
		}
		
		// Kill the projectile after a certain amount of rounds equal to projectile health
		setLife(getLife()-1);
		if(getLife() <= 0) {
			setAlive(false);
		}
		
		// Sprite controller
		spriteCounter++;
		if(spriteCounter > 12) {
			if(spriteNum == 1) {
				spriteNum = 2;
			} else {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
	}

	//#####################################################################
	// 							Getters and Setters
	//#####################################################################
	
	/**
	 * Returns the caster of the projectile.
	 * 
	 * @return caster (GameObject)
	 */
	public GameObject getCaster() {
		return caster;
	}

	/**
	 * Sets the caster of the projectiel.
	 * 
	 * @param caster (GameObject)
	 */
	public void setCaster(GameObject caster) {
		this.caster = caster;
	}
}
