package gameobject;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import enums.ObjectType;
import projectiles.SuperProjectile;

/**
 * The gameObject interface allows for polymorphism and dynamic dispatching.
 * 
 * @author Robert Wilson
 *
 */
public interface GameObjectInt {

	//#####################################################################
	// 								Components
	//#####################################################################
	
	/**
	 * The draw method for the objects.  Takes the buffered images, chooses the correct one
	 * and then draws it. Also controls the animations.
	 */
	public void draw(Graphics2D g2);
	
	/**
	 * Current object's update method which updates all information about the object.
	 */
	public void update();
	
	/**
	 * Buffer the current object's image and scale it to the games tile size 
	 * 
	 * @param imagePath (String) the path of the image to be buffered
	 * 
	 * @return image (BufferedImage) scaled image
	 */
	public BufferedImage setup(String imagePath);
	
	/**
	 * The speech method of the current object.  Sends a string to the ui to print.
	 */
	public void speak();
	
	/**
	 * The idle action of the current object.
	 */
	public void setAction();
	
	/**
	 * The action an object takes in response to taking damage.
	 */
	public void damageReaction();
	
	/**
	 * This method controls how the current object takes damage.
	 * 
	 * @param damage (int) damage sent to it by the damager
	 * @return exp (int) Experience if the object dies
	 */
	public int takeDamage(int damage);
	
	/**
	 * The attack action taken when an object attacks another object.
	 * 
	 */
	public void attack();
	
	/**
	 * This method governs how an object is used
	 * 
	 * @param gameObject (GameObject) the object the item is being used on
	 */
	public void use(GameObject gameObject);
	
	/**
	 * The FSM method for finding aide when a boss is severely hurt.
	 */
	public void findAide();

	/**
	 * Returns a relative distance from the current object to the gameObject passed in.
	 * 
	 * @param gameObject (GameObject) the object to measure the distance to
	 * 
	 * @return distance (int) the relative distance to the given object
	 */
	int getDistance(GameObject gameObject);

	/**
	 * Returns the direction to the given gameObject determined by the angle to the 
	 * given gameObject.  Quadrants 1 and 2 are positive and 3 and 4 are negative.
	 * 
	 * @param gameObject (GameObject) the object to which the direction pertains
	 * 
	 * @return direction (String) the direction to the given game object
	 */
	String getDirectionTo(GameObject gameObject);
	
	//#####################################################################
	// 							Getters and Setters
	//#####################################################################
	
	/**
	 * Getter for the max life an object can have
	 * 
	 * @return maxLife (int) the maximum life of an object
	 */
	public int getMaxLife();

	/**
	 * Getter for the current life of an object
	 * 
	 * @return life (int) life of the current object
	 */
	public int getLife();
	
	/**
	 * Setter for the life of an object
	 * 
	 * @param life (int) the life to set to the current object. If it is under o set to zero
	 * and set the object to dying.  If over the max Life then set to the maximum life. 
	 */
	public void setLife(int life);

	/**
	 * Getter returns whether the object is alive
	 * 
	 * @return (boolean) true if object is alive, false otherwise
	 */
	public boolean isAlive();

	/**
	 * Sets whether the current object is alive or dead.
	 * 
	 * @param alive (boolean) whether the object is alive or not
	 */
	public void setAlive(boolean alive);

	/**
	 * Gets whether the object is currently dying
	 * 
	 * @return dying (boolean) 
	 */
	public boolean isDying();

	/**
	 * Sets whether the object is currently dying
	 * 
	 * @param dying 8(boolean)	 
	 */
	public void setDying(boolean dying);

	/**
	 * Getter for player level
	 * 
	 * @return level (int) level of the player
	 */
	public int getLevel();

	/**
	 * Sets the level of the player
	 * 
	 * @param level (int) the level to set the player to
	 */
	public void setLevel(int level);

	/**
	 * Getter for the object's strength
	 */
	public int getStrength();

	/**
	 * Setter for the object's strength
	 * 
	 * @param strength (int) strength to set to the object
	 */
	public void setStrength(int strength);

	/**
	 * Getter for dexterity of the object
	 * 
	 * @return dexterity (int) dexterity of the object
	 */
	public int getDexterity();

	/**
	 * Setter for an object's dexterity
	 * 
	 * @param dexterity (int) dexterity to set to the object
	 */
	public void setDexterity(int dexterity);

