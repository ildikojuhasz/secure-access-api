package com.example.secureaccessapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.secureaccessapi.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);
	
	Optional<User> findById(Long id);
	
	boolean existsByEmail(String email);
	
	@Query("""
		    SELECT u
		    FROM User u
		    WHERE (:email IS NULL OR LOWER(u.email) LIKE LOWER(CONCAT('%', CAST(:email AS string), '%')))
		      AND (:firstName IS NULL OR LOWER(u.firstName) LIKE LOWER(CONCAT('%', CAST(:firstName AS string), '%')))
		      AND (:lastName IS NULL OR LOWER(u.lastName) LIKE LOWER(CONCAT('%', CAST(:lastName AS string), '%')))
		""")
		List<User> searchUsers(
		        @Param("email") String email,
		        @Param("firstName") String firstName,
		        @Param("lastName") String lastName);
}
