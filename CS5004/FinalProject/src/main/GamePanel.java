package main;



import java.util.ArrayList;
import java.util.Comparator;

import models.*;
import controllers.*;
import views.*;
import components.*;

public class GamePanel implements Runnable {
	
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
	private int FPS = 60;
	public GameState gameState = GameState.TITLESTATE;
	public GameState gameStatePrev;
	
	// System
	public TileManager tileM = new TileManager(this);
	public KeyHandler keyH = new KeyHandler(this);
	Sound music = new Sound();
	Sound se = new Sound();
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	public EventHandler eHandler = new EventHandler(this);
	public Thread gameThread;  // keeps the programming running
	
	// Entity and objects
	public PlayerController player = new PlayerController(this, keyH);
	public GameObjectModel obj[] = new SuperObject[20];
	public GameObjectModel npc[] = new GameObject[10];
	public GameObjectModel monster[]= new GameObject[20];
	public ArrayList<GameObjectModel> entityList = new ArrayList<>();
	public Comparator<GameObjectModel> ee;
	
	// Game MVC
	public GameModel gm;
	public GameController gc;
	public GameView gv;
	
	public GamePanel() {
		gm = new GameModel(this);
		gv = new GameView(this);
		gc = new GameController(this, gm, gv);
	}
	
	public void setupGame() {
		gameState = GameState.TITLESTATE;
		ee = new Comparator<GameObjectModel>() {

			@Override
			public int compare(GameObjectModel o1, GameObjectModel o2) {
				
				int result = Integer.compare(o1.worldY, o2.worldY);
				return result;
			}
		};
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
		int frames = 0;
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			if(delta >= 1) {
				// 1 UPDATE: update information such as character positions
				gc.update();
				
				// DRAW: draw the screen with the updated information
				gv.repaint();
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
	
	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

}
