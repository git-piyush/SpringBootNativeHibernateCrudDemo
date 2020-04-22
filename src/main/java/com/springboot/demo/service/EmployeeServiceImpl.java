package com.springboot.demo.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.demo.EmployeeModelResponse.EmployeeModelResponse;
import com.springboot.demo.dao.EmployeeDao;
import com.springboot.demo.modelrequest.EmployeeModelRequest;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private EmployeeModelResponse modelResponse;
	
	@Override
	public EmployeeModelResponse findAll() {
		modelResponse = employeeDao.findAll();
		return modelResponse;
	}

	@Override
	public EmployeeModelResponse findEmployeeById(int employeeId) {
		modelResponse = employeeDao.findEmployeeById(employeeId);
		return modelResponse;
	}

	@Override
	public EmployeeModelResponse findEmployeeByAddress(EmployeeModelRequest modelRequest) {
		modelResponse = employeeDao.findEmployeeByAddress(modelRequest);
		return modelResponse;
	}

	@Override
	public EmployeeModelResponse createEmployee(EmployeeModelRequest modelRequest) {
		modelResponse = employeeDao.createEmployee(modelRequest);
		return modelResponse;
	}

	@Override
	public EmployeeModelResponse deleteEmployee(int employeeId) {
		modelResponse = employeeDao.deleteEmployee(employeeId);
		return modelResponse;
	}

	@Override
	public boolean updateEmployee(@Valid EmployeeModelRequest modelRequest) {
		return employeeDao.updateEmployee(modelRequest);
	}

	
}
