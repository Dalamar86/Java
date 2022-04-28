package gameobject;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.ObjectType;

public interface GameObjectInt {

	//#####################################################################
	// 								Components
	//#####################################################################
	
	public void draw(Graphics2D g2);
	
	public void update();
	
	public BufferedImage setup(String imagePath);
	
	public void speak();
	
	public void setAction();
	
	public void damageReaction();
	
	public int takeDamage(int damage);
	
	public int attack();
	
	public void use(GameObject gameObject);
	
	//#####################################################################
	// 							Getters and Setters
	//#####################################################################
	
	public int getMaxLife();

	public int getLife();

	public void setLife(int life);

	public boolean isAlive();

	public void setAlive(boolean alive);

	public boolean isDying();

	public void setDying(boolean dying);

	public int getLevel();

	public void setLevel(int level);

	public int getStrength();

	public void setStrength(int strength);

	public int getDexterity();

	public void setDexterity(int dexterity);

	public int getAttack();

	public void setAttack(int attack);

	public int getDefense();

	public void setDefense(int defense);

	public int getExp();

	public void setExp(int exp);

	public int getNextLevelExp();

	public void setNextLevelExp(int nextLevelExp);

	public int getCoin();

	public void setCoin(int coin);

	public String getDescription();

	public void setDescription(String description);

	public ObjectType getType();

	public String getDirection();

	public void setDirection(String direction);

	public int getAttackValue();

	public void setAttackValue(int attackValue);

	public int getDefenseValue();

	public void setDefenseValue(int defenseValue);

	public String getName();

	public void setName(String name);

	public BufferedImage getImage1();

	public void setImage1(BufferedImage image1);

	public BufferedImage getImage2();

	public void setImage2(BufferedImage image2);

	public BufferedImage getImage3();

	public void setImage3(BufferedImage image3);

	public GameObject getCurrentWeapon();

	public void setCurrentWeapon(GameObject currentWeapon);

	public GameObject getCurrentShield();

	public void setCurrentShield(GameObject currentShield);
	
	public int getSpeed();

	public void setSpeed(int speed);
	
	public int getSpeeddiag();

	public void setSpeeddiag(int speeddiag);

	public boolean isCollisionOn();

	public void setCollisionOn(boolean collisionOn);

	boolean isCollision();

	boolean setCollision(boolean collision);
}
