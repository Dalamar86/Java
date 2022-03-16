package main;

import entity.Entity;

public class CollisionChecker {

	GamePanel gp;
	
	public CollisionChecker (GamePanel gp) {
		this.gp = gp;
	}
	
	public void checkTile(Entity entity) {
		
		int entityLeftWorldX = entity.worldX + entity.solidArea.x;
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.worldY + entity.solidArea.y;
		int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
		
		int entityLeftCol = entityLeftWorldX/gp.tileSize;
		int entityRightCol = entityRightWorldX/gp.tileSize;
		int entityTopRow = entityTopWorldY/gp.tileSize;
		int entityBottomRow = entityBottomWorldY/gp.tileSize;
		
		int tileNum1, tileNum2, tileNum3, tileNum4;
		
		
		switch(entity.direction) {
		case "up":
			entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "uplt":
			entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
			tileNum3 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum4 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true || gp.tileM.tile[tileNum3].collision == true || gp.tileM.tile[tileNum4].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "uprt":
			entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
			tileNum3 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum4 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true || gp.tileM.tile[tileNum3].collision == true || gp.tileM.tile[tileNum4].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "down":
			entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "downlt":
			entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
			tileNum3 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum4 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true || gp.tileM.tile[tileNum3].collision == true || gp.tileM.tile[tileNum4].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "downrt":
			entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
			tileNum3 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum4 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true || gp.tileM.tile[tileNum3].collision == true || gp.tileM.tile[tileNum4].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "right":
			entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		}
	}
	
