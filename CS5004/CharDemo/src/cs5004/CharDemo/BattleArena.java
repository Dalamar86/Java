/* 
 * Created by: "Robert Wilson"
 * Date: "01 February 2022"
 */

package cs5004.CharDemo;

/**
 * This class represents a Battle Arena. A battle arena has two Characters.
 */
public class BattleArena {

	private Character c1;
	private Character c2;
	
	/**
	 * Construct a battle arena object and initializes it with characters c1 and c2.  
	 * The fight method is called in the constructor which initiates the battle.
	 *  
	 * @param c1 the first character in the arena
	 * @param c2 the second character in the arena
	 */
	public BattleArena(Character c1, Character c2)
	{
		this.c1 = c1;
		this.c2 = c2;
		
		this.fight();
	}
	
	/**
	 * Fight method which uses a while loop to cause the characters to battle until one is no longer alive.
	 */
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
