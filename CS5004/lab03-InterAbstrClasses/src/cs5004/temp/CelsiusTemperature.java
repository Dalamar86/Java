package cs5004.temp;

/**
 * This class describes a temperature object in celsius
 * 
 * @author Robert Wilson
 *
 */
public class CelsiusTemperature extends TemperatureImp {
	
	/**
	 * Constructs a Celsius instance using the temperature provided. It then fills the fields for 
	 * fahrenheit, kelvin, and celsius by calling parents methods.   
	 * 
	 * @param temperature (double) in celsius
	 */
	public CelsiusTemperature (double temperature) {
		//super(temperature);    Removed as it was not a requirement for the lab or this project
		this.celsius = temperature;
		// If temperature is below absolute zero print exception
		try {
			this.kelvin = super.inKelvin();
		} catch (IllegalArgumentException e ) {
			e.printStackTrace();
		}
		this.fahrenheit = super.inFahrenheit();
	}
	
	/**
	 * Constructs a Celsius instance using the temperature and boolean provided. It then fills the fields for 
	 * fahrenheit, kelvin, and celsius by calling parents methods.   
	 * 
	 * @param temperature (double) in celsius
	 * @param bool (Boolean) must be true to turn temperature to fahrenheit
	 */
	public CelsiusTemperature (double temperature, boolean bool) {
		// super(temperature);    Removed as it was not a requirement for the lab or this project
		if(bool != true) {
			return;
		}
		this.fahrenheit = temperature;
		// If temperature is below absolute zero print exception 
		try {
			this.kelvin = super.inKelvin();
		} catch (IllegalArgumentException e ) {
			e.printStackTrace();
		}
		this.celsius = super.inFahrenheit();
	}
	
	/**
	 * Overrides toString() method of String class
	 */
	@Override
	public String toString() {
		String str;
			str = String.format("%.1f", celsius) + "° Celsius"; 
		return str;
	}
}