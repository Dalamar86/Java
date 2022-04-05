package organization;
/*
This class has to be redesigned to use the generic tree node system instead of the former system. 
I've included a backup of this file in case it is useful to you. 
You'll need to populate the body based on the module and add the requested additional functionality.
*/

import java.util.List;
import java.util.function.Predicate;

import employee.*;
import tree.*;

public class OrganizationImpl implements Organization {

	// Must have a CEO, or the root of the hierarchy.  We will implement the TreeNode interface
	// for the organization of the organization.
	private TreeNode<Employee> root;
	
	public OrganizationImpl(String name, double pay, Gender gender) {
		root = new LeafNode<>(new InternalEmployee(name, pay, gender));
	}

	@Override
	public void addEmployee(String name, double pay, Gender gender, String supervisorName) {
		Employee newEmployee = new InternalEmployee(name, pay, gender);
	    TreeNode<Employee> newNode = new LeafNode<>(newEmployee);
	    root = root.addChild(e->e.getName().equals(supervisorName), newNode);
	}

	@Override
	public void addContractEmployee(String name, double pay, Gender gender, int endDate, int endMonth, int endYear, String supervisorName) {
		Employee newEmployee = new ContractEmployee(name, pay, gender, endDate, endMonth, endYear);
	    TreeNode<Employee> newNode = new LeafNode<>(newEmployee);
	    root = root.addChild(e->e.getName().equals(supervisorName),newNode);
	}

	@Override
	public int getSize() {
		return root.map(e-> Integer.valueOf(1)).reduce(0, (a,b)->a+b);
	}

	/**
	 * Get the size of the conditioned subset of the organization.  This is a generalized version which can be used by many different 
	 * predicates.  
	 * 
	 * @param condition (Predicate) the conditions to test for
	 * @return (int) number of instances with the given condition
	 */
	public int getSize(Predicate<Employee> condition) {
		return root.map(
	            e-> { 
	              if (condition.test(e)) { //condition to be counted
	                return Integer.valueOf(1);
	              } else {
	                return Integer.valueOf(0);
	              }
	            }).reduce(0, (a,b)->a+b);
	}
	
	/**
	 * Returns the number of the specified gender in the organization.  This method creates a predicate to send
	 * to getSize(Predicate<Employee> condition).
	 * 
	 * @param gender (Gender) gender you are filtering for
	 * @return (int) size of the subset specified
	 */
	public int getSizeGender(Gender gender) {
		return getSize(e-> e.getGender() == gender);
	}
	
	/**
	 * Returns the number of the specified annual pay in the organization.  This method creates a predicate to send
	 * to getSize(Predicate<Employee> condition).
	 * 
	 * @param pay (double) pay we are filtering for
	 * @return (int) size of the subset specified
	 */
	public int getSizePay(double pay) {
		return getSize(e-> e.getAnnualPay() == pay);
	}
	
	@Override
	public int getSizeByGender(Gender gender) {
		return root.map(
	            e-> { 
	              if (e.getGender() == gender) { //condition to be counted
	                return Integer.valueOf(1);
	              } else {
	                return Integer.valueOf(0);
	              }
	            }).reduce(0, (a,b)->a+b);
	}

	@Override
	public List<String> allEmployees() {
		return root.map(e->e.getName()).toList();
	}
	
	/*
	@Override
	public List<String> allEmployees(Predicate<Employee> condition) {
		return root.map(e-> condition.test(e)).toList();
	}
	*/

	/**
	 * Adds an employee who has already been created.
	 * 
	 * @param m (Employee) The employee to add to the hierarchy
	 * @param supervisorName (String) the employees supervisor
	 */
	public void addEmployee(Employee m, String supervisorName) {
	    TreeNode<Employee> newNode = new LeafNode<>(m);
	    root = root.addChild(e->e.getName().equals(supervisorName), newNode);
	}

	/**
	 * Prints all employees details.  Calls the Tree's print() method.
	 */
	public void printEmployees() {
		root.print();		
	}
}