package com.admin.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class LoginRequest {
	private String username;
	  @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", message = "Password must be at least 8 characters with a special character, upper case, and lower case combination")
	  @NotNull(message = "Please enter a password")
	  @NotBlank(message = "Password cannot be null")
	  private String password;
}
