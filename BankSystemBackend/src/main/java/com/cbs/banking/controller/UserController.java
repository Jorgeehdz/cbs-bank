package com.cbs.banking.controller;

import java.security.Principal;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cbs.banking.model.UserInfo;
import com.cbs.banking.repository.UserRepository;
 
@RestController
public class UserController {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	
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
	
	@GetMapping("/user/login")
	public UserInfo login(Principal pricipal) {
		String username = pricipal.getName();
		return userRepository.fetchByUserName(username, true);
	}
	
	@GetMapping("/user/question/{username}")
	public String getSecurityQuestion(@PathVariable("username") String username) {
		String question =  userRepository.fetchSecurityQuestionByUsername(username,true);
		if(question == null)
			throw new RuntimeException("Invalid User");
		
		return question; 
	}
	
	@GetMapping("/user/answer/{username}/{answer}")
	public boolean checkSecurityAnswer(@PathVariable("username") String username , 
									@PathVariable("answer") String answer) {
		UserInfo user = userRepository.checkSecurityAnswer(username,answer);
		if(user == null)
			return false;
		
		return true;
	}
	
	@PutMapping("/user/password/{username}/{password}")
	public boolean passwordUpdate(@PathVariable("username") String username, 
							   @PathVariable("password") String passwordEnc) {
		String password = new String(Base64.decodeBase64(passwordEnc));
		String encodedPassword = passwordEncoder.encode(password);
		
		UserInfo user = userRepository.fetchByUserName(username, true);
		
		if(user == null)
			return false;
		
		userRepository.updatePassword(username,encodedPassword);
		return true; 
	}
}
