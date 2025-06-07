package com.tyss.ca.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tyss.ca.enums.ClassMode;
import com.tyss.ca.enums.Course;
import com.tyss.ca.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "enquiry")
@Getter
@Setter
public class Enquiry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Size(min = 3, max = 15, message = "Name must be between 3 and 15 characters")
	private String name;

	private Long phoneNumber;

	@Enumerated(EnumType.STRING) 
	private ClassMode classMode = ClassMode.OFFLINE;

	@Enumerated(EnumType.STRING)
	private Course course;

	@Enumerated(EnumType.STRING)
	private Status status = Status.ACTIVE;

	@ManyToOne
	@JoinColumn(name = "counselor_id")
	@JsonIgnore
	private Counselor counselor;
	
	@CreationTimestamp
	@Column(name = "created_date_time", updatable = false)
	private LocalDateTime createdDateTime;

	@UpdateTimestamp
	private LocalDateTime updatedDateTime;
}
