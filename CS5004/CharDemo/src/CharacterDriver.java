/*
GameDriver for in class demonstration
Author : Dr. G
Date : 5/13/2021
Editted 9/17/2021

Because we are going to create some JavaDocs and run some JUnit tests, we'll do this demo in Eclipse, but
we can actually accomplish all of this using the command line. 

*/


public class CharacterDriver
{
	public static void main(String [] args)
	{
		//Start by creating a Character.java file
		
		Character c1 = new Character();
		Character c2 = new Character("Tony");
		Character c3 = new Character("Steve", -8, 1);
		
		c1.takeDamage(30);
		System.out.println(c1.getHp());
		
		c1.printChar();
		c2.printChar();
		c3.printChar();
		
		BattleArena ba1 = new BattleArena(c1, c2);
		//1. Add three member variables to character
		//2. Create an instance of a character and print it with System.out.println("Character");
		//3. Using public access set and then print the variables
		//4. Change access to private and observe the results
		//5. Add getters and setters and a print method
			//5.1 Demonstrate the "this" pointer
		//6. Demonstrate how this allows one to "protect" a variables by preventing negative values on a variable
		//7. Create a no argument, 1 argument, and all argument constructor
		//8. Implement a test class
		//9. Demonstrate how to find Java documenation and then create a javadoc
			//file ->export -> javadoc
		//10. Create a UML diagram
		//11. Create and run a simple JUnit test
		//12. Get started on Battle Arena
	}
}

/*
/** -> signifies a java doc comment
@param denotes information about a method argument.
@return denotes information about whatever the method returns.
*/