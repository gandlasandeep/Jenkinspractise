package com.example.controller;

import java.util.List;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.services.*;
import com.example.beans.Employee;
import com.example.exception.InsertionException;

@RestController
public class EmployeeController {
	@Autowired
	private EmployeeServices services;


	@GetMapping("/getEmployee")
	public ResponseEntity<List<Employee>> displayEmployee() {
		return new ResponseEntity<List<Employee>>(services.displayEmployee(), HttpStatus.OK);
	}
	
	@PostMapping("/addEmployee")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee emp) throws InsertionException {
		System.out.println(emp);
		return new ResponseEntity<Employee>(services.addEmployee(emp), HttpStatus.OK);
	}

	@GetMapping("/searchEmployeeById/{id}")
	public ResponseEntity<Employee> searchEmployee(@PathVariable int id) throws InsertionException {
		return new ResponseEntity<Employee>(services.searchDetails(id),HttpStatus.OK);
	}

	@DeleteMapping("/deleteEmployee/{id}")
	public ResponseEntity<Boolean> deleteEmployee(@PathVariable int id) throws InsertionException {
		return new ResponseEntity<Boolean>(services.deleteEmployee(id), HttpStatus.CREATED);
	}

	
	@PutMapping("/updateEmployee/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable int id, @RequestBody Employee emp)throws InsertionException {
		return new ResponseEntity<Employee>(services.updateEmployee(id,emp), HttpStatus.OK);
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleRuntimeException(HttpServletRequest request, Exception ex){
		System.out.println("Controller based Exception");
		System.out.println("RuntimeException Occured:: URL="+request.getRequestURL() +" raised "+ex);
		return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.NOT_FOUND);	
	}

}
