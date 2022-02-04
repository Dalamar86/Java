/*
GameDriver for in class demonstration
Author : Dr. G
Date : 5/13/2021
Edited 9/17/2021

Because we are going to create some JavaDocs and run some JUnit tests, we'll do this demo in Eclipse, but
we can actually accomplish all of this using the command line. 

 * Edited by: "Robert Wilson"
 * Date: "01 February 2022"
 
*/
package cs5004.CharDemo;

/**
 * This class drives the CharDemo project.
 * 
 * @author Robert Wilson
 *
 */
public class CharacterDriver {
	/**
	 * Main method which drives the CharDemo project. First it creates three Characters, 
	 * prints them out with the character printChar method, and then passes c1 and c2 to 
	 * BattleArena.
	 * 
	 * @param args command line arguments which are not used
	 */
	public static void main(String [] args) {
		// Create Character instances
		Character c1 = new Character();
		Character c2 = new Character("Tony");
		Character c3 = new Character("Steve", -8, 1);
		
		// Print out Character objects attributes
		c1.printChar();
		c2.printChar();
		c3.printChar();
		
		// Create an instance of the battle arena, which also runs the battle
		BattleArena ba1 = new BattleArena(c1, c2);
		
		//1. Add three member variables to character
			// Added name, health, and strength
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