package com.jeffreyghj.springusers.dao;

import java.util.List;

import com.jeffreyghj.springusers.entity.User;

public interface UserDAO {
	
	public List<User> getUsers();

	public void saveUser(User theUser);
	
	public User getUser(int theId);
	
	public void deleteUser(int theId);
	
}
