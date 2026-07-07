package com.example.secureaccessapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.secureaccessapi.dto.request.UserRequest;
import com.example.secureaccessapi.dto.response.UserResponse;
import com.example.secureaccessapi.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toResponse(User user);
    
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "password", ignore = true)
    User toEntity(UserRequest request);
	
}
