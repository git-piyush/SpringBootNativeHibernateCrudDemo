package com.springboot.demo.dao;

import java.util.List;

import javax.validation.Valid;

import com.springboot.demo.EmployeeModelResponse.EmployeeModelResponse;
import com.springboot.demo.entity.Employee;
import com.springboot.demo.modelrequest.EmployeeModelRequest;

public interface EmployeeDao {

	public EmployeeModelResponse findAll();
	
	public EmployeeModelResponse findEmployeeById(int employeeId);
	
	public EmployeeModelResponse findEmployeeByAddress(EmployeeModelRequest modelRequest);

	public EmployeeModelResponse createEmployee(EmployeeModelRequest modelRequest);
	
	public EmployeeModelResponse deleteEmployee(int employeeId);

	public boolean updateEmployee(@Valid EmployeeModelRequest modelRequest);

}
