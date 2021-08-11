package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.entity.Employee;
import com.app.exceptions.customExceptions.DatabaseException;

public interface ServiceInterface {

	public Employee addEmployee(Employee emp) throws DatabaseException;
	
	public Employee updateEmployee(int id, Employee employee) throws DatabaseException;
	
	public boolean deleteEmployee(int id) throws DatabaseException;
	
	public List<Employee> getAllEmp() throws DatabaseException;

	Employee getEmp(int id) throws DatabaseException; 
}
