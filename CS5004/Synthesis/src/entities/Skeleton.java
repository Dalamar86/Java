package entities;

import items.MeleeWeapon;

public class Skeleton extends Entities {

	private static int skeletonCount = 0;
	
	public Skeleton() {
		setDefaultValues();
		skeletonCount++;
	}
	
	@Override
	public void setDefaultValues() {
		super.setDefaultValues();
		this.health = 30;
		this.strength = 2;
		this.name = "skeleton";
		this.addWeapon(new MeleeWeapon("claws"));
		
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
	public int getStrength() {
		return strength;
	}
	
	@Override
	public int attack() {
		
		int damage = 0;
		
		return damage;
	}
	
	public static int getSkeletonCount() {
		return skeletonCount;
	}
}
