package com.springboot.demo.service;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.springboot.demo.EmployeeModelResponse.EmployeeModelResponse;
import com.springboot.demo.modelrequest.EmployeeModelRequest;


public interface EmployeeService {

	public EmployeeModelResponse findAll();
	
	public EmployeeModelResponse findEmployeeById(int employeeId);
	
	public EmployeeModelResponse findEmployeeByAddress(EmployeeModelRequest modelRequest);

	public EmployeeModelResponse createEmployee(EmployeeModelRequest modelRequest);
	
	public EmployeeModelResponse deleteEmployee(int employeeId);

	public boolean updateEmployee(@Valid EmployeeModelRequest modelRequest);
}
