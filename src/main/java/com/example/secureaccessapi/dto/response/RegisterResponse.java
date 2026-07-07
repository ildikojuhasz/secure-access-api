package com.example.secureaccessapi.dto.response;

public record RegisterResponse(Long id, String email, String firstName, String lastName, String message) {
	public static RegisterResponse of(Long id, String email, String firstName, String lastName, String message) {
		return new RegisterResponse(id, email, firstName, lastName, message);
	}
}
