package com.springboot.demo.dao;

import java.util.List;

import com.springboot.demo.entity.Employee;

public interface EmployeeDao {

	public List<Employee> findAll();
}
