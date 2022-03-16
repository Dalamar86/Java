package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import main.GamePanel.GameState;

/**
 * This class controls our keyboard input.
 * @author Robert Wilson
 *
 */
public class KeyHandler implements KeyListener {

	GamePanel gp;
	public boolean upPressed, downPressed, ltPressed, rtPressed;
	
	// Debug
	boolean checkDrawTime = false;
	
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		switch(gp.gameState) {
			case PLAYSTATE:
				
				if(code == KeyEvent.VK_W) {
					upPressed = true;
				} else if(code == KeyEvent.VK_S) {
					downPressed = true;
				} else if(code == KeyEvent.VK_A) {
					ltPressed = true;
				} else if(code == KeyEvent.VK_D) {
					rtPressed = true;
				} else if(code == KeyEvent.VK_T) {
					if(checkDrawTime) {
						checkDrawTime = false;
					} else {
						checkDrawTime = true;
					}
				} else if(code == KeyEvent.VK_ESCAPE) {
					System.out.println("Pressed Escape");
					System.out.println("In Play Before: gameState: " + gp.gameState + "\tPrev: " + gp.gameStatePrev);
					gp.gameStatePrev = gp.gameState;
					System.out.println("In Play After: gameState: " + gp.gameState + "\tPrev: " + gp.gameStatePrev);
					gp.gameState = GameState.PAUSESTATE;
					System.out.println("In Play End: gameState: " + gp.gameState + "\tPrev: " + gp.gameStatePrev);
					
				}
				break;
			case PAUSESTATE:
				if(code == KeyEvent.VK_ESCAPE) {
					System.out.println("Pressed Escape");
					System.out.println("In Pause Before: gameState: " + gp.gameState + "\tPrev: " + gp.gameStatePrev);
					gp.gameState = gp.gameStatePrev;
					System.out.println("In Pause After: gameState: " + gp.gameState + "\tPrev: " + gp.gameStatePrev);
				}
				break;
			case DIALOGUESTATE:
				if(code == KeyEvent.VK_ENTER) {
					System.out.println("Pressed Enter");
					System.out.println("gameState: " + gp.gameState + "\tPrev: " + gp.gameStatePrev);
					gp.gameState = GameState.PLAYSTATE;
					System.out.println("gameState: " + gp.gameState + "\tPrev: " + gp.gameStatePrev);
				} else if(code == KeyEvent.VK_ESCAPE) {
					System.out.println("Pressed Escape");
					System.out.println("In Dial Before: gameState: " + gp.gameState + "\tPrev: " + gp.gameStatePrev);
					gp.gameStatePrev = gp.gameState;
					System.out.println("In Dial Before: gameState: " + gp.gameState + "\tPrev: " + gp.gameStatePrev);
					gp.gameState = GameState.PAUSESTATE;
					System.out.println("In Dial Before: gameState: " + gp.gameState + "\tPrev: " + gp.gameStatePrev);
				}
				break;
			default:
				break;
		
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			upPressed = false;
		} else if(code == KeyEvent.VK_S) {
			downPressed = false;
		} else if(code == KeyEvent.VK_A) {
			ltPressed = false;
		} else if(code == KeyEvent.VK_D) {
			rtPressed = false;
		} 
		
	}

}
