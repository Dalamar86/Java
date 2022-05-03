package main;

import enums.GameState;
import gameobject.GameObject;

/**
 * Creates and manages events set around the map.  
 * 
 * @author Robert Wilson
 *
 */
public class EventHandler {
	// Current Game Panel
	GamePanel gp;
	
	// Event holder 
	EventRect eventRect[][];
	
	// Event triggering modifiers
	int previousEventX, previousEventY;
	boolean canTriggerEvent = true;
	
	/**
	 * Creates an instance of the map events.  An event is added to each tile on the map
	 * 
	 * @param gp (GamePanel) Current game panel
	 */
	public EventHandler (GamePanel gp) {
		this.gp = gp;
		
		// event holder
		eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];
		
		// create a new instance of events at every tile on the current map
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
	
	/**
	 * Checks if a player hits an event.  If so it triggers the appropriate event.
	 */
	public void checkEvent() {
		
		// Check player left area, if so then the events can be triggered again
		int xDistance = Math.abs(gp.player.getWorldX() - previousEventX);
		int yDistance = Math.abs(gp.player.getWorldY() - previousEventY);
		int distance = Math.max(xDistance, yDistance);
		if(distance > gp.tileSize*1.5) {
			canTriggerEvent = true;
		}
		
		// modified triggering of events
		if(canTriggerEvent) {
			if(hit(27, 16, "right") == true) {damagePit(27, 16, GameState.DIALOGUESTATE);}
			if(hit(23, 16, "any") == true) {damagePit(27, 16, GameState.DIALOGUESTATE);}
			if(hit(23, 12, "up") == true) {healingPool(23, 12, GameState.DIALOGUESTATE);}
			if(hit(25, 16, "left")) {teleport(GameState.DIALOGUESTATE);}
		}
		
		// These events trigger with no modifiers
		if(hit(12, 24, "down") || hit(8, 28, "down")) {enterBossArea();}
		if(hit(12, 24, "up") || hit(8, 28, "up")) {leaveBossArea();}
	}
	
	/**
	 * Checks if the player hit the event in the direction given
	 * 
	 * @param col (int) column of the event
	 * @param row (int) row of the event
	 * @param reqDirection (String) the direction the player needs to be going to trigger event
	 * @return hit (boolean) true if it event is triggered and false otherwise
	 */
	public boolean hit(int col, int row, String reqDirection) {
		
		boolean hit = false;
		
		// get the player's solid area position
		gp.player.getSolidArea().x += gp.player.getWorldX();
		gp.player.getSolidArea().y += gp.player.getWorldY();
		eventRect[col][row].x += col*gp.tileSize;
		eventRect[col][row].y += row*gp.tileSize;
		
		// Check if player solid area triggers the event
		if(gp.player.getSolidArea().intersects(eventRect[col][row]) && eventRect[col][row].isEventFinished() == false) {
			if(gp.player.getDirection().contains(reqDirection) || reqDirection.contentEquals("any")) {
				hit = true;
				previousEventX = gp.player.getWorldX();
				previousEventY = gp.player.getWorldY();
			}
		}
		
		// Reset solid areas
		gp.player.getSolidArea().x = gp.player.getSolidAreaDefaultX();
		gp.player.getSolidArea().y = gp.player.getSolidAreaDefaultY();
		eventRect[col][row].x = eventRect[col][row].getEventRectDefaultX();
		eventRect[col][row].y = eventRect[col][row].getEventRectDefaultY();
		
		return hit;
	}
	
	/**
	 * Event which teleports player to new location
	 * 
	 * @param gameState (GameState) dialogue state to send a message of the event
	 */
	public void teleport(GameState gameState) {
		gp.setGameState(gameState);
		gp.ui.setCurrentDialogue("Teleport!");
		gp.player.setWorldX(gp.tileSize * 37);
		gp.player.setWorldY(gp.tileSize * 10);
	}
	
	/**
	 * Event which triggers the player to take damage from a pit, ignoring player defense.
	 * 
	 * @param col (int) column of event
	 * @param row (int) row of event
	 * @param gameState (GameState) dialogue state to send a message of the event
	 */
	public void damagePit(int col, int row, GameState gameState) {
		gp.setGameState(gameState);
		gp.playSE(6);
		gp.ui.setCurrentDialogue("you fall into a pit!");
		gp.player.setLife(gp.player.getLife() - 1);
		//eventRect[col][row].eventFinished = true;
		canTriggerEvent = false;
	}
	
	/**
	 * Event which heals the player and resets all non-boss monsters on the map.
	 * 
	 * @param col (int) column of event
	 * @param row (int) row of event
	 * @param gameState (GameState) dialogue state to send a message of the event
	 */
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
	
	/**
	 * Event triggered by entering boss area.  This triggers the boss to start 
	 * looking for the player.
	 */
	public void enterBossArea() {
		for(GameObject m: gp.monster) {
			if(m != null) {
				if(m.getName().contains("Boss")) {
					m.setAttacking(true);
				}
			}
		}
	}
	
	/**
	 * Event triggered by player leaving boss area.  This triggers the boss to 
	 * stop looking for the player.
	 */
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
