package com.jeffreyghj.springusers.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jeffreyghj.springusers.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	//Add a sorting method
	public List<User> findAllByOrderByLastNameAsc();	// Method behavior defined by method name
	public User findByUsername(String username);
	
}
