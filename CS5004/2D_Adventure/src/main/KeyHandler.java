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
	public boolean upPressed, downPressed, ltPressed, rtPressed, enterPressed;
	
	// Debug
	boolean showDebugText = false;
	
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if(showDebugText) {
			if(code == KeyEvent.VK_R ) {
				gp.tileM.loadMap("/maps/worldV2.txt");
			}
		}
		
		switch(gp.gameState) {
		case TITLESTATE:
			if(gp.ui.titleScreenState == 0) {
				if(code == KeyEvent.VK_W ||  code == KeyEvent.VK_KP_UP) {
					gp.ui.commandNum--;
					if(gp.ui.commandNum < 0) {
						gp.ui.commandNum = 2;
					}
				} else if (code == KeyEvent.VK_S || code == KeyEvent.VK_KP_DOWN) {
					gp.ui.commandNum++;
					gp.ui.commandNum%=3;
				} else if(code == KeyEvent.VK_ENTER) {
					switch(gp.ui.commandNum) {
					case 0:
						gp.ui.titleScreenState = 1;
						break;
					case 1:
						// TODO add Load method
						gp.gameState = GameState.PLAYSTATE;
						break;
					case 2:
						System.exit(0);
						break;
					}
				}
			} else if(gp.ui.titleScreenState == 1) {
				if(code == KeyEvent.VK_W ||  code == KeyEvent.VK_KP_UP) {
					gp.ui.commandNum--;
					if(gp.ui.commandNum < 0) {
						gp.ui.commandNum = 3;
					}
				} else if (code == KeyEvent.VK_S || code == KeyEvent.VK_KP_DOWN) {
					gp.ui.commandNum++;
					gp.ui.commandNum%=4;
				} else if(code == KeyEvent.VK_ENTER) {
					switch(gp.ui.commandNum) {
					case 0:
						// TODO add fighter stats
						
						//gp.player = new Player(gp, this);
						//break;
					case 1:
						// TODO add sorcerer stats
						//break;
					case 2:
						// TODO add rogue stats
						gp.gameState = GameState.PLAYSTATE;
						break;
					case 3:
						gp.ui.titleScreenState = 0;
						gp.ui.commandNum = 0;
						break;
					}
				}
			}
			break;
		
		case PLAYSTATE:
			if(code == KeyEvent.VK_W) {
				upPressed = true;
			} else if(code == KeyEvent.VK_S) {
				downPressed = true;
			} else if(code == KeyEvent.VK_A) {
				ltPressed = true;
			} else if(code == KeyEvent.VK_D) {
				rtPressed = true;
			} else if (code == KeyEvent.VK_C) {
				gp.gameState = GameState.CHARACTERSTATE;
			} else if(code == KeyEvent.VK_T) {
				if(showDebugText) {
					showDebugText = false;
				} else {
					showDebugText = true;
				}
			} else if(code == KeyEvent.VK_ESCAPE) {
				System.out.println("Pressed Escape");
				System.out.println("In Play Before: gameState: " + gp.gameState + "\tPrev: " + gp.gameStatePrev);
				gp.gameStatePrev = gp.gameState;
				System.out.println("In Play After: gameState: " + gp.gameState + "\tPrev: " + gp.gameStatePrev);
				gp.gameState = GameState.PAUSESTATE;
				System.out.println("In Play End: gameState: " + gp.gameState + "\tPrev: " + gp.gameStatePrev);
				
			} else if(code == KeyEvent.VK_ENTER) {
				enterPressed = true;
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
		case CHARACTERSTATE:
			if (code == KeyEvent.VK_C) {
				gp.gameState = GameState.PLAYSTATE;
			} else if(code == KeyEvent.VK_ESCAPE) {
				System.out.println("Pressed Escape");
				System.out.println("In Dial Before: gameState: " + gp.gameState + "\tPrev: " + gp.gameStatePrev);
				gp.gameStatePrev = gp.gameState;
				System.out.println("In Dial Before: gameState: " + gp.gameState + "\tPrev: " + gp.gameStatePrev);
				gp.gameState = GameState.PAUSESTATE;
				System.out.println("In Dial Before: gameState: " + gp.gameState + "\tPrev: " + gp.gameStatePrev);
			}
			if(code == KeyEvent.VK_W) { 
				if(gp.ui.slotRow != 0) {
					gp.ui.slotRow--; gp.playSE(10);} 
				}
			if(code == KeyEvent.VK_S) {	
				if(gp.ui.slotRow != gp.ui.slotRowMax-1) {
					gp.ui.slotRow++; gp.playSE(10);} 
				}
			if(code == KeyEvent.VK_A) {	
				if(gp.ui.slotCol != 0) {
					gp.ui.slotCol--; gp.playSE(10);} 
				}
			if(code == KeyEvent.VK_D) {	
				if(gp.ui.slotCol != gp.ui.slotColMax-1) {
					gp.ui.slotCol++; gp.playSE(10);
				}
			}
			if(code == KeyEvent.VK_ENTER) {	
				gp.player.equipItem();
			}
			break;
		case DEADSTATE:
			if(code == KeyEvent.VK_ENTER) {	
				
				gp.gameThread.stop();
				System.exit(0);
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
		}  if(code == KeyEvent.VK_S) {
			downPressed = false;
		}  if(code == KeyEvent.VK_A) {
			ltPressed = false;
		}  if(code == KeyEvent.VK_D) {
			rtPressed = false;
		}  if(code == KeyEvent.VK_ENTER) {
			enterPressed = false;
		}
	}

}
