package com.admin.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@NotNull(message = "Please enter Name")
	private String username;

	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", message = "Password must be at least 8 characters with a special character, upper case, and lower case combination")

	@NotNull(message = "Please enter a password")

	@NotBlank(message = "Password cannot be null")
	private String password;

	@Email
	private String email;

	public Admin(@NotNull(message = "Please enter Name") String username,
			@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", message = "Password must be at least 8 characters with a special character, upper case, and lower case combination") @NotNull(message = "Please enter a password") @NotBlank(message = "Password cannot be null") String password,
			@javax.validation.constraints.Email String email) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
	}

}
