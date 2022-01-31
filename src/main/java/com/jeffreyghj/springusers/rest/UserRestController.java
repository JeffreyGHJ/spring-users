package com.jeffreyghj.springusers.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeffreyghj.springusers.dao.RoleRepository;
import com.jeffreyghj.springusers.dto.PasswordDto;
import com.jeffreyghj.springusers.dto.UpdateUserDto;
import com.jeffreyghj.springusers.dto.UserDto;
import com.jeffreyghj.springusers.entity.User;
import com.jeffreyghj.springusers.service.UserService;

//@RestController
@RestController
@RequestMapping("/api")
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRepository roleRepository;
	
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
	public User createUser(@Valid @RequestBody UserDto theUserDto) {
		
//		// set id to 0 to force save of new item even if another id is given
//		// 0 = "INSERT"
//		theUser.setId((long) 0); 
//		theUser.setRoles(Arrays.asList(roleRepository.findRoleByName("USER")));
//		userService.save(theUser);
//		return theUser;
		
		return userService.createNewUser(theUserDto);
	}
	
	// Update
	@PutMapping("/users")
	public User saveUser(@Valid @RequestBody UpdateUserDto theUser) {
		//userService.save(theUser);
		return userService.updateUser(theUser);
	}
	
	// To Delete through HTTP DELETE 
	@DeleteMapping("/users/{userId}")
	public String deleteUser(@PathVariable Long userId, @RequestBody PasswordDto passwordDto) {
	
		System.out.println("Password given: " + passwordDto.getPassword());
		
		User tempUser = userService.findById(userId);
		
		if ( tempUser == null ) {
			throw new RuntimeException("User id not found: " + userId);
		}
		
		if ( !userService.authenticate(tempUser, passwordDto.getPassword()) ) {
			throw new RuntimeException("Incorrect password - delete not authorized");
		}
		
		userService.deleteById(userId);
		
		return "Deleted User id: " + userId;
	}
}
 