	public int checkObject(Entity entity, boolean player) {
		int index = 999;
		
		for(int i = 0; i < gp.obj.length; i++) {
			if(gp.obj[i] != null) {
				// get entity's solid area position
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				
				// get the object's solid area position
				gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
				gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;
				
				switch(entity.direction) {
				case "up":
					entity.solidArea.y -= entity.speed;
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if(gp.obj[i].collision = true) {
							entity.collisionOn = true;
						}
						if(player) {
							index = i;
						}
					}
					break;
				case "uplt":
					entity.solidArea.x -= entity.speeddiag;
					entity.solidArea.y -= entity.speeddiag;
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if(gp.obj[i].collision = true) {
							entity.collisionOn = true;
						}
						if(player) {
							index = i;
						}
					}
				case "uprt":
					entity.solidArea.x += entity.speeddiag;
					entity.solidArea.y -= entity.speeddiag;
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if(gp.obj[i].collision = true) {
							entity.collisionOn = true;
						}
						if(player) {
							index = i;
						}
					}
				case "down":
					entity.solidArea.y += entity.speed;
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if(gp.obj[i].collision = true) {
							entity.collisionOn = true;
						}
						if(player) {
							index = i;
						}
					}
					break;
				case "downlt":
					entity.solidArea.x -= entity.speeddiag;
					entity.solidArea.y += entity.speeddiag;
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if(gp.obj[i].collision = true) {
							entity.collisionOn = true;
						}
						if(player) {
							index = i;
						}
					}
				case "downrt":
					entity.solidArea.x += entity.speeddiag;
					entity.solidArea.y += entity.speeddiag;
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if(gp.obj[i].collision = true) {
							entity.collisionOn = true;
						}
						if(player) {
							index = i;
						}
					}
				case "left":
					entity.solidArea.x -= entity.speed;
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if(gp.obj[i].collision = true) {
							entity.collisionOn = true;
						}
						if(player) {
							index = i;
						}
					}
					break;
				case "right":
					entity.solidArea.x += entity.speed;
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if(gp.obj[i].collision = true) {
							entity.collisionOn = true;
						}
						if(player) {
							index = i;
						}
					}
					break;
				}
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
				gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
			}
		}
		
		return index;
	}
	
	// NPC or Monster collision
	public int checkEntity(Entity entity, Entity[] target) {
		int index = 999;
		
		for(int i = 0; i < target.length; i++) {
			if(target[i] != null) {
				// get entity's solid area position
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				
				// get the targets's solid area position
				target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
				target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;
				
				switch(entity.direction) {
				case "up":
					entity.solidArea.y -= entity.speed;
					if(entity.solidArea.intersects(target[i].solidArea)) {
						entity.collisionOn = true;
						index = i;
					}
					break;
				case "uplt":
					entity.solidArea.x -= entity.speeddiag;
					entity.solidArea.y -= entity.speeddiag;
					if(entity.solidArea.intersects(target[i].solidArea)) {
						entity.collisionOn = true;
						index = i;
					}
				case "uprt":
					entity.solidArea.x += entity.speeddiag;
					entity.solidArea.y -= entity.speeddiag;
					if(entity.solidArea.intersects(target[i].solidArea)) {
						entity.collisionOn = true;
						index = i;
					}
				case "down":
					entity.solidArea.y += entity.speed;
					if(entity.solidArea.intersects(target[i].solidArea)) {
						entity.collisionOn = true;
						index = i;
					}
					break;
				case "downlt":
					entity.solidArea.x -= entity.speeddiag;
					entity.solidArea.y += entity.speeddiag;
					if(entity.solidArea.intersects(target[i].solidArea)) {
						entity.collisionOn = true;
						index = i;
					}
				case "downrt":
					entity.solidArea.x += entity.speeddiag;
					entity.solidArea.y += entity.speeddiag;
					if(entity.solidArea.intersects(target[i].solidArea)) {
						entity.collisionOn = true;
						index = i;
					}
				case "left":
					entity.solidArea.x -= entity.speed;
					if(entity.solidArea.intersects(target[i].solidArea)) {
						entity.collisionOn = true;
						index = i;
					}
					break;
				case "right":
					entity.solidArea.x += entity.speed;
					if(entity.solidArea.intersects(target[i].solidArea)) {
						entity.collisionOn = true;	
						index = i;
						break;
					}
				}
				
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				target[i].solidArea.x = target[i].solidAreaDefaultX;
				target[i].solidArea.y = target[i].solidAreaDefaultY;
			}
		}
		
		return index;
	}
	
	public void checkPlayer(Entity entity) {
		// get entity's solid area position
		entity.solidArea.x = entity.worldX + entity.solidArea.x;
		entity.solidArea.y = entity.worldY + entity.solidArea.y;
		
		// get the object's solid area position
		gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
		
		switch(entity.direction) {
		case "up":
			entity.solidArea.y -= entity.speed;
			if(entity.solidArea.intersects(gp.player.solidArea)) {
				entity.collisionOn = true;
			}
			break;
		case "uplt":
			entity.solidArea.x -= entity.speeddiag;
			entity.solidArea.y -= entity.speeddiag;
			if(entity.solidArea.intersects(gp.player.solidArea)) {
				entity.collisionOn = true;
			}
		case "uprt":
			entity.solidArea.x += entity.speeddiag;
			entity.solidArea.y -= entity.speeddiag;
			if(entity.solidArea.intersects(gp.player.solidArea)) {
				entity.collisionOn = true;
			}
		case "down":
			entity.solidArea.y += entity.speed;
			if(entity.solidArea.intersects(gp.player.solidArea)) {
				entity.collisionOn = true;
			}
			break;
		case "downlt":
			entity.solidArea.x -= entity.speeddiag;
			entity.solidArea.y += entity.speeddiag;
			if(entity.solidArea.intersects(gp.player.solidArea)) {
				entity.collisionOn = true;
			}
		case "downrt":
			entity.solidArea.x += entity.speeddiag;
			entity.solidArea.y += entity.speeddiag;
			if(entity.solidArea.intersects(gp.player.solidArea)) {
				entity.collisionOn = true;
			}
		case "left":
			entity.solidArea.x -= entity.speed;
			if(entity.solidArea.intersects(gp.player.solidArea)) {
				entity.collisionOn = true;
			}
			break;
		case "right":
			entity.solidArea.x += entity.speed;
			if(entity.solidArea.intersects(gp.player.solidArea)) {
				entity.collisionOn = true;
			}
			break;
		}
		entity.solidArea.x = entity.solidAreaDefaultX;
		entity.solidArea.y = entity.solidAreaDefaultY;
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
	}
}
