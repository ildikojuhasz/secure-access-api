package com.example.secureaccessapi.dto.request;

import java.util.List;

import com.example.secureaccessapi.entity.UserConsent;

import jakarta.validation.constraints.NotEmpty;

public record UserRequest(String firstName, String lastName, @NotEmpty(message = "At least one consent need to be set") List<UserConsent> consents) {

}
