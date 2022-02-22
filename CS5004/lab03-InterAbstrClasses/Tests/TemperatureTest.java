/*
 * Edited by:	Robert Wilson
 * Date:		15 February 2022
 */
import org.junit.Before;
import org.junit.Test;

import cs5004.temp.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class TemperatureTest {

  private Temperature cTemp1;
  private Temperature fTemp1;
  
  private Temperature cTemp2;
  private Temperature fTemp2;
  
  private Temperature cTemp3;
  private Temperature fTemp3;
  
  private Temperature fTemp4;

  @Before
  public void init() {
    cTemp1 = new CelsiusTemperature(100);
    fTemp1 = new FahrenheitTemperature(100, true);
    
	fTemp2 = new FahrenheitTemperature(50);
	cTemp2 = new CelsiusTemperature(50, true);
	
	cTemp3 = new CelsiusTemperature(0, false);
	fTemp3 = new FahrenheitTemperature(0, false);
	
	fTemp4 = new FahrenheitTemperature(0);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testForInvalidFTemp() {
	// If i remove the try catch block this passes but the program stops running as we are not catching the exception in the driver
    new FahrenheitTemperature(-1000);
    new FahrenheitTemperature(-1000, true);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testForInvalidCTemp() {
	// If i remove the try catch block this passes but the program stops running as we are not catching the exception in the driver
	new CelsiusTemperature(-1000);
    new CelsiusTemperature(-1000, true);
  }
	
  @Test
  public void testObservers() {
    assertEquals(100, cTemp1.inCelsius(), 0.001);
    assertEquals(212, cTemp1.inFahrenheit(), 0.001);
    assertEquals(373.15, cTemp1.inKelvin(), 0.001); 
	
	assertEquals(100, fTemp1.inCelsius(), 0.001);
	assertEquals(212, fTemp1.inFahrenheit(), 0.001);
	assertEquals(373.15, fTemp1.inKelvin(), 0.001);
	
	assertEquals(10, cTemp2.inCelsius(), 0.001);
	assertEquals(50, cTemp2.inFahrenheit(), 0.001);
	assertEquals(283.15, cTemp2.inKelvin(), 0.001);
	
	assertEquals(10, fTemp2.inCelsius(), 0.001);
	assertEquals(50, fTemp2.inFahrenheit(), 0.001);
	assertEquals(283.15, fTemp2.inKelvin(), 0.001);
  }

  @Test
  public void testInF() {
    assertEquals(212.0, fTemp1.inFahrenheit(), 0.001);
  }
 
  @Test
  public void testToString() {
    assertEquals("100.0° Celsius", cTemp1.toString());
    assertEquals("212.0° Fahrenheit", fTemp1.toString());
  }
  
  @Test
  public void testEquals() {
	  
	  Temperature cTemp3 = new CelsiusTemperature(100);
	  Temperature fTemp3 = new FahrenheitTemperature(50);
	  
	  assertTrue(cTemp3.equals(cTemp1));
	  assertFalse(cTemp3.equals(cTemp2));
	  
	  assertTrue(fTemp3.equals(fTemp2));
	  assertFalse(fTemp3.equals(fTemp1));
	  
  }
  
  /*Add additional tests for methods you added or tests that were left out below this line.*/
  @Test
  public void testFreeze() {
	  assertTrue(fTemp4.isFreezing());
	  assertFalse(cTemp2.isFreezing());
  }
  
  
}
