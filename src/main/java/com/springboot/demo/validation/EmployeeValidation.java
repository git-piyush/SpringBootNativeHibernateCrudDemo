package com.springboot.demo.validation;

import javax.validation.Valid;

import com.springboot.demo.modelrequest.EmployeeModelRequest;

public class EmployeeValidation {

	public EmployeeValidation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean validateIdInput(int theId) {  
		String employeeId=String.valueOf(theId); 
		
		if(theId>0) {
			if(null != employeeId) {
				return true;
			}
		}
		return false;
	}

	public boolean validateAddrInput(EmployeeModelRequest modelRequest) {
		if((null==modelRequest.getFirstName() || "".endsWith(modelRequest.getFirstName())) && 
				(null==modelRequest.getLastName() || "".endsWith(modelRequest.getLastName()))
					&& (null==modelRequest.getEmail() || "".endsWith(modelRequest.getEmail()))) {
			return false;
		}
		
		
		return true;
	}

	public boolean createAddrVal(@Valid EmployeeModelRequest modelRequest) {
		
		return false;
	}
}
