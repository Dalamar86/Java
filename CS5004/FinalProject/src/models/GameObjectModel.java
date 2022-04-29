package models;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.ObjectType;

public class GameObjectModel implements GameObjectModelInt {
	
	protected GamePanel gp;
	
	// State
	public int worldX, worldY;
	private String direction = "";
	public int spriteNum = 1;
	public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public int solidAreaDefaultX, solidAreaDefaultY;
	
	// NPC specific
	protected String dialogues[] = new String[20];
	int dialogueIndex = 0;
	
	// Character status
	protected int level;
	public int speeddiag;
	protected int exp;
	protected int nextLevelExp;
	private int coin;
	public GameObjectModel currentWeapon;
	public GameObjectModel currentShield;
	
	// Shared stats
	protected int maxLife;
	protected int life;
	private int strength;
	private int dexterity;
	protected int mana;
	protected int attack;
	protected int attackBonus;
	protected int defense;
	public int speed;
	
	public GameObjectModel(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setAction() {}
	@Override
	public void damageReaction() {}
	@Override
	public int takeDamage(int damage) {return 0;}
	@Override
	public int attack() {return 0;}
	@Override
	public void use(GameObjectModel gameObject) {}
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
		if(life <= 0) {
			life = 0;
			setDying(true);
		} else if(life > getMaxLife()) {
			life = getMaxLife();
		}
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

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getDexterity() {
		return dexterity;
	}

	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getNextLevelExp() {
		return nextLevelExp;
	}

	public void setNextLevelExp(int nextLevelExp) {
		this.nextLevelExp = nextLevelExp;
	}

	public int getCoin() {
		return coin;
	}

	public void setCoin(int coin) {
		this.coin = coin;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public ObjectType getType() {
		return type;
	}

	protected void setType(ObjectType type) {
		this.type = type;
	}
	
	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
}
