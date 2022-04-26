package com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.model.Project;
import com.springboot.repository.ProjectRepository;
@RestController
public class ProjectController {

	@Autowired
	private ProjectRepository projectRepository; 
	/*
	 * API: to fetch all projects 
	 */
	public List<Project> allProjects(){
		return projectRepository.findAll();
	}
	/*
	 * API to POST project 
	 */
	@PostMapping("/project")
	public Project postProject(@RequestBody Project project) {
		return projectRepository.save(project);
	}
	/*
	 * API to get single project details by ID
	 */
	@GetMapping("/project/{id}")
	public Project getProjectById(@PathVariable("id") Long id) {
		return projectRepository.getById(id);
	}
	
	/*
	 * API to delete a project by ID
	 */
	@DeleteMapping("/project/{id}")
	public void deleteProject(@PathVariable("id") Long id) {
		projectRepository.deleteById(id);
	}
	
	/*
	 * API to update project details 
	 */
	@PutMapping("/project/{id}")
	public Project updateProject(@PathVariable("id") Long id, @RequestBody Project project) {
		Project projectdb = projectRepository.getById(id);
		
		projectdb.setTitle(project.getTitle());
		projectdb.setCredits(project.getCredits());
		
		return projectRepository.save(project); 
	}
}









