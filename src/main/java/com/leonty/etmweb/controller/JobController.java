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
import org.springframework.web.bind.annotation.RequestParam;

import com.leonty.etmweb.domain.AuthenticatedUser;
import com.leonty.etmweb.domain.Tenant;
import com.leonty.etmweb.form.JobForm;
import com.leonty.etmweb.service.JobService;
import com.leonty.etmweb.validator.JobFormValidator;

@Controller
@Secured("ROLE_USER")
@RequestMapping("/job")
public class JobController {

	@Resource(name="jobService")
	JobService jobService;	

	@Autowired
	JobFormValidator jobFormValidator;		
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		
		Tenant tenant = ((AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getTenant();
		
		model.addAttribute("list", jobService.getList(tenant.getId()));
		
		return "job/list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		
		model.addAttribute("jobForm", new JobForm());
		
		return "job/add";
	}	

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addPost(@ModelAttribute("jobForm") JobForm jobForm, 
    					Model model,
    					BindingResult result) {
    	   	
    	jobFormValidator.validate(jobForm, result); 
        if (result.hasErrors()) { 
        	return "job/add"; 
        } 
        
        Tenant tenant = ((AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getTenant();
        com.leonty.etmweb.domain.Job job = jobForm.getJob();
        job.setTenantId(tenant.getId());
        
        jobService.save(job);   	
		
    	return "redirect:list";
	}
    
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(@RequestParam(value="id", required=true) Integer jobId,
    							Model model) {
    	
    	Tenant tenant = ((AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getTenant();
    	com.leonty.etmweb.domain.Job job = jobService.getById(jobId, tenant.getId());
    	
		model.addAttribute("jobForm", new JobForm(job));
		
		return "job/edit";
    }    
	
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editPost(@RequestParam(value="id", required=true) Integer jobId,
    					@ModelAttribute("jobForm") JobForm jobForm, 
    					Model model,
    					BindingResult result) {
    	
    	jobFormValidator.validate(jobForm, result); 
        if (result.hasErrors()) { 
        	return "job/edit"; 
        } 
        
        Tenant tenant = ((AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getTenant();
        com.leonty.etmweb.domain.Job job = jobService.getById(jobId, tenant.getId());
        job.setTitle(jobForm.getTitle());
        job.setWage(Double.valueOf(jobForm.getWage()));
        
        jobService.save(job);   	
		
    	return "redirect:list";
	}    
    
}