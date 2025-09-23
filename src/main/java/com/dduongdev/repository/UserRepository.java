package com.dduongdev.repository;

import java.util.Optional;

import com.dduongdev.entity.User;

public interface UserRepository {
	Optional<User> findByUsername(String username);

	Optional<User> findById(int id);

	void update(User user);
}
