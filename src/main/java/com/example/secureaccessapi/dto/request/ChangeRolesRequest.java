package com.example.secureaccessapi.dto.request;

import com.example.secureaccessapi.enums.Role;

import jakarta.validation.constraints.NotEmpty;

public record ChangeRolesRequest(
		
		@NotEmpty(message = "Role must be provided.")
		Role role

) {

	
}
