package entities;
import items.*;

public abstract class Entities implements Entity {
	
	protected int health;
	protected int strength;
	protected boolean hasWeapon;
	protected Item weapon;
	public String name;
	public boolean isAlive;
	
	public Entities() {
		
	}
	
	public Entities(int health, int strength, String name) {
		this.health = health;
		this.strength = strength;
		this.name = name;
		this.hasWeapon = false;
		this.isAlive = true;
	}
	
	@Override
	public void setDefaultValues() {
		this.hasWeapon = false;
		this.isAlive = true;
		
	}
	
	@Override
	public String getSpeech() {
		return "I am, therefore I am";
	}
	
	@Override
	public int attack() {
		return 0;
	}
	
	@Override
	public boolean isAlive() {
		return this.isAlive;
	}
	
	@Override
	public void addWeapon(Weapons weapon) {
		this.hasWeapon = true;
		this.weapon = weapon;
	}
	
	/**
	 * Returns an entities weapon if there is one
	 * 
	 * @return weapon (Weapons) 
	 * @return null
	 */
	public Weapons getWeapon() {
		if(hasWeapon == true) {
			return (Weapons)this.weapon;
		}
			return null;
	}
	
	/**
	 * Overrides the toString method
	 * 
	 * @return str (String)
	 */
	@Override
	public String toString() {
		String str = "Name: " + name;
		if(hasWeapon) {
			str += "\nWeapon Type: " + weapon;
		} else {
			str += "\nUnarmed\n";
		}
		
		return str;
	}
}
