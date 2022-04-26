package com.springboot.security.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.security.model.Comment;
import com.springboot.security.model.UserInfo;
import com.springboot.security.repository.CommentRepository;
import com.springboot.security.repository.UserRepository;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder; 
	
	@Autowired
	private CommentRepository commentRepository;
	
	@GetMapping("/hello")
	public String sayHello() {
		return "Hello User";
	}
	
	@GetMapping("/hello/private")
	public String sayHelloPrivate() {
		return "Hello Private User";
	}
	
	@GetMapping("/hello/bank-manager")
	public String sayHelloBankManager() {
		return "Hello Bank Manager";
	}
	
	@GetMapping("/hello/country-manager")
	public String sayHelloCountryManager() {
		return "Hello Country Manager";
	}
	
	@GetMapping("/hello/accountant")
	public String sayHelloAccountant() {
		return "Hello Accountant";
	}

	@PostMapping("/user/register")
	public UserInfo postUser(@RequestBody UserInfo userInfo) {
		/* 
		 * Fetch Raw/Clear text password from UserInfo given by User
		 */
		String rawPassword = userInfo.getPassword(); //this is a clear text password 
		
		/*
		 * Encode the Raw password 
		 */
		String encodedPassword = passwordEncoder.encode(rawPassword);
		
		/*
		 * Set encoded password in UserInfo
		 */
		userInfo.setPassword(encodedPassword);
		
		/*
		 * Save UserInfo in DB
		 */
		return userRepository.save(userInfo); 
	}
	
	@PostMapping("/comment")
	public Comment postComment(Principal pricipal, @RequestBody Comment comment) {
		String username = pricipal.getName();
		UserInfo user = userRepository.fetchUserByUsername(username);
		comment.setUserInfo(user);
		return commentRepository.save(comment);
	}
}

/*
	/hello : let everyone access this api 
	/hello/private: make this secure with authentication 
*/