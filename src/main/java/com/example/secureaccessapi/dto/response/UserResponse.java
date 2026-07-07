package com.example.secureaccessapi.dto.response;

import java.util.List;

import com.example.secureaccessapi.entity.UserConsent;
import com.example.secureaccessapi.enums.Role;

public record UserResponse(Long id, String firstName, String lastName, String email, List<UserConsent> consents, Role role) {

}
