package com.cbsbankui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.cbsbankui.model.User;
import com.cbsbankui.utility.CredentialsUtility;

@Controller
public class LoginController {

	private RestTemplate restTemplate = new RestTemplate();
	HttpSession session;
	@RequestMapping("/")
	public String showLogin() {
		return "login";
	}
	
	@RequestMapping("/process-login")
	public String processLogin(@RequestParam("username") String username, 
							   @RequestParam("password") String password,
							   HttpServletRequest request,Model model) {
		
		HttpHeaders httpHeaders = CredentialsUtility.getHeaders(username, password);
		
		HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
		//call the Login API 
		ResponseEntity<User> response =  restTemplate
										.exchange("http://localhost:8282/user/login", 
										HttpMethod.GET, entity, User.class);
		if(response.getStatusCodeValue() == 200) {
			if(response.hasBody()) {
				User user = response.getBody();
				session= request.getSession();
				session.setAttribute("user", user);
				return "dashboard";
			}
		}
		model.addAttribute("msg", "Login Denied, Contact Support");
		return "login";
		
	}
	
	@RequestMapping("/sign-up")
	public String showSignUp() {
		return "signup";
	}
	
	@RequestMapping("/process-sign-up")
	public String processSignUp(
			@RequestParam("name") String name, 
			@RequestParam("username") String username, 
			@RequestParam("password") String password,
			@RequestParam("accountType") String accountType, 
			@RequestParam("question") String question,
			@RequestParam("answer") String answer, 
			Model model) {
		
		 User user = new User();
		 user.setName(name);
		 user.setUsername(username);
		 user.setPassword(password);
		 user.setAccountType(accountType);
		 user.setSecurityQuestion(question);
		 user.setSecurityAnswer(answer);
		 
		 
		restTemplate.postForObject("http://localhost:8282/user/register", user, User.class);
		model.addAttribute("msg","Sign-Up success, Please Login"); 
		return "login";
	}
	
	@RequestMapping("/forgot-password")
	public String showForgotPassword() {
		return "forgot-password-1";
	}
	
	@RequestMapping("/process-forgot-password")
	public String ProcessForgotPassword(@RequestParam("username") String username,Model model) {
		//call the api and pass username, I need Security Question.
		HttpEntity<String> entity = new HttpEntity<>(CredentialsUtility.getHeaders());
		
		try {
			ResponseEntity<String> response = 
					restTemplate.exchange("http://localhost:8282/user/question/"+username, 
							HttpMethod.GET, entity, String.class);
			String question = response.getBody();
			model.addAttribute("question", question); 
			model.addAttribute("username", username); 
			return "forgot-password-2";
		}
		catch(Exception e) {
			model.addAttribute("msg", "Invalid Username Provided..");
			return "forgot-password-1";
		}
	}
	
	@RequestMapping("/process-security-question")
	public String showForgotPassword(@RequestParam("answer") String answer,
									 @RequestParam("username") String username,
									 Model model) {
		//call the api and pass username and answer and verify
			HttpEntity<String> entity = new HttpEntity<>(CredentialsUtility.getHeaders());
			ResponseEntity<Boolean> response = 
					restTemplate.exchange("http://localhost:8282/user/answer/"+username+"/"+answer, 
							HttpMethod.GET, entity, Boolean.class);
			boolean status = response.getBody();
			if(status== true) {
				model.addAttribute("username", username); 
				return "forgot-password-3";
			}
			else {
				model.addAttribute("msg", "Invalid Security Answer..");
				return "forgot-password-1";
			}
			 
	}
	
	@RequestMapping("/reset-password")
	public String resetPassword(@RequestParam("username") String username, 
								@RequestParam("password") String password
								,Model model) {
		
		//call the API and update password 
		HttpEntity<String> entity = new HttpEntity<>(CredentialsUtility.getHeaders());
		byte[] byteVal = Base64.encodeBase64(password.getBytes());
		String passwordenc = new String(byteVal);
		ResponseEntity<Boolean> response = 
				restTemplate.exchange("http://localhost:8282/user/password/"+username+"/"+passwordenc, 
						HttpMethod.PUT, entity, Boolean.class);
		boolean status = response.getBody();
		
		if(status== true) {
			model.addAttribute("msg", "Password Reset Success!!"); 
			return "login";
		}
		else {
			model.addAttribute("msg", "Invalid Username..");
			return "forgot-password-1";
		}
		
	}
	
	
	
	
	
	
}
