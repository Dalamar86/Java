package cs5004.temp;

/**
 * Driver for temperature classes
 * 
 * @author Robert Wilson
 *
 */
public class TemperatureDriver {

	public static void main(String[] args) {
		// Initialize temperatures
		TemperatureImp temp = new CelsiusTemperature(-275.0);
		TemperatureImp temp1 = new CelsiusTemperature(100.0);
		TemperatureImp temp2 = new FahrenheitTemperature(68.0);
		TemperatureImp temp3 = new CelsiusTemperature(20.0);
		
		TemperatureImp temp4 = new FahrenheitTemperature(212.0, true);
		TemperatureImp temp5 = new FahrenheitTemperature(212.0, false);

		TemperatureImp temp6 = new FahrenheitTemperature(-1000.0);
		TemperatureImp temp7 = new CelsiusTemperature(-1000.0);
		
		// Print using toString Overrides
		System.out.println(temp);
		System.out.println(temp1);
		System.out.println(temp2);
		System.out.println(temp3);
		System.out.println(temp4);
		System.out.println(temp5);
		System.out.println(temp6);
		System.out.println(temp7);
		
		// Demonstrate each field is populated properly and isFreezing is working
		System.out.println("\ntemp\nCelsius:\t" + temp.celsius + "\nFahrenheit:\t" + temp.fahrenheit + "\nKelvin:\t" + String.format("%.2f", temp.kelvin) 
				+ "\nIs it Freezing:\t" + temp.isFreezing() + "\n");
		System.out.println("\ntemp1\nCelsius:\t" + temp1.celsius + "\nFahrenheit:\t" + temp1.fahrenheit + "\nKelvin:\t" + String.format("%.2f", temp1.kelvin) 
				+ "\nIs it Freezing:\t" + temp1.isFreezing() + "\n");
		System.out.println("temp2\nCelsius:\t" + temp2.celsius + "\nFahrenheit:\t" + temp2.fahrenheit + "\nKelvin:\t" + String.format("%.2f", temp2.kelvin)
				+ "\nIs it Freezing:\t" + temp2.isFreezing() + "\n");
		System.out.println("temp3\nCelsius:\t" + temp3.celsius + "\nFahrenheit:\t" + temp3.fahrenheit + "\nKelvin:\t" + String.format("%.2f", temp3.kelvin)
				+ "\nIs it Freezing:\t" + temp3.isFreezing() + "\n");
		System.out.println("temp4\nCelsius:\t" + temp4.celsius + "\nFahrenheit:\t" + temp4.fahrenheit + "\nKelvin:\t" + String.format("%.2f", temp4.kelvin)
				+ "\nIs it Freezing:\t" + temp4.isFreezing() + "\n");
		System.out.println("temp5\nCelsius:\t" + temp5.celsius + "\nFahrenheit:\t" + temp5.fahrenheit + "\nKelvin:\t" + String.format("%.2f", temp5.kelvin)
				+ "\nIs it Freezing:\t" + temp5.isFreezing() + "\n");
		System.out.println("\ntemp6\nCelsius:\t" + temp6.celsius + "\nFahrenheit:\t" + temp6.fahrenheit + "\nKelvin:\t" + String.format("%.2f", temp6.kelvin) 
				+ "\nIs it Freezing:\t" + temp6.isFreezing() + "\n");
		System.out.println("\ntemp7\nCelsius:\t" + temp7.celsius + "\nFahrenheit:\t" + temp7.fahrenheit + "\nKelvin:\t" + String.format("%.2f", temp7.kelvin) 
				+ "\nIs it Freezing:\t" + temp7.isFreezing() + "\n");
		
		// Demonstrate that the equals Overrides is working properly
		System.out.println("temp1 equlas temp2: " + temp1.equals(temp2));
		System.out.println("temp1 equlas temp3: " + temp2.equals(temp3));
	}
}