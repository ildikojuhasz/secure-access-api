package com.example.secureaccessapi.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.secureaccessapi.dto.request.ChangeRolesRequest;
import com.example.secureaccessapi.dto.response.UserListResponse;
import com.example.secureaccessapi.dto.response.UserResponse;
import com.example.secureaccessapi.entity.User;
import com.example.secureaccessapi.exception.UserNotFoundException;
import com.example.secureaccessapi.mapper.UserMapper;
import com.example.secureaccessapi.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;
	
	@Transactional(readOnly = true)
	public UserListResponse searchUsers(String firstName, String lastName, String email) {
		
		List<User> users = userRepository.searchUsers(firstName, lastName, email);
				
		return new UserListResponse(users.stream().map(userMapper::toResponse).toList());
	}
	
	@Transactional(readOnly = true)
	public UserResponse getUser(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
		return userMapper.toResponse(user);
	}
	
	@Transactional
	public UserResponse changeRoles(Long id, ChangeRolesRequest request) {		
		User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

		user.setRole(request.role());
		
		// Note: no save needed, because we have a managed entity here and hibernate dirty checking will save it on commit
		
		return userMapper.toResponse(user);
	}
	
	@Transactional
	public void deleteUser(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
		userRepository.delete(user);
	}
	
}
