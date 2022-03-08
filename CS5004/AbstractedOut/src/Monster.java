//Abstract monster parent class
abstract class Monster
{
	protected String name;
	protected int hitPoints;
	protected boolean alive;
	
	public abstract void speak(); 
	
	public void takeDamage(int damage)
	{
		hitPoints-=damage;
		if (hitPoints <= 0)
		{
			hitPoints = 0;
			alive = false;
		}
	}
	
	public Monster()
	{
		name = null;
		hitPoints = 1; 
		alive = true;
	}
	
	public String toString()
	{
		return "Name : " + name + "\nHit Points : " + hitPoints + "\n" ;
	}
	
	public boolean isAlive()
	{
		return alive;
	}
	
}

//Zombie class
class Zombie extends Monster
{
	public Zombie(){super();}
	public Zombie(String name){this.name=name;}
	
	public void speak()
	{
		System.out.println("Brains!");
	}
		
	public void goodBye()
	{
		System.out.println("Bye Brains!");
	}
	
}

//Werewolf class object
class Werewolf extends Monster
{
	
	public Werewolf(){super();}
	public Werewolf(String name){this.name=name;}
	
	public void speak()
	{
		System.out.println("Howl!");
	}
}


//Goblin class object
class Goblin extends Monster
{
	public Goblin(){super();}
	public Goblin(String name){this.name=name;}
	
	public void speak()
	{
		System.out.println("I like to blow things up!");
	}
}
