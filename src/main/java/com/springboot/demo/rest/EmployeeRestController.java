package com.springboot.demo.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.demo.EmployeeModelResponse.EmployeeModelResponse;
import com.springboot.demo.dao.EmployeeDao;
import com.springboot.demo.entity.Employee;
import com.springboot.demo.modelrequest.EmployeeModelRequest;
import com.springboot.demo.validation.EmployeeValidation;

@RestController
@RequestMapping("/")
public class EmployeeRestController {

	private EmployeeDao employeeDao;
	
	

	//inject employee dao
	@Autowired
	public EmployeeRestController(EmployeeDao employeeDao) {
		super();
		this.employeeDao = employeeDao;
	}
	@GetMapping("/getall")
	public List<Employee> findAll() {
		List<Employee> employeeList = employeeDao.findAll();
		return employeeList;
	}
	
	@GetMapping("/getemployeebyid")
	public EmployeeModelResponse findEmployeeById(@Valid @RequestBody EmployeeModelRequest modelRequest){
		EmployeeValidation employeeValidation = new EmployeeValidation();
		EmployeeModelResponse response = new EmployeeModelResponse();
		boolean getEmployee = employeeValidation.validateInput(modelRequest.getEmployeeId());
		
		if(getEmployee) {
			response = employeeDao.findEmployeeById(modelRequest.getEmployeeId());
			if(response.getEmployeeRes()==null) {
				response.setErrorMsg("Result Not found.");
				return response;
			}
			response.setErrorMsg("Sucess");
			return response;
		}
		response.setErrorMsg("Fail");
		return response;
		
	}
	
	
}
