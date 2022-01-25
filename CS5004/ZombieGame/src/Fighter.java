import java.lang.Math;

public class Fighter {

	private String phrase;
	private int health;
	private boolean status;
	private int strength;
	
	public Fighter()
	{
		phrase = "Woot";
		health = 90;
		strength = 10;
		status = true;
	}
	
	public String speak()
	{
		return phrase;
	}
	
	public int takeDamage(int damage)
	{
		health -= damage;
		
		if (health <= 0)
			status = false;
		
		return health;
	}

	public boolean isAlive()
	{
		return status;
	}
	
	public int getHealth()
	{
		return health;
	}
	
	public int attack()
	{
		
		return (int)(Math.random() * strength);
	}
}
