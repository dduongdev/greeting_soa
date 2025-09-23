package com.dduongdev.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dduongdev.entity.User;
import com.dduongdev.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void updateToken(int userId, String token) {
		Optional<User> foundUser = userRepository.findById(userId);
		if (foundUser.isPresent()) {
			User existingUser = foundUser.get();
			existingUser.setToken(token);
			userRepository.update(existingUser);
		}
	}

}
