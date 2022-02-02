package cs5004.CharDemo;

public class BattleArena {

	private Character c1;
	private Character c2;
	
	public BattleArena(Character c1, Character c2)
	{
		this.c1 = c1;
		this.c2 = c2;
		
		this.fight();
	}
	
	public void fight()
	{
		while(this.c1.isAlive() && this.c2.isAlive())
		{
			// Damage dealt by Player 1
			int damage = c1.attack();
			
			// Player 2 receives damage
			c2.takeDamage(damage);
			System.out.println("player 2 has taken: " + damage + " damage \n\tThey are now at: " + c2.getHp() + " health.");
			
			// Is Player 2 still alive to attack
			if(c2.isAlive())
			{			
				// Damage dealt by Player 2
				damage = c2.attack();
				
				// Player 1 receives damage
				c1.takeDamage(damage);
				System.out.println("Player 1 has taken: " + damage + " damage \n\tThey are now at: " + c1.getHp() + " health.");
			}
		}
		
		// Check winning conditions
		if(c1.isAlive()) { System.out.println("\nPlayer 1 has won the battle");	}
		else { System.out.println("\nPlayer 2 has won the battle");	}		
	}
	
}
