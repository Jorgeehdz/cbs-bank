package com.springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
			@Autowired
			private MyUserDetailsService myUserDetailsService;
	
			@Override
			protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//				 auth.inMemoryAuthentication()
//				 	.withUser("harry")
//				 	.password("{noop}potter")
//				 	.roles("BRANCH_MANAGER")
//				 	.and()
//				 	.withUser("ronald")
//				 	.password("{noop}weasley")
//				 	.roles("ACCOUNTANT")
//				 	.and()
//				 	.withUser("albus")
//				 	.password("{noop}dumbledore")
//				 	.roles("COUNTRY_MANAGER");
				
				auth.authenticationProvider(myAuthManager()); 
			}
	
			@Override
			protected void configure(HttpSecurity http) throws Exception {
				  http.httpBasic()
				 .and()
				 .authorizeRequests()
				 .antMatchers(HttpMethod.GET, "/hello").permitAll()
				 .antMatchers(HttpMethod.POST, "/user/register").permitAll()
				 .antMatchers(HttpMethod.GET, "/hello/private").authenticated()
				 .antMatchers(HttpMethod.GET, "/hello/bank-manager").hasAnyAuthority("BRANCH_MANAGER")
				 .antMatchers(HttpMethod.GET, "/hello/country-manager").hasAnyAuthority("COUNTRY_MANAGER")
				 .antMatchers(HttpMethod.GET, "/hello/accountant").hasAnyAuthority("ACCOUNTANT")
				 .antMatchers(HttpMethod.POST, "/comment").authenticated()
				 .and()
				 .csrf().disable()
				 ; 
			}
			
			@Bean
			public PasswordEncoder getPaswordEncoder() {
				PasswordEncoder passEncoder = new BCryptPasswordEncoder();
				return passEncoder;
			}
			
			public DaoAuthenticationProvider myAuthManager(){
				DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
				authProvider.setUserDetailsService(myUserDetailsService);
				authProvider.setPasswordEncoder(getPaswordEncoder());
				return authProvider;
			}
}

/*
	WebSecurityConfigurerAdapter : Internal Spring framework class for default security config 
			: configure(AuthenticationManagerBuilder): 
				This allows us to create our own custom authentication. 
				
			: configure(Http) : THis method takes api urls. 
			
			
			: configure(WebSecurity): This should not change
					
	
	{bcrypt}: BCryptPasswordEncoder
	{noop}: NoOpPasswordEncoder
	{sha256}: StandardPasswordEncoder
	
	  PasswordEncoder
	         |
	BCryptPasswordEncoder
	
	
	In spring framework, there is a Service interface called UserDetailsService that provides User details 
	to Spring security. 
	
	If we can implement this UserDetailsService interface and pass it to out authProvider, 
	then we can create a path to connect to DB. 
*/



















