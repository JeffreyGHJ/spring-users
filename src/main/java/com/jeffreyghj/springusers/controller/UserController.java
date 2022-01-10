package com.jeffreyghj.springusers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
