package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import enums.GameState;

/**
 * Keyboard action listener.
 * 
 * @author Robert Wilson
 *
 */
public class KeyHandler implements KeyListener {
	// Current game panel
	GamePanel gp;
	
	// Button pressed booleans, true if pressed
	private boolean upPressed, downPressed, ltPressed, rtPressed, enterPressed, spacePressed;
	
	// Debug
	private boolean showDebugText = false;
	
	/**
	 * Creates an instance of the key Handler.
	 * 
	 * @param gp (GamePanel) Current game panel
	 */
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
			} else if(code == KeyEvent.VK_SPACE) {
				setSpacePressed(true);
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
		} if(code == KeyEvent.VK_SPACE) {
			setSpacePressed(false);
		} 
	}

	//#####################################################################
	// 							Getters and Setters
	//#####################################################################
	
	/**
	 * Returns true if the enter button was pressed
	 * 
	 * @return enterPressed (boolean)
	 */
	public boolean isEnterPressed() {
		return enterPressed;
	}

	/**
	 * Sets the enterPressed boolean to parameter.
	 * 
	 * @param enterPressed (boolean)
	 */
	public void setEnterPressed(boolean enterPressed) {
		this.enterPressed = enterPressed;
	}
	
	/**
	 * Returns true if the space button was pressed
	 * 
	 * @return spacePressed (boolean)
	 */
	public boolean isSpacePressed() {
		return spacePressed;
	}

	/**
	 * Sets the spacePressed boolean to parameter.
	 * 
	 * @param spacePressed (boolean)
	 */
	public void setSpacePressed(boolean spacePressed) {
		this.spacePressed = spacePressed;
	}

	/**
	 * Returns true if the up button was pressed
	 * 
	 * @return upPressed (boolean)
	 */
	public boolean isUpPressed() {
		return upPressed;
	}

	/**
	 * Sets the upPressed boolean to parameter.
	 * 
	 * @param upPressed (boolean)
	 */
	public void setUpPressed(boolean upPressed) {
		this.upPressed = upPressed;
	}

	/**
	 * Returns true if the down button was pressed
	 * 
	 * @return downPressed (boolean)
	 */
	public boolean isDownPressed() {
		return downPressed;
	}

	/**
	 * Sets the downPressed boolean to parameter.
	 * 
	 * @param downPressed (boolean)
	 */
	public void setDownPressed(boolean downPressed) {
		this.downPressed = downPressed;
	}

	/**
	 * Returns true if the a button was pressed
	 * 
	 * @return ltPressed (boolean)
	 */
	public boolean isLtPressed() {
		return ltPressed;
	}

	/**
	 * Sets the ltPressed boolean to parameter.
	 * 
	 * @param ltPressed (boolean)
	 */
	public void setLtPressed(boolean ltPressed) {
		this.ltPressed = ltPressed;
	}

	/**
	 * Returns true if the d button was pressed
	 * 
	 * @return rtPressed (boolean)
	 */
	public boolean isRtPressed() {
		return rtPressed;
	}

	/**
	 * Sets the rtPressed boolean to parameter.
	 * 
	 * @param rtPressed (boolean)
	 */
	public void setRtPressed(boolean rtPressed) {
		this.rtPressed = rtPressed;
	}

	/**
	 * Returns true if the t button was pressed
	 * 
	 * @return showDebugText (boolean)
	 */
	public boolean isShowDebugText() {
		return showDebugText;
	}

	/**
	 * Sets the showDebugText boolean to parameter.
	 * 
	 * @param showDebugText (boolean)
	 */
	public void setShowDebugText(boolean showDebugText) {
		this.showDebugText = showDebugText;
	}

}
