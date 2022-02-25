package cs5004.temp;

/**
 * This class describes a temperature object which is extended by fahrenheit and celsius.
 * 
 * @author Robert Wilson
 *
 */
public abstract class TemperatureImp implements Temperature {
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
	
	//################ Was told we do not need to include the constructor here for the lab so i have removed it and its associated field ############################//
	
	/**
	 * the input temperature
	 *
	protected double temperature;
	
	/**
	 * Construct the temperature object which is extended by CelsiusTemperature and fahrenheitTemperature
	 * 
	 * @param temperature (double) temperature input
	 *
	public TemperatureImp(double temperature) {
		this.temperature = temperature;
	}
	*/
	
	//#################################################################################################################################################################//
	
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
		if(kelvin < -0.00001) { throw new IllegalArgumentException("Temperature is below know limits of 'Absolute Zero'");}
		
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
	
	//################################### Static conversion methods #############################################//
	
	/**
	 * Converts given celsius temperature to fahrenheit
	 * 
	 * @param c (double) temperature in celsius
	 * @return f (double) temperature in fahrenheit
	 */
	public static double cToF(double c) {
		double f = (c*(9.0/5.0))+32.0;
		return f;
	}
	
	/**
	 * Converts given fahrenheit temperature to celsius
	 * 
	 * @param f (double) temperature in fahrenheit
	 * @return c (double) temperature in celsius
	 */
	public static double fToC(double f) {
		double c = (f-32.0) *(5.0/9.0);
		return c;
	}
	
	/**
	 * Converts given celsius temperature to kelvin
	 * 
	 * @param c (double) temperature in celsius
	 * @return k (double) temperature in kelvin
	 */
	public static double cToK(double c) {
		double k = c - ABS_ZERO_C;
		return k;
	}
	
	/**
	 * Converts given kelvin temperature to celsius
	 * 
	 * @param k (double) temperature in kelvin
	 * @return c (double) temperature in celsius
	 */
	public static double kToC(double k) {
		double c = k + ABS_ZERO_C;
		return c;
	}
	
	/**
	 * Converts given fahrenheit temperature to kelvin
	 * 
	 * @param f (double) temperature in fahrenheit
	 * @return k (double) temperature in kelvin
	 */
	public static double fToK(double f) {
		double k = (f-32.0) *(5.0/9.0)-ABS_ZERO_C;
		return k;
	}
	
	/**
	 * Converts given kelvin temperature to fahrenheit
	 * 
	 * @param k (double) temperature in kelvin
	 * @return f (double) temperature in fahrenheit
	 */
	public static double kToF(double k) {
		double f = ((k + ABS_ZERO_C)*(9.0/5.0))+32.0;
		return f;
	}
}
