package controllers;

import main.GamePanel;
import models.GameModel;
import views.GameView;

public class GameController {
	
	private GamePanel gp;
	private GameModel gm;
	private GameView gv;

	public GameController(GamePanel gp, GameModel gm, GameView gv) {
		this.gp = gp;
		this.gm = gm;
		this.gv = gv;
	}
	
	public void update( ) {
		switch(gameState) {
		case TITLESTATE:
			break;
		case PLAYSTATE:
			// Player
			player.update();
			// NPC
			for(int i = 0; i < npc.length; i++) {
				if(npc[i] != null) {
					npc[i].update();
				}
			}
			
			for(int i = 0; i < monster.length; i++) {
				if(monster[i] != null) {
					if(monster[i].isAlive() == true && monster[i].isDying() == false) {
						monster[i].update();
					}
					if(monster[i].isAlive() == false) {
						monster[i] = null;
					}
				}
			}
			
			/*
			for(Entity m: monster) {
				if(m != null) {
					System.out.println(m);
					if(m.isAlive() && !m.isDying()) {
						m.update();
					}
					if(!m.isAlive()) {
						System.out.println("monster has died: " + m);
						m.remove();
						System.out.println(m);
					}
					
				}
			}
			*/
			break;
		case PAUSESTATE:
			break;
		case DIALOGUESTATE:
			break;
		case CHARACTERSTATE:
			break;
		case DEADSTATE:
			break;
		default:
			break;
		}
		
	}
}
