/* 
 * Created by: "Robert Wilson"
 * Date: "07 February 2022"
 * CS5004--Spring 2022
 */

package cs5004.EmployeePayroll;

/**
 * This class describes a pay check with an Employee name, pay rate, and hours worked
 * 
 * @author Robert Wilson
 *
 */
public class PayCheck {

	private String name;
	private double rate;
	private double hours;
	
	/**
	 * Construct a PayCheck with the given employee who has a name, pay rate, and hours worked
	 * 
	 * @param emp (Employee) with details of name, rate, and hours
	 */
	public PayCheck(Employee emp) {
		setName(emp.getName());
		setRate(emp.getPayRate());
		setHours(emp.getHours());
	}
	
	/**
	 * @override toString
	 * 
	 * @return prntStr (Str) formated string of object 
	 */
	public String toString() {
		String payStr = "%.2f".formatted(this.getRate());
		String payStr2 ="%.2f".formatted(this.getTotalPay());
		String prntStr = "Name: " + this.getName() + "\nPayRate: " + payStr
				+ "\nHours Worked This Week: " + this.getHours() + "\nTotal Pay This Week: "
				+ payStr2 + "\n";
		return prntStr;
	}
	
	/**
	 * Sets the name of the employee on the pay check
	 * 
	 * @param name (Str) name of employee
	 */
	private void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the name of this pay check
	 * 
	 * @return (Str)name of pay check
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Sets the pay rate of the employee on this pay check as long as it is above zero.
	 * 
	 * @param payRate (double) rate of pay in dollars and two digit cents
	 */
	private void setRate(double rate) {
		this.rate = rate;
	}
	
	/**
	 * Returns the pay rate of this pay check
	 * 
	 * @return payRate (double) pay rate of this pay check
	 */
	public double getRate() {
		return this.rate;
	}
	
	/**
	 * Set the hours the employee worked during this week on the pay check
	 * 
	 * @param hours (double) hours the employee worked this pay period
	 */
	private void setHours(double hours) {
		this.hours = hours;
	}

	/**
	 * Returns the number of hours the employee worked this week
	 * 
	 * @return hoursWorked (double) hours worked in a week
	 */
	public double getHours() {
		return this.hours;
	}
	
	/**
	 * Returns the total pay of this pay check depending on the time worked.  
	 * Anything over 40 hours constitutes overtime.  
	 * 
	 * @return pay (double) pay based on hours and rate
	 */
	public double getTotalPay() {
		double pay = 0.0;
		double totalHours = hours;
		double hoursOver = 0.0;
		if(hours > 40) {
			hoursOver = hours - 40.0;
			totalHours -= hoursOver;
		}
		totalHours *= rate;
		hoursOver *= (rate*1.5);
		pay = totalHours + hoursOver;
		return pay;
	}
}
