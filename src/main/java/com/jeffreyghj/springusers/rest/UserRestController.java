package com.jeffreyghj.springusers.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeffreyghj.springusers.entity.User;
import com.jeffreyghj.springusers.service.UserService;

//@RestController
@RestController
@RequestMapping("/")
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public List<User> findAllUsers() {
		return userService.findAll();
	}
	
	@GetMapping("/users/{userId}")
	public User findUserById(@PathVariable Long userId) {
		User theUser = userService.findById(userId);
		
		if ( theUser == null ) {
			throw new RuntimeException("User id not found: " + userId);
		}
		
		return theUser;
	}
	
	@PostMapping("/users")
	public User createUser(@RequestBody User theUser) {
		
		// set id to 0 to force save of new item even if another id is given
		// 0 = "INSERT"
		theUser.setId((long) 0); 
		
		userService.save(theUser);
		
		return theUser;
	}
	
	// Update
	@PutMapping("/users")
	public User saveUser(@RequestBody User theUser) {
		userService.save(theUser);
		return theUser;
	}
	
	// To Delete through HTTP DELETE 
	@DeleteMapping("/users/{userId}")
	public String deleteUser(@PathVariable Long userId) {
	
		User tempUser = userService.findById(userId);
		
		if ( tempUser == null ) {
			throw new RuntimeException("User id not found: " + userId);
		}
		
		userService.deleteById(userId);
		
		return "Deleted User id: " + userId;
	}
}
 