package com.springboot.demo.validation;

import javax.validation.Valid;

import com.springboot.demo.EmployeeModelResponse.EmployeeModelResponse;
import com.springboot.demo.modelrequest.EmployeeModelRequest;

public class EmployeeValidation {

	public EmployeeValidation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean RetrieveIdInput(int theId) {  
		String employeeId=String.valueOf(theId); 
		
		if(theId>0) {
			if(null != employeeId) {
				return true;
			}
		}
		return false;
	}

	public boolean RetrieveAddrInput(EmployeeModelRequest modelRequest) {
		if((null==modelRequest.getFirstName() || "".equals(modelRequest.getFirstName())) && 
				(null==modelRequest.getLastName() || "".equals(modelRequest.getLastName()))
					&& (null==modelRequest.getEmail() || "".equals(modelRequest.getEmail()))) {
			return false;
		}
		
		
		return true;
	}

	public EmployeeModelResponse createEmployeeAddrVal(@Valid EmployeeModelRequest modelRequest) {
		EmployeeModelResponse modelResponse = new EmployeeModelResponse();
		if(null == modelRequest.getFirstName() || "".equals(modelRequest.getFirstName())) {
			modelResponse.setErrorMsg("Invalid Request");
			return modelResponse;
		}
		if(null == modelRequest.getLastName() || "".equals(modelRequest.getLastName())) {
			modelResponse.setErrorMsg("Invalid Request");
			return modelResponse;
		}
		if(null == modelRequest.getEmail() || "".equals(modelRequest.getEmail())) {
			modelResponse.setErrorMsg("Invalid Request");
			return modelResponse;
		}
		return modelResponse;
	}
}
