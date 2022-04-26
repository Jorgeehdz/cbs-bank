package com.cbs.banking;

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

import com.cbs.banking.service.MyUserDetailsService;

 
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(myAuthManager()); 
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http.httpBasic()
		 .and()
		 .authorizeRequests()
		 .antMatchers(HttpMethod.POST, "/user/register").permitAll()
		 .antMatchers(HttpMethod.GET, "/user/login").authenticated()
		 .antMatchers(HttpMethod.GET, "/user/question/{username}").permitAll()
		 .antMatchers(HttpMethod.GET, "/user/answer/{username}/{answer}").permitAll()
		 .and()
		 .csrf().disable(); 
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
