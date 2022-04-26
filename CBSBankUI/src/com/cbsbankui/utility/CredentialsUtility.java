package com.cbsbankui.utility;

import java.util.Arrays;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class CredentialsUtility {

	public static HttpHeaders getHeaders(String username,String password){
		HttpHeaders httpHeaders = new HttpHeaders();
		String userNamePass = username + ":" + password; //harry@gamil.com:potter
		String encodedCredentials = 
				new String(Base64.encodeBase64(userNamePass.getBytes()));
		httpHeaders.add("Authorization", "Basic " + encodedCredentials);
		httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		return httpHeaders;
	}
	public static HttpHeaders getHeaders(){
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		return httpHeaders;
	}
	
}
