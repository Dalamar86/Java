package monster;

import enums.FSM;
import gameobject.GameObject;
import main.GamePanel;

public abstract class SuperMonster extends GameObject {

	private FSM fsm;
	
	public SuperMonster(GamePanel gp) {
		super(gp);
		setFsm(FSM.IDLE);
	}
	
	@Override
	public void update() {
		
		
		setCollisionOn(false);
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkEntity(this, gp.npc);
		gp.cChecker.checkEntity(this, gp.monster);
		boolean contactPlayer = gp.cChecker.checkPlayer(this);
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
		
		spriteCounter++;
		if(spriteCounter > 12) {
			if(spriteNum == 1) {
				spriteNum = 2;
			} else {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
		
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
			
			setLife(getLife() - damage);
			gp.ui.addMessage(damage + " damage!");
			
			invincible = true;
			setFsm(FSM.EVADE);
			actionTimeCounter = 60;
			
			if(life <= 0) {
				gp.ui.addMessage("Killed the " + getName() + "!");
				gp.playSE(8);
				return exp;
			} else {
				gp.playSE(5);
			}
		}
		return 0;
	}
	
	@Override
	public int attack() {
		return attack;
	}

	//#####################################################################
	// 							Getters and Setters
	//#####################################################################
	
	public FSM getFsm() {
		return fsm;
	}

	public void setFsm(FSM fsm) {
		this.fsm = fsm;
	}
}
