package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.*;

/**
 * This stores variables that will be used in player, monster, and NPC classes
 * @author Robert Wilson
 *
 */
public abstract class Entity implements EntityInt {
	
	//Setup
	protected GamePanel gp;
	
	// State
	public int worldX, worldY;
	public String direction = "";
	public int spriteNum = 1;
	public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
	public boolean invincible = false;
	boolean attacking = false;
	private boolean alive = true;
	private boolean dying = false;
	
	// NPC specific
	String dialogues[] = new String[20];
	int dialogueIndex = 0;
	
	// Character status
	protected int maxLife;
	protected int life;
	public int speed;
	public int speeddiag;
	protected int type; 
	
	// Object stats
	public BufferedImage image1, image2, image3;
	public String name;
	public boolean collision = false;
	
	// Counters
	public int spriteCounter = 0;
	protected int actionTimeCounter = 0;
	public int invincibleCounter = 0;
	int dyingCounter = 0;
	
	// Describes an image with an accessible buffer of image data.
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
	
	
	public Entity(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setAction() {}
	public void damageReaction() {}
	public void speak() {
		if(dialogues[dialogueIndex] == null) {
			dialogueIndex = 0;
		}
		gp.ui.currentDialogue = dialogues[dialogueIndex];
		dialogueIndex++;
		
		switch(gp.player.direction) {
		case "up": direction = "down"; break;
		case "down": direction = "up"; break;
		case "uplt": case "downlt":
		case "left": direction = "right"; break;
		case "uprt": case "downrt":
		case "right": direction = "left"; break;
		}
	}
	
	public void update() {
		setAction();
		
		collisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkEntity(this, gp.npc);
		gp.cChecker.checkEntity(this, gp.monster);
		boolean contactPlayer = gp.cChecker.checkPlayer(this);
		
		if(this.type == 2 && contactPlayer == true) {
			if(!gp.player.invincible) {
				gp.playSE(6);
				gp.player.life--;
				gp.player.invincible = true;
			}
		}
		// if collision is false, player can move
		if(collisionOn == false) {
			
			switch(direction) {
			case "up":		worldY -= speed; break;
			case "down":	worldY += speed; break;
			case "left":	worldX -= speed; break;
			case "right":	worldX += speed; break;
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
		
		if(invincible) {
			invincibleCounter++;
			if(invincibleCounter > 30) {
				invincible = false;
				invincibleCounter = 0;
			}
		}	
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
		   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
		   worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && 
		   worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
			
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
			default:
				image = image1;
				break;	
			}
			
			// Monster Hp Bar
			if(type == 2) {
				if(life < maxLife) {
					double oneScale = (double)gp.tileSize/maxLife;
					double hpBarValue = oneScale*life;
					
					g2.setColor(new Color(35, 35, 35));
					g2.fillRect(screenX - 1, screenY - 16, gp.tileSize + 2, 12);
					
					
					g2.setColor(new Color(255, 0, 30));
					g2.fillRect(screenX, screenY - 15, (int)hpBarValue, 10);	
				}
			}
			
			
			if(invincible) {
				changeAlpha(g2, 0.4f);
				//g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4F));
			}
			if(isDying()) {
				dyingAnimation(g2);
			}
				
			g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
			
			// Reset alpha
			changeAlpha(g2, 1f);
			//g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F));
		}
	}
	
	public void draw(Graphics2D g2, GamePanel gp) {
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
		   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
		   worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && 
		   worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
			g2.drawImage(image1, screenX, screenY, gp.tileSize, gp.tileSize, null);
		}
	}
	
	public void dyingAnimation(Graphics2D g2) {
		
		dyingCounter++;
		
		int i = 5;
		
		if(dyingCounter <= i) {changeAlpha(g2, 0f);}
		if(dyingCounter > i && dyingCounter <= i*2) {changeAlpha(g2, 1f);}
		if(dyingCounter > i*2 && dyingCounter <= i*3) {changeAlpha(g2, 0f);}
		if(dyingCounter > i*3 && dyingCounter <= i*4) {changeAlpha(g2, 1f);}
		if(dyingCounter > i*4 && dyingCounter <= i*5) {changeAlpha(g2, 0f);}
		if(dyingCounter > i*5 && dyingCounter <= i*6) {changeAlpha(g2, 1f);}
		if(dyingCounter > i*6 && dyingCounter <= i*7) {changeAlpha(g2, 0f);}
		if(dyingCounter > i*7 && dyingCounter <= i*8) {changeAlpha(g2, 1f);}
		if(dyingCounter > i*8) {
			dying = false;
			alive = false;
		}
	}
	
	public void changeAlpha(Graphics2D g2, float alphaValue) {
		
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
	}
	
	public BufferedImage setup(String imagePath) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
			image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	
	public BufferedImage setup(String imagePath, int width, int height) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
			image = uTool.scaleImage(image, width, height);
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		return image;
	}

	public int getMaxLife() {
		return maxLife;
	}

	protected void setMaxLife(int maxLife) {
		this.maxLife = maxLife;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public boolean isDying() {
		return dying;
	}

	public void setDying(boolean dying) {
		this.dying = dying;
	}
}
