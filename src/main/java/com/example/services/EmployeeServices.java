package com.example.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.beans.Employee;
import com.example.dao.IEmployeeDao;
import com.example.exception.InsertionException;

@Service
public class EmployeeServices {
	@Autowired
	private IEmployeeDao dao;

	public List<Employee> displayEmployee() {
		return dao.findAll();
	}

	public Employee addEmployee(Employee employee) throws InsertionException {
		if (!dao.findById(employee.getId()).isPresent()) {
			return dao.save(employee);
		} else
			throw new InsertionException("Id Already Exits enter other Id");
	}

	public Employee updateEmployee(int id, Employee emp) throws InsertionException {
		if (dao.findById(id).isPresent()) {
			return dao.saveAndFlush(emp);
		} else
			throw new InsertionException("Id Didnt Exits enter other Id");
	}

	public Boolean deleteEmployee(int id) throws InsertionException {
		if (dao.findById(id).isPresent()) {
			dao.deleteById(id);
			return true;
		} else
			throw new InsertionException("No Employee with this Id");
	}

	public Employee searchDetails(int id) throws InsertionException {
		if (dao.findById(id).isPresent()) {
			return dao.findById(id).get();
		} else
			throw new InsertionException("Id Didnt Exits enter Exits Id");
	}

}
