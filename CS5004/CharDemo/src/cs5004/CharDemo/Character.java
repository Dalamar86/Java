/* 
 * Created by: "Robert Wilson"
 * Date: "01 February 2022"
 */

package cs5004.CharDemo;
import java.lang.Math;

/** 
 * This class represents a Character. A character has a name, hit points,
 * strength, and a boolean alive. 
 */
public class Character {

	private String name;
	private int hp;
	private int strength;
	private boolean alive;
	
	/**
	 * Construct a Character object that has the default values for name, hit points, strength, and alive.
	 */
	public Character()
	{
		name = "Conan";
		setHp(100);
		setStrength(9);
		
		alive = true;
	}
	
	/**
	 * Construct a Character object that has the default values for hit points, strength, and alive. 
	 * A name is provided.
	 * 
	 *  @param name The name to be given to this character
	 */
	public Character(String name)
	{
		this();
		this.name = name;
	}
	
	/**
	 * Construct a Character object that has the default values for alive. 
	 * A name, hit points, and strength is provided.
	 * 
	 *  @param name 	The name to be given to this character 
	 *  @param hp 		The hit points to be given to this character via setHp()
	 *  @param strength	The strength to be given to this character via setStrength()
	 */
	public Character(String name, int hp, int strength)
	{
		this();
		this.name = name;
		setHp(hp);
		setStrength(strength);
	}
	
	/**
	 * Returns the hit points of this character.
	 * 
	 * @return the hit points of this character
	 */
	public int getHp()       { return hp; }	
	
	/**
	 * Returns the name of this character.
	 * 
	 * @return the name of this character
	 */
	public String getName()  { return name; }
	
	/**
	 * Returns the strength of this character.
	 * 
	 * @return the strength of this character
	 */
	public int getStrength() { return strength; }
	
	/**
	 * Returns alive status of this character.
	 * 
	 * @return alive the boolean alive of this character
	 */
	public boolean isAlive() { return alive; }
	
	/**
	 * Sets the name of this character.
	 * 
	 * @param name The name to be given to this character
	 */
	public void setName(String name) { this.name = name; }
	
	/**
	 * Sets the hit points of this character after checking to see it has gone below or equal to zero.  
	 * If it has dropped below or equal to zero sets alive to false and hit points to zero.
	 * 
	 * @param hp The hit points to be given to this character
	 */
	private void setHp(int hp) {
		if(hp<=0) {
			this.alive = false;	
			hp = 0;
		}
		
		this.hp = hp;
	}
	
	/**
	 * Sets the strength of this character after checking that it has not exceeded 99.  
	 * If it has exceeded 99, sets strength to 99.
	 * 
	 * @param strength The strength to be given to this character
	 */
	public void setStrength(int strength) {
		if(strength>99) strength = 99;
		this.strength = strength;
	}
	
	/**
	 * Prints to System.Out the attributes of this character.
	 */
	public void printChar() {
		System.out.println("name: " + name);
		System.out.println("hp: " + hp);
		System.out.println("Strength: " + strength);
		System.out.println("Alive: " + alive);
	}
	
	/**
	 * Re-sets this character's hit points to the value getHp()-damage by calling setHp().
	 * 
	 * @param damage The damage to subtract from character's hit points
	 */
	public void takeDamage(int damage) { this.setHp(this.getHp()-damage); }
	
	/**
	 * Returns the attack damage of this character.  This is calculated by multiplying
	 * this characters strength by the call to Math.random(), resulting in a number between
	 * zero and this characters strength.
	 * 
	 * @return damage the character will attack with cast as an int
	 */
	public int attack() { return (int)(Math.random() * strength); }	
}
