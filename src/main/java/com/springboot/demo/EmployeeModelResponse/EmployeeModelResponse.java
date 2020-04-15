package com.springboot.demo.EmployeeModelResponse;

import com.springboot.demo.entity.Employee;

public class EmployeeModelResponse {
	private String errorMsg;
	private Employee employeeRes;
	
	public EmployeeModelResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee getEmployeeRes() {
		return employeeRes;
	}

	public void setEmployeeRes(Employee employeeRes) {
		this.employeeRes = employeeRes;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	
}
