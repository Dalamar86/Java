package items;

public abstract class Weapons implements Item{
	
	protected int health;
	protected int durability;
	protected int damageModifier;
	protected String type;
	protected String name;
	
	public Weapons() {
		this.health = 10;
		this.durability = health;
		this.damageModifier = 1;
		this.type = "improvised weapon";
	}
	
	@Override
	public int getDurability() {
		return durability;
	}
	
	@Override
	public int getDamagemodifier() {
		return damageModifier;
	}
	
	@Override
	public String getName() {
		return this.name;
	}
}
