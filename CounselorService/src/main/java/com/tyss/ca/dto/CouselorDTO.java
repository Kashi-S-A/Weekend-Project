package com.tyss.ca.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CouselorDTO {

	@Size(min = 3, max = 15, message = "Name must be between 3 and 15 characters")
	private String name;

	@Email(message = "Invalid email format")
	private String email;

	private String phoneNumber;

	@Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
	private String password;
}
