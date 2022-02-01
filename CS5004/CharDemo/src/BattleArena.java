
public class BattleArena {

	private Character c1;
	private Character c2;
	private boolean winner;
	
	public BattleArena(Character c1, Character c2)
	{
		this.c1 = c1;
		this.c2 = c2;
		
		this.fight();
	}
	
	public void fight()
	{
		while(this.c1.isAlive() || this.c2.isAlive())
		{
			// Damage of c1
			int damage = c1.attack();
			
			// Hurt c2
			c2.takeDamage(damage);
			System.out.println("c2 has taken " + damage + " damage and is onw at " + c2.getHp() + " health.");
			// Is c2 still alive
			if(!c2.isAlive())
			{
				System.out.println("c2 has died!");
				winner = true;
				break;
			}
			
			// Damage from c2
			damage = c2.attack();
			
			// Hurt c1
			c1.takeDamage(damage);
			System.out.println("c1 has taken " + damage + " damage and is onw at " + c2.getHp() + " health.");
			
			// c1 still alive
			if(!c2.isAlive())
			{
				System.out.println("c1 has died!");
				winner = false;
				break;
			}
			
		}
		
		if(winner)
		{
			System.out.println("c1 has won the battle");
		}
		else
		{
			System.out.println("c2 has won the battle");
		}
		
		
	}
	
}
