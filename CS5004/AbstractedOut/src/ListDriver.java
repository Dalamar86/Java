//import java.util.function.Function;

public class ListDriver
{
	public static void main(String [] args)
	{		
		
		//Call back from last class
		ListADT<Monster> monsterList = new ListADTImpl<Monster>();
		
		monsterList.addBack(new Zombie("Bob"));
		monsterList.addBack(new Werewolf("Pro. Lupin"));
		monsterList.addBack(new Goblin("Nott"));
		
		for(int x = 0; x<monsterList.getSize(); ++x)
		{
			System.out.println(monsterList.get(x));
		}
		
		//What's the difference/same in this implementation and the linked monsters?
		//What's the difference in addBack and addFront?
		//What's the difference in the recursive & accumulator implementation?
		//Which is more efficient? Why?
		
		//What's going on with the map function?
		
		ListADT<String> monsterStrings = new ListADTImpl<String>();
		monsterStrings = monsterList.map((m)->m.toString());
		
		System.out.println(monsterStrings);
		
		ListADT<Boolean> monstersAlive = new ListADTImpl<Boolean>();
		monstersAlive = monsterList.map((m)->m.isAlive());
		
		System.out.println(monstersAlive);
		//Convert the list of monsters to a list of monster strings
		//Convert the list of monsters to a list of boolean variables if they are alive
		//Check to see if all the monsters are alive
		//Create a variable that holds a function and pass that instead
		
		//Examine the below example and do a code trace. 
		
		/*
		//Example from module
		ListADT<String> stringList = new ListADTImpl<String>();
		
		String sentence = "The quick brown fox jumps over the lazy dog";
		String []words = sentence.split("\\s+");
		
		for (String w:words) 
		{
			stringList.addBack(w);
		}

		ListADT<Integer> wordLengths = stringList.map(s->s.length());
		
		System.out.println(stringList.getSize());
		
		for (int i=0;i<words.length;i++) 
		{
			System.out.println(wordLengths.get(i));
		}
		*/
		
	}
	
}