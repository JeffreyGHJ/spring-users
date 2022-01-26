package com.jeffreyghj.springusers.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateUserDto {
	
	@NotNull(message="Required")
	@NotEmpty(message="Required, must not be empty")
	@NotBlank(message="Required, must not be blank")
	@Size(min=1, message="First Name must be at least 1 character long")
	private String firstName;
	
	@NotNull(message="Required")
	@NotEmpty(message="Required, must not be empty")
	@NotBlank(message="Required, must not be blank")
	@Size(min=1, message="last Name must be at least 1 character long")
	private String lastName;
	
	@NotNull(message="Required")
	@NotEmpty(message="Required, must not be empty")
	@NotBlank(message="Required, must not be blank")
	@Size(min=3, message="Username must be at least 3 characters long")
	private String username;
	
	private Long id;
	
	public UpdateUserDto() {
		
	}
	
	public UpdateUserDto(
			@NotNull(message = "Required") @NotEmpty(message = "Required, must not be empty") @NotBlank(message = "Required, must not be blank") @Size(min = 1, message = "First Name must be at least 1 character long") String firstName,
			@NotNull(message = "Required") @NotEmpty(message = "Required, must not be empty") @NotBlank(message = "Required, must not be blank") @Size(min = 1, message = "last Name must be at least 1 character long") String lastName,
			@NotNull(message = "Required") @NotEmpty(message = "Required, must not be empty") @NotBlank(message = "Required, must not be blank") @Size(min = 3, message = "Username must be at least 3 characters long") String username) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
