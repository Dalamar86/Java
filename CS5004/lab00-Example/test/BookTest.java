/*
 * Edited by: "Robert Wilson"
 * Date: "25 January 2022"
 */

import cs5004.lab00.Book;
import cs5004.lab00.Person;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit test class for the Book class.
 */
public class BookTest {

	private Book TreasureIsland;
	private Person RStev;
	
	/**
	 * Setup the Book object
	 */
	@Before
	  public void setUp() {

		TreasureIsland = new Book("Treasure Island", RStev, (float) 25.49);
	  }
	
	/**
	 * Assert that the Author is saved and returned using 
	 * the getAuthor method
	 */
	@Test
	public void testAuthor() {
		assertEquals(RStev, TreasureIsland.getAuthor());
	}
	
	/**
	 * Assert that the title is saved and returned using 
	 * the getTitle method.
	 */
	@Test
	public void testTitle() {
		assertEquals("Treasure Island", TreasureIsland.getTitle());
	}
	
	/**
	 * Assert that the price is saved properly and is returned using 
	 * the getPrice method with a delta of 0.02.
	 */
	@Test
	public void testPrice() {
		assertEquals((float) 25.49, TreasureIsland.getPrice(), 0.02);
	}
}
