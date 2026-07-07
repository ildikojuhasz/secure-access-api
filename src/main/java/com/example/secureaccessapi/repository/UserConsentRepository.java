package com.example.secureaccessapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.secureaccessapi.entity.UserConsent;

@Repository
public interface UserConsentRepository extends JpaRepository<UserConsent, Long> {

	List<UserConsent> findByUserId(Long id);
	
}
