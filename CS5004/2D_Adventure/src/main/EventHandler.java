package main;

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
			eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
			eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;
			
			col++;
			if(col == gp.maxWorldCol) {
				col = 0;
				row++;
			}
		}
		
		
	}
	
	public void checkEvent() {
		
		// Check player left area
		int xDistance = Math.abs(gp.player.worldX - previousEventX);
		int yDistance = Math.abs(gp.player.worldY - previousEventY);
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
		
	}
	
	public boolean hit(int col, int row, String reqDirection) {
		
		boolean hit = false;
		
		// get the player's solid area position
		gp.player.solidArea.x += gp.player.worldX;
		gp.player.solidArea.y += gp.player.worldY;
		eventRect[col][row].x += col*gp.tileSize;
		eventRect[col][row].y += row*gp.tileSize;
		
		if(gp.player.solidArea.intersects(eventRect[col][row]) && eventRect[col][row].eventFinished == false) {
			if(gp.player.getDirection().contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
				hit = true;System.out.println("You made it here");
				
				previousEventX = gp.player.worldX;
				previousEventY = gp.player.worldY;
				
			}
		}
		
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
		eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
		eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;
		
		return hit;
	}
	
	public void teleport(GameState gameState) {
		gp.gameState = gameState;
		gp.ui.setCurrentDialogue("Teleport!");
		gp.player.worldX = gp.tileSize * 37;
		gp.player.worldY = gp.tileSize * 10;
	}
	
	public void damagePit(int col, int row, GameState gameState) {
		gp.gameState = gameState;
		gp.playSE(6);
		gp.ui.setCurrentDialogue("you fall into a pit!");
		gp.player.setLife(gp.player.getLife() - 1);
		//eventRect[col][row].eventFinished = true;
		canTriggerEvent = false;
	}
	
	public void healingPool(int col, int row, GameState gameState) {
		
		if(gp.keyH.enterPressed == true) {
			gp.gameState = gameState;
			gp.player.setAttackCanceled(true);
			gp.playSE(2);
			gp.ui.setCurrentDialogue("You drink the water.\nYour life has been recovered!");
			gp.player.setLife(gp.player.getMaxLife());
			//eventRect[col][row].eventFinished = true;
			gp.aSetter.setMonster();
		}
	}
}
