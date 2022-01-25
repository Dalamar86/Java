
public class GameDriver {

	public static void main(String[] args) {
		
		Zombie bob = new Zombie();
		Fighter joe = new Fighter();
		int damage = 0;
		
		while (bob.isAlive())
		{
			System.out.println(bob.speak());
			System.out.println(joe.speak());
			
			damage = joe.attack();
			bob.takeDamage(damage);
			
			System.out.println("Figher attacked Zombie for " + damage + " damage");
			
			damage = bob.attack();
			joe.takeDamage(damage);
			
			System.out.println("Zombie attacked Fighter for " + damage + " damage");
			
			if(!joe.isAlive())
			{
				System.out.println("The Fighter has died!");
				break;
			}
			if(!bob.isAlive())
				System.out.println("The Zombie has died!");
		}
		
		System.out.println("Battle over");
	}

}
