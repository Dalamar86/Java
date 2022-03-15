package entity;


import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.*;

/**
 * 
 * @author Robert Wilson
 *
 */
public class Player extends Entity{
	
	GamePanel gp;
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	//public int hasKey = 0;
	int standCounter = 0;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		this.gp = gp;
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
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		speed = 4;
		speeddiag = speed - 1;
		direction = "down";
	}
	
	public void getPlayerImage() {		
		up1 = setup("boy_up_1");
		up2 = setup("boy_up_2");
		down1 = setup("boy_down_1");
		down2 = setup("boy_down_2");
		left1 = setup("boy_left_1");
		left2 = setup("boy_left_2");
		right1 = setup("boy_right_1");
		right2 = setup("boy_right_2");
	}
	
	public BufferedImage setup(String imageName) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/player/" + imageName + ".png"));
			image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	
	public void update() {
		
		if(keyH.upPressed == true || keyH.downPressed == true || keyH.ltPressed == true || keyH.rtPressed == true ) {
			
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
			}
			
			// Check tile collision
			collisionOn = false;
			//gp.cChecker.checkTile(this);
			
			// Check object collision
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);

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
				if(collisionOn == false) {
					worldX -= speed;
				} else {				
					direction = "up";
					collisionOn = false;
					gp.cChecker.checkTile(this);
					gp.cChecker.checkObject(this, true);
					if(collisionOn == false) {
						worldY -= speed;
					}
				}
			} else if(collisionOn == true && direction == "uprt") {
				direction = "right";
				collisionOn = false;
				gp.cChecker.checkTile(this);
				gp.cChecker.checkObject(this, true);
				if(collisionOn == false) {
					worldX += speed;
				} else {				
					direction = "up";
					collisionOn = false;
					gp.cChecker.checkTile(this);
					gp.cChecker.checkObject(this, true);
					if(collisionOn == false) {
						worldY -= speed;
					}
				}
			} else if(collisionOn == true && direction == "downlt") {
				direction = "left";
				collisionOn = false;
				gp.cChecker.checkTile(this);
				gp.cChecker.checkObject(this, true);
				if(collisionOn == false) {
					worldX -= speed;
				} else {
					direction = "down";
					collisionOn = false;
					gp.cChecker.checkTile(this);
					gp.cChecker.checkObject(this, true);
					if(collisionOn == false) {
						worldY += speed;
					}
				}
			} else if(collisionOn == true && direction == "downrt") {
				direction = "right";
				collisionOn = false;
				gp.cChecker.checkTile(this);
				gp.cChecker.checkObject(this, true);
				if(collisionOn == false) {
					worldX += speed;
				} else {
					direction = "down";
					collisionOn = false;
					gp.cChecker.checkTile(this);
					gp.cChecker.checkObject(this, true);
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
			
		}
	}
	
	public void pickUpObject(int index) {
		if(index != 999) {
			
			/*
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
			*/
		}
	}
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if(spriteNum == 1) {
				image = up1;
			} else {
				image = up2;
			}
			break;
		case "downlt":
		case "uplt":
			if(spriteNum == 1) {
				image = left1;
			} else {
				image = left2;
			}
			break;
		case "downrt":
		case "uprt":
			if(spriteNum == 1) {
				image = right1;
			} else {
				image = right2;
			}
			break;
		case "down":
			if(spriteNum == 1) {
				image = down1;
			} else {
				image = down2;
			}
			break;
		case "left":
			if(spriteNum == 1) {
				image = left1;
			} else {
				image = left2;
			}
			break;
		case "right":
			if(spriteNum == 1) {
				image = right1;
			} else {
				image = right2;
			}
			break;
		}
		int x = screenX;
		int y = screenY;
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
		
		g2.drawImage(image,  x,  y, null);
	}
}
