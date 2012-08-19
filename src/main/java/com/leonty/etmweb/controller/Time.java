package com.leonty.etmweb.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.leonty.etmweb.domain.AuthenticatedUser;
import com.leonty.etmweb.domain.Tenant;

@Controller
@Secured("ROLE_SERVICE")
@RequestMapping("/time")
public class Time {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String landing(Model model) {
		
		AuthenticatedUser authUser = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Tenant tenant = authUser.getTenant();
		
		return "time/landing";
	}	
	
}
