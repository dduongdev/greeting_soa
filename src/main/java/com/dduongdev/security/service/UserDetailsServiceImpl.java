package com.dduongdev.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dduongdev.entity.User;
import com.dduongdev.repository.UserRepository;
import com.dduongdev.security.entity.CustomUserDetails;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private UserRepository userRepository;

	@Autowired
	public UserDetailsServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> foundUserOpt = userRepository.findByUsername(username);
		if (foundUserOpt.isEmpty()) {
			throw new UsernameNotFoundException("No user found with this username: " + username);
		}

		User user = foundUserOpt.get();

		return new CustomUserDetails(user.getId(), user.getUsername(), user.getPassword());
	}

}
