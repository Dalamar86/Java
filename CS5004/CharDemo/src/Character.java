import java.lang.Math;

public class Character {

	private String name;
	private int hp;
	private int strength;
	private boolean alive;
	
	public Character()
	{
		name = "Conan";
		setHp(100);
		setStrength(9);
		
		alive = true;
	}
	
	public Character(String name)
	{
		this();
		this.name = name;
		// hp = 100;
		// strength = 9;
	}
	
	public Character(String name, int hp, int strength)
	{
		this();
		this.name = name;
		setHp(hp);
		setStrength(strength);
	}
	
	public int getHp()
	{
		return hp;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getStrength()
	{
		return strength;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	private void setHp(int hp)
	{
		if(hp<=0)
		{
			this.alive = false;	
			hp = 0;
		}
		this.hp = hp;
	}
	
	public void setStrength(int strength)
	{
		if(strength>99) strength = 99;
		this.strength = strength;
	}
	
	public void takeDamage(int damage)
	{
		this.setHp(this.getHp()-damage);
	}
	
	public boolean isAlive()
	{
		return alive;
	}
	
	public int attack()
	{
		
		return (int)(Math.random() * strength);
	}
	
	public void printChar()
	{
		System.out.println("name:" + name);
		System.out.println("hp:" + hp);
		System.out.println("Strength:" + strength);
		System.out.println("Alive:" + alive);
	}
	
}
