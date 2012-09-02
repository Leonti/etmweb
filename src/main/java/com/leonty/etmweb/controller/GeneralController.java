package com.leonty.etmweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GeneralController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String about_test(Model model) {
		
		return "home";
	}	
	
	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String about(Model model) {
		
		return "about";
	}
	
	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String contact(Model model) {
		
		return "contact";
	}	
}
