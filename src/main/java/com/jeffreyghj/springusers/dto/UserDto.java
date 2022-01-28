package com.jeffreyghj.springusers.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.jeffreyghj.springusers.validation.UniqueEmail;
import com.jeffreyghj.springusers.validation.ValidPassword;


// This DTO is required during user creation - the user entity only has columns for password but not confirm password etc...

@ValidPassword(
		password = "password",
		confirmPass = "confirmPassword",
		message="Passwords must match")
public class UserDto {

	@NotBlank(message="Required: must not be blank")
	private String firstName;

	@NotBlank(message="Required: must not be blank")
	private String lastName;
	
	@NotBlank(message="Required: must not be blank")
	@UniqueEmail(message="Email already registered")
	private String email;
	

	@NotBlank(message="Required: must not be blank")
	@Size(min=3, message="Username must be at least 3 characters long")
	private String username;
	
	@NotBlank(message="Required: must not be blank")
	@Size(min=4, message="Password must be at least 4 characters long")
	private String password;
	private String confirmPassword;
	
	public UserDto() {
		
	}

	public UserDto(@NotBlank String firstName, @NotBlank String lastName,
			@NotBlank String email, @NotBlank String username, @NotBlank String password,
			String confirmPassword) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@Override
	public String toString() {
		return "UserDto [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", username="
				+ username + "]";
	}
	
}
