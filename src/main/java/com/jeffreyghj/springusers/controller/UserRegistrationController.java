package com.jeffreyghj.springusers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jeffreyghj.springusers.dto.UserDto;
import com.jeffreyghj.springusers.entity.User;
import com.jeffreyghj.springusers.service.UserService;

@Controller
@RequestMapping("/users")
public class UserRegistrationController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/showCreateUserForm")
	public String showCreateUserForm(Model theModel) {
		UserDto userDto = new UserDto();
		theModel.addAttribute("user", userDto);
		return "users/create-user";
	}
	
	@PostMapping("/createNewUser")
	public ModelAndView createNewUser(@ModelAttribute("user") UserDto theUserDto) {
		//userService.save(theUser);
		//return "redirect:/users/confirmCreateUser";
		
		userService.createNewUser(theUserDto);
		return new ModelAndView("users/confirm-create-user", "user", theUserDto);
	}

	/*
	@GetMapping("/confirmCreateUser")
	public String confirmCreateUser( ) {
		return "users/confirm-create-user";
	}
	*/
}
