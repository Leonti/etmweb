package com.leonty.etmweb.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.leonty.etmweb.auth.ServiceAuthManager;
import com.leonty.etmweb.domain.AuthenticatedUser;

@Controller
@RequestMapping("/auth")
public class AuthController {
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage(@RequestParam(value="error", required=false) boolean error,
								ModelMap model) {
	
		return "auth/login";
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/service", method = RequestMethod.GET)
	public String service(ModelMap model) {
			
		AuthenticatedUser authUser = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                authUser, null);

        ServiceAuthManager authenticationManager = new ServiceAuthManager();       
        Authentication authenticatedUser = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
        
		return "redirect:/time/";
	}	
	
}
