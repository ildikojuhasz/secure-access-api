package com.example.secureaccessapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.secureaccessapi.dto.request.UserRequest;
import com.example.secureaccessapi.dto.response.ConsentListResponse;
import com.example.secureaccessapi.dto.response.UserResponse;
import com.example.secureaccessapi.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	
	@GetMapping("/me")
	@Operation(summary = "Get user profile")
	public UserResponse getCurrentUser() {
		return userService.getCurrentUser();
	}
	
	@PutMapping("/me")
	@Operation(summary = "Change user profile")
	public UserResponse modifyUser(@Valid @RequestBody UserRequest request) {
		return userService.modifyUser(request);
	}
	
	// TODO
//	@PutMapping("/me/password")
//	@Operation(summary = "Change password")
//	public UserResponse changePassword(@Valid @RequestBody UserRequest request) {
//		return userService.changePassword(request);
//	}
	
	@GetMapping("/me/consents")
	@Operation(summary = "Get user consents")
	public ConsentListResponse getConsents(@Valid @RequestBody UserRequest request) {
		return userService.getConsents(request);
	}
	
	@PutMapping("/me/consents")
	@Operation(summary = "Change user consents")
	public ConsentListResponse changeConsents(@Valid @RequestBody UserRequest request) {
		return userService.changeConsents(request);
	}
}
