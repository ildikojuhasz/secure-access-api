package com.example.secureaccessapi.service;

import java.util.function.Consumer;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.secureaccessapi.dto.request.UserRequest;
import com.example.secureaccessapi.dto.response.ConsentListResponse;
import com.example.secureaccessapi.dto.response.UserResponse;
import com.example.secureaccessapi.entity.User;
import com.example.secureaccessapi.mapper.UserMapper;
import com.example.secureaccessapi.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;
	
	@Transactional(readOnly = true)
	public UserResponse getCurrentUser() {		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		
		User currentUser = userRepository.findByEmail(email).get();
		
		return userMapper.toResponse(currentUser);
	}
	
	@Transactional
	public UserResponse modifyUser(UserRequest request) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		
		User currentUser = userRepository.findByEmail(email).get();
		updateIfNotNull(request.firstName(), currentUser::setFirstName);
		updateIfNotNull(request.lastName(), currentUser::setLastName);
		updateIfNotNull(request.consents(), currentUser::setConsents);
		
		// Note: no save needed, because we have a managed entity here and hibernate dirty checking will save it on commit
		
		return userMapper.toResponse(currentUser);
	}
	
	// TODO
//	@Transactional
//	public UserResponse changePassword(UserRequest request) {
//		return new UserResponse();
//	}
	
	@Transactional(readOnly = true)
	public ConsentListResponse getConsents(UserRequest request) {		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		
		User currentUser = userRepository.findByEmail(email).get();
		return new ConsentListResponse(currentUser.getId(), currentUser.getConsents());
	}
	
	@Transactional
	public ConsentListResponse changeConsents(UserRequest request) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		
		User currentUser = userRepository.findByEmail(email).get();
		
		currentUser.setConsents(request.consents());
		
		// Note: no save needed, because we have a managed entity here and hibernate dirty checking will save it on commit
		
		return new ConsentListResponse(currentUser.getId(), currentUser.getConsents());
	}
		
	private <T> void updateIfNotNull(T value, Consumer<T> setter) {
	    if (value != null) {
	        setter.accept(value);
	    }
	}
}
