package com.example.services;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.payload.requests.SignupRequest;

@Service
public class AuthService {

	private final SignupRequest signupRequest;
	private final MultipartFile multipartFile;
	
	public AuthService() {
		this.signupRequest = new SignupRequest();
		this.multipartFile = null;
		
	}
	
	public AuthService(SignupRequest signupRequest, MultipartFile multipartFile) {
		this.signupRequest = signupRequest;
		this.multipartFile = multipartFile;
	}
	
	public void registerUser(SignupRequest signupRequest, MultipartFile multipartFile) {
		
	}
}
