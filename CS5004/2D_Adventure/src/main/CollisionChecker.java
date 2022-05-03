package main;

import gameobject.GameObject;

/**
 * Checks for collisions between gameObjects, tiles, and players.
 * 
 * @author Robert Wilson
 *
 */
public class CollisionChecker {
	// Current GamePanel
	GamePanel gp;
	
	/**
	 * Creates a new instance of a collision checker
	 * 
	 * @param gp (GamePanel) Current game Panel
	 */
	public CollisionChecker (GamePanel gp) {
		this.gp = gp;
	}
	
	/**
	 * Checks for collision between a gameobject and a tile.  If a collision is detected it 
	 * sets the gameObject's collisionOn to true. 
	 * 
	 * @param gameObject (GameObject) object to check for collision with
	 */
	public void checkTile(GameObject gameObject) {
		// Get every tile around the object
		int entityLeftWorldX = gameObject.getWorldX() + gameObject.getSolidArea().x;
		int entityRightWorldX = gameObject.getWorldX() + gameObject.getSolidArea().x + gameObject.getSolidArea().width;
		int entityTopWorldY = gameObject.getWorldY() + gameObject.getSolidArea().y;
		int entityBottomWorldY = gameObject.getWorldY() + gameObject.getSolidArea().y + gameObject.getSolidArea().height;
		
		int entityLeftCol = entityLeftWorldX/gp.tileSize;
		int entityRightCol = entityRightWorldX/gp.tileSize;
		int entityTopRow = entityTopWorldY/gp.tileSize;
		int entityBottomRow = entityBottomWorldY/gp.tileSize;
		
		int tileNum1, tileNum2, tileNum3, tileNum4;
		
		// Depending on which direction the object is moving, check the tiles in that direction
		switch(gameObject.getDirection()) {
		case "up":
			entityTopRow = (entityTopWorldY - gameObject.getSpeed())/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				gameObject.setCollisionOn(true);
			}
			break;
		case "upleft":
			entityTopRow = (entityTopWorldY - gameObject.getSpeed())/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			entityLeftCol = (entityLeftWorldX - gameObject.getSpeed())/gp.tileSize;
			tileNum3 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum4 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true || gp.tileM.tile[tileNum3].collision == true || gp.tileM.tile[tileNum4].collision == true) {
				gameObject.setCollisionOn(true);
			}
			break;
		case "upright":
			entityTopRow = (entityTopWorldY - gameObject.getSpeed())/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			entityRightCol = (entityRightWorldX + gameObject.getSpeed())/gp.tileSize;
			tileNum3 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum4 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true || gp.tileM.tile[tileNum3].collision == true || gp.tileM.tile[tileNum4].collision == true) {
				gameObject.setCollisionOn(true);
			}
			break;
		case "down":
			entityBottomRow = (entityBottomWorldY + gameObject.getSpeed())/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				gameObject.setCollisionOn(true);
			}
			break;
		case "downleft":
			entityBottomRow = (entityBottomWorldY + gameObject.getSpeed())/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			entityLeftCol = (entityLeftWorldX - gameObject.getSpeed())/gp.tileSize;
			tileNum3 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum4 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true || gp.tileM.tile[tileNum3].collision == true || gp.tileM.tile[tileNum4].collision == true) {
				gameObject.setCollisionOn(true);
			}
			break;
		case "downright":
			entityBottomRow = (entityBottomWorldY + gameObject.getSpeed())/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			entityRightCol = (entityRightWorldX + gameObject.getSpeed())/gp.tileSize;
			tileNum3 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum4 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true || gp.tileM.tile[tileNum3].collision == true || gp.tileM.tile[tileNum4].collision == true) {
				gameObject.setCollisionOn(true);
			}
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX - gameObject.getSpeed())/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				gameObject.setCollisionOn(true);
			}
			break;
		case "right":
			entityRightCol = (entityRightWorldX + gameObject.getSpeed())/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				gameObject.setCollisionOn(true);
			}
			break;
		}
	}
	
	/**
	 * Checker for when a gameObject interacts with an object, If the boolean player is true then a player is
	 * the one interacting.  Returns an index of 999 if nothing is interacted with. 
	 * 
	 * @param gameObject (GameObject) gameObject to check if it interacts with any objects in gp's object list
	 * @param player (boolean) true if the object is a player, setting the index to the object interacted with if true
	 * @return index (int) index of the object interacted with
	 */
	public int checkObject(GameObject gameObject, boolean player) {
		int index = 999;
		
		for(int i = 0; i < gp.obj.length; i++) {
			if(gp.obj[i] != null) {
				// get entity's solid area position
				gameObject.getSolidArea().x = gameObject.getWorldX() + gameObject.getSolidArea().x;
				gameObject.getSolidArea().y = gameObject.getWorldY() + gameObject.getSolidArea().y;
				
				// get the object's solid area position
				gp.obj[i].getSolidArea().x = gp.obj[i].getWorldX() + gp.obj[i].getSolidArea().x;
				gp.obj[i].getSolidArea().y = gp.obj[i].getWorldY() + gp.obj[i].getSolidArea().y;
				
				switch(gameObject.getDirection()) {
				case "up": gameObject.getSolidArea().y -= gameObject.getSpeed(); break;
				case "upleft":
					gameObject.getSolidArea().x -= gameObject.getSpeeddiag();
					gameObject.getSolidArea().y -= gameObject.getSpeeddiag();
					break;
				case "upright":
					gameObject.getSolidArea().x += gameObject.getSpeeddiag();
					gameObject.getSolidArea().y -= gameObject.getSpeeddiag();
					break;
				case "down": gameObject.getSolidArea().y += gameObject.getSpeed(); break;
				case "downleft":
					gameObject.getSolidArea().x -= gameObject.getSpeeddiag();
					gameObject.getSolidArea().y += gameObject.getSpeeddiag();
					break;
				case "downright":
					gameObject.getSolidArea().x += gameObject.getSpeeddiag();
					gameObject.getSolidArea().y += gameObject.getSpeeddiag();
					break;
				case "left": gameObject.getSolidArea().x -= gameObject.getSpeed(); break;
				case "right": gameObject.getSolidArea().x += gameObject.getSpeed(); break;
				}
				if(gameObject.getSolidArea().intersects(gp.obj[i].getSolidArea())) {
					if(gp.obj[i].setCollision(true)) {
						gameObject.setCollisionOn(true);
					}
					if(player) {
						index = i;
					}
				}
				gameObject.getSolidArea().x = gameObject.getSolidAreaDefaultX();
				gameObject.getSolidArea().y = gameObject.getSolidAreaDefaultY();
				gp.obj[i].getSolidArea().x = gp.obj[i].getSolidAreaDefaultX();
				gp.obj[i].getSolidArea().y = gp.obj[i].getSolidAreaDefaultY();
			}
		}
		return index;
	}
	
	// 
	/**
	 * Checks for NPC or Monster collision and returns the index if the target is collided with
	 * 
	 * @param gameObject (GameObject) object doing the checking
	 * @param target (GameObject) object being checked against
	 * @return index (int) index of the target interacted with, 999 if not interacted with
	 */
	public int checkEntity(GameObject gameObject, GameObject[] target) {
		int index = 999;
		
		for(int i = 0; i < target.length; i++) {
			if(target[i] != null) {
				// get entity's solid area position
				gameObject.getSolidArea().x = gameObject.getWorldX() + gameObject.getSolidArea().x;
				gameObject.getSolidArea().y = gameObject.getWorldY() + gameObject.getSolidArea().y;
				
				// get the targets's solid area position
				target[i].getSolidArea().x = target[i].getWorldX() + target[i].getSolidArea().x;
				target[i].getSolidArea().y = target[i].getWorldY() + target[i].getSolidArea().y;
				
				switch(gameObject.getDirection()) {
				case "up": gameObject.getSolidArea().y -= gameObject.getSpeed(); break;
				case "upleft":
					gameObject.getSolidArea().x -= gameObject.getSpeeddiag();
					gameObject.getSolidArea().y -= gameObject.getSpeeddiag();
					break;
				case "upright":
					gameObject.getSolidArea().x += gameObject.getSpeeddiag();
					gameObject.getSolidArea().y -= gameObject.getSpeeddiag();
					break;
				case "down": gameObject.getSolidArea().y += gameObject.getSpeed();	break;
				case "downleft":
					gameObject.getSolidArea().x -= gameObject.getSpeeddiag();
					gameObject.getSolidArea().y += gameObject.getSpeeddiag();
					break;
				case "downright":
					gameObject.getSolidArea().x += gameObject.getSpeeddiag();
					gameObject.getSolidArea().y += gameObject.getSpeeddiag();
					break;
				case "left": gameObject.getSolidArea().x -= gameObject.getSpeed(); break;
				case "right": gameObject.getSolidArea().x += gameObject.getSpeed(); break;
				}
				if(gameObject.getSolidArea().intersects(target[i].getSolidArea())) {
					if(target[i] != gameObject) {
						gameObject.setCollisionOn(true);
						index = i;
					}
					
				}
				gameObject.getSolidArea().x = gameObject.getSolidAreaDefaultX();
				gameObject.getSolidArea().y = gameObject.getSolidAreaDefaultY();
				target[i].getSolidArea().x = target[i].getSolidAreaDefaultX();
				target[i].getSolidArea().y = target[i].getSolidAreaDefaultY();
			}
		}
		return index;
	}
	
	/**
	 * Checker if the object interacts with the player, returns true if it does and false if it doesn't.
	 * 
	 * @param gameObject (GameObject)  object doing the checking
	 * @return contacctPlayer (boolean) 
	 */
	public boolean checkPlayer(GameObject gameObject) {
		
		boolean contactPlayer = false;
		
		// get entity's solid area position
		gameObject.getSolidArea().x = gameObject.getWorldX() + gameObject.getSolidArea().x;
		gameObject.getSolidArea().y = gameObject.getWorldY() + gameObject.getSolidArea().y;
		
		// get the player's solid area position
		gp.player.getSolidArea().x = gp.player.getWorldX() + gp.player.getSolidArea().x;
		gp.player.getSolidArea().y = gp.player.getWorldY() + gp.player.getSolidArea().y;
		
		switch(gameObject.getDirection()) {
		case "up": gameObject.getSolidArea().y -= gameObject.getSpeed(); break;
		case "upleft":
			gameObject.getSolidArea().x -= gameObject.getSpeeddiag();
			gameObject.getSolidArea().y -= gameObject.getSpeeddiag();
			break;
		case "upright":
			gameObject.getSolidArea().x += gameObject.getSpeeddiag();
			gameObject.getSolidArea().y -= gameObject.getSpeeddiag();
			break;
		case "down": gameObject.getSolidArea().y += gameObject.getSpeed(); break;
		case "downleft": 
			gameObject.getSolidArea().x -= gameObject.getSpeeddiag(); 
			gameObject.getSolidArea().y += gameObject.getSpeeddiag();
			break;
		case "downright":
			gameObject.getSolidArea().x += gameObject.getSpeeddiag();
			gameObject.getSolidArea().y += gameObject.getSpeeddiag();
			break;
		case "left": gameObject.getSolidArea().x -= gameObject.getSpeed(); break;
		case "right": gameObject.getSolidArea().x += gameObject.getSpeed();	break;
		}
		if(gameObject.getSolidArea().intersects(gp.player.getSolidArea())) {
			gameObject.setCollisionOn(true);
			contactPlayer = true;
		}
		
		gameObject.getSolidArea().x = gameObject.getSolidAreaDefaultX();
		gameObject.getSolidArea().y = gameObject.getSolidAreaDefaultY();
		gp.player.getSolidArea().x = gp.player.getSolidAreaDefaultX();
		gp.player.getSolidArea().y = gp.player.getSolidAreaDefaultY();
		
		return contactPlayer;
	}
}
