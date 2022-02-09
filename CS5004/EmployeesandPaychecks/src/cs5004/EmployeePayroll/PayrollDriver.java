/* 
 * Created by: "Robert Wilson"
 * Date: "07 February 2022"
 * CS5004--Spring 2022
 */

package cs5004.EmployeePayroll;

public class PayrollDriver {

	public static void main(String[] args) {
		Employee iris = new Employee("Iris West", 35.00);
		iris.addHoursWorked(40);
		double weekPay1 = iris.getWeeklyCheck();
		iris.resetHoursWorked();
		iris.addHoursWorked(70);
		double weekPay2 = iris.getWeeklyCheck();
		System.out.println(iris);
		System.out.println("Iris made %.2f the first week and %.2f the second week\n".formatted(weekPay1, weekPay2));
		
		Employee cisqo = new Employee("Cisqo Ramone", 55.00);
		cisqo.addHoursWorked(40);
		weekPay1 = cisqo.getWeeklyCheck();
		cisqo.resetHoursWorked();
		cisqo.addHoursWorked(70);
		weekPay2 = cisqo.getWeeklyCheck();
		System.out.println(cisqo);
		System.out.println("Cisqo made %.2f the first week and %.2f the second week\n".formatted(weekPay1, weekPay2));
		
		Employee snow = new Employee("Kate Snow", 75.00);
		snow.addHoursWorked(40);
		weekPay1 = snow.getWeeklyCheck();
		snow.resetHoursWorked();
		snow.addHoursWorked(70);
		weekPay2 = snow.getWeeklyCheck();
		System.out.println(snow);
		System.out.println("Kate made %.2f the first week and %.2f the second week\n".formatted(weekPay1, weekPay2));
		
		System.out.println("We currently have " + Employee.getNumEmployees() + " employees at Star Labs in Central City");
	}

}
