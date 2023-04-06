package com.example.services;

import java.util.List;

import com.example.models.User;
import org.springframework.stereotype.Service;

import com.example.repositories.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<User> getByKeyword(String keyword) {
		return userRepository.findByKeyword(keyword);
	}
}
