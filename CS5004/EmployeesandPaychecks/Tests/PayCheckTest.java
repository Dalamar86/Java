/* 
 * Created by: "Robert Wilson"
 * Date: "07 February 2022"
 * CS5004--Spring 2022
 */
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import cs5004.EmployeePayroll.*;

public class PayCheckTest {

	private String name1 = "Joe West";
	private String name2 = "Barry Allen";
	private double payRate1 = 21.50;
	private double payRate2 = 19.50;
	private double hours1 = 35.0;
	private double hours2 = 50.0;
	
	private String prntStr;
	private Employee joe;
	private Employee barry;
	
	private PayCheck check1;
	private PayCheck check2;
	
	@Before
	public void setUp() throws Exception {
		joe = new Employee(name1, payRate1);
		barry = new Employee(name2, payRate2);
		joe.addHoursWorked(hours1);
		barry.addHoursWorked(hours2);
		check1 = new PayCheck(joe);
		check2 = new PayCheck(barry);
	}

	@Test
	public void gettersTest() {
		assertEquals(name1, check1.getName());
		assertEquals(name2, check2.getName());
		assertEquals(payRate1, check1.getRate(), 0.1);
		assertEquals(payRate2, check2.getRate(), 0.1);
		assertEquals(hours1, check1.getHours(), 0.1);
		assertEquals(hours2, check2.getHours(), 0.1);
	}
	
	@Test
	public void getTotalPayTest() {
		double pay = 752.50;
		assertEquals(pay, check1.getTotalPay(), 0.1);
		pay = 1072.50;
		assertEquals(pay, check2.getTotalPay(), 0.1);
	}

	@Test
	public void toStringTest() {
		String payStr = "%.2f".formatted(check1.getRate());
		String payStr2 ="%.2f".formatted(check1.getTotalPay());
		prntStr = "Name: " + name1 + "\nPayRate: " + payStr
				+ "\nHours Worked This Week: " + hours1 +  "\nTotal Pay This Week: "
				+ payStr2 + "\n";
		assertEquals(check1.toString(), (prntStr));
	}
}
