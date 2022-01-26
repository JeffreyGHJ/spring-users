package com.jeffreyghj.springusers.service;

import java.util.List;

import com.jeffreyghj.springusers.dto.UpdateUserDto;
import com.jeffreyghj.springusers.dto.UserDto;
import com.jeffreyghj.springusers.entity.User;

public interface UserService {

	/*
	public List<User> getUsers();
	public void saveUser(User theUser);
	public User getUser(int theId);
	public void deleteUser(int theId);
	*/
	
	public List<User> findAll();
	public User findById(Long theId);
	public boolean emailExists(String theEmail);
	public void save(User theUser);
	public void deleteById(Long theId);
	
	public User createNewUser(UserDto userDto);
	public User updateUser(UpdateUserDto updateUserDto);
}
