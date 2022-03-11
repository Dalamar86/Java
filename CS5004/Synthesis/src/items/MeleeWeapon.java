package items;

public class MeleeWeapon extends Weapons{

	public MeleeWeapon() {
		super();
		this.damageModifier = 3;
		this.type = "melee weapon";
		this.name = "unarmed";		
	}
	
	public MeleeWeapon(String name) {
		super();
		this.damageModifier = 3;
		this.type = "melee weapon";
		this.name = name;
	}
	
	/**
	 * Overrides the toString method
	 * 
	 * @return str (String)
	 */
	@Override
	public String toString() {
		String str = "Weapon Name: " + name + "\nWeapon Type: " + type + "\n";
		return str;
	}
}
