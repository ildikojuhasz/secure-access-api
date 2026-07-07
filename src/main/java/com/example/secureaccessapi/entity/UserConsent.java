package com.example.secureaccessapi.entity;

import com.example.secureaccessapi.enums.ConsentType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_consents", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "consent_type"}))
@Getter
@Setter
@NoArgsConstructor
public class UserConsent extends BaseEntity {

	@ManyToOne
	private User user;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ConsentType consentType;
	
	@Column(nullable = false)
	private boolean granted;
}
