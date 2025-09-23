package com.dduongdev.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.dduongdev.entity.User;

@Repository
public class InMemoryUserRepository implements UserRepository {

	private final List<User> users = new ArrayList<User>();
	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public InMemoryUserRepository() {
		users.add(new User(1, "duong", passwordEncoder.encode("12345"), ""));
		users.add(new User(2, "admin", passwordEncoder.encode("admin123"), ""));
		users.add(new User(3, "john", passwordEncoder.encode("password"), ""));
	}

	@Override
	public Optional<User> findByUsername(String username) {
		return users.stream().filter(user -> user.getUsername().equals(username)).findFirst();
	}

	@Override
	public Optional<User> findById(int id) {
		return users.stream().filter(user -> user.getId() == id).findFirst();
	}

	@Override
	public void update(User user) {
		Optional<User> foundUser = findById(user.getId());
		if (foundUser.isPresent()) {
			User existingUser = foundUser.get();
			existingUser.setId(user.getId());
			existingUser.setUsername(user.getUsername());
			existingUser.setPassword(user.getPassword());
			existingUser.setToken(user.getToken());
		}
	}

}
