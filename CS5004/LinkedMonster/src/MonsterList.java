/*
A wrapper class for creating a monster list. 
Place any methods in here needed for interacting with a monster list

6-11-2021
edited 2/25/22

Dr. G
*/

public class MonsterList
{
	//the actual data
	//we need a "type" that will hold monster nodes or empty nodes
	//this can be private as it will only be used by this class
	private MonsterNode data;

	
	//no argument constructor
	//an empty monster list would still contain something. What and why?
	public MonsterList()
	{
		data = new EmptyNode();
	}
	
	//argument construtor taking a single monster and storing it in a node
	public MonsterList(Monster m)
	{
		data = new Node(m, new EmptyNode());
		
	}
	
	//argument constructor making a new MonsterList from a MonsterList object
	public MonsterList(MonsterNode data)
	{
		this.data = data;
	}
	
	//add a monster to the list
	//you'll need to make a new node with each add. Why?
	//how does data change each time you add?
	public void addMonster(Monster m)
	{
		this.data = new Node(m, data);
	}
	
	//toString override method
	public String toString()
	{
		return data.toString();
	}
	
	//return the number of monsters in the list
	//you'll need to use recursion again here
	//ask the list to count itself
	public int count()
	{
		int acc = 0;
		return data.count(acc);
	}
	
	//return a list of all monsters that are still "alive"
	//public MonsterListI removeDead()
	public void removeDead()
	{		
		if (data != null)
		{
			data = data.getAlive();
		}		
	}
	
	//return a specific monster based on its position
	public Monster getMonster(int position)
	{
		return data.getMonster(position);
	}
	
	
	//using predicate abstraction get only Zombie objects
	//1st just implement a getZombies then swap it out for a generic predicate object
	public MonsterList getZombies()
	{
		//return new MonsterList(data.getZombies());
		return new MonsterList(data.getMonsters(new ZombieTest()));
		
	}
	
	public MonsterList getWerewolfs()
	{
		//return new MonsterList(data.getZombies());
		return new MonsterList(data.getMonsters(new WerewolfTest()));
		
	}
	
}