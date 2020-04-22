package com.springboot.demo.rest;

import java.util.List;

import javax.validation.Valid;

import org.apache.tomcat.util.modeler.modules.ModelerSource;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.demo.EmployeeModelResponse.EmployeeModelResponse;
import com.springboot.demo.dao.EmployeeDao;
import com.springboot.demo.entity.Employee;
import com.springboot.demo.modelrequest.EmployeeModelRequest;
import com.springboot.demo.service.EmployeeService;
import com.springboot.demo.validation.EmployeeValidation;

@RestController
@RequestMapping("/")
public class EmployeeRestController {
	
	private EmployeeService employeeService;
	
	private EmployeeDao employeeDao;
	//inject employee dao
	@Autowired
	public EmployeeRestController(EmployeeService employeeService, EmployeeDao employeeDao) {
		super();
		this.employeeService = employeeService;
		this.employeeDao = employeeDao;
	}
	
	@GetMapping("/getall")
	public EmployeeModelResponse findAll() {
		EmployeeModelResponse modelResponse = employeeService.findAll();
		return modelResponse;
	}

	@GetMapping("/getemployeebyid")
	public EmployeeModelResponse findEmployeeById(@Valid @RequestBody EmployeeModelRequest modelRequest){
		EmployeeValidation employeeValidation = new EmployeeValidation();
		EmployeeModelResponse response = new EmployeeModelResponse();
		boolean getEmployee = employeeValidation.IdInputVal(modelRequest.getEmployeeId());
		
		if(getEmployee) {
			response = employeeService.findEmployeeById(modelRequest.getEmployeeId());
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
	
	@GetMapping("/getemployeebyaddr")
	public EmployeeModelResponse findEmployeeByAddr(@Valid @RequestBody EmployeeModelRequest modelRequest) {
		EmployeeValidation employeeValidation = new EmployeeValidation();
		EmployeeModelResponse modelResponse = new EmployeeModelResponse();
		boolean getEmployeeList = employeeValidation.RetrieveAddrInputVal(modelRequest);
		if(getEmployeeList) {
			modelResponse = employeeService.findEmployeeByAddress(modelRequest);
			 if(modelResponse.getEmpList()==null || modelResponse.getEmpList().isEmpty()) {
				 modelResponse.setErrorMsg("Result Not Found");
				 return modelResponse;
			 }
			 modelResponse.setErrorMsg("Sucess");
			 return modelResponse;
		}
		modelResponse.setErrorMsg("Input Required");
		return modelResponse;
	}
	
	@PostMapping("/createemployee")
	public EmployeeModelResponse createEmployee(@Valid @RequestBody EmployeeModelRequest modelRequest) {
		EmployeeModelResponse modelResponse = null;
		EmployeeValidation employeeValidation = new EmployeeValidation();
		modelResponse = employeeValidation.createEmployeeAddrVal(modelRequest);
		if(modelResponse.getErrorMsg()==null) {
			modelResponse = employeeService.createEmployee(modelRequest);
			modelResponse.setErrorMsg("Sucess");
			return modelResponse;
		}
		return modelResponse;
	}
	
	@DeleteMapping("/deleteemployee")
	public EmployeeModelResponse deleteEmployeeById(@Valid @RequestBody EmployeeModelRequest modelRequest) {
		EmployeeModelResponse employeeDeleted = null;
		EmployeeValidation employeeValidation = new EmployeeValidation();
		boolean deleteEmployee = employeeValidation.deleteEmployeeIdVal(modelRequest);
		if(deleteEmployee) {
			employeeDeleted = employeeService.deleteEmployee(modelRequest.getEmployeeId());
			return employeeDeleted;
		}
		return employeeDeleted;
	}
	
	@PutMapping("/updateemployeebyid")
	public EmployeeModelResponse updateEmployeeById(@Valid @RequestBody EmployeeModelRequest modelRequest) {
		EmployeeModelResponse modelResponse = new EmployeeModelResponse();
		EmployeeValidation employeeValidation = new EmployeeValidation();
		boolean updateEmployee = employeeValidation.IdInputVal(modelRequest.getEmployeeId());
		if(updateEmployee) {
			modelResponse = employeeService.findEmployeeById(modelRequest.getEmployeeId());
			if(modelRequest.getUpdate().equalsIgnoreCase("Y")) {
				if(!(modelRequest.getNewFirstName().equals(modelResponse.getEmployeeRes().getFirstName())) ||
						!(modelRequest.getNewLastName().equals(modelResponse.getEmployeeRes().getLastName())) || 
							!(modelRequest.getNewEmail().equals(modelResponse.getEmployeeRes().getEmail()))) {
					
					boolean empUpdated = employeeService.updateEmployee(modelRequest);
					if(empUpdated) {
						modelResponse = employeeService.findEmployeeById(modelRequest.getEmployeeId());
						modelResponse.setErrorMsg("Employee Update To :");
						return modelResponse;
					}
					modelResponse.setErrorMsg("Result Not Found");
					return modelResponse;
				}
				modelResponse.setErrorMsg("Current employee and Updated Employee have the same values");
				return modelResponse;
			}
			modelResponse.setErrorMsg("To Update below record make update field Y.");
			return modelResponse;
		}
		modelResponse.setErrorMsg("Id is mandatory field to update a employee.");
		return modelResponse;
	}
	
}
