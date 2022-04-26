package com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.model.Department;
import com.springboot.repository.DepartmentRepository;

@RestController
public class DepartmentController {

	@Autowired
	private DepartmentRepository departmentRepository;
	
	/*
	 * Post API 
	 */
	@PostMapping("/department")
	public Department postDepartment(@RequestBody Department department){
		return departmentRepository.save(department);
	}
	
	/*
	 * GET-ALL API
	 */
	@GetMapping("/department")
	public List<Department> getAll(){
		return departmentRepository.findAll();
	}
}









