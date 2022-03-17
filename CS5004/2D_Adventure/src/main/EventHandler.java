package main;

import java.awt.Rectangle;

import main.GamePanel.GameState;

public class EventHandler {

	GamePanel gp;
	Rectangle eventRect;
	int eventRectDefaultX, eventRectDefaultY;
	
	public EventHandler (GamePanel gp) {
		this.gp = gp;
		
		eventRect = new Rectangle();
		eventRect.x = 23;
		eventRect.y = 23;
		eventRect.width = 2;
		eventRect.height = 2;
		eventRectDefaultX = eventRect.x;
		eventRectDefaultY = eventRect.y;
		
	}
	
	public void checkEvent() {
		
		if(hit(27, 16, "right") == true) {damagePit(GameState.DIALOGUESTATE);}
		if(hit(23, 12, "up") == true) {healingPool(GameState.DIALOGUESTATE);}
		if(hit(26, 16, "left")) {teleport(GameState.DIALOGUESTATE);}
	}
	
	public boolean hit(int eventCol, int eventRow, String reqDirection) {
		
		boolean hit = false;
		
		// get the player's solid area position
		gp.player.solidArea.x += gp.player.worldX;
		gp.player.solidArea.y += gp.player.worldY;
		eventRect.x += eventCol*gp.tileSize;
		eventRect.y += eventRow*gp.tileSize;
		
		if(gp.player.solidArea.intersects(eventRect)) {
			if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
				hit = true;System.out.println("You made it here");
			}
		}
		
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
		eventRect.x = eventRectDefaultX;
		eventRect.y = eventRectDefaultY;
		
		return hit;
	}
	
	public void teleport(GameState gameState) {
		gp.gameState = gameState;
		gp.ui.currentDialogue = "Teleport!";
		gp.player.worldX = gp.tileSize * 37;
		gp.player.worldY = gp.tileSize * 10;
	}
	
	public void damagePit(GameState gameState) {
		gp.gameState = gameState;
		gp.ui.currentDialogue = "you fall into a pit!";
		gp.player.setLife(gp.player.getLife() - 1);
	}
	
	public void healingPool(GameState gameState) {
		
		if(gp.keyH.enterPressed == true) {
			gp.gameState = gameState;
			gp.ui.currentDialogue = "You drink the water.\nYour life has been recovered!";
			gp.player.setLife(gp.player.getMaxLife()); 
		}
	}
}
