package components;

import main.GamePanel;
import models.*;

public class CollisionChecker {

	GamePanel gp;
	
	public CollisionChecker (GamePanel gp) {
		this.gp = gp;
	}
	
	public void checkTile(GameObjectModel gameObject) {
		
		int entityLeftWorldX = gameObject.worldX + gameObject.solidArea.x;
		int entityRightWorldX = gameObject.worldX + gameObject.solidArea.x + gameObject.solidArea.width;
		int entityTopWorldY = gameObject.worldY + gameObject.solidArea.y;
		int entityBottomWorldY = gameObject.worldY + gameObject.solidArea.y + gameObject.solidArea.height;
		
		int entityLeftCol = entityLeftWorldX/gp.tileSize;
		int entityRightCol = entityRightWorldX/gp.tileSize;
		int entityTopRow = entityTopWorldY/gp.tileSize;
		int entityBottomRow = entityBottomWorldY/gp.tileSize;
		
		int tileNum1, tileNum2, tileNum3, tileNum4;
		
		
		switch(gameObject.getDirection()) {
		case "up":
			entityTopRow = (entityTopWorldY - gameObject.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				gameObject.collisionOn = true;
			}
			break;
		case "uplt":
			entityTopRow = (entityTopWorldY - gameObject.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			entityLeftCol = (entityLeftWorldX - gameObject.speed)/gp.tileSize;
			tileNum3 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum4 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true || gp.tileM.tile[tileNum3].collision == true || gp.tileM.tile[tileNum4].collision == true) {
				gameObject.collisionOn = true;
			}
			break;
		case "uprt":
			entityTopRow = (entityTopWorldY - gameObject.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			entityRightCol = (entityRightWorldX + gameObject.speed)/gp.tileSize;
			tileNum3 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum4 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true || gp.tileM.tile[tileNum3].collision == true || gp.tileM.tile[tileNum4].collision == true) {
				gameObject.collisionOn = true;
			}
			break;
		case "down":
			entityBottomRow = (entityBottomWorldY + gameObject.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				gameObject.collisionOn = true;
			}
			break;
		case "downlt":
			entityBottomRow = (entityBottomWorldY + gameObject.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			entityLeftCol = (entityLeftWorldX - gameObject.speed)/gp.tileSize;
			tileNum3 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum4 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true || gp.tileM.tile[tileNum3].collision == true || gp.tileM.tile[tileNum4].collision == true) {
				gameObject.collisionOn = true;
			}
			break;
		case "downrt":
			entityBottomRow = (entityBottomWorldY + gameObject.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			entityRightCol = (entityRightWorldX + gameObject.speed)/gp.tileSize;
			tileNum3 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum4 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true || gp.tileM.tile[tileNum3].collision == true || gp.tileM.tile[tileNum4].collision == true) {
				gameObject.collisionOn = true;
			}
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX - gameObject.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				gameObject.collisionOn = true;
			}
			break;
		case "right":
			entityRightCol = (entityRightWorldX + gameObject.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				gameObject.collisionOn = true;
			}
			break;
		}
	}
	
	public int checkObject(GameObject gameObject, boolean player) {
		int index = 999;
		
		for(int i = 0; i < gp.obj.length; i++) {
			if(gp.obj[i] != null) {
				// get entity's solid area position
				gameObject.solidArea.x = gameObject.worldX + gameObject.solidArea.x;
				gameObject.solidArea.y = gameObject.worldY + gameObject.solidArea.y;
				
				// get the object's solid area position
				gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
				gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;
				
				switch(gameObject.getDirection()) {
				case "up": gameObject.solidArea.y -= gameObject.speed; break;
				case "uplt":
					gameObject.solidArea.x -= gameObject.speeddiag;
					gameObject.solidArea.y -= gameObject.speeddiag;
					break;
				case "uprt":
					gameObject.solidArea.x += gameObject.speeddiag;
					gameObject.solidArea.y -= gameObject.speeddiag;
					break;
				case "down": gameObject.solidArea.y += gameObject.speed; break;
				case "downlt":
					gameObject.solidArea.x -= gameObject.speeddiag;
					gameObject.solidArea.y += gameObject.speeddiag;
					break;
				case "downrt":
					gameObject.solidArea.x += gameObject.speeddiag;
					gameObject.solidArea.y += gameObject.speeddiag;
					break;
				case "left": gameObject.solidArea.x -= gameObject.speed; break;
				case "right": gameObject.solidArea.x += gameObject.speed; break;
				}
				if(gameObject.solidArea.intersects(gp.obj[i].solidArea)) {
					if(gp.obj[i].collision = true) {
						gameObject.collisionOn = true;
					}
					if(player) {
						index = i;
					}
				}
				
				gameObject.solidArea.x = gameObject.solidAreaDefaultX;
				gameObject.solidArea.y = gameObject.solidAreaDefaultY;
				gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
				gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
			}
		}
		
		return index;
	}
	
	// NPC or Monster collision
	public int checkEntity(GameObject gameObject, GameObject[] target) {
		int index = 999;
		
		for(int i = 0; i < target.length; i++) {
			if(target[i] != null) {
				// get entity's solid area position
				gameObject.solidArea.x = gameObject.worldX + gameObject.solidArea.x;
				gameObject.solidArea.y = gameObject.worldY + gameObject.solidArea.y;
				
				// get the targets's solid area position
				target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
				target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;
				
				switch(gameObject.getDirection()) {
				case "up": gameObject.solidArea.y -= gameObject.speed; break;
				case "uplt":
					gameObject.solidArea.x -= gameObject.speeddiag;
					gameObject.solidArea.y -= gameObject.speeddiag;
					break;
				case "uprt":
					gameObject.solidArea.x += gameObject.speeddiag;
					gameObject.solidArea.y -= gameObject.speeddiag;
					break;
				case "down": gameObject.solidArea.y += gameObject.speed;	break;
				case "downlt":
					gameObject.solidArea.x -= gameObject.speeddiag;
					gameObject.solidArea.y += gameObject.speeddiag;
					break;
				case "downrt":
					gameObject.solidArea.x += gameObject.speeddiag;
					gameObject.solidArea.y += gameObject.speeddiag;
					break;
				case "left": gameObject.solidArea.x -= gameObject.speed; break;
				case "right": gameObject.solidArea.x += gameObject.speed; break;
				}
				if(gameObject.solidArea.intersects(target[i].solidArea)) {
					if(target[i] != gameObject) {
						gameObject.collisionOn = true;
						index = i;
					}
					
				}
				
				gameObject.solidArea.x = gameObject.solidAreaDefaultX;
				gameObject.solidArea.y = gameObject.solidAreaDefaultY;
				target[i].solidArea.x = target[i].solidAreaDefaultX;
				target[i].solidArea.y = target[i].solidAreaDefaultY;
			}
		}
		
		return index;
	}
	
	public boolean checkPlayer(GameObject gameObject) {
		
		boolean contactPlayer = false;
		
		// get entity's solid area position
		gameObject.solidArea.x = gameObject.worldX + gameObject.solidArea.x;
		gameObject.solidArea.y = gameObject.worldY + gameObject.solidArea.y;
		
		// get the player's solid area position
		gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
		
		switch(gameObject.getDirection()) {
		case "up": gameObject.solidArea.y -= gameObject.speed; break;
		case "uplt":
			gameObject.solidArea.x -= gameObject.speeddiag;
			gameObject.solidArea.y -= gameObject.speeddiag;
			break;
		case "uprt":
			gameObject.solidArea.x += gameObject.speeddiag;
			gameObject.solidArea.y -= gameObject.speeddiag;
			break;
		case "down": gameObject.solidArea.y += gameObject.speed; break;
		case "downlt": 
			gameObject.solidArea.x -= gameObject.speeddiag; 
			gameObject.solidArea.y += gameObject.speeddiag;
			break;
		case "downrt":
			gameObject.solidArea.x += gameObject.speeddiag;
			gameObject.solidArea.y += gameObject.speeddiag;
			break;
		case "left": gameObject.solidArea.x -= gameObject.speed; break;
		case "right": gameObject.solidArea.x += gameObject.speed;	break;
		}
		if(gameObject.solidArea.intersects(gp.player.solidArea)) {
			gameObject.collisionOn = true;
			contactPlayer = true;
		}
		
		gameObject.solidArea.x = gameObject.solidAreaDefaultX;
		gameObject.solidArea.y = gameObject.solidAreaDefaultY;
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
		
		return contactPlayer;
	}
}
