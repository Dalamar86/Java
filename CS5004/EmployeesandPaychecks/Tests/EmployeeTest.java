/* 
 * Created by: "Robert Wilson"
 * Date: "07 February 2022"
 * CS5004--Spring 2022
 */
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import cs5004.EmployeePayroll.*;

public class EmployeeTest {

	String name1;
	String name2;
	String prntStr;
	
	double pay;
	double payRate1;
	double payRate2;
	
	Employee joe;
	Employee west;
	Employee barry;
	
	@Before
	public void setUp() throws Exception {
		name1 = "Joe West";
		payRate1 = 21.50;
		joe = new Employee(name1, payRate1);
		west = new Employee(name1, payRate1);
		name2 = "Barry Allen";
		payRate2 = 19.50;
		barry = new Employee(name2, payRate2);
	}

	@Test
	public void getterTest() {
		assertEquals(name1, joe.getName());
		assertEquals(name2, barry.getName());
		assertEquals(payRate1, joe.getPayRate(), 0.1);
		assertEquals(payRate2, barry.getPayRate(), 0.1);
	}
	
	@Test
	public void addHoursWorkedtest() {
		assertEquals(0.0, joe.getHours(), 0.1);
		joe.addHoursWorked(35.0);
		barry.addHoursWorked(50.0);
		assertEquals(35.0, joe.getHours(), 0.1);
		assertEquals(50.0, barry.getHours(), 0.1);
	}
	
	@Test
	public void getWeeklyCheckTest() {
		joe.addHoursWorked(35.0);
		barry.addHoursWorked(50.0);
		pay = 752.50;
		assertEquals(pay, joe.getWeeklyCheck(), 0.1);
		pay = 1072.50;
		assertEquals(pay, barry.getWeeklyCheck(), 0.1);
	}
	
	@Test
	public void resetHoursWorkedTest() {
		joe.resetHoursWorked();
		assertEquals(0.0, joe.getHours(), 0.1);
		barry.resetHoursWorked();
		assertEquals(0.0, barry.getHours(), 0.1);
	}

	@Test
	public void toStringTest() {
		String payStr = "%.2f".formatted(barry.getPayRate());
		prntStr = "Name: " + name2 + "\nPayRate: " + payStr
				+ "\nHours Worked This Week: " + barry.getHours() + "\n";
		assertEquals(barry.toString(), (prntStr));
	}
	
	@Test
	public void isEqualsTest() {
		assertTrue(joe.equals(west));
		assertFalse(joe.equals(barry));
	}
	/*
	 * It Doesn't make sense to test this out since JUnit Test makes a 
	 * new instance of every object for each Test case. 
	 * You also can not predict where this test will be run as they are 
	 * not always run in the order they are defined.
	 
	@Test
	public void getNumEmployeesTest() {
		System.out.println(Employee.getNumEmployees());
		assertEquals(21, Employee.getNumEmployees());
	}
	*/
}
