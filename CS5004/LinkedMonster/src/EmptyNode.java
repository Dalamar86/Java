/*
Linked list tail object for a list of monsters
6-11-2021

edited 2/25/22


Dr. G
*/

import java.util.function.Predicate;

public class EmptyNode implements MonsterNode
{
	
	//basic methods
	public int count(int acc){return acc;} 
	public String toString(){return "";}
	
	
	//list methods
	public Monster getMonster(int position){return null;}
	public MonsterNode getAlive(){return new EmptyNode();}
	
	public MonsterNode getZombies(){return new EmptyNode();}
	
	//predicate abstraction
	public MonsterNode getMonsters(Predicate<Monster> predicate){return new EmptyNode();}
	
}