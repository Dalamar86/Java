package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import main.GamePanel;

public class GameView extends JPanel {
	
	private GamePanel gp;
	
	// Screen Settings
		final int originalTileSize = 16;	// 16 x 16 tile
		final int scale = 3;				// Make it larger for larger resolution
		
		public final int tileSize = originalTileSize * scale;		// 48 x 48 tile
		public final int maxScreenCol = 16;
		public final int maxScreenRow = 12;
		public final int screenWidth = tileSize * maxScreenCol;	// 768 Pixels
		public final int screenHeight = tileSize * maxScreenRow;	// 576 Pixels
		
		// World Setting
		public final int maxWorldCol = 50;
		public final int maxWorldRow = 50;
		public final int worldWidth = tileSize * maxWorldCol;
		public final int worldHeight = tileSize * maxWorldRow;
	
	public GameView(GamePanel gp) {
		this.gp = gp;
	}

public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		// Debug
		long drawStart = 0;
		if(keyH.showDebugText) {
			drawStart = System.nanoTime();
		}
		
		// Title Screen
		switch(gp.gameState) {
		case TITLESTATE:
			ui.draw(g2);
			break;
		case DIALOGUESTATE:
			/*
			// Tile
			tileM.draw(g2);
			
			// Object
			for(int i = 0; i < obj.length; i++) {
				if(obj[i] != null) {
					obj[i].draw(g2, this);
				}
			}
			
			// NPC
			for(int i = 0;i < npc.length; i++) {
				if(npc[i] != null) {
					npc[i].draw(g2);
				}
			}
			
			// Player
			player.draw(g2);
			
			// UI
			ui.draw(g2);
			break;
			*/
		case PAUSESTATE:
			/*
			// Tile
			tileM.draw(g2);
			
			// Object
			for(int i = 0; i < obj.length; i++) {
				if(obj[i] != null) {
					obj[i].draw(g2, this);
				}
			}
			
			// NPC
			for(int i = 0;i < npc.length; i++) {
				if(npc[i] != null) {
					npc[i].draw(g2);
				}
			}
			
			// Player
			player.draw(g2);
			
			// UI
			ui.draw(g2);
			break;
			*/
		case CHARACTERSTATE:
		case DEADSTATE:
		case PLAYSTATE:
			// Tile
			tileM.draw(g2);
			
			// Add entities to list
			entityList.add(player);
			
			// NPC
			for(int i = 0;i < npc.length; i++) {
				if(npc[i] != null) {
					entityList.add(npc[i]);
				}
			}
			
			// Object
			for(int i = 0; i < obj.length; i++) {
				if(obj[i] != null) {
					entityList.add(obj[i]); // draw(g2, this);
				}
			}
			
			// Monsters
			for(GameObject m: monster) {
				if(m != null) {
					entityList.add(m);
				}
			}
			
			entityList.sort(ee);
			
			// Sorting
			entityList.sort((GameObject o1, GameObject o2)-> o1.worldY - o2.worldY);
			
			//Collections.sort(entityList, );
			
			// Draw entities
			for(GameObject e: entityList) {
				e.draw(g2);
			}
			
			entityList.clear();
			
			// UI
			ui.draw(g2);
			
			// Debug
			if(keyH.showDebugText) {
				long drawEnd = System.nanoTime();
				long passed = drawEnd - drawStart;
				
				g2.setFont(ui.arial_40);
				g2.setFont(getFont().deriveFont(Font.PLAIN, 20f));
				g2.setColor(Color.white);
				int x = 10;
				int y = 400;
				int lineHeight = 20;
				
				g2.drawString("WorldX: " + player.worldX, x, y);
				y += lineHeight;
				g2.drawString("WorldY: " + player.worldY, x, y);
				y += lineHeight;
				g2.drawString("Col: " + (player.worldX + player.solidArea.x)/tileSize, x, y);
				y += lineHeight;
				g2.drawString("Row: " + (player.worldY + player.solidArea.y)/tileSize, x, y);
				y += lineHeight;
				g2.drawString("Draw Time: " + passed, x, y);
				System.out.println("Draw Time: " + passed);
			}
			break;
		
		default:
			break;
		}
		g2.dispose();
	}

}
