package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.*;
import tile.*;
import object.*;

/**
 * This is our game panel and is the window for our game.
 * @author Robert Wilson
 *
 */
public class GamePanel extends JPanel implements Runnable {
	
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
	
	// FPS
	int FPS = 60;
	
	// Game State
	public enum GameState {
		TITLESTATE,
		PLAYSTATE, 
		PAUSESTATE,
		DIALOGUESTATE
	}
	public GameState gameState = GameState.TITLESTATE;
	public GameState gameStatePrev;
	
	// System
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler(this);
	Sound music = new Sound();
	Sound se = new Sound();
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	public EventHandler eHandler = new EventHandler(this);
	Thread gameThread;  // keeps the programming running
	
	// Entity and objects
	public Player player = new Player(this, keyH);
	public SuperObject obj[] = new SuperObject[20];
	public Entity npc[] = new Entity[10];
	
	
			
	public GamePanel () {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}

	public void setupGame() {
		
		aSetter.setObject();
		aSetter.setNPC();
		playMusic(0);
		gameState = GameState.TITLESTATE;
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			if(delta >= 1) {
				// 1 UPDATE: update information such as character positions
				update();
				
				// DRAW: draw the screen with the updated information
				repaint();
				delta--;
				drawCount++;
			}
			
			if(timer >= 1000000000) {
				System.out.println("FPS: " + drawCount);
				drawCount = 0;
				timer = 0;
			}
		}
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
			break;
		case PAUSESTATE:
			break;
		case DIALOGUESTATE:
			break;
		default:
			break;
		}
		
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		// Debug
		long drawStart = 0;
		if(keyH.checkDrawTime) {
			drawStart = System.nanoTime();
		}
		
		// Title Screen
		switch(gameState) {
		case TITLESTATE:
			ui.draw(g2);
			break;
		case DIALOGUESTATE:
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
		case PAUSESTATE:
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
		case PLAYSTATE:
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
			
			// Debug
			if(keyH.checkDrawTime) {
				long drawEnd = System.nanoTime();
				long passed = drawEnd - drawStart;
				g2.setColor(Color.white);
				g2.drawString("Draw Time: " + passed, 10, 400);
				System.out.println("Draw Time: " + passed);
			}
			break;
		default:
			break;
		}
		
		
		
		g2.dispose();
	}
	
	public void playMusic(int index) {
		
		music.setFile(index);
		music.play();
		music.loop();
	}
	
	public void stopMusic() {
		music.stop();
	}
	
	public void playSE(int index) {
		se.setFile(index);
		se.play();
	}
}
