package com.jeffreyghj.springusers.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jeffreyghj.springusers.dto.UserDto;
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
	public String createNewUser(@Valid @ModelAttribute("user") UserDto theUserDto,
									  BindingResult result) {
		//userService.save(theUser);
		//return "redirect:/users/confirmCreateUser";
		System.out.println(result.getAllErrors());
		if( result.hasErrors() ) {
			//return new ModelAndView("users/create-user", "user", theUserDto);
			return "users/create-user";
		} else {
			userService.createNewUser(theUserDto);
			//return new ModelAndView("users/confirm-create-user", "user", theUserDto);
			return "users/confirm-create-user";
		}
	}
	

	/*
	@GetMapping("/confirmCreateUser")
	public String confirmCreateUser( ) {
		return "users/confirm-create-user";
	}
	*/
}
