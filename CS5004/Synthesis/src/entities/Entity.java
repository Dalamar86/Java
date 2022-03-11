package entities;
import items.*;

public interface Entity {
	
	/**
	 * Set default values for each entity
	 */
	public void setDefaultValues();
	
	/**
	 * Returns the health of the entity
	 * @return health (int) health as an integer
	 */
	public int getHealth();
	
	/**
	 * Returns the name of the entity
	 * @return String (String) name as a String
	 */
	public String getName();
	
	/**
	 * Returns the attacks damage as a result of strength*weapon damage
	 * @return damage (int) damage
	 */
	public int attack();
	
	/**
	 * Returns the strength of the entity
	 * @return strength (int) strength as an integer
	 */
	public int getStrength();
	
	/**
	 * Speech of character
	 * @return talk (String) characters speech
	 */
	public String getSpeech();
	
	/**
	 * Returns a boolean that is true if the entity is alive and false otherwise
	 * @Return isAlive (boolean)
	 */
	public boolean isAlive();
	
	/**
	 * Adds a weapon to the specified entity
	 */
	public void addWeapon(Weapons weapon);
}
