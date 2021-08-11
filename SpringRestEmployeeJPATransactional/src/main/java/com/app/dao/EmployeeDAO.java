package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.app.entity.Employee;

public interface EmployeeDAO extends JpaRepository<Employee, Integer> {

	
	
	
	
}
