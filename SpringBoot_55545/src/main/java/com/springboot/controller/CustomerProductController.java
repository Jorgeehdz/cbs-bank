package com.springboot.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.model.Customer;
import com.springboot.model.CustomerProduct;
import com.springboot.model.Product;
import com.springboot.repository.CustomerProductRepository;
import com.springboot.repository.CustomerRepository;
import com.springboot.repository.ProductRepository;

@RestController
public class CustomerProductController {

	@Autowired
	private CustomerProductRepository customerProductRepository;
	
	@Autowired
	private CustomerRepository customerRepository; 
	
	@Autowired
	private ProductRepository productRepository;
	
	@PostMapping("/customer/product/purchase/{cid}/{pid}")
	public CustomerProduct purchaseCustomerProduct(
			@RequestBody CustomerProduct customerProduct, 
			@PathVariable("cid") Long cid, 
			@PathVariable("pid") Long pid) {
		
		//fetch Customer from DB using cid
		Customer customer = customerRepository.getById(cid);
		
		//fetch Product from DB using pid
		Product product = productRepository.getById(pid);
		
		//set customer and product object to customerProduct
		customerProduct.setCustomer(customer);
		customerProduct.setProduct(product);
		
		//set todays date for the purchase
		customerProduct.setPurchaseDate(LocalDate.now());
		
		return customerProductRepository.save(customerProduct);
	}
	
	@GetMapping("/customer/product")
	public List<CustomerProduct> getAllCustomerPurchase(){
		return customerProductRepository.findAll();
	}
	
	@GetMapping("/customer/product/{pid}")
	public List<Customer> getCustomerByProductId(@PathVariable("pid") Long pid) {
		return customerProductRepository.getCustomerByProductId(pid);
	}
	
	@GetMapping("/product/customer/{cid}")
	public List<Product> getProductByCustomerId(@PathVariable("cid") Long cid) {
		return customerProductRepository.getProductByCustomerId(cid);
	}
	
	@GetMapping("/customer/category/{cname}")
	public List<Customer> getCustomerByCategoryName(@PathVariable("cname") String cname){
		List<Customer> list = customerProductRepository.getCustomerByCategoryName(cname);
		
		return list.stream().distinct().collect(Collectors.toList());
	}
	
	/*
	 * display the list of products that have been purchased by 
	 * customers living in either london or shire city
	 */
	@GetMapping("/product/customer")
	public List<Product> getProductByCustomerCity(){
		List<Product> list = customerProductRepository.getProductByCustomerCity("london","shire");
		
		return list.stream().distinct().collect(Collectors.toList());
	}
}







