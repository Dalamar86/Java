package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.JFrame;
import javax.swing.JPanel;

import entity.*;
import enums.Area;
import enums.GameState;
import gameobject.GameObject;
import tile.*;
import object.*;

/**
 * This game panel holds all aspects of the game.  It implements Runnable which provides us with the 
 * thread interface while letting us extend JPanel, which provides us with paint.    
 * 
 * @author Robert Wilson
 *
 */
public class GamePanel extends JPanel implements Runnable {
	
	// Serial version
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
	
	private JFrame window;
	int FPS = 60;
	private GameState gameState = GameState.TITLESTATE;
	private GameState gameStatePrev;
	private Area area = Area.MAIN;
	
	
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
	public GameObject monster[]= new GameObject[30];
	public ArrayList<GameObject> projectileList = new ArrayList<>();
	public ArrayList<GameObject> entityList = new ArrayList<>();
	public Comparator<GameObject> ee;
	
	/**
	 * Creates an instance of the GamePanel.  This panel will encompass the entire game
	 * and all classes will be able to be reached through tihs one.  
	 * 
	 * @param window
	 */
	public GamePanel (JFrame window) {
		this.setWindow(window);
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}

	/**
	 * Setup the game by starting with the debug area and title state.  Also 
	 * start the music and set the comparator for entities.
	 */
	public void setupGame() {
		setArea(Area.DEBUG);
		gameState = GameState.TITLESTATE;
		area.setup(this);
		addAssets();
		playMusic(0);
		
		// Comparator for entities
		ee = new Comparator<GameObject>() {
			@Override
			public int compare(GameObject o1, GameObject o2) {
				
				int result = Integer.compare(o1.getWorldY(), o2.getWorldY());
				return result;
			}
		};
	}

	/**
	 * Load the current area and all its assets and tiles
	 */
	public void loadArea() {
		aSetter.reset();
		switch(getArea()) {
		case DEBUG:
			area.setup(this);
			break;
		case MAIN:
			//Area.mainSetup(this);
			break;
		case BOTTOM:
			break;
		case LEFT:
			break;
		case RIGHT:
			break;
		case TEMPLE:
			break;
		case TOP:
			break;
		default:
			break;
		}
	}
	
	/**
	 * Create and start the game thread
	 */
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	/**
	 * Ends the game. Depreciated but works fine for this implementation.
	 */
	public void endGameThread() {
		gameThread.stop();
	}
	
	@Override
	public void run() {
		// Use well known FPS based timing system
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
	
	/**
	 * Update all object by calling the update method of all objects.
	 */
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
			
			for(int i = 0; i < projectileList.size(); i++) {
				if(projectileList.get(i) != null) {
					if(projectileList.get(i).isAlive()) {
						projectileList.get(i).update();
					}
					if(projectileList.get(i).isAlive() == false) {
						projectileList.remove(i);
					}
				}
			}
			
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
	
	/**
	 * Call all objects draw methods as well as tileManager and UI.  Sort entities 
	 * by their worldY position.
	 */
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
		case PAUSESTATE:
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
					entityList.add(obj[i]);
				}
			}
			
			// Monsters
			for(GameObject m: monster) {
				if(m != null) {
					entityList.add(m);
				}
			}
			
			// Projectiles
			for(GameObject p: projectileList) {
				if(p != null) {
					entityList.add(p);
				}
			}
			
			entityList.sort(ee);
			
			// Sorting
			entityList.sort((GameObject o1, GameObject o2)-> o1.getWorldY() - o2.getWorldY());
			
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
				
				g2.drawString("WorldX: " + player.getWorldX(), x, y);
				y += lineHeight;
				g2.drawString("WorldY: " + player.getWorldY(), x, y);
				y += lineHeight;
				g2.drawString("Col: " + (player.getWorldX() + player.getSolidArea().x)/tileSize, x, y);
				y += lineHeight;
				g2.drawString("Row: " + (player.getWorldY() + player.getSolidArea().y)/tileSize, x, y);
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
	
	//#####################################################################
	// 								Helper Methods
	//#####################################################################
	
	/**
	 * Add all assest to the board from the asset setter.
	 */
	private void addAssets() {
		for(int index = 0; index < aSetter.getObj().size(); index++) {
			obj[index] = aSetter.getObj().get(index);
		}
		
		for(int index = 0; index < aSetter.getNpc().size(); index++) {
			npc[index] = aSetter.getNpc().get(index);
		}
		
		for(int index = 0; index < aSetter.getMonster().size(); index++) {
			monster[index] = aSetter.getMonster().get(index);
		}
	}
	
	/**
	 * Reset the monsters on the board.
	 */
	public void resetAssetMonster() {
		for(int index = 0; index < aSetter.getMonster().size(); index++) {
			monster[index] = aSetter.getMonster().get(index);
		}
	}
	
	/**
	 * Add new monsters to the board from the asset setter.
	 */
	public void addAssetMonster() {
		int index2 = 0;
		for(int index = 0; index2 < aSetter.getMonster().size(); index++) {
			if(monster[index] == null) {
				monster[index] = aSetter.getMonster().get(index2);
				index2++;
				System.out.println("monster added to  gp at index: " + index);
			}
		}
	}
	
	/**
	 * Play the background music of the game.
	 * 
	 * @param index
	 */
	public void playMusic(int index) {
		music.setFile(index);
		music.play();
		music.loop();
	}
	
	/**
	 * Stop the current music playing.
	 */
	public void stopMusic() {
		music.stop();
	}
	
	/**
	 * Plays sound effects.
	 * 
	 * @param index (int) number of sound effect to play
	 */
	public void playSE(int index) {
		se.setFile(index);
		se.play();
	}
	
	
	//#####################################################################
	// 							Getters and Setters
	//#####################################################################
	
	/**
	 * Sets the current game state.
	 * 
	 * @param gameState (GameState)
	 */
	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	/**
	 * Returns the current game state.
	 * 
	 * @return gameState (GameState)
	 */
	public GameState getGameState() {
		return gameState;
	}

	/**
	 * Sets the previous game state to the current.
	 * game state.
	 * 
	 */
	public void setPrevState() {
		this.gameStatePrev = gameState;
		
	}

	/**
	 * Returns the previous game state.
	 * 
	 * @return gameStatePrev (GameState)
	 */
	public GameState getPrevSate() {
		return gameStatePrev;
	}

	/**
	 * Returns the current window of the game.
	 * 
	 * @return window (JFrame)
	 */
	public JFrame getWindow() {
		return window;
	}

	/**
	 * Sets the window of the current game.
	 * 
	 * @param window (JFrame)
	 */
	public void setWindow(JFrame window) {
		this.window = window;
	}

	/**
	 * Returns the current game area.
	 * 
	 * @return area (Area)
	 */
	public Area getArea() {
		return area;
	}

	/**
	 * Sets the current game area.
	 * 
	 * @param area (Area)
	 */
	public void setArea(Area area) {
		this.area = area;
	}
}
