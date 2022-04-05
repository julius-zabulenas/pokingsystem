package lt.vtmc.services;

import java.util.List;

import org.springframework.stereotype.Service;

import lt.vtmc.models.User;
import lt.vtmc.repositories.UserRepository;

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
