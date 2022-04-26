package com.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.dto.ProductDto;
import com.springboot.model.Category;
import com.springboot.model.Product;
import com.springboot.repository.CategoryRepository;
import com.springboot.repository.ProductRepository;

@RestController
public class ProductController {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@PostMapping("/product/{cid}")
	public Product postProduct(@RequestBody Product product, @PathVariable("cid") Long cid) {
		Category category = categoryRepository.getById(cid);
		product.setCategory(category);
		
		return productRepository.save(product);
	}
	
	@GetMapping("/product")
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}
	
	@GetMapping("/product/category/{cid}")
	public List<Product> fetchProductsByCategoryId(@PathVariable("cid") Long cid) {
		return productRepository.fetchProductsByCategoryId(cid);
	}
	
	@GetMapping("/product/cateogry-name/{cname}")
	public List<Product> fetchProductsByCategoryName(@PathVariable("cname") String cname) {
		return productRepository.fetchProductsByCategoryName(cname.toLowerCase());
	}
	
	
	/*
	 * display all products with pagination and Sorting based on salary in Ascending order 
	 */
	@GetMapping("/product/paging/sorting")
	public List<Product> getProductsWithPagingAndSorting(
							@RequestParam("page") Integer page, 
							@RequestParam("size") Integer size) {
		
		Sort sort = Sort.by("price").ascending();
		Pageable pageable = PageRequest.of(page, size, sort);
		return productRepository.findAll(pageable).getContent();
	}
	
	@GetMapping("/product/paging/sorting/desc")
	public List<ProductDto> getProductsWithPagingAndSortingDesc(
							@RequestParam("page") Integer page, 
							@RequestParam("size") Integer size) {
		
		Sort sort = Sort.by("price").descending();
		Pageable pageable = PageRequest.of(page, size, sort);
		List<Product> list =  productRepository.findAll(pageable).getContent();
		
		List<ProductDto> listDto = new ArrayList<>(); 
		for(Product p : list) {
			ProductDto dto = new ProductDto();
			dto.setId(p.getId());
			dto.setTitle(p.getTitle());
			dto.setPrice(p.getPrice());
			dto.setCname(p.getCategory().getName());
			listDto.add(dto);
		}
		return listDto; 
	}
}










