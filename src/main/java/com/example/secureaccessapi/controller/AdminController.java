package com.example.secureaccessapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.secureaccessapi.dto.request.ChangeRolesRequest;
import com.example.secureaccessapi.dto.response.UserListResponse;
import com.example.secureaccessapi.dto.response.UserResponse;
import com.example.secureaccessapi.service.AdminService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
public class AdminController {

	private final AdminService adminService;
	
	@GetMapping
	@Operation(summary = "Search users")
	@PreAuthorize("hasRole('ADMIN')")
	public UserListResponse searchUsers(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email) {
		return adminService.searchUsers(firstName, lastName, email);
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Get user by id")
	@PreAuthorize("hasRole('ADMIN')")
	public UserResponse getUser(@PathVariable Long id) {
		return adminService.getUser(id);
	}
	
	@PutMapping("/{id}/role")
	@Operation(summary = "Change user role")
	@PreAuthorize("hasRole('ADMIN')")
	public UserResponse changeRoles(@PathVariable Long id, @Valid @RequestBody ChangeRolesRequest request) {
		return adminService.changeRoles(id, request);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Operation(summary = "Delete user by id")
	@PreAuthorize("hasRole('ADMIN')")
	public void deleteUser(@PathVariable Long id) {
		adminService.deleteUser(id);
	}
}
