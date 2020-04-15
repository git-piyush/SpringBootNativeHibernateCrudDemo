package com.springboot.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.demo.entity.Employee;

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
	public Employee findEmployeeById(int employeeId) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		Employee employee = currentSession.get(Employee.class, employeeId);
		
		return employee;
	}
}
