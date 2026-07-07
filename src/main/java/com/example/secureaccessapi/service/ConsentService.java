package com.example.secureaccessapi.service;

import org.springframework.stereotype.Service;

import com.example.secureaccessapi.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConsentService {

	private final UserRepository userRepository;
	
}
