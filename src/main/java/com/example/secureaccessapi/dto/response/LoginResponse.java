package com.example.secureaccessapi.dto.response;

public record LoginResponse(String token, String type, Long id, String email, String role) {
	public static LoginResponse of(String token, Long id, String email, String role) {
        return new LoginResponse(token, "Bearer", id, email, role);
    }
}
