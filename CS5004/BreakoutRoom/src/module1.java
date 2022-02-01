/*
Demo Code for Basic data types and ranges
Author : Dr. G
Date : 7/31/2020
Edited : 5/13/2021
Edited : 9/17/2021
*/


//class DataTypes
public class module1
{
	public static void main(String args[])
	{
		
		System.out.println("Woot");
		
	/*
		There's an associated ICE with this demonstration.
		What is the purpose of typing? What's happening in memory?
	*/
		
	/*
    byte: The byte data type is an 8-bit signed two's complement integer. It has a minimum value of -128 and a maximum value of 127 (inclusive). The byte data type can be useful for saving memory in large arrays, where the memory savings actually matters. They can also be used in place of int where their limits help to clarify your code; the fact that a variable's range is limited can serve as a form of documentation.

    short: The short data type is a 16-bit signed two's complement integer. It has a minimum value of -32,768 and a maximum value of 32,767 (inclusive). As with byte, the same guidelines apply: you can use a short to save memory in large arrays, in situations where the memory savings actually matters.

    int: By default, the int data type is a 32-bit signed two's complement integer, which has a minimum value of -2,147,483,648 and a maximum value of 2,147,483,647. In Java SE 8 and later, you can use the int data type to represent an unsigned 32-bit integer giving a larger postive range. Use the Integer class to use int data type as an unsigned integer. See the section The Number Classes for more information. Static methods like compareUnsigned, divideUnsigned etc have been added to the Integer class to support the arithmetic operations for unsigned integers.

    long: The long data type is a 64-bit two's complement integer. The signed long has a minimum value of -263 and a maximum value of 263-1. In Java SE 8 and later, you can use the long data type to represent an unsigned 64-bit long, which has a minimum value of 0 and a maximum value of 264-1. Use this data type when you need a range of values wider than those provided by int. The Long class also contains methods like compareUnsigned, divideUnsigned etc to support arithmetic operations for unsigned long.

    float: The float data type is a single-precision 32-bit IEEE 754 floating point. Its range of values is beyond the scope of this discussion, but is specified in the Floating-Point Types, Formats, and Values section of the Java Language Specification. As with the recommendations for byte and short, use a float (instead of double) if you need to save memory in large arrays of floating point numbers. This data type should never be used for precise values, such as currency. For that, you will need to use the java.math.BigDecimal class instead. Numbers and Strings covers BigDecimal and other useful classes provided by the Java platform.

    double: The double data type is a double-precision 64-bit IEEE 754 floating point. Its range of values is beyond the scope of this discussion, but is specified in the Floating-Point Types, Formats, and Values section of the Java Language Specification. For decimal values, this data type is generally the default choice. As mentioned above, this data type should never be used for precise values, such as currency.

    boolean: The boolean data type has only two possible values: true and false. Use this data type for simple flags that track true/false conditions. This data type represents one bit of information, but its "size" isn't something that's precisely defined.

    char: The char data type is a single 16-bit Unicode character. It has a minimum value of '\u0000' (or 0) and a maximum value of '\uffff' (or 65,535 inclusive).
	
	Source : https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html
	
	*/
	
	/*
	For now you are only likely to use int, double, boolean, char, but I'd like you to explore all of the types briefly. 
	I'm putting you in breakout rooms. Uncomment each section and try to answer the question. 
	We'll come back together as a group after a set time. 
	*/
		
		/*
		//1. How much memory is being reserved here?
		int x;//Throw an error message on line 52 by making x a constant.
		x = 999;
		++x; //++x is a shortcut for x = x + 1 or x+=1
		System.out.println(x);
		*/
		
		
		/*
		//2. if I don't assign it anything what goes in there? 
		//Discuss this and come up with a theory.
		//Why is this a bad idea.
		int x; 
		System.out.println(x);
		*/
	
		
		//Uncomment the below section and observe the results
		//3. How could this be useful?
		//Why is there a limit?
		/*
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Integer.MIN_VALUE);
		
		System.out.println(Double.MAX_VALUE);
		System.out.println(Double.MIN_VALUE);
		*/
		
		
		//4. For each line: 
		//uncomment the print statement and the run each line one at a time by 
		//commenting one back in and then back out. 
		// 		predict what's going to happen
		//		observe the actual results
		//		explain what's going on if you can
		
		//int x = 4.6; //What is this type of conversion called?
	    //int x = (int)4.6; //What is this type of conversion called?
		//int x = 9/4;
		
		//System.out.println(x);
		
		//5. Explain what's going on
		/*
		double y = 5.8;
		//int x = y;
		int x = (int) y;
		
		System.out.println(x);
		System.out.println(y);
		*/
		
		//6. Explain what's going on. When would you use this data type?
		/*
		byte x = 126;
		System.out.println(x);
		x = 127; x++;
		System.out.println(x);
		*/
		
		//7. Explain what's going. on. When would you use this data type?
		/*
		//short x = 32768;
		//System.out.println(x);
		short x = 32767; x++;
		System.out.println(x);
		*/
		
		//8. Experiment with the below code. When would you use this data type?
		// In Java "or" is represented by || and "and" is represented as &&
		//boolean x = 0;
	    //boolean x = true;
		//x = !x;
		//x = x || false;
		
		//System.out.println(x);
		
		
		//9. Explain what's going on here. 
		//char x = 'A';
		//char x = 65; 
		//char x = 'A'; x++;
		//char x = 128;
		 
		//System.out.println("x = " + x);
	}
}