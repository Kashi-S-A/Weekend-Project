package com.tyss.ca.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {

	@Email(message = "Invalid email format")
	private String email;
	
	@Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
	private String password;
}
