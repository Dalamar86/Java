/*
Basic node for containing a monster
6-11-2021
edited 2/25/22

Dr. G
*/

import java.util.function.Predicate;

//Why does the node need to implement MonsterNode?
public class Node implements MonsterNode
{
	//Allow the node to hold a Monster
	//Create a variable that will "point" to the next node
	//The next node could be a node or an empty node
	//I know the modules say rest, but let's just use "nextNode"
	private Monster m;
	private MonsterNode nextNode;
	
	//basic constructor
	//this node has a monster and "points" to another monster node or an empty node
	public Node(Monster m, MonsterNode nextNode) 
	{
		this.m = m;
		this.nextNode = nextNode;
	}
	
	public int count(int acc){return nextNode.count(acc + 1);}//this node + all the rest of the nodes
	public String toString(){return m.toString() + nextNode.toString();}//return this nodes data as a string + the next node's data
	
	//if the monster in this node is still alive return it as a new node
	//otherwise exlclude it from the new list by moving on to the next node
	public MonsterNode getAlive()
	{
		if(m.isAlive()) {
			return new Node(m, nextNode.getAlive());
		} else {
			return nextNode.getAlive();
		}
	}
	
	//return a specific monster based on its position in the list
	public Monster getMonster(int position)
	{
		if(position == 0) {
			return m;
		}
		return nextNode.getMonster(position - 1);
	}
	  
	  
	 public MonsterNode getZombies()
	 {
		  if (m instanceof Zombie)
			  return new Node(m, nextNode.getZombies());
		  else
			  return nextNode.getZombies();
	 }
	  
	
	
	//predict abstraction  
	public MonsterNode getMonsters(Predicate<Monster> predicate)
	{
	  if (predicate.test(m))
	  {
		  return new Node(m, nextNode.getMonsters(predicate));
	  }
	  else 
	  {
		  return nextNode.getMonsters(predicate);
	  }
  }
  
  
}