package com.leonty.etmweb.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.leonty.etmweb.domain.AuthenticatedUser;
import com.leonty.etmweb.domain.Tenant;
import com.leonty.etmweb.form.EmployeeForm;
import com.leonty.etmweb.service.EmployeeService;
import com.leonty.etmweb.validator.EmployeeFormValidator;

@Controller
@Secured("ROLE_USER")
@RequestMapping("/employee")
public class Employee {

	@Resource(name="employeeService")
	EmployeeService employeeService;	

	@Autowired
	EmployeeFormValidator employeeFormValidator;	

	@Autowired
	private ApplicationContext context; 	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		
		Tenant tenant = ((AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getTenant();
		
		model.addAttribute("list", employeeService.getList(tenant.getId()));
		
		return "employee/list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		
		model.addAttribute("employeeForm", new EmployeeForm());
		
		return "employee/add";
	}	

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addPost(@ModelAttribute("employeeForm") EmployeeForm employeeForm, 
    					Model model,
    					BindingResult result) {
    	   	
    	employeeFormValidator.validate(employeeForm, result); 
        if (result.hasErrors()) { 
        	return "employee/add"; 
        } 
        
        Tenant tenant = ((AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getTenant();
        com.leonty.etmweb.domain.Employee employee = employeeForm.getEmployee();
        employee.setTenantId(tenant.getId());
        
        employeeService.save(employee);   	
		
    	return "redirect:list";
	}
    
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(@RequestParam(value="id", required=true) Integer employeeId,
    							Model model) {
    	
    	Tenant tenant = ((AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getTenant();
    	com.leonty.etmweb.domain.Employee employee = employeeService.getById(employeeId, tenant.getId());
    	
		model.addAttribute("employeeForm", new EmployeeForm(employee));
		
		return "employee/edit";
    }    
	
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editPost(@RequestParam(value="id", required=true) Integer employeeId,
    					@ModelAttribute("employeeForm") EmployeeForm employeeForm, 
    					Model model,
    					BindingResult result) {
    	
    	employeeFormValidator.setEmployeeId(employeeId);
    	employeeFormValidator.validate(employeeForm, result); 
        if (result.hasErrors()) { 
        	return "employee/edit"; 
        } 
        
        Tenant tenant = ((AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getTenant();
        com.leonty.etmweb.domain.Employee employee = employeeService.getById(employeeId, tenant.getId());
        employee.setName(employeeForm.getName());
        employee.setCode(employeeForm.getCode());
        
        employeeService.save(employee);   	
		
    	return "redirect:list";
	}    
    
}
