package gameobject;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.lang.Math;

import enums.ObjectType;
import main.*;
import projectiles.SuperProjectile;

/**
 * This stores variables and methods which will be used in player, monster, NPC, 
 * objects, and projectiles.  
 * 
 * @author Robert Wilson
 *
 */
public abstract class GameObject implements GameObjectInt {
	
	//Setup
	protected GamePanel gp;
	private ObjectType type;
	
	// State
	private int worldX;
	private int worldY;
	private Rectangle attackArea = new Rectangle(0, 0, 0, 0);
	private Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	private int solidAreaDefaultX;
	private int solidAreaDefaultY;
	
	private String direction = "";
	protected int spriteNum = 1;
	private boolean collisionOn = false;
	protected boolean invincible = false;
	private boolean attacking = false;
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
	private SuperProjectile projectile;
	
	// Shared stats
	protected int maxLife;
	protected int life;
	private int strength;
	private int dexterity;
	protected int maxMana;
	private int mana;
	protected int attack;
	protected int attackBonus;
	protected int defense;
	private int speed;
	private int attackSpeed;
	
	// Object stats
	private BufferedImage image1;
	private BufferedImage image2;
	private BufferedImage image3;
	private String name;
	private boolean collision = false;
	private int attackValue;
	private int defenseValue;
	private String description = "";
	protected int useCost;
	
	// Counters
	protected int spriteCounter = 0;
	protected int actionTimeCounter = 0;
	protected int invincibleCounter = 0;
	protected int dyingCounter = 0;
	protected int attackingSpeedCounter = 0;
	
	// Describes an image with an accessible buffer of image data.
	public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;
	public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
	protected double scale = 1;
	
	/**
	 * Super constructor of the different children classes
	 * 
	 * @param gp Current Game Panel
	 */
	public GameObject(GamePanel gp) {
		this.gp = gp;
	}
	
	//#####################################################################
	// 									Setup
	//#####################################################################
	
