package com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.model.Category;
import com.springboot.repository.CategoryRepository;

@RestController
public class CategoryController {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@PostMapping("/category")
	public Category postCategory(@RequestBody Category category) {
		return categoryRepository.save(category);
	}
	
	@GetMapping("/category")
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}
}
