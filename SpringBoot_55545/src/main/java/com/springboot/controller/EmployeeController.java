package com.springboot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.model.Employee;
import com.springboot.repository.EmployeeRepository;

@RestController //this annotation defines this class as a controller
public class EmployeeController {

	@Autowired
	EmployeeRepository employeeRepository; 
	 
	@PostMapping("/employee")
	public Employee postEmployee(@RequestBody Employee employee) {
		//We read the employee details from the User and save it in DB
		 
		return employeeRepository.save(employee);
	}
	
 	/*
	 * Fetch all employees from the DB
	 */
	@GetMapping("/employee")
	public List<Employee> fetchEmployees() {
		return employeeRepository.findAll();
	}
	
	/*
	 * Fetch Single Employee based on ID 
	 */
	@GetMapping("/employee/{id}")
	public Employee fetchEmployeeById(@PathVariable("id") Long id) {
			return employeeRepository.getById(id);				
	}
	
	/*
	 * Delete API: delete Employee record based on ID 
	 */
	@DeleteMapping("/employee/{id}")
	public void deleteEmployee(@PathVariable("id") Long id) {
		employeeRepository.deleteById(id);
	}
	
	/*
	 *  Update Operation: 
	 */
	@PutMapping("/employee/{id}")
	public Employee updateEmployee(@PathVariable("id") Long id, @RequestBody Employee newEmployee) {
		/* Step 1: Read the ID of the Employee that the user wants to update (id=1)
		 * Step 2: Read the New Values of the Employee that the user wants to save in DB
		 * newEmployee={
			    "name": "harry",
			    "salary":80000,
			    "city": "hogwards"
			}
		 * Step 3: Fetch the existing record from DB based on given ID.  
		 * employeeDB = {
        	"id": 1,
        	"name": "harry potter",
        	"city": "london",
        	"salary": 70000.0
    		}
		 * Step 4: Update the fetched Object with New Values 
		 * Step 5: re-insert fetched-object into DB 
		 */
		Employee employeeDB = employeeRepository.getById(id);
		employeeDB.setName(newEmployee.getName());
		employeeDB.setCity(newEmployee.getCity());
		employeeDB.setSalary(newEmployee.getSalary());		
		/*
		 * employeeDB = {
        	"id": 1,
        	"name": "harry",
			 "salary":80000,
			 "city": "hogwards"
    		}
		 */
		return employeeRepository.save(employeeDB);
	}
	
	
	/*
	 * fetch all employees based on city 
	 */
	@GetMapping("/employee/city")
	 public List<Employee> fetchEmployeeByCity(@RequestParam("city") String city) {
		 List<Employee> list = employeeRepository.getByCityNative(city);
		 return list; 
	 } 
	
	 
}
/*  POST /employee
 *  GET /employee
 *  GET /employee/{}
 *  DELETE /employee/{}
 *  PUT /employee/{}
 *  GET /employee/city/{}
 */












