package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
			switch(gp.gameState) {
			case PLAYSTATE:
				gp.gameState = gp.gameState.PAUSESTATE;
				break;
			case PAUSESTATE:
				gp.gameState = gp.gameState.PLAYSTATE;
			}
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
