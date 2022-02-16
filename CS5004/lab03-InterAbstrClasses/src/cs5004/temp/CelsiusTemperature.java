package cs5004.temp;

public class CelsiusTemperature extends TemperatureImp {
	
	public CelsiusTemperature (double temperature) {
		super(temperature);
		this.celsius = temperature;
		this.kelvin = super.inKelvin();
		this.fahrenheit = super.inFahrenheit();	
	}
}
