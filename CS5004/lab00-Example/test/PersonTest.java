/*
 * Edited by: "Robert Wilson"
 * Date: "25 January 2022"
 */

import cs5004.lab00.Person;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * A JUnit test class for the Person class.
 */
public class PersonTest {

  private Person john;

  /**
   * Setup Person object
   */
  @Before
  public void setUp() {

    john = new Person("John", "Doe", 1945);
  }

  /**
   * Assert that first name is saved and can be returned using
   * the getFirstName Method
   */
  @Test
  public void testFirst() {
    assertEquals("John", john.getFirstName());

  }

  /**
   * Assert that the last name is saved and is returned using 
   * the getLastName method
   */
  @Test
  public void testSecond() {
    assertEquals("Doe", john.getLastName());
  }

  /**
   * Assert that the int year is saved and is returned using
   * the getYearOfBirth method
   */
  @Test
  public void testYearOfBirth() {
    assertEquals(1945, john.getYearOfBirth());
  }

}
