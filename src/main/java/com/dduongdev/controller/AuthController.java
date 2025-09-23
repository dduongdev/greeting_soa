package com.dduongdev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.dduongdev.dto.AuthRequest;
import com.dduongdev.dto.AuthResponse;
import com.dduongdev.security.entity.CustomUserDetails;
import com.dduongdev.security.service.JwtService;
import com.dduongdev.service.UserService;

@Controller
public class AuthController {
	private UserService userService;
	private AuthenticationManager authenticationManager;
	private JwtService jwtService;

	@Autowired
	public AuthController(UserService userService, AuthenticationManager authenticationManager, JwtService jwtService) {
		this.userService = userService;
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
	}

	@PostMapping(value = "/auth")
	public ResponseEntity<?> auth(@RequestBody AuthRequest authRequest) {
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			String token = jwtService.generateToken(userDetails);
			userService.updateToken(((CustomUserDetails) userDetails).getUserId(), token);
			return ResponseEntity.ok(new AuthResponse(token));
		} catch (BadCredentialsException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong username or password.");
		}
	}
}
