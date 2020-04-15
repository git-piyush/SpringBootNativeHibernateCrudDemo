package com.springboot.demo.validation;

public class EmployeeValidation {

	public EmployeeValidation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean validateInput(int theId) {  
		String employeeId=String.valueOf(theId); 
		
		if(theId>0) {
			if(null != employeeId) {
				return true;
			}
		}
		return false;
		
	}
}
