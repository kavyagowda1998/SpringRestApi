package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.app.dao.EmployeeDAO;
import com.app.entity.Employee;
import com.app.exceptions.customExceptions.DatabaseException;
import com.app.exceptions.customExceptions.RecordNotFoundException;

@Service
public class ServiceImpl implements ServiceInterface {

	@Autowired
	private EmployeeDAO dao;

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Employee addEmployee(Employee emp) throws DatabaseException {
		System.out.println("Revert");
		try {
			Employee employeeObj = dao.save(emp);
			System.out.println("Employee object added into database");
			return employeeObj;

		} catch (DataAccessException e) {
			throw new DatabaseException(e);
		}

	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Employee updateEmployee(int id, Employee employeeIn) throws DatabaseException {

		try {
			if (!dao.existsById(id)) {
				//custom unchecked run time exception
				throw new RecordNotFoundException("No Record found");
			}
			Employee employeeObj = dao.save(employeeIn);

			return employeeObj;
		} catch (DataAccessException e) {
			throw new DatabaseException(e);
		}

	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public boolean deleteEmployee(int id) throws DatabaseException {
		try {
			boolean isTrue;
			if (!dao.existsById(id)) {
				throw new RecordNotFoundException("No Record found");
			} else {
				dao.deleteById(id);
				return isTrue = true;

			}

		} catch (DataAccessException e) {
			throw new DatabaseException(e);
		}

	}

	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<Employee> getAllEmp() throws DatabaseException {
		try {
			List<Employee> employeeList = (List<Employee>) dao.findAll();// TODO Auto-generated method stub
			return employeeList;
		} catch (DataAccessException e) {
			throw new DatabaseException(e);
		}
	}

	@Transactional(propagation = Propagation.NEVER)
	@Override
	public Employee getEmp(int id) throws DatabaseException {
		try {
			Employee employee = dao.findById(id).get();// TODO Auto-generated method stub
			return employee;
		} catch (DataAccessException e) {
			throw new DatabaseException(e);
		}
	}

}
