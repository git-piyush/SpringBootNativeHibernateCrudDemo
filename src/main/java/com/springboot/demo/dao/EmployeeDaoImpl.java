package com.springboot.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.demo.EmployeeModelResponse.EmployeeModelResponse;
import com.springboot.demo.entity.Employee;
import com.springboot.demo.modelrequest.EmployeeModelRequest;
import com.springboot.demo.rest.EmployeeRestController;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	//define field for entitymanager
	private EntityManager entityManager;
	
	//set up constructor injection
	@Autowired
	public EmployeeDaoImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}
	@Override
	@Transactional
	public EmployeeModelResponse findAll() {
		EmployeeModelResponse modelResponse = new EmployeeModelResponse();
		//get the current session
		Session currentSession = entityManager.unwrap(Session.class);
		//create a query
		Query query = currentSession.createQuery("from Employee");
		//execute query and get result
		List<Employee> employeeList = query.getResultList();
		
		modelResponse.setEmpList(employeeList);
		//return result
		return modelResponse;
	}
	@Override
	@Transactional
	public EmployeeModelResponse findEmployeeById(int employeeId) {
		EmployeeModelResponse employee = new EmployeeModelResponse();
		Session currentSession = entityManager.unwrap(Session.class);
		Employee employee1 = currentSession.get(Employee.class, employeeId);
		employee.setEmployeeRes(employee1);
		return employee;
	}
	@Override
	@Transactional
	public EmployeeModelResponse findEmployeeByAddress(EmployeeModelRequest modelRequest) {
		EmployeeModelResponse modelResponse = new EmployeeModelResponse();
		Session currentSession = entityManager.unwrap(Session.class);
		Query query = currentSession.createQuery("from Employee e where e.firstName = :fName and e.lastName = :lName and e.email = :email");
		query.setParameter("fName", modelRequest.getFirstName());
		query.setParameter("lName", modelRequest.getLastName());
		query.setParameter("email", modelRequest.getEmail());
		List<Employee> employeeList = query.getResultList();
		modelResponse.setEmpList(employeeList);
		return modelResponse;
	}
	
	@Override
	@Transactional
	public EmployeeModelResponse createEmployee(EmployeeModelRequest modelRequest) {
		EmployeeModelResponse modelResponse = new EmployeeModelResponse();
		Employee emp = new Employee();
		emp.setFirstName(modelRequest.getFirstName());
		emp.setLastName(modelRequest.getLastName());
		emp.setEmail(modelRequest.getEmail());		
		Session currentSession = entityManager.unwrap(Session.class);
		int theId = (int)currentSession.save(emp);
		Employee employee = currentSession.get(Employee.class, theId);
		modelResponse.setEmployeeRes(employee);
		return modelResponse;
	}
	@Override
	@Transactional
	public EmployeeModelResponse deleteEmployee(int employeeId) {
		EmployeeModelResponse modelResponse = new EmployeeModelResponse();
		Session currentSession = entityManager.unwrap(Session.class);
		Employee emp = currentSession.get(Employee.class, employeeId);
		
		try {
			currentSession.delete(emp);
			modelResponse.setEmployeeRes(emp);
			modelResponse.setErrorMsg("Below Employee removed sucessfully.");
			return modelResponse;
		} catch (Exception e) {
			modelResponse.setErrorMsg("Deletion Failed");
			return modelResponse;
		}
		
	}
	@Override
	@Transactional
	public boolean updateEmployee(@Valid EmployeeModelRequest modelRequest) {
		Session currentSession = entityManager.unwrap(Session.class);
		EmployeeModelResponse modelResponse = null;
		Query query = currentSession.createQuery("update Employee e set e.firstName='"+modelRequest.getNewFirstName()+"' , e.lastName='"+modelRequest.getNewLastName()+"' , e.email='"+modelRequest.getNewEmail()+"' where e.id='"+modelRequest.getEmployeeId()+"'");    
		int theId = query.executeUpdate();
		if(theId>0) {
			return true;
		}
		return false;
	}	
	
}
