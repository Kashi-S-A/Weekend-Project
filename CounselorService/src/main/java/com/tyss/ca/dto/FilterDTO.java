package com.tyss.ca.dto;

import com.tyss.ca.enums.ClassMode;
import com.tyss.ca.enums.Course;
import com.tyss.ca.enums.Status;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilterDTO {

	@Enumerated(EnumType.STRING) 
	private ClassMode classMode;

	@Enumerated(EnumType.STRING)
	private Course course;

	@Enumerated(EnumType.STRING)
	private Status status;
}
