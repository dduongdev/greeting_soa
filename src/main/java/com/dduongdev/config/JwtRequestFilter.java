package com.dduongdev.config;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.dduongdev.security.entity.CustomUserDetails;
import com.dduongdev.security.service.JwtService;

import io.jsonwebtoken.lang.Collections;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class JwtRequestFilter extends OncePerRequestFilter {
	private JwtService jwtService;

	@Autowired
	public JwtRequestFilter(JwtService jwtService) {
		this.jwtService = jwtService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		Optional<String> jwtTokenOpt = jwtService.parseTokenFrom(request);

		if (jwtTokenOpt.isPresent() && jwtService.validateToken(jwtTokenOpt.get())) {
			String jwtToken = jwtTokenOpt.get();
			Optional<String> extractedUsernameOpt = jwtService.extractUsernameFrom(jwtToken);
			Optional<Integer> extractedUserIdOpt = jwtService.extractUserIdFrom(jwtToken);

			/**
			 * Check valid for (if necessary): extractedUsernameOpt, extractedUserIdOpt,
			 * extractedAuthorities
			 */

			String extractedUsername = extractedUsernameOpt.get();
			int extractedUserId = extractedUserIdOpt.get();

			UserDetails userDetails = new CustomUserDetails(extractedUserId, extractedUsername, "");

			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
					userDetails, null, Collections.emptyList());
			usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		}

		filterChain.doFilter(request, response);
	}

}