	@Override
	public BufferedImage setup(String imagePath) {
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
			image = scaleImage(image, gp.tileSize, gp.tileSize);
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	
	/**
	 * Buffer the current object's  image and scale it to the given parameters.
	 * 
	 * @param imagePath (String) The path of the image to be buffered
	 * @param width (int) width to scale the image at
	 * @param height (int) height to scale the image at
	 * 
	 * @return image (BufferedImage) scaled image
	 */
	public BufferedImage setup(String imagePath, int width, int height) {
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
			image = scaleImage(image, width, height);
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	
	/**
	 * Scales the given image to the specified width and height.
	 * 
	 * @param original (BufferedImage) Original image to scale
	 * @param width (int) width to scale the image at
	 * @param height (int) height to scale the image at
	 * 
	 * @return image (BufferedImage) scaled image
	 */
	public BufferedImage scaleImage(BufferedImage original, int width, int height) {
		
		BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
		Graphics2D g2 = scaledImage.createGraphics();
		g2.drawImage(original, 0, 0, width, height, null);
		g2.dispose();

		return scaledImage;
	}
	
	//#####################################################################
	// 							Update and Draw
	//#####################################################################
	
	@Override
	public void update() {
		// call the idle action of the object
		setAction();
		
		// Check if the object will collide with anything
		setCollisionOn(false);
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkEntity(this, gp.npc);
		gp.cChecker.checkEntity(this, gp.monster);
		gp.cChecker.checkPlayer(this);
		
		// if collision is false, player can move
		if(isCollisionOn() == false) {
			switch(getDirection()) {
			case "up":		setWorldY(getWorldY() - getSpeed()); break;
			case "down":	setWorldY(getWorldY() + getSpeed()); break;
			case "left":	setWorldX(getWorldX() - getSpeed()); break;
			case "right":	setWorldX(getWorldX() + getSpeed()); break;
			}
		}
		
		// Update the sprite
		spriteCounter++;
		if(spriteCounter > 12) {
			if(spriteNum == 1) {
				spriteNum = 2;
			} else {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
	}
	
	
	@Override
	public void draw(Graphics2D g2) {
		
		// Find the objects position on the map relative to the player
		BufferedImage image = null;
		int screenX = getWorldX() - gp.player.getWorldX() + gp.player.screenX;
		int screenY = getWorldY() - gp.player.getWorldY() + gp.player.screenY;
		
		// if the object is on the player screen, then draw it, otherwise don't
		if(getWorldX() + gp.tileSize > gp.player.getWorldX() - gp.player.screenX &&
		   getWorldX() - gp.tileSize < gp.player.getWorldX() + gp.player.screenX && 
		   getWorldY() + gp.tileSize > gp.player.getWorldY() - gp.player.screenY && 
		   getWorldY() - gp.tileSize < gp.player.getWorldY() + gp.player.screenY) {
			
			// Find the direction of the object and draw the appropriate animation
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
				// if the monster is dying then change the attack to zero so the hero 
				// won't get hurt if it contacts it
				if(isDying()) { 
					setAttack(0);
					dyingAnimation(g2);
				}
			}
			
			// If the object is invincible then change the transparency 
			if(invincible) {
				changeAlpha(g2, 0.4f);
			}

			// Draw the object
			g2.drawImage(image, screenX, screenY, (int) (gp.tileSize*scale), (int) (gp.tileSize*scale), null);
			
			// Reset alpha
			changeAlpha(g2, 1f);
		}
	}
	
	/**
	 * Controls the death animation of this object
	 * 
	 * @param g2 (Graphics2D) the render object
	 */
	public void dyingAnimation(Graphics2D g2) {
		// Increment the dying counter
		dyingCounter++;
		
		// Starting at five animate the object
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
			// finally the object is dead
			dying = false;
			alive = false;
		}
	}
	
	/**
	 * Change the transparency of the current object
	 * 
	 * @param g2 (Graphics2D) the current rendering object
	 * @param alphaValue (float) the percentage transparency
	 */
	public void changeAlpha(Graphics2D g2, float alphaValue) {
		
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
	}
	
	//#####################################################################
	// 								Components
	//#####################################################################
	
	@Override
	public void setAction() {}
	
	@Override
	public void damageReaction() {}
	
	@Override
	public int takeDamage(int damage) {return 0;}
	
	@Override
	public void attack() {}
	
	@Override
	public void use(GameObject gameObject) {}
	
	@Override
	public void speak() {
		// if there is no speech queued up then set it to zero 
		if(dialogues[dialogueIndex] == null) {
			dialogueIndex = 0;
		}
		
		// Call UI's speech visualization method
		gp.ui.setCurrentDialogue(dialogues[dialogueIndex]);
		
		// increment the counter for how long the message will be displayed 
		dialogueIndex++;
		
		// Make the object face the player when speaking to them
		switch(gp.player.getDirection()) {
		case "up": setDirection("down"); break;
		case "down": setDirection("up"); break;
		case "upleft": case "downleft":
		case "left": setDirection("right"); break;
		case "upright": case "downright":
		case "right": setDirection("left"); break;
		}
	}	
	
	@Override
	public void findAide() {};
	
	@Override
	public int getDistance(GameObject gameObject) {
		// return a relative the distance to the player
		return (int) Math.sqrt(Math.abs(gameObject.getWorldX()-worldX) + Math.abs(gameObject.getWorldY()-worldY));
	}
	
	@Override
	public String getDirectionTo(GameObject gameObject) {
		double xDir = worldX - gameObject.getWorldX();
		double yDir = worldY - gameObject.getWorldY();
		double angle = Math.toDegrees(Math.atan2(yDir, xDir));
		String direction = "down";
		
		if(angle <= -135 || angle >= 135) {
			direction = "right";
		} else if(angle < -45  && angle > -135) { 
			direction = "down";
		} else if(angle >= -45  && angle <= 45) {
			direction = "left";
		} else if(angle > 45  && angle < 135) {
			direction = "up";
		} 
		return direction;
	}
	
	//#####################################################################
	// 							Getters and Setters
	//#####################################################################
	
	@Override
	public int getMaxLife() {
		return maxLife;
	}

	/**
	 * Set's the max life of the current object
	 * 
	 * @param maxLife (int) amount to set the max life to
	 */
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

	/**
	 * Set the type of the object
	 * 
	 * @param type (ObjectType) the object type to set the current object to
	 */
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

	@Override
	public int getWorldX() {
		return worldX;
	}

	@Override
	public void setWorldX(int worldX) {
		this.worldX = worldX;
	}

	@Override
	public int getWorldY() {
		return worldY;
	}

	@Override
	public void setWorldY(int worldY) {
		this.worldY = worldY;
	}

	@Override
	public Rectangle getSolidArea() {
		return solidArea;
	}

	@Override
	public void setSolidArea(Rectangle solidArea) {
		this.solidArea = solidArea;
	}

	@Override
	public int getSolidAreaDefaultX() {
		return solidAreaDefaultX;
	}

	@Override
	public void setSolidAreaDefaultX(int solidAreaDefaultX) {
		this.solidAreaDefaultX = solidAreaDefaultX;
	}

	@Override
	public int getSolidAreaDefaultY() {
		return solidAreaDefaultY;
	}

	@Override
	public void setSolidAreaDefaultY(int solidAreaDefaultY) {
		this.solidAreaDefaultY = solidAreaDefaultY;
	}

	@Override
	public Rectangle getAttackArea() {
		return attackArea;
	}

	@Override
	public void setAttackArea(Rectangle attackArea) {
		this.attackArea = attackArea;
	}

	@Override
	public boolean isAttacking() {
		return attacking;
	}

	@Override
	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}

	@Override
	public SuperProjectile getProjectile() {
		return projectile;
	}

	@Override
	public void setProjectile(SuperProjectile projectile) {
		this.projectile = projectile;
	}

	@Override
	public int getUseCost() {
		return useCost;
	}

	@Override
	public void setUseCost(int useCost) {
		this.useCost = useCost;
	}

	@Override
	public int getMana() {
		return mana;
	}

	@Override
	public void setMana(int mana) {
		this.mana = mana;
	}

	@Override
	public int getAttackSpeed() {
		return attackSpeed;
	}

	@Override
	public void setAttackSpeed(int attackSpeed) {
		this.attackSpeed = attackSpeed;
	}
}
