package com.leonty.etmweb.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.leonty.etmweb.domain.AuthenticatedUser;
import com.leonty.etmweb.domain.Tenant;
import com.leonty.etmweb.form.EmployeeLoginForm;
import com.leonty.etmweb.service.EmployeeService;
import com.leonty.etmweb.validator.EmployeeLoginFormValidator;

@Controller
@Secured("ROLE_SERVICE")
@RequestMapping("/time")
public class Time {

	@Autowired
	EmployeeLoginFormValidator employeeLoginFormValidator;	

	@Resource(name="employeeService")
	EmployeeService employeeService;	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String landing(Model model) {
		
		model.addAttribute("employeeLoginForm", new EmployeeLoginForm());
		
		return "time/landing";
	}	

	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String landingPost(@ModelAttribute("employeeLoginForm") EmployeeLoginForm employeeLoginForm,
						Model model,
						BindingResult result) {

		AuthenticatedUser authUser = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Tenant tenant = authUser.getTenant();		
		
		employeeLoginFormValidator.setTenantId(tenant.getId());
		employeeLoginFormValidator.validate(employeeLoginForm, result); 
        if (result.hasErrors()) { 
        	return "time/landing"; 
        } 
		
        com.leonty.etmweb.domain.Employee employee = employeeService.getByCode(employeeLoginForm.getCode(), tenant.getId());
		
        model.addAttribute("employee", employee);
        
		return "time/jobselect";
	}	
}
