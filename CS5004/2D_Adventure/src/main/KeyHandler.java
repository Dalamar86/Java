package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import enums.GameState;

/**
 * This class controls our keyboard input.
 * @author Robert Wilson
 *
 */
public class KeyHandler implements KeyListener {

	GamePanel gp;
	private boolean upPressed, downPressed, ltPressed, rtPressed, enterPressed;
	
	// Debug
	private boolean showDebugText = false;
	
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if(isShowDebugText()) {
			if(code == KeyEvent.VK_R ) {
				gp.tileM.loadMap("/maps/worldV2.txt");
			}
		}
		
		switch(gp.getGameState()) {
		case TITLESTATE:
			if(gp.ui.getTitleScreenState() == 0) {
				if(code == KeyEvent.VK_W ||  code == KeyEvent.VK_KP_UP) {
					gp.ui.setCommandNum(gp.ui.getCommandNum() - 1);
					if(gp.ui.getCommandNum() < 0) {
						gp.ui.setCommandNum(2);
					}
				} else if (code == KeyEvent.VK_S || code == KeyEvent.VK_KP_DOWN) {
					gp.ui.setCommandNum(gp.ui.getCommandNum() + 1);
					gp.ui.setCommandNum(gp.ui.getCommandNum() % 3);
				} else if(code == KeyEvent.VK_ENTER) {
					switch(gp.ui.getCommandNum()) {
					case 0:
						gp.ui.setTitleScreenState(1);
						break;
					case 1:
						// TODO add Load method
						gp.setGameState(GameState.PLAYSTATE);
						break;
					case 2:
						gp.endGameThread();
						System.exit(0);
						break;
					}
				}
			} else if(gp.ui.getTitleScreenState() == 1) {
				if(code == KeyEvent.VK_W ||  code == KeyEvent.VK_KP_UP) {
					gp.ui.setCommandNum(gp.ui.getCommandNum() - 1);
					if(gp.ui.getCommandNum() < 0) {
						gp.ui.setCommandNum(3);
					}
				} else if (code == KeyEvent.VK_S || code == KeyEvent.VK_KP_DOWN) {
					gp.ui.setCommandNum(gp.ui.getCommandNum() + 1);
					gp.ui.setCommandNum(gp.ui.getCommandNum() % 4);
				} else if(code == KeyEvent.VK_ENTER) {
					switch(gp.ui.getCommandNum()) {
					case 0:
						// TODO add fighter stats
						
						//gp.player = new Player(gp, this);
						//break;
					case 1:
						// TODO add sorcerer stats
						//break;
					case 2:
						// TODO add rogue stats
						gp.setGameState(GameState.PLAYSTATE);
						break;
					case 3:
						gp.ui.setTitleScreenState(0);
						gp.ui.setCommandNum(0);
						break;
					}
				}
			}
			break;
		
		case PLAYSTATE:
			if(code == KeyEvent.VK_W) {
				setUpPressed(true);
			} else if(code == KeyEvent.VK_S) {
				setDownPressed(true);
			} else if(code == KeyEvent.VK_A) {
				setLtPressed(true);
			} else if(code == KeyEvent.VK_D) {
				setRtPressed(true);
			} else if (code == KeyEvent.VK_C) {
				gp.setGameState(GameState.CHARACTERSTATE);
			} else if(code == KeyEvent.VK_T) {
				if(isShowDebugText()) {
					setShowDebugText(false);
				} else {
					setShowDebugText(true);
				}
			} else if(code == KeyEvent.VK_ESCAPE) {
				gp.setPrevState();
				gp.setGameState(GameState.PAUSESTATE);
				
			} else if(code == KeyEvent.VK_ENTER) {
				setEnterPressed(true);
			}
			break;
			
		case PAUSESTATE:
			if(code == KeyEvent.VK_ESCAPE) {
				gp.setGameState(gp.getPrevSate());
			}
			break;
			
		case DIALOGUESTATE:
			if(code == KeyEvent.VK_ENTER) {
				gp.setGameState(GameState.PLAYSTATE);
			} else if(code == KeyEvent.VK_ESCAPE) {
				gp.setPrevState();
				gp.setGameState(GameState.PAUSESTATE);
			}
			break;
		case CHARACTERSTATE:
			if (code == KeyEvent.VK_C) {
				gp.setGameState(GameState.PLAYSTATE);
			} else if(code == KeyEvent.VK_ESCAPE) {
				gp.setPrevState();
				gp.setGameState(GameState.PAUSESTATE);
			}
			if(code == KeyEvent.VK_W) { 
				if(gp.ui.getSlotRow() != 0) {
					gp.ui.setSlotRow(gp.ui.getSlotRow() - 1); gp.playSE(10);} 
				}
			if(code == KeyEvent.VK_S) {	
				if(gp.ui.getSlotRow() != gp.ui.getSlotRowMax()-1) {
					gp.ui.setSlotRow(gp.ui.getSlotRow() + 1); gp.playSE(10);} 
				}
			if(code == KeyEvent.VK_A) {	
				if(gp.ui.getSlotCol() != 0) {
					gp.ui.setSlotCol(gp.ui.getSlotCol() - 1); gp.playSE(10);} 
				}
			if(code == KeyEvent.VK_D) {	
				if(gp.ui.getSlotCol() != gp.ui.getSlotColMax()-1) {
					gp.ui.setSlotCol(gp.ui.getSlotCol() + 1); gp.playSE(10);
				}
			}
			if(code == KeyEvent.VK_ENTER) {	
				gp.player.equipItem();
			}
			break;
		case DEADSTATE:
			if(code == KeyEvent.VK_ENTER) {	
				gp.endGameThread();
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
			setUpPressed(false);
		}  if(code == KeyEvent.VK_S) {
			setDownPressed(false);
		}  if(code == KeyEvent.VK_A) {
			setLtPressed(false);
		}  if(code == KeyEvent.VK_D) {
			setRtPressed(false);
		}  if(code == KeyEvent.VK_ENTER) {
			setEnterPressed(false);
		}
	}

	public boolean isEnterPressed() {
		return enterPressed;
	}

	public void setEnterPressed(boolean enterPressed) {
		this.enterPressed = enterPressed;
	}

	public boolean isUpPressed() {
		return upPressed;
	}

	public void setUpPressed(boolean upPressed) {
		this.upPressed = upPressed;
	}

	public boolean isDownPressed() {
		return downPressed;
	}

	public void setDownPressed(boolean downPressed) {
		this.downPressed = downPressed;
	}

	public boolean isLtPressed() {
		return ltPressed;
	}

	public void setLtPressed(boolean ltPressed) {
		this.ltPressed = ltPressed;
	}

	public boolean isRtPressed() {
		return rtPressed;
	}

	public void setRtPressed(boolean rtPressed) {
		this.rtPressed = rtPressed;
	}

	public boolean isShowDebugText() {
		return showDebugText;
	}

	public void setShowDebugText(boolean showDebugText) {
		this.showDebugText = showDebugText;
	}

}
