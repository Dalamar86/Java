/*

Modified Class file from PolymorphicMonsters.
Includes classes: LinkedListDriver, Monster, Werewolf, Zombie, and Goblin
6-11-2021
updated 2/25/22

Dr. G

This code is a combination of the polymorphic monster code and the linked list code from the class. 
I've adapted the code from the module, but I've changed the names and the format based on how I see it. 

Use the demo instructions for a step-by-step process on what to do.

*/

import java.security.SecureRandom;

public class LinkedMonsterDriver
{
	public static void main(String [] args)
	{
		
		//Declaring a monsterList
		//What is a MonsterList?
		MonsterList mList = new MonsterList();
		
		
		
		//testing add method and count method
		System.out.println(mList.count()); 
		mList.addMonster(new Zombie("Bob"));
		System.out.println(mList.count());
		mList.addMonster(new Werewolf("Pro. Lupin"));
		System.out.println(mList.count());
		mList.addMonster(new Goblin("Nott"));
		System.out.println(mList.count());
		
		//print final results
		System.out.println(mList);
		
		
		
		//Testing getMonster
		System.out.println("Get Monster Testing");
		System.out.println("\n" + mList.getMonster(0));
		System.out.println("\n" + mList.getMonster(1));
		System.out.println("\n" + mList.getMonster(2));
		
		
		
		//testing take damage method and getMonster method
		try{
			System.out.println(mList.getMonster(0));
			mList.getMonster(0).takeDamage(1);
		}
		catch(NullPointerException E)
		{
			System.out.println("Monster not found");
		}
	
		
		
		//testing the remove dead method
		//do a code trace on this method to understand what's happening
		mList.removeDead();
		System.out.println("\nAfter Remove Dead\n\n" + mList);
		
		
		
		//testing the extract only method
		//this  only demonstrates predicate abstraction which is a next topic...
		System.out.println("Extract Only Zombies : ");
		MonsterList zombiesOnly = mList.getZombies();
		System.out.println(zombiesOnly);
		
		System.out.println("Extract Only werewolf : ");
		MonsterList werewolfOnly = mList.getWerewolfs();
		System.out.println(werewolfOnly);
		
	}
}


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
