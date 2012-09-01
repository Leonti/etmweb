package com.leonty.etmweb.controller;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
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
import com.leonty.etmweb.domain.Employee;
import com.leonty.etmweb.domain.Job;
import com.leonty.etmweb.domain.Tenant;
import com.leonty.etmweb.form.EmployeeLoginForm;
import com.leonty.etmweb.form.JobSelectForm;
import com.leonty.etmweb.service.EmployeeService;
import com.leonty.etmweb.service.JobService;
import com.leonty.etmweb.service.TimeService;
import com.leonty.etmweb.validator.EmployeeLoginFormValidator;

@Controller
@Secured("ROLE_SERVICE")
@RequestMapping("/time")
public class TimeController {

	@Autowired
	EmployeeLoginFormValidator employeeLoginFormValidator;	

	@Resource(name="employeeService")
	EmployeeService employeeService;	

	@Resource(name="jobService")
	JobService jobService;	
	
	@Resource(name="timeService")
	TimeService timeService;	
	
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
		
        model.addAttribute("jobSelectForm", new JobSelectForm());
        
        Employee employee = employeeService.getByCode(employeeLoginForm.getCode(), tenant.getId());
		
        model.addAttribute("employee", employee);
        
        model.addAttribute("employeeJobs", employee.getJobs());
        
        Job currentJob = timeService.getJobAtTime(
        		employee, 
        		new DateTime(DateTimeZone.forID(tenant.getSettings().getTimeZone())), 
        		tenant.getId());
        model.addAttribute("currentJob", currentJob);
        model.addAttribute("isWorking", currentJob != null ? true : false);
        
		return "time/jobselect";
	}
	
	@RequestMapping(value = "/selectjob", method = RequestMethod.POST)
	public String selectJob(@ModelAttribute("jobSelectForm") JobSelectForm jobSelectForm,
						Model model,
						BindingResult result) {

		AuthenticatedUser authUser = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Tenant tenant = authUser.getTenant();
		
		Employee employee = employeeService.getByCode(jobSelectForm.getCode(), tenant.getId());
		
		Job job = null;
		DateTime date = new DateTime(DateTimeZone.forID(tenant.getSettings().getTimeZone()));
		if (jobSelectForm.getJobId() != 0) {
			
			job = jobService.getById(jobSelectForm.getJobId(), tenant.getId());		
			timeService.signInEmployee(employee, job, date, tenant.getId());
		} else {

			timeService.signOutEmployee(employee, date, tenant.getId());
		}

        model.addAttribute("employee", employee);
        model.addAttribute("currentJob", job);
        model.addAttribute("isWorking", job != null ? true : false);        		
		
		return "time/stats";
	}
}
