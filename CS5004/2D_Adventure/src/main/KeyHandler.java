package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * This class controls our keyboard input.
 * @author Robert Wilson
 *
 */
public class KeyHandler implements KeyListener {

	public boolean upPressed, downPressed, ltPressed, rtPressed;
	
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