	/**
	 * Getter for object attack
	 * 
	 * @return attack (int) attack of the object
	 */
	public int getAttack();

	/**
	 * Setter for an objects attack
	 * 
	 * @param attack (int) integer to set to an object's attack
	 */
	public void setAttack(int attack);

	/**
	 * Getter for object defense
	 * 
	 * @return defense (int) defense of the object
	 */
	public int getDefense();

	/**
	 * Setter for an objects defense
	 * 
	 * @param defense (int) integer to set to an object's defense
	 */
	public void setDefense(int defense);

	/**
	 * Getter for object experience
	 * 
	 * @return exp (int) experience of the object
	 */
	public int getExp();

	/**
	 * Setter for an objects experience
	 * 
	 * @param exp (int) integer to set to an object's experience
	 */
	public void setExp(int exp);

	/**
	 * Getter for object's next level
	 * 
	 * @return nextLevel (int) next level of the object
	 */
	public int getNextLevelExp();

	/**
	 * Setter for an object's next level
	 * 
	 * @param nextLevelExp (int) 
	 */
	public void setNextLevelExp(int nextLevelExp);

	/**
	 * Getter for the player's coin
	 * 
	 * @return coin (int) the players current number of coins
	 */
	public int getCoin();

	/**
	 * Setter for the players number of coins
	 * 
	 * @param coin (int) the number of coins to set the player's coin level to
	 */
	public void setCoin(int coin);

	/**
	 * Getter for an object's description
	 * 
	 * @return description (String) an object's description
	 */
	public String getDescription();

	/**
	 * Sets the description of an object
	 * 
	 * @param description (String) String to save to an object's description
	 */
	public void setDescription(String description);

	/**
	 * Getter for an object's type
	 * 
	 * @return (ObjectType) the type of the object
	 */
	public ObjectType getType();

	/**
	 * Getter for an object's direction
	 * 
	 * @return direction (String) the direction of the object
	 */
	public String getDirection();

	/**
	 * Setter of an object's direction
	 * 
	 * @param direction (String) direction to set to the object
	 */
	public void setDirection(String direction);

	/**
	 * Getter for an object's attack value
	 * 
	 * @return (int) an object's attack value
	 */
	public int getAttackValue();

	/**
	 * Setter for an object's attack value
	 * 
	 * @param attackValue (int) the value to save as an object's attack value
	 */
	public void setAttackValue(int attackValue);

	/**
	 * Getter for an object's defense value
	 * 
	 * @return (int) an object's defense value
	 */
	public int getDefenseValue();

	/**
	 * Setter for an object's defense value
	 * 
	 * @param defenseValue (int) the value to save as an object's defense value
	 */
	public void setDefenseValue(int defenseValue);

	/**
	 * Getter for an object's name
	 * 
	 * @return name (String) An object's name
	 */
	public String getName();

	/**
	 * Setter for an object's name
	 * 
	 * @param name (String) string to set as an object's name
	 */
	public void setName(String name);

	/**
	 * Getter of the object's first image
	 * 
	 * @return image1 (BufferedImage) the object's first image
	 */
	public BufferedImage getImage1();

	/**
	 * Setter of an object's first image
	 * 
	 * @param image1 (BufferedImage) image to save to the object
	 */
	public void setImage1(BufferedImage image1);

	/**
	 * Getter of the object's second image
	 * 
	 * @return image2 (BufferedImage) the object's second image
	 */
	public BufferedImage getImage2();

	/**
	 * Setter of an object's second image
	 * 
	 * @param image2 (BufferedImage) image to save to the object
	 */
	public void setImage2(BufferedImage image2);

	/**
	 * Getter of the object's third image
	 * 
	 * @return image3 (BufferedImage) the object's third image
	 */
	public BufferedImage getImage3();

	/**
	 * Setter of an object's third image
	 * 
	 * @param image3 (BufferedImage) image to save to the object
	 */
	public void setImage3(BufferedImage image3);

	/**
	 * Returns a players current weapon
	 * 
	 * @return currentWeapon
	 */
	public GameObject getCurrentWeapon();

	/**
	 * Sets the current weapon of a player
	 * 
	 * @param currentWeapon
	 */
	public void setCurrentWeapon(GameObject currentWeapon);

	/**
	 * Returns a players current shield
	 * 
	 * @return currentShield
	 */
	public GameObject getCurrentShield();

