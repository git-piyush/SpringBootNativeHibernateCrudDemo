package com.springboot.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

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
	public List<Employee> findAll() {
		//get the current session
		Session currentSession = entityManager.unwrap(Session.class);
		//create a query
		Query<Employee> query = currentSession.createQuery("from Employee", Employee.class);
		//execute query and get result
		List<Employee> employeeList = query.getResultList();
		//return result
		return employeeList;
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
		Query<Employee> query = currentSession.createQuery("from Employee e where e.firstName = :fName or e.lastName = :lName or e.email = :email",Employee.class);
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
	
}
