/*
Interface object for creating a list of monsters
This allows for node and emptyNode to be of the same type. 

6-11-2021

edited 2/25/22

Dr. G
*/

import java.util.function.Predicate;

public interface MonsterNode
{
	//require the following
	//an int method that returns a count
	int count(int acc);
	//a toString override
	String toString();
	//a method that returns an MonsterNode that are alive
	MonsterNode getAlive();
	//a method that will return a monster given a position
	Monster getMonster(int position);

	
	//we'll come back to these in the end
	public MonsterNode getMonsters(Predicate<Monster> predicate);
	public MonsterNode getZombies();

}