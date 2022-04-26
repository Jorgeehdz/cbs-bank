package com.springboot.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.springboot.dto.PostDto;
import com.springboot.model.Address;
import com.springboot.model.Company;
import com.springboot.model.Geo;
import com.springboot.model.Post;
import com.springboot.model.User;
import com.springboot.repository.AddressRepository;
import com.springboot.repository.CompanyRepository;
import com.springboot.repository.GeoRepository;
import com.springboot.repository.UserRepository;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AddressRepository addressRepository; 
	@Autowired
	private CompanyRepository companyRepository; 
	@Autowired
	private GeoRepository geoRepository; 
	
	@PostMapping("/user/populate")
	public void fetchUserData() {
		//call the external API and fetch all user data
		RestTemplate restTemplate = new RestTemplate();
		User[] users = restTemplate.
							getForObject("https://jsonplaceholder.typicode.com/users", User[].class);	
		//convert user array into List
		List<User> list = new ArrayList<>(Arrays.asList(users));
		//list.stream().forEach(System.out :: println);
		
		//fetch all address from the UserList
		for(User user  : list) {
			//fetch address of this user
			Address address = user.getAddress();
			Geo geo = address.getGeo();
			Company company = user.getCompany();
			
			geo = geoRepository.save(geo);
			address.setGeo(geo);
			
			address = addressRepository.save(address);
			company = companyRepository.save(company);
			
			user.setAddress(address);
			user.setCompany(company);
			
			userRepository.save(user);
		}
		
	}
	
	/*
	 * display the posts written by User based on given username. 
	 * Response: 
	 * {
	 *   totalPages: 
	 *   totalPosts: 
	 *   postData: [
	 *   {  }, {   }
	 *   ]
	 * }
	 * 
	 * Note: Activate pagination
	 */
	@GetMapping("/post/username/{username}")
	public PostDto getPostsByUsername(@PathVariable("username") String username,
								   @RequestParam("page") Integer page, 
								   @RequestParam("size") Integer size) {
		
		Pageable pageable = PageRequest.of(page, size);
		Page<Post> postPage = userRepository.getPostsByUsername(username,pageable);
		 
		int totalPages = postPage.getTotalPages();
		int totalPosts = (int)postPage.getTotalElements();
		
		PostDto postDto = new PostDto();
		postDto.setPageNumber(page);
		postDto.setSize(size);
		postDto.setTotalPages(totalPages);
		postDto.setTotalRecords(totalPosts);
		postDto.setPageData(postPage.getContent());
		return postDto;
	}
	
	
	/*
	 * Display all Users that are working in given Company name
	 * and live in given city
	 * Activate pagination: 
	 * 
	 * Response: 
	 * {
	 * 	totalPages: 
	 *  totalUsers: 
	 *  currentPage:
	 *  size: 
	 *  users:[] 
	 * }
	 */
	@GetMapping("/user/company/city/{company}/{city}")
	public void getUserByCompanyAndCity() {
		
	}
	
}






