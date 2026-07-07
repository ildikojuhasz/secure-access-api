package com.example.secureaccessapi.initializer;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.secureaccessapi.entity.User;
import com.example.secureaccessapi.enums.Role;
import com.example.secureaccessapi.repository.UserRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataInitializer {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@PostConstruct
	public void init() {
		if (userRepository.existsByEmail("admin@katze.com")) {
			return;
		}

		User admin = new User();
		admin.setEmail("admin@katze.com");
		admin.setPassword(passwordEncoder.encode("katze123"));
		admin.setFirstName("Katze");
		admin.setLastName("Admin");
		admin.setRole(Role.ADMIN);

		userRepository.save(admin);
	}
}