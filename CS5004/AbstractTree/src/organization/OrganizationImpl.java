package organization;
/*
This class has to be redesigned to use the generic tree node system instead of the former system. 
I've included a backup of this file in case it is useful to you. 
You'll need to populate the body based on the module and add the requested additional functionality.
*/

import java.util.List;

import employee.Gender;
import employee.NonManagerEmployee;

public class OrganizationImpl implements Organization {

	public OrganizationImpl(String string, double d, Gender undisclosed) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addEmployee(String name, double pay, Gender gender, String supervisorName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addContractEmployee(String name, double pay, Gender gender, int endDate, int endMonth, int endYear,
			String supervisorName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSizeByGender(Gender gender) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> allEmployees() {
		// TODO Auto-generated method stub
		return null;
	}

	public void addEmployee(NonManagerEmployee m2, String string) {
		// TODO Auto-generated method stub
		
	}}