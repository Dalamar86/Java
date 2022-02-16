package cs5004.temp;

public class TemperatureDriver {

	public static void main(String[] args) {
		// TemperatureImp temp = new CelsiusTemperature(2.0);
		TemperatureImp temp = new CelsiusTemperature(20.0);
		TemperatureImp temp2 = new FahrenheitTemperature(68.0);
		System.out.println(temp.celsius + ", " + temp.fahrenheit + ", " + temp.kelvin);
		System.out.println(temp2.celsius + ", " + temp2.fahrenheit + ", " + temp2.kelvin);
	}

}
