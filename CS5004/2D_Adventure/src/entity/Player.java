package entity;


import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.*;
import main.GamePanel.GameState;

/**
 * 
 * @author Robert Wilson
 *
 */
public class Player extends Entity{
	
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	private int hasKey = 0;
	int standCounter = 0;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		super(gp);

		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		solidArea = new Rectangle();
		solidArea.x = gp.tileSize/6;
		solidArea.y = gp.tileSize/3;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = (int) (gp.tileSize/1.5);
		solidArea.height = (int) (gp.tileSize/1.5);
		
		attackArea.width = 36;
		attackArea.height = 36;
		
		setDefaultValues();
		getImage();
		getPlayerAttackImage();
	}
	
	public void setDefaultValues() {
		
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		
		type = 0;
		speed = 4;
		speeddiag = speed - 1;
		direction = "down";
		
		// Player status
		setMaxLife(6);
		setLife(getMaxLife());
	}
	
	public void getImage() {		
		up1 = setup("/player/boy_up_1");
		up2 = setup("/player/boy_up_2");
		down1 = setup("/player/boy_down_1");
		down2 = setup("/player/boy_down_2");
		left1 = setup("/player/boy_left_1");
		left2 = setup("/player/boy_left_2");
		right1 = setup("/player/boy_right_1");
		right2 = setup("/player/boy_right_2");
	}
	
	public void getPlayerAttackImage() {
		attackUp1 = setup("/player/boy_attack_up_1", 		gp.tileSize,   gp.tileSize*2);
		attackUp2 = setup("/player/boy_attack_up_2", 		gp.tileSize,   gp.tileSize*2);
		attackDown1 = setup("/player/boy_attack_down_1",	gp.tileSize,   gp.tileSize*2);
		attackDown2 = setup("/player/boy_attack_down_2", 	gp.tileSize,   gp.tileSize*2);
		attackLeft1 = setup("/player/boy_attack_left_1", 	gp.tileSize*2, gp.tileSize);
		attackLeft2 = setup("/player/boy_attack_left_2", 	gp.tileSize*2, gp.tileSize);
		attackRight1 = setup("/player/boy_attack_right_1",	gp.tileSize*2, gp.tileSize);
		attackRight2 = setup("/player/boy_attack_right_2", 	gp.tileSize*2, gp.tileSize);
	}
	
	public void update() {
		
		if(attacking) {
			attacking();
		} else if(keyH.upPressed == true || keyH.downPressed == true || keyH.ltPressed == true || keyH.rtPressed == true || keyH.enterPressed) {
			
			if(keyH.upPressed == true) {
				if(keyH.ltPressed) {
					direction = "uplt";
				} else if (keyH.rtPressed) {
					direction = "uprt";
				} else {
					direction = "up";
				}
			} else if(keyH.downPressed == true) {
				if(keyH.ltPressed) {
					direction = "downlt";
				} else if (keyH.rtPressed) {
					direction = "downrt";
				} else {
					direction = "down";
				}
			} else if(keyH.ltPressed == true) {
				if(keyH.upPressed) {
					direction = "uplt";
				} else if (keyH.downPressed) {
					direction = "downlt";
				} else {
					direction = "left";
				}
			} else if(keyH.rtPressed == true) {
				if(keyH.upPressed) {
					direction = "uprt";
				} else if (keyH.downPressed) {
					direction = "downrt";
				} else {
					direction = "right";
				}
			} else if(keyH.enterPressed) {
				attacking = true;
			}
			
			// Check tile collision
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			// Check object collision
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);
			
			// Check NPC collision
			int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
			interactNPC(npcIndex);

			// Check monster collision
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			contactMonster(monsterIndex);
			
			// Check events
			gp.eHandler.checkEvent();
			
			// if collision is False, Player can move
			if(collisionOn == false) {
	
				switch(direction) {
				case "up":
					worldY -= speed;
					break;
				case "uplt":
					worldY -= speeddiag;
					worldX -= speeddiag;
					break;
				case "uprt":
					worldY -= speeddiag;
					worldX += speeddiag;
					break;
				case "down":
					worldY += speed;
					break;
				case "downlt":
					worldY += speeddiag;
					worldX -= speeddiag;
					break;
				case "downrt":
					worldY += speeddiag;
					worldX += speeddiag;
					break;
				case "left":
					worldX -= speed;
					break;
				case "right":
					worldX += speed;
					break;
				}
				
			} else if(collisionOn == true && direction == "uplt") {
				direction = "left";
				collisionOn = false;
				gp.cChecker.checkTile(this);
				gp.cChecker.checkObject(this, true);
				gp.cChecker.checkEntity(this, gp.npc);
				gp.cChecker.checkEntity(this, gp.monster);
				if(collisionOn == false) {
					worldX -= speed;
				} else {				
					direction = "up";
					collisionOn = false;
					gp.cChecker.checkTile(this);
					gp.cChecker.checkObject(this, true);
					gp.cChecker.checkEntity(this, gp.npc);
					gp.cChecker.checkEntity(this, gp.monster);
					if(collisionOn == false) {
						worldY -= speed;
					}
				}
			} else if(collisionOn == true && direction == "uprt") {
				direction = "right";
				collisionOn = false;
				gp.cChecker.checkTile(this);
				gp.cChecker.checkObject(this, true);
				gp.cChecker.checkEntity(this, gp.npc);
				gp.cChecker.checkEntity(this, gp.monster);
				if(collisionOn == false) {
					worldX += speed;
				} else {				
					direction = "up";
					collisionOn = false;
					gp.cChecker.checkTile(this);
					gp.cChecker.checkObject(this, true);
					gp.cChecker.checkEntity(this, gp.npc);
					gp.cChecker.checkEntity(this, gp.monster);
					if(collisionOn == false) {
						worldY -= speed;
					}
				}
			} else if(collisionOn == true && direction == "downlt") {
				direction = "left";
				collisionOn = false;
				gp.cChecker.checkTile(this);
				gp.cChecker.checkObject(this, true);
				gp.cChecker.checkEntity(this, gp.npc);
				gp.cChecker.checkEntity(this, gp.monster);
				if(collisionOn == false) {
					worldX -= speed;
				} else {
					direction = "down";
					collisionOn = false;
					gp.cChecker.checkTile(this);
					gp.cChecker.checkObject(this, true);
					gp.cChecker.checkEntity(this, gp.npc);
					gp.cChecker.checkEntity(this, gp.monster);
					if(collisionOn == false) {
						worldY += speed;
					}
				}
			} else if(collisionOn == true && direction == "downrt") {
				direction = "right";
				collisionOn = false;
				gp.cChecker.checkTile(this);
				gp.cChecker.checkObject(this, true);
				gp.cChecker.checkEntity(this, gp.npc);
				gp.cChecker.checkEntity(this, gp.monster);
				if(collisionOn == false) {
					worldX += speed;
				} else {
					direction = "down";
					collisionOn = false;
					gp.cChecker.checkTile(this);
					gp.cChecker.checkObject(this, true);
					gp.cChecker.checkEntity(this, gp.npc);
					gp.cChecker.checkEntity(this, gp.monster);
					if(collisionOn == false) {
						worldY += speed;
					}
				}
			}
			
			
			spriteCounter++;
			if(spriteCounter > 12) {
				if(spriteNum == 1) {
					spriteNum = 2;
				} else {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
			
		} else {
			
			standCounter++;
			if(standCounter == 20) {
				spriteNum = 1;
				standCounter = 0;
			}
			
			if(invincible) {
				invincibleCounter++;
				if(invincibleCounter > 60) {
					invincible = false;
					invincibleCounter = 0;
				}
			}		
		}
	}
	
	public void attacking() {
		spriteCounter++;
		
		if(spriteCounter <= 5) {
			spriteNum = 1;
		} else if(spriteCounter > 5 && spriteCounter <= 25) {
			spriteNum = 2;
			
			// save current worldX, worldY, solidArea
			int currentWorldX = worldX;
			int currentWorldY = worldY;
			int solidAreaWidth = solidArea.width;
			int solidAreaHeight = solidArea.height;
			
			// Adjust player's worldX/Y for the attackArea
			switch(direction) {
			case "up": worldY -= attackArea.height; break;
			case "down": worldY += attackArea.height; break;
			case "left": worldX -= attackArea.width; break;
			case "right": worldX += attackArea.width; break;
			}
			
			
			// Attack area becomes solid area
			solidArea.width = attackArea.width;
			solidArea.height = attackArea.height;
			
			// Check collision of sword and monster
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			
			worldX = currentWorldX;
			worldY = currentWorldY;
			solidArea.width = solidAreaWidth;
			solidArea.height = solidAreaHeight;
			damageMonster(monsterIndex);
			
		} else {
			spriteNum = 1;
			spriteCounter = 0;
			attacking = false;
		}
	}
	
	public void pickUpObject(int index) {
		if(index != 999) {
			
			
			String objectName = gp.obj[index].name;
			
			switch(objectName) {
			case "Key":
				gp.playSE(1);
				hasKey++;
				gp.obj[index] = null;
				gp.ui.showMessage("You got a key!");
				break;
			case "Door":
				if(hasKey > 0) {
					gp.playSE(3);
					gp.obj[index] = null;
					hasKey--;
					gp.ui.showMessage("Door Unlocked!");
				} else {
					gp.ui.showMessage("Door Locked!");
				}
				break;
			case "Boots":
				gp.playSE(2);
				speed += 2;
				gp.obj[index] = null;
				gp.ui.showMessage("Speed boost");
				break;
			case "Chest":
				gp.ui.levelFinished = true;
				gp.stopMusic();
				gp.playSE(4);
				break;
			}
			
		}
	}
	
	public void interactNPC(int index) {
		if(index != 999) {
			gp.gameState = GameState.DIALOGUESTATE;
			gp.npc[index].speak();
		}
	}
	
	public void contactMonster(int i) {
		if(i != 999) {
			if(!invincible ) {
				life--;
				invincible = true;
			}
		}
	}
	
	public void damageMonster(int index) {
		if(index != 999) {
			if(!gp.monster[index].invincible) {
				gp.monster[index].life--;
				gp.monster[index].invincible = true;
				
				if(gp.monster[index].life <= 0) {
					gp.monster[index] = null;
				}
			}
		}
	}
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		int tempScreenX = screenX;
		int tempScreenY = screenY;
		
		int x = tempScreenX;
		int y = tempScreenY;
		int rightOffset = gp.screenWidth - screenX;
		int bottomOffset = gp.screenHeight - screenY;
		
		if(screenX > worldX) {
			x = worldX;
		} if(screenY > worldY) {
			y = worldY;
		} if(rightOffset > gp.worldWidth -worldX) {
			x = gp.screenWidth - (gp.worldWidth - worldX);
		} if(bottomOffset > gp.worldHeight - worldY) {
			y = gp.screenHeight - (gp.worldHeight - worldY);
		}
		
		switch(direction) {
		case "up":
			if(!attacking) {
				if(spriteNum == 1) {image = up1;} 
				else {image = up2;}
			} else {
				y -= gp.tileSize;
				if(spriteNum == 1) {image = attackUp1;} 
				else {image = attackUp2;}
			}
			break;
		case "downlt":
		case "uplt":
			if(!attacking) {
				if(spriteNum == 1) {image = left1;} 
				else {image = left2;}
			} else {
				x -= gp.tileSize;
				if(spriteNum == 1) {image = attackLeft1;} 
				else {image = attackLeft2;}
			}
			break;
		case "downrt":
		case "uprt":
			if(!attacking) {
				if(spriteNum == 1) {image = right1;} 
				else {image = right2;}
			} else {
				if(spriteNum == 1) {image = attackRight1;} 
				else {image = attackRight2;}
			}
			break;
		case "down":
			if(!attacking) {
				if(spriteNum == 1) {image = down1;} 
				else {image = down2;}
			} else {
				if(spriteNum == 1) {image = attackDown1;} 
				else {image = attackDown2;}
			}
			break;
		case "left":
			if(!attacking) {
				if(spriteNum == 1) {image = left1;} 
				else {image = left2;}
			} else {
				x -= gp.tileSize;
				if(spriteNum == 1) {image = attackLeft1;} 
				else {image = attackLeft2;}
			}
			break;
		case "right":
			if(!attacking) {
				if(spriteNum == 1) {image = right1;} 
				else {image = right2;}
			} else {
				if(spriteNum == 1) {image = attackRight1;} 
				else {image = attackRight2;}
			}
			break;
		}
		
		if(invincible) {
			if(invincibleCounter%20 == 0) {
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1F));
			} else if(invincibleCounter%10 == 0) {
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3F));
			}
		}
		
		g2.drawImage(image,  x,  y, null);
		
		
		// Reset alpha
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F));
	}
}
