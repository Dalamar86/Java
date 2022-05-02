package main;

import enums.Area;
import enums.GameState;
import gameobject.GameObject;

public class EventHandler {

	GamePanel gp;
	EventRect eventRect[][];
	
	int previousEventX, previousEventY;
	boolean canTriggerEvent = true;
	
	public EventHandler (GamePanel gp) {
		this.gp = gp;
		
		eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];
		
		int col = 0;
		int row = 0;
		while(col< gp.maxWorldCol && row < gp.maxWorldRow) {
			eventRect[col][row] = new EventRect();
			eventRect[col][row].x = 23;
			eventRect[col][row].y = 23;
			eventRect[col][row].width = 2;
			eventRect[col][row].height = 2;
			eventRect[col][row].setEventRectDefaultX(eventRect[col][row].x);
			eventRect[col][row].setEventRectDefaultY(eventRect[col][row].y);
			
			col++;
			if(col == gp.maxWorldCol) {
				col = 0;
				row++;
			}
		}
		
		
	}
	
	public void checkEvent() {
		
		// Check player left area
		int xDistance = Math.abs(gp.player.getWorldX() - previousEventX);
		int yDistance = Math.abs(gp.player.getWorldY() - previousEventY);
		int distance = Math.max(xDistance, yDistance);
		if(distance > gp.tileSize*1.5) {
			canTriggerEvent = true;
		}
		
		if(canTriggerEvent) {
			if(hit(27, 16, "right") == true) {damagePit(27, 16, GameState.DIALOGUESTATE);}
			if(hit(23, 16, "any") == true) {damagePit(27, 16, GameState.DIALOGUESTATE);}
			if(hit(23, 12, "up") == true) {healingPool(23, 12, GameState.DIALOGUESTATE);}
			if(hit(25, 16, "left")) {teleport(GameState.DIALOGUESTATE);}
		}
		if(hit(12, 24, "down") || hit(8, 28, "down")) {enterBossArea();}
		if(hit(12, 24, "up") || hit(8, 28, "up")) {leaveBossArea();}
	}
	
	public boolean hit(int col, int row, String reqDirection) {
		
		boolean hit = false;
		
		// get the player's solid area position
		gp.player.getSolidArea().x += gp.player.getWorldX();
		gp.player.getSolidArea().y += gp.player.getWorldY();
		eventRect[col][row].x += col*gp.tileSize;
		eventRect[col][row].y += row*gp.tileSize;
		
		if(gp.player.getSolidArea().intersects(eventRect[col][row]) && eventRect[col][row].isEventFinished() == false) {
			if(gp.player.getDirection().contains(reqDirection) || reqDirection.contentEquals("any")) {
				hit = true;System.out.println("You made it here");
				
				previousEventX = gp.player.getWorldX();
				previousEventY = gp.player.getWorldY();
				
			}
		}
		
		gp.player.getSolidArea().x = gp.player.getSolidAreaDefaultX();
		gp.player.getSolidArea().y = gp.player.getSolidAreaDefaultY();
		eventRect[col][row].x = eventRect[col][row].getEventRectDefaultX();
		eventRect[col][row].y = eventRect[col][row].getEventRectDefaultY();
		
		return hit;
	}
	
	public void teleport(GameState gameState) {
		gp.setGameState(gameState);
		gp.ui.setCurrentDialogue("Teleport!");
		gp.player.setWorldX(gp.tileSize * 37);
		gp.player.setWorldY(gp.tileSize * 10);
	}
	
	public void damagePit(int col, int row, GameState gameState) {
		gp.setGameState(gameState);
		gp.playSE(6);
		gp.ui.setCurrentDialogue("you fall into a pit!");
		gp.player.setLife(gp.player.getLife() - 1);
		//eventRect[col][row].eventFinished = true;
		canTriggerEvent = false;
	}
	
	public void healingPool(int col, int row, GameState gameState) {
		if(gp.keyH.isEnterPressed() == true) {
			gp.setGameState(gameState);
			gp.player.setAttackCanceled(true);
			gp.playSE(2);
			gp.ui.setCurrentDialogue("You drink the water.\nYour life has been recovered!");
			gp.player.setLife(gp.player.getMaxLife());
			//eventRect[col][row].eventFinished = true;
			gp.getArea().resetMonster(gp);
			gp.resetAssetMonster();
		}
	}
	
	public void enterBossArea() {
		for(GameObject m: gp.monster) {
			if(m != null) {
				if(m.getName().contains("Boss")) {
					m.setAttacking(true);
				}
			}
		}
	}
	
	public void leaveBossArea() {
		for(GameObject m: gp.monster) {
			if(m != null) {
				if(m.getName().contains("Boss")) {
					m.setAttacking(false);
				}
			}
		}
	}
}
