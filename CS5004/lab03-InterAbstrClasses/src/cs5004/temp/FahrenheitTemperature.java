package cs5004.temp;

/**
 * This class describes a temperature object in fahrenheit
 * 
 * @author Robert Wilson
 *
 */
public class FahrenheitTemperature extends TemperatureImp {

	/**
	 * Constructs a Fahrenheit instance using the temperature provided. It then fills the fields for 
	 * fahrenheit, kelvin, and celsius by calling parents methods.   
	 * 
	 * @param temperature (double) in fahrenheit
	 */
	public FahrenheitTemperature (double temperature) {
		//super(temperature);    Removed as it was not a requirement for the lab or this project
		this.fahrenheit = temperature;
		// If temperature is below absolute zero print exception
		this.kelvin = super.inKelvin();
		this.celsius = super.inCelsius();
	}
	
	/**
	 * Constructs a Fahrenheit instance using the temperature and boolean provided. It then fills the fields for 
	 * fahrenheit, kelvin, and celsius by calling parents methods.   
	 * 
	 * @param temperature (double) in fahrenheit
	 * @param bool (Boolean) must be true, changes type of temperature to celsius
	 */
	public FahrenheitTemperature (double temperature, boolean bool) {
		//super(temperature);    Removed as it was not a requirement for the lab or this project
		if(bool != true) {
			return;
		}
		this.celsius = temperature;
		// If temperature is below absolute zero print exception
		this.kelvin = super.inKelvin();
		this.fahrenheit = super.inFahrenheit();		
	}
	
	/**
	 * Overrides toString() method of String class
	 */
	@Override
	public String toString() {
		String str;
			str = String.format("%.1f", fahrenheit) + "° Fahrenheit"; 
		return str;
	}
}
