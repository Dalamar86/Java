package cs5004.temp;

/**
 * This class describes a temperature object which is extended by fahrenheit and celsius.
 * 
 * @author Robert Wilson
 *
 */
public class TemperatureImp implements Temperature {
	/**
	 * temperature in celsius
	 */
	protected Double celsius = null;
	/**
	 *  temperature in fahrenheit
	 */
	protected Double fahrenheit = null;
	/**
	 * temperature in kelvin
	 */
	protected double kelvin;
	/**
	 * the input temperature
	 */
	protected double temperature;
	
	/**
	 * Construct the temperature object which is extended by CelsiusTemperature and fahrenheitTemperature
	 * 
	 * @param temperature (double) temperature input
	 */
	public TemperatureImp(double temperature) {
		this.temperature = temperature;
	}
	
	@Override
	public double inCelsius() {
		celsius = kelvin + ABS_ZERO_C;
		return celsius;
	}
	
	@Override
	public double inFahrenheit() {
		
		fahrenheit = ((kelvin + ABS_ZERO_C)*(9.0/5.0))+32.0;
		return fahrenheit;
	}
	
	@Override
	public double inKelvin () throws IllegalArgumentException  {
		
		if(fahrenheit == null) {
			kelvin = celsius - ABS_ZERO_C;
		} else if(celsius == null) {
			kelvin = (fahrenheit-32.0)*(5.0/9.0) - ABS_ZERO_C;
		} 
		if(kelvin < 0) { throw new IllegalArgumentException("Temperature is below know limits of 'Absolute Zero'");}
		
		return kelvin;
	}
	
	/**
	 * Overrides equals method to compare the kelvin temperatures of two values
	 * 
	 * @param t1 (Temperaturemp) temperature to compare with
	 * @return
	 */
	@Override
	public boolean equals(Object o) {
		TemperatureImp t1 = (TemperatureImp)o; 
		if(this.kelvin == t1.kelvin) { return true;}
		return false;
	}
	
	@Override
	public Boolean isFreezing() {
		if(kelvin <= -ABS_ZERO_C) {
			return true;
		}
		return false;
	}
}
