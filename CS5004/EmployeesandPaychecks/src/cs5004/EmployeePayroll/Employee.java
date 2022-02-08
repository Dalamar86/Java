/* 
 * Created by: "Robert Wilson"
 * Date: "07 February 2022"
 * CS5004--Spring 2022
 */
package cs5004.EmployeePayroll;

/**
 * This class describes an Employee with name, pay rate, and hours.
 * 
 * @author Robert Wilson
 *
 */
public class Employee {
	
	private String name;
	private double payRate;
	private double hoursWorked;
	public static int numEmployees = 0;
	
	/**
	 * Constructor creates an Employee object with the given name and payRate. 
	 * It initializes the hoursWorked to zero.
	 * 
	 * @param name String name of this employee
	 * @param payRate double rate in dollars and two digit cents of this employee
	 */
	public Employee(String name, double payRate) {
		setName(name);
		setPayRate(payRate);
		hoursWorked = 0.0;
		numEmployees++;
	}
	
	/**
	 * @override equals
	 * 
	 * @param emp (Employee) to compare with
	 * @return boolean true or false of comparison
	 */
	public boolean equals(Employee emp) {
		return this.name.equals(emp.getName()) && this.payRate == emp.getPayRate() && this.hoursWorked == emp.getHours();
	}
	
	/**
	 * @override toString
	 * 
	 * @return prntStr (Str) formated string of object 
	 */
	public String toString() {
		String payStr = "%.2f".formatted(this.getPayRate());
		String prntStr = "Name: " + this.getName() + "\nPayRate: " + payStr
				+ "\nHours Worked This Week: " + this.getHours() + "\n";
		return prntStr;
	}
	
	/**
	 * Sets the name of the employee
	 * 
	 * @param name (Str) name of employee
	 */
	private void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the name of this Employee
	 * 
	 * @return (Str)name of employee
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Sets the pay rate of the employee as long as it is zero or above
	 * 
	 * @param payRate (double) rate of pay in dollars and two digit cents
	 */
	private void setPayRate(double payRate) {
		if(payRate <= 0.0)
			payRate = 0.0;
		this.payRate = payRate;
	}
	
	/**
	 * Returns the pay rate of this Employee
	 * 
	 * @return payRate (double) pay rate of this employee
	 */
	public double getPayRate() {
		return this.payRate;
	}
	
	/**
	 * Adds to the number of hours this employee has worked
	 * 
	 * @param hours (double) hours to be added
	 */
	public void addHoursWorked(double hours) {
		this.hoursWorked += hours;
	}
	
	/**
	 * Resets the hoursWorked field of this Employee to 0.0
	 */
	public void resetHoursWorked() {
		this.hoursWorked = 0.0;
	}
	
	/**
	 * Returns the number of hours this employee worked this week
	 * 
	 * @return hoursWorked (double) hours worked in a week
	 */
	public double getHours() {
		return this.hoursWorked;
	}
	
	/**
	 * Creates a PayCheck object and calls the getTotalPay method to get the pay of this Employee
	 * 
	 * @return pay (double) total pay of the employee based on hours and pay rate
	 */
	public double getTotalPay() {
		PayCheck check = new PayCheck(this);
		double pay = check.getTotalPay();
		return pay;
	}
	
	public static int getNumEmployees() {
		return numEmployees;
	}
}
