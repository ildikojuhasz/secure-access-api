package com.example.secureaccessapi.dto.response;

import java.util.List;

import com.example.secureaccessapi.entity.UserConsent;

public record ConsentListResponse(Long id, List<UserConsent> consents) {

}
