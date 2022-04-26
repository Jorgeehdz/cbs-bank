package com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.model.Department;
import com.springboot.model.Student;
import com.springboot.repository.DepartmentRepository;
import com.springboot.repository.StudentRepository;

@RestController
public class StudentController {

	@Autowired
	private DepartmentRepository departmentRepository; 
	
	@Autowired
	private StudentRepository studentRepository; 
	/*
	 * POST Student
	 */
	
	@PostMapping("/student/{did}")
	public Student postStudent(@RequestBody Student student, @PathVariable("did") Long did) {
		Department department = departmentRepository.getById(did);
		student.setDepartment(department);
		
		return studentRepository.save(student);
	}
	
	/*
	 * Fetch all students by department id 
	 */
	@GetMapping("/student/department/{did}")
	public List<Student> getStudentsByDepartmentId(@PathVariable("did") Long did) {
		List<Student> list = studentRepository.fetchByDepartmentId(did);
		return list; 
	}
	
	/*
	 * fetch all students by department name 
	 */
}













