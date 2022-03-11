package entities;

import items.MeleeWeapon;

public class Player extends Entities {

	public Player() {
		setDefaultValues();
	}
	
	public Player(int health, int strength, String name) {
		super(health, strength, name);
	}

	@Override
	public void setDefaultValues() {
		super.setDefaultValues();
		this.health = 100;
		this.strength = 10;
		this.name = "Bard";
		addWeapon(new MeleeWeapon());
	}
		

	@Override
	public int getHealth() {
		return health;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int attack() {
		
		int damage = 0;
		
		return damage;
	}

	@Override
	public int getStrength() {
		return strength;
	}
	
	@Override
	public String getSpeech() {
		return "Leeroy Jenkins";
	}

}
