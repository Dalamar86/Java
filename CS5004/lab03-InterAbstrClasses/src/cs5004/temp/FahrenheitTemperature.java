package cs5004.temp;

public class FahrenheitTemperature extends TemperatureImp {

	public FahrenheitTemperature (double temperature) {
		super(temperature);
		this.fahrenheit = temperature;
		this.kelvin = super.inKelvin();
		this.celsius = super.inCelsius();
	}
}
