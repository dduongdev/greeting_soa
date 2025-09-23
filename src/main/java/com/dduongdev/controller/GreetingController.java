package com.dduongdev.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dduongdev.dto.GreetingResponse;

@RestController
public class GreetingController {

	@PreAuthorize("isAuthenticated()")
	@GetMapping(value = "/greeting")
	public ResponseEntity<GreetingResponse> greeting(
			@RequestParam(name = "name", defaultValue = "World", required = false) String name) {
		return ResponseEntity.ok(new GreetingResponse(name));
	}
}
