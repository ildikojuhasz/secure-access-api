package com.example.secureaccessapi.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.example.secureaccessapi.entity.User;

import jakarta.persistence.criteria.Predicate;

public class UserSpecification {

	public static Specification<User> emailWithIgnroeCase(String email) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), "%" + email.toLowerCase() + "%");
	}
	
	public static Specification<User> searchUsers(String email, String firstName, String lastName) {
		return (root, querz, criteriaBuilder) ->
		criteriaBuilder.and(
				criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), "%" + email.toLowerCase() + "%"), 
				criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), "%" + firstName.toLowerCase() + "%"), 
				criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")), "%" + lastName.toLowerCase() + "%"));
	}
	
	public static Specification<User> matches(String email, String firstName, String lastName) {
	    return (root, query, cb) -> {
	        List<Predicate> predicates = new ArrayList<>();

	        if (email != null && !email.isBlank()) {
	            predicates.add(
	                cb.like(
	                    cb.lower(root.get("email")),
	                    "%" + email.toLowerCase() + "%"
	                )
	            );
	        }

	        if (firstName != null && !firstName.isBlank()) {
	            predicates.add(
	                cb.like(
	                    cb.lower(root.get("firstName")),
	                    "%" + firstName.toLowerCase() + "%"
	                )
	            );
	        }
	        
	        if (lastName != null && !lastName.isBlank()) {
	            predicates.add(
	                cb.like(
	                    cb.lower(root.get("lastName")),
	                    "%" + lastName.toLowerCase() + "%"
	                )
	            );
	        }

	        return cb.and(predicates.toArray(Predicate[]::new));
	    };
	}
}
