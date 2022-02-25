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
		TemperatureImp temp;
		TemperatureImp temp6;
		TemperatureImp temp7;
		
		try {
			temp = new CelsiusTemperature(-275.0);
		} catch  (IllegalArgumentException e ) {
			e.printStackTrace();
			temp = new CelsiusTemperature(-273);
		}
		
		TemperatureImp temp1 = new CelsiusTemperature(100.0);
		TemperatureImp temp2 = new FahrenheitTemperature(68.0);
		TemperatureImp temp3 = new CelsiusTemperature(20.0);
		
		TemperatureImp temp4 = new FahrenheitTemperature(212.0, true);
		TemperatureImp temp5 = new FahrenheitTemperature(212.0, false);
		
		try {
			temp6 = new FahrenheitTemperature(-1000.0);
			temp7 = new CelsiusTemperature(-1000.0);
		} catch (IllegalArgumentException e ) { 
			temp6 = new FahrenheitTemperature(-459.67);
			temp7 = new CelsiusTemperature(-273.15);
		}
		
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
		
		// Static conversion calls
		System.out.println("\nStatic Conversion Statements:");
		System.out.println("\nConvert " + temp1 + " to fahrenheit: \n\t" + String.format("%.1f", TemperatureImp.cToF(temp1.inCelsius())) + "° Fahrenheit");
		System.out.println("\nConvert " + temp4 + " to celsius: \n\t" + String.format("%.1f", TemperatureImp.fToC(temp4.inFahrenheit())) + "° Celsius");
		System.out.println("\nConvert " + temp4 + " to kelvin: \n\t" + String.format("%.2f", TemperatureImp.fToK(temp4.inFahrenheit())) + "° Kelvin");
		System.out.println("\nConvert " + temp1 + " to kelvin: \n\t" + String.format("%.2f", TemperatureImp.cToK(temp1.inCelsius())) + "° Kelvin");
		System.out.println("\nConvert " + String.format("%.2f", temp4.inKelvin()) + "° kelvin to fahrenheit: \n\t" + String.format("%.2f", TemperatureImp.kToF(temp4.inKelvin())) + "° Fahrenheit");
		System.out.println("\nConvert " + String.format("%.2f", temp1.inKelvin()) + "° kelvin to celsius: \n\t" + String.format("%.2f", TemperatureImp.kToC(temp1.inKelvin())) + "° Celsius");
	}
}