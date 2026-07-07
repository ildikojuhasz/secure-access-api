package com.example.secureaccessapi.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.secureaccessapi.dto.request.RegisterRequest;
import com.example.secureaccessapi.dto.response.RegisterResponse;
import com.example.secureaccessapi.entity.User;
import com.example.secureaccessapi.exception.EmailAlreadyExistsException;
import com.example.secureaccessapi.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	public RegisterResponse register(RegisterRequest request) {
		if (userRepository.existsByEmail(request.email())) {
			throw new EmailAlreadyExistsException(request.email());
		}

		User user = new User();
		user.setEmail(request.email());
		user.setFirstName(request.firstName());
		user.setLastName(request.lastName());
		user.setPassword(passwordEncoder.encode(request.password()));

		User saved = userRepository.save(user);

		// TODO: Decide whether registration should return a JWT token.

		return RegisterResponse.of(saved.getId(), saved.getEmail(), saved.getFirstName(), saved.getLastName(),
				"User registered successfully.");
	}
}
