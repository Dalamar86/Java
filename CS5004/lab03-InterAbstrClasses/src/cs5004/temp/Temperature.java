package cs5004.temp;

/**
 * Represents a temperature.
 */
public interface Temperature {
  /**
   * Absolute zero, in degrees Celsius. For our purposes, no temperature can be below
   * this value. –273.15°C or –459.67°F
   */
  double ABS_ZERO_C = -273.15f;

  /**
   * The temperature in degrees Celsius.
   *
   * @return the temperature in degrees Celsius
   */
  double inCelsius();

  /**
   * The temperature in degrees Fahrenheit.
   *
   * @return the temperature in degrees Fahrenheit
   */
  double inFahrenheit();

  /**
   * The temperature in degrees Kelvin.
   *
   * @return the temperature in degrees Kelvin
   */
  double inKelvin();

  /**
   * Determine if the current temperature is below freezing
   *
   * @return True or False
   */
  Boolean isFreezing();

}
