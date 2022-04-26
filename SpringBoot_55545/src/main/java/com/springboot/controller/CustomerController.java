package com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.model.Customer;
import com.springboot.repository.CustomerRepository;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerRepository customerRepository; 
	
	@GetMapping("/customer")
	public List<Customer> getAllCustomer() {
		return customerRepository.findAll(); //10000
	}
	
	@PostMapping("/customer")
	public Customer postCustomer(@RequestBody Customer customer) {
		return customerRepository.save(customer);
	}
}
