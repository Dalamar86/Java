package gameobject;

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
public abstract class GameObject implements GameObjectInt {
	
	//Setup
	protected GamePanel gp;
	
	private ObjectType type;
	
	// State
	public int worldX, worldY;
	public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public int solidAreaDefaultX, solidAreaDefaultY;
	
	private String direction = "";
	protected int spriteNum = 1;
	private boolean collisionOn = false;
	protected boolean invincible = false;
	protected boolean attacking = false;
	private boolean alive = true;
	private boolean dying = false;
	
	// NPC specific
	protected String dialogues[] = new String[20];
	protected int dialogueIndex = 0;
	
	// Character status
	protected int level;
	private int speeddiag;
	protected int exp;
	protected int nextLevelExp;
	private int coin;
	private GameObject currentWeapon;
	private GameObject currentShield;
	
	// Shared stats
	protected int maxLife;
	protected int life;
	private int strength;
	private int dexterity;
	protected int mana;
	protected int attack;
	protected int attackBonus;
	protected int defense;
	private int speed;
	
	// Object stats
	private BufferedImage image1;
	private BufferedImage image2;
	private BufferedImage image3;
	private String name;
	private boolean collision = false;
	private int attackValue;
	private int defenseValue;
	private String description = "";
	
	// Counters
	protected int spriteCounter = 0;
	protected int actionTimeCounter = 0;
	protected int invincibleCounter = 0;
	protected int dyingCounter = 0;
	
	// Describes an image with an accessible buffer of image data.
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
	
	
	public GameObject(GamePanel gp) {
		this.gp = gp;
	}
	
	//#####################################################################
	// 									Setup
	//#####################################################################
	
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
	
	//#####################################################################
	// 							Update and Draw
	//#####################################################################
	
	public void update() {
		setAction();
		
		setCollisionOn(false);
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkEntity(this, gp.npc);
		gp.cChecker.checkEntity(this, gp.monster);
		boolean contactPlayer = gp.cChecker.checkPlayer(this);
		
		if(this.getType() == ObjectType.MONSTER && contactPlayer == true) {
			gp.player.takeDamage(attack);
		}
		
		// if collision is false, player can move
		if(isCollisionOn() == false) {
			
			switch(getDirection()) {
			case "up":		worldY -= getSpeed(); break;
			case "down":	worldY += getSpeed(); break;
			case "left":	worldX -= getSpeed(); break;
			case "right":	worldX += getSpeed(); break;
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
			
			switch(getDirection()) {
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
				image = getImage1();
				break;	
			}
			
			// Monster HP Bar
			if(getType() == ObjectType.MONSTER) {
				if(life < maxLife && life >= 0) {
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
				attack = 0;
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
			g2.drawImage(getImage1(), screenX, screenY, gp.tileSize, gp.tileSize, null);
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
	
	//#####################################################################
	// 								Components
	//#####################################################################
	
	public void setAction() {}
	
	@Override
	public void damageReaction() {}
	
	@Override
	public int takeDamage(int damage) {return 0;}
	
	@Override
	public int attack() {return 0;}
	
	@Override
	public void use(GameObject gameObject) {}
	
	@Override
	public void speak() {
		if(dialogues[dialogueIndex] == null) {
			dialogueIndex = 0;
		}
		gp.ui.setCurrentDialogue(dialogues[dialogueIndex]);
		dialogueIndex++;
		
		switch(gp.player.getDirection()) {
		case "up": setDirection("down"); break;
		case "down": setDirection("up"); break;
		case "uplt": case "downlt":
		case "left": setDirection("right"); break;
		case "uprt": case "downrt":
		case "right": setDirection("left"); break;
		}
	}	
	
	//#####################################################################
	// 							Getters and Setters
	//#####################################################################
	
	@Override
	public int getMaxLife() {
		return maxLife;
	}

	protected void setMaxLife(int maxLife) {
		this.maxLife = maxLife;
	}

	@Override
	public int getLife() {
		return life;
	}

	@Override
	public void setLife(int life) {
		if(life <= 0) {
			life = 0;
			setDying(true);
		} else if(life > getMaxLife()) {
			life = getMaxLife();
		}
		this.life = life;
	}

	@Override
	public boolean isAlive() {
		return alive;
	}

	@Override
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	@Override
	public boolean isDying() {
		return dying;
	}

	@Override
	public void setDying(boolean dying) {
		this.dying = dying;
	}

	@Override
	public int getLevel() {
		return level;
	}

	@Override
	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public int getStrength() {
		return strength;
	}

	@Override
	public void setStrength(int strength) {
		this.strength = strength;
	}

	@Override
	public int getDexterity() {
		return dexterity;
	}

	@Override
	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
	}

	@Override
	public int getAttack() {
		return attack;
	}

	@Override
	public void setAttack(int attack) {
		this.attack = attack;
	}

	@Override
	public int getDefense() {
		return defense;
	}

	@Override
	public void setDefense(int defense) {
		this.defense = defense;
	}

	@Override
	public int getExp() {
		return exp;
	}

	@Override
	public void setExp(int exp) {
		this.exp = exp;
	}

	@Override
	public int getNextLevelExp() {
		return nextLevelExp;
	}

	@Override
	public void setNextLevelExp(int nextLevelExp) {
		this.nextLevelExp = nextLevelExp;
	}

	@Override
	public int getCoin() {
		return coin;
	}

	@Override
	public void setCoin(int coin) {
		this.coin = coin;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public ObjectType getType() {
		return type;
	}

	protected void setType(ObjectType type) {
		this.type = type;
	}

	@Override
	public String getDirection() {
		return direction;
	}

	@Override
	public void setDirection(String direction) {
		this.direction = direction;
	}

	@Override
	public int getAttackValue() {
		return attackValue;
	}

	@Override
	public void setAttackValue(int attackValue) {
		this.attackValue = attackValue;
	}

	@Override
	public int getDefenseValue() {
		return defenseValue;
	}

	@Override
	public void setDefenseValue(int defenseValue) {
		this.defenseValue = defenseValue;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public BufferedImage getImage1() {
		return image1;
	}

	@Override
	public void setImage1(BufferedImage image1) {
		this.image1 = image1;
	}

	@Override
	public BufferedImage getImage2() {
		return image2;
	}

	@Override
	public void setImage2(BufferedImage image2) {
		this.image2 = image2;
	}

	@Override
	public BufferedImage getImage3() {
		return image3;
	}

	@Override
	public void setImage3(BufferedImage image3) {
		this.image3 = image3;
	}

	@Override
	public GameObject getCurrentWeapon() {
		return currentWeapon;
	}

	@Override
	public void setCurrentWeapon(GameObject currentWeapon) {
		this.currentWeapon = currentWeapon;
	}

	@Override
	public GameObject getCurrentShield() {
		return currentShield;
	}

	@Override
	public void setCurrentShield(GameObject currentShield) {
		this.currentShield = currentShield;
	}

	@Override
	public int getSpeed() {
		return speed;
	}

	@Override
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	@Override
	public int getSpeeddiag() {
		return speeddiag;
	}

	@Override
	public void setSpeeddiag(int speeddiag) {
		this.speeddiag = speeddiag;
	}

	@Override
	public boolean isCollisionOn() {
		return collisionOn;
	}

	@Override
	public void setCollisionOn(boolean collisionOn) {
		this.collisionOn = collisionOn;
	}

	@Override
	public boolean isCollision() {
		return collision;
	}

	@Override
	public boolean setCollision(boolean collision) {
		this.collision = collision;
		return collision;
	}
}
