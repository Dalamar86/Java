/* 
 * Created by: "Robert Wilson"
 * Date: "04 February 2022"
 */

import cs5004.CharDemo.Character;
import static org.junit.Assert.*;

import java.io.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;

/**
 * A JUnit4 test class for the Character class
 * 
 * @author Robert Wilson
 *
 */
public class CharacterTest {

	private Character player1;
	private Character player2;
	private Character player3;
	private int attack;
	private int hp;
	private String expectedOutput;
	
	/*
	 *  Solution for testing a System.Out.print commands.
	 *  Solutions adapted from these three sources:
	 *  dfa:
	 *  	-https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println
	 *  https://www.baeldung.com/java-testing-system-out-println
	 *  René Link
	 *  	-https://stackoverflow.com/questions/41674408/java-test-system-output-including-new-lines-with-assertequals
	 */
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream standardOut = System.out;
	private final PrintStream standarErr = System.err;
	
	/**
	 * Setup Three character objects and two PrintStream objects for setOut and setErr. 
	 */
	@Before
	public void setUp() {
		player1 = new Character();
		player2 = new Character("Arnold");
		player3 = new Character("Barb", -12, 105);
		System.setOut( new PrintStream(outContent));
		System.setErr( new PrintStream(errContent));
	}

	/**
	 * Restores the Output stream after the test is completed. 
	 */
	@After
	public void restoreStreams() {
		System.setOut(standardOut);
		System.setErr(standarErr);
	}
	
	/**
	 * Assert that each constructors name is saved and can be returned. 
	 * Also check that setName() works.
	 */
	@Test
	public void testName() {
		assertEquals("Conan", player1.getName());
		assertEquals("Arnold", player2.getName());
		assertEquals("Barb", player3.getName());
		player3.setName("Diane");
		assertEquals("Diane", player3.getName());
	}
	
	/**
	 * Asserts that attack() and takeDamage() work as expected reducing the 
	 * character's hit points by damage.
	 */
	@Test
	public void testDamage() {
		attack = player1.attack();
		hp = player1.getHp();
		player1.takeDamage(attack);
		assertEquals(hp-attack, player1.getHp());
	}
	
	/**
	 * Assert that getStrength() and isAlive() getters works as expected.
	 */
	@Test
	public void testGetters() {
		assertEquals(9, player1.getStrength());
		assertEquals(false, player3.isAlive());
	}
	
	/**
	 * Asserts that printChar() outputs.
	 * This implementation is adapted from two sources as noted above.
	 */
	@Test
	public void testPrint() {
		expectedOutput = "name: Conan\nhp: 100\nStrength: 9\nAlive: true\n".
				replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));
		player1.printChar();
		assertEquals(expectedOutput, outContent.toString());
	}

}
