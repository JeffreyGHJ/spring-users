package com.jeffreyghj.springusers.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	// this method might not be called and spring might be returning the index page another way
	@RequestMapping("/")
	public String showIndexPage() {
		return "index";
	}
	
	@RequestMapping("/home")
	public String showHomePage() {
		return "home";
	}
	
	/*
	@RequestMapping("/")
	public ModelAndView showHomePage() {
		
	}
	*/
	
}
