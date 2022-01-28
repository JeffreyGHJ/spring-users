package com.jeffreyghj.springusers.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UpdateUserDto {
	

	@NotBlank(message="Required: must not be blank")
	private String firstName;
	
	@NotBlank(message="Required: must not be blank")
	private String lastName;
	
	@NotBlank(message="Required: must not be blank")
	@Size(min=3, message="Username must be at least 3 characters long")
	private String username;
	
	private Long id;
	
	public UpdateUserDto() {
		
	}

	public UpdateUserDto(@NotBlank(message = "Required: must not be blank") String firstName,
			@NotBlank(message = "Required: must not be blank") String lastName,
			@NotBlank(message = "Required: must not be blank") @Size(min = 3, message = "Username must be at least 3 characters long") String username) {
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

	@Override
	public String toString() {
		return "UpdateUserDto [firstName=" + firstName + ", lastName=" + lastName + ", username=" + username + ", id="
				+ id + "]";
	}

}