	/**
	 * Sets the current shield of a player
	 * 
	 * @param currentSheild
	 */
	public void setCurrentShield(GameObject currentShield);
	
	/**
	 * Returns the speed of the object
	 * 
	 * @return speed (int) speed of the object
	 */
	public int getSpeed();

	/**
	 * Sets the speed of the object
	 * 
	 * @param speed (int) integer to set as object's speed
	 */
	public void setSpeed(int speed);
	
	/**
	 * Returns the diagonal speed of the object
	 * 
	 * @return speedDiag (int) diagonal speed of the object
	 */
	public int getSpeeddiag();

	/**
	 * Sets the diagonal speed of the object
	 * 
	 * @param speedDiag (int) integer to set as object's diagonal speed
	 */
	public void setSpeeddiag(int speeddiag);

	/**
	 * Returns whether an object has collided with something
	 * 
	 * @return isCollisionOn (boolean) 
	 */
	public boolean isCollisionOn();

	/**
	 * Sets whether there has been a collision of an object with another 
	 * 
	 * @param collisionOn
	 */
	public void setCollisionOn(boolean collisionOn);

	/**
	 * 
	 * @return
	 */
	boolean isCollision();

	boolean setCollision(boolean collision);

	/**
	 * Returns an object's worldX
	 * 
	 * @return worldX (int) objects x place in the world 
	 */
	int getWorldX();

	/**
	 * Sets an object's worldX
	 * 
	 * @param worldX (int) x position to set the object to
	 */
	void setWorldX(int worldX);

	/**
	 * Returns an object's worldY
	 * 
	 * @return worldY (int) objects y place in the world 
	 */
	int getWorldY();

	/**
	 * Sets an object's worldY
	 * 
	 * @param worldY (int) y position to set the object to
	 */
	void setWorldY(int worldY);

	/**
	 * Returns the solid area of an object
	 * 
	 * @return (Rectangle) 
	 */
	Rectangle getSolidArea();

	/**
	 * Sets an object's solid area to the given rectangle
	 * 
	 * @param solidArea (Rectangle)
	 */
	void setSolidArea(Rectangle solidArea);

	/**
	 * Returns an object's default solid area x value
	 * 
	 * @return (int) objects solid area x value
	 */
	int getSolidAreaDefaultX();

	/**
	 * Sets an object's default solid area x value
	 * 
	 * @param solidAreaDefaultX (int)
	 */
	void setSolidAreaDefaultX(int solidAreaDefaultX);

	/**
	 * Returns an object's default solid area y value
	 * 
	 * @return (int) objects solid area y value
	 */
	int getSolidAreaDefaultY();

	/**
	 * Sets an object's default solid area y value
	 * 
	 * @param solidAreaDefaultY (int)
	 */
	void setSolidAreaDefaultY(int solidAreaDefaultY);

	/**
	 * Returns the attack area of the current object
	 * 
	 * @return (Rectangle)
	 */
	Rectangle getAttackArea();

	/**
	 * Sets an object#s attack area
	 * 
	 * @param attackArea (Rectangle)
	 */
	void setAttackArea(Rectangle attackArea);

	/**
	 * Returns whether the player is attacking
	 * 
	 * @return (boolean)
	 */
	boolean isAttacking();

	/**
	 * Set whether the player is attacking
	 * 
	 * @param attacking (boolean)
	 */
	void setAttacking(boolean attacking);

	/**
	 * Returns the projectile of the current object
	 * 
	 * @return (SuperProjectile)
	 */
	SuperProjectile getProjectile();

	/**
	 * Sets the object's projectile
	 * 
	 * @param projectile (SuperProjectile)
	 */
	void setProjectile(SuperProjectile projectile);

	/**
	 * Returns the cost of using the projectile
	 * 
	 * @return (int)
	 */
	int getUseCost();

	/**
	 * Sets the cost of using a projectile
	 * 
	 * @param useCost (int)
	 */
	void setUseCost(int useCost);

	/**
	 * Returns the mana of an object
	 * 
	 * @return mana (int)
	 */
	int getMana();

	/**
	 * Sets the mana of an object
	 * 
	 * @param mana (int)
	 */
	void setMana(int mana);

	/**
	 * Returns the attack speed of the object
	 * 
	 * @return (int)
	 */
	int getAttackSpeed();

	/**
	 * Sets the attack speed of the object
	 * 
	 * @param attackSpeed (int)
	 */
	void setAttackSpeed(int attackSpeed);
}
