package com.jeffreyghj.springusers.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jeffreyghj.springusers.dto.UpdateUserDto;
import com.jeffreyghj.springusers.entity.User;
import com.jeffreyghj.springusers.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/list")
	public String listUsers(Model theModel) {
		List<User> theUsers = userService.findAll();
		theModel.addAttribute("users", theUsers);
		return "users/list-users"; // return templates/users/list-users.html
	}
	
	// To Delete with "Delete" button
	@GetMapping("/delete")
	public String delete(@RequestParam("userId") Long theId) {
		userService.deleteById(theId); // No checks necessary, the button only exists if the user+id exist
		return "redirect:/users/list";
	}
	
	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute("user") User theUser) {
		userService.save(theUser);
		return "redirect:/users/list";
	}
	
	@GetMapping("/showUpdateUserForm")
	public String showUpdateUserForm(@RequestParam("userId") Long theId, Model theModel) {
		
		User theUser = userService.findById(theId);
		
		if ( theUser != null ) {
			System.out.println(getClass() + "-- found: " + theUser.getUsername() + "; by Id: " + theId);
		}
		
		UpdateUserDto updateUserDto = new UpdateUserDto();
		updateUserDto.setId(theId);
		
		System.out.println("User dto id: " + updateUserDto.getId());

		
		theModel.addAttribute("user", theUser);
		theModel.addAttribute("updateUserDto", updateUserDto);
		
		return "users/update-user";
	}
	
	@PostMapping("/updateUser")
	public String updateUser(@Valid @ModelAttribute("updateUserDto") UpdateUserDto updateUserDto, 
									BindingResult result) {
		
		System.out.println(getClass() + " executing...");
		System.out.println("User dto: " + updateUserDto.getId() + updateUserDto.getFirstName() + updateUserDto.getLastName() + updateUserDto.getUsername());
		
		// Check for error
		if( result.hasErrors() ) {
			return "users/update-user";
		} else {
			// Apply updates to user
			userService.updateUser(updateUserDto);
			return "users/confirm-create-user";
		}

	} 
	
	/*
	@PostMapping("/createNewUser")
	public String createNewUser(@ModelAttribute("user") User theUser) {
		userService.save(theUser);
		return "redirect:/users/confirmCreateUser";
	}
	*/
	
	/*
	@GetMapping("/showCreateUserForm")
	public String showCreateUserForm(Model theModel) {
		User theUser = new User();
		theModel.addAttribute("user", theUser);
		return "users/create-user";
	}
	*/
	
}
