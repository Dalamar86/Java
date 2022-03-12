package entities;

import items.MeleeWeapon;

public class Player extends Entities {

	private static int playerCount = 0;
	
	public Player() {
		setDefaultValues();
		playerCount++;
	}
	
	public Player(int health, int strength, String name) {
		super(health, strength, name);
		setHealth(health);
		playerCount++;
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

	public void setHealth(int health) {
		if(health > 100) {
			health = 100;
		}
		this.health = health;
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


	public static int getPlayerCount() {
		return playerCount;
	}
}
