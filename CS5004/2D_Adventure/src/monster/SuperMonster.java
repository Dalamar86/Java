package monster;

import java.util.Random;

import enums.FSM;
import gameobject.GameObject;
import main.GamePanel;

/**
 * Monster gameObject parent wrapper.Holds specific methods for updating and controlling monsters.
 * 
 * @author Robert Wilson
 *
 */
public abstract class SuperMonster extends GameObject {
	// Finite-State Machine
	private FSM fsm;
	
	/**
	 * Super constructor of monster children.
	 * 
	 * @param gp (GamePanel) Current game panel
	 */
	public SuperMonster(GamePanel gp) {
		super(gp);
		setFsm(FSM.IDLE);
	}
	
	@Override
	public void update() {
		// reset collision and check before next move
		setCollisionOn(false);
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkEntity(this, gp.npc);
		gp.cChecker.checkEntity(this, gp.monster);
		boolean contactPlayer = gp.cChecker.checkPlayer(this);
		// update the Finite-State Machine
		fsm.update(this);
		
		// Check if monster collides with player
		if(contactPlayer) {
			gp.player.takeDamage(attack);
		}
		
		// Check if player is within a certain radius
		if(isAttacking()) {
			if(getDistance(gp.player) <= 14) {
				setFsm(FSM.ATTACKING);
			} else {
				setFsm(FSM.IDLE);
			}
		} else {
			setFsm(FSM.IDLE);
		}
		
		// if collision is false, player can move
		if(isCollisionOn() == false) {
			
			switch(getDirection()) {
			case "up":		setWorldY(getWorldY() - getSpeed()); break;
			case "down":	setWorldY(getWorldY() + getSpeed()); break;
			case "left":	setWorldX(getWorldX() - getSpeed()); break;
			case "right":	setWorldX(getWorldX() + getSpeed()); break;
			}
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
		
		// Invincible controller
		if(invincible) {
			invincibleCounter++;
			if(invincibleCounter > 30) {
				invincible = false;
				invincibleCounter = 0;
			}
		}	
	}
	
	//#####################################################################
	// 								Components
	//#####################################################################
	
	@Override
	public int takeDamage(int damage) {
		if(!invincible) {
			damage -= defense;
			if(damage < 0) {
				damage = 0;
			}
			
			// Set stats
			setLife(getLife() - damage);
			gp.ui.addMessage(damage + " damage!");
			invincible = true;
			
			// Update Finite-State Machine because monster was hit
			setFsm(FSM.EVADE);
			actionTimeCounter = 60;
			
			// Check if monster was killed in the attack and return experience if it has
			if(life <= 0) {
				gp.ui.addMessage("Killed the " + getName() + "!");
				gp.playSE(8);
				return exp;
			} else {
				gp.playSE(5);
			}
		}
		// If monster did not die do not return experience
		return 0;
	}

	/**
	 * Idle action take when nothing happening. Monster moves in a random direction every two seconds.
	 */
	@Override
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
	
	//#####################################################################
	// 							Getters and Setters
	//#####################################################################
	
	/**
	 * Returns the monsters current Finite-State Machine
	 * 
	 * @return fsm (FSM) enum of current objects fsm
	 */
	public FSM getFsm() {
		return fsm;
	}

	/**
	 * Sets the objects current Finite-State Machine.
	 * 
	 * @param fsm (FSM) enum of current objects fsm
	 */
	public void setFsm(FSM fsm) {
		this.fsm = fsm;
	}
}
