package com.springboot.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.security.model.UserInfo;
import com.springboot.security.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//Go to DB and fetch the User based on username. 
		UserInfo userInfo = userRepository.fetchUserByUsername(username);
		String role = userInfo.getRole();
		List<GrantedAuthority> list = new ArrayList<>();
		
		SimpleGrantedAuthority sga = new SimpleGrantedAuthority(role);
		list.add(sga);
		
		UserDetails user = new User(userInfo.getUsername(), userInfo.getPassword(), list); 
		return user;
	}

}

/*
 * GrantedAuthority
 */







