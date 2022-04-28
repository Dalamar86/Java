package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.JPanel;

import entity.*;
import gameobject.GameObject;
import tile.*;
import object.*;

/**
 * This is our game panel and is the window for our game.
 * @author Robert Wilson
 *
 */
public class GamePanel extends JPanel implements Runnable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
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
	
	
	int FPS = 60;
	private GameState gameState = GameState.TITLESTATE;
	private GameState gameStatePrev;
	
	
	public TileManager tileM = new TileManager(this);
	public KeyHandler keyH = new KeyHandler(this);
	Sound music = new Sound();
	Sound se = new Sound();
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	public EventHandler eHandler = new EventHandler(this);
	Thread gameThread;  // keeps the programming running
	
	// Entity and objects
	public Player player = new Player(this, keyH);
	public GameObject obj[] = new SuperObject[20];
	public GameObject npc[] = new GameObject[10];
	public GameObject monster[]= new GameObject[20];
	public ArrayList<GameObject> entityList = new ArrayList<>();
	public Comparator<GameObject> ee;
	
			
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
		aSetter.setMonster();
		playMusic(0);
		gameState = GameState.TITLESTATE;
		ee = new Comparator<GameObject>() {

			@Override
			public int compare(GameObject o1, GameObject o2) {
				
				int result = Integer.compare(o1.worldY, o2.worldY);
				return result;
			}
		};
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void endGameThread() {
		gameThread.stop();
	}
	
	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int frames = 0;
		
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
				frames++;
			}
			
			if(timer >= 1000000000) {
				System.out.println("FPS: " + frames);
				frames = 0;
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
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		// Debug
		long drawStart = 0;
		if(keyH.isShowDebugText()) {
			drawStart = System.nanoTime();
		}
		
		// Title Screen
		switch(gameState) {
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
			if(keyH.isShowDebugText()) {
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
	
	
	//#####################################################################
	// 							Getters and Setters
	//#####################################################################
	
	
	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	public GameState getGameState() {
		return gameState;
	}

	public void setPrevState() {
		this.gameStatePrev = gameState;
		
	}

	public GameState getPrevSate() {
		return gameStatePrev;
	}
}
