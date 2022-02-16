package cs5004.temp;

public class TemperatureImp implements Temperature {
	protected Double celsius = null;
	protected Double fahrenheit = null;
	protected double kelvin;
	protected double temperature;
	
	
	public TemperatureImp(double temperature) {
		this.temperature = temperature;
	}
	
	@Override
	public double inCelsius() {
		celsius = kelvin - 273.15;
		return celsius;
	}
	@Override
	public double inFahrenheit() {
		
		fahrenheit = ((kelvin- 273.15)*(9.0/5.0))+32.0;
		return fahrenheit;
	}
	@Override
	public double inKelvin() {
		
		if(fahrenheit == null) {
			kelvin = celsius + 273.15;
		} else if(celsius == null) {
			kelvin = (fahrenheit-32.0)*(5.0/9.0) + 273.15;
		} 
		
		return kelvin;
	}
}
