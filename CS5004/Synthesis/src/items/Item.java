package items;

public interface Item {
	
	/**
	 * Returns the durability of the item
	 * 
	 * @return durability (int) durability out of whole health
	 */
	public int getDurability();
	
	/**
	 * Returns the damage modifier on the weapon
	 * 
	 * @return damage (int) modifier to add to damage
	 */
	public int getDamagemodifier();
	
	/**
	 * Return the name of the item-
	 * 
	 * @return name (String) name of the item
	 */
	public String getName();
}
