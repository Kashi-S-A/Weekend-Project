package com.tyss.ca.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "counselor")
@Getter
@Setter
public class Counselor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Size(min = 3, max = 15, message = "Name must be between 3 and 15 characters")
	private String name;

	@Email(message = "Invalid email format")
	@Column(unique = true)
	private String email;

	@Column(unique = true)
	private String phoneNumber;

	@Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
	private String password;

	@OneToMany(mappedBy = "counselor")
	@JsonIgnore
	private List<Enquiry> enquiries;

	@CreationTimestamp
	@Column(name = "created_date_time", updatable = false)
	private LocalDateTime createdDateTime;

	@UpdateTimestamp
	private LocalDateTime updatedDateTime;
}
