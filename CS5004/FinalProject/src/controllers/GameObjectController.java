package controllers;

import main.*;
import components.*;
import models.GameObjectModel;
import views.GameObjectView;

public class GameObjectController implements GameObjectControllerInt {

	private GamePanel gp;
	private KeyHandler keyH;
	
	private GameObjectModel gom;
	private GameObjectView gov;
	
	public GameObjectController(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		this.gom = new GameObjectModel(gp);
		
	}
	
	@Override
	public void update() {
		setAction();
		
		collisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkEntity(this, gp.npc);
		gp.cChecker.checkEntity(this, gp.monster);
		boolean contactPlayer = gp.cChecker.checkPlayer(this);
		
		if(this.getType() == ObjectType.MONSTER && contactPlayer == true) {
			gp.player.takeDamage(attack);
		}
		
		// if collision is false, player can move
		if(collisionOn == false) {
			
			switch(getDirection()) {
			case "up":		worldY -= speed; break;
			case "down":	worldY += speed; break;
			case "left":	worldX -= speed; break;
			case "right":	worldX += speed; break;
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

}
