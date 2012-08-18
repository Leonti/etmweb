package com.leonty.etmweb.controller;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.leonty.etmweb.domain.Tenant;
import com.leonty.etmweb.form.EditTenantForm;
import com.leonty.etmweb.form.ForgotForm;
import com.leonty.etmweb.form.PasswordForm;
import com.leonty.etmweb.form.TenantForm;
import com.leonty.etmweb.service.ConfigurationService;
import com.leonty.etmweb.service.EmailService;
import com.leonty.etmweb.service.TenantService;
import com.leonty.etmweb.validator.EditTenantFormValidator;
import com.leonty.etmweb.validator.ForgotFormValidator;
import com.leonty.etmweb.validator.PasswordFormValidator;
import com.leonty.etmweb.validator.TenantFormValidator;

@Controller
@RequestMapping("/account")
public class Account {

	@Resource(name="tenantService")
	TenantService tenantService;

	@Resource(name="emailService")
	EmailService emailService;
	
	@Resource(name="configService")
	ConfigurationService configService;
	
	@Autowired
	TenantFormValidator tenantFormValidator;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ApplicationContext context;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {	 
		dataBinder.setDisallowedFields(new String[] {"id"});
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model) {
		
		System.out.println(context.getMessage("expiredReset", null, Locale.getDefault()));
		System.out.println(request.getRequestURL().toString().replace(request.getServletPath(), ""));
		
		model.addAttribute("tenantForm", new TenantForm());						
		return "account/register";
	}	
	
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPost(@ModelAttribute("tenantForm") TenantForm tenantForm, 
    					Model model,
    					BindingResult result) {
    	
    	
    	tenantFormValidator.validate(tenantForm, result); 
        if (result.hasErrors()) { 
        	return "account/register"; 
        } 
        
        Tenant tenant = tenantForm.getTenant();
        
        SecureRandom random = new SecureRandom();
        String confirmationKey = new BigInteger(130, random).toString(32);
        
        tenant.setConfirmationKey(confirmationKey);       
    	tenantService.save(tenant);
    	
    	
		try {
			
	        Map<String, Object> replacements = new HashMap<String, Object>();
	        replacements.put("baseUrl", configService.getProperty("site.baseurl"));
			replacements.put("confirmationkey", confirmationKey);
			
			emailService.sendSimpleMail(tenantForm.getEmail(), "confirmation", replacements);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	return "account/confirmationsent";
	}
    
    @RequestMapping(value = "/confirm", method = RequestMethod.GET)
    public String confirmation(@RequestParam(value="key", required=true) String confirmationKey,
    							Model model) {
    	
    	Tenant tenant = tenantService.getByConfirmationKey(confirmationKey);

    	if (tenant == null) {    		
    		model.addAttribute("error", "Invalid confirmation key");
    		return "errormessage";
    	}
    	
		tenant.setConfirmationKey("");
		tenantService.save(tenant);
    	return "account/confirmed";
    }
    
    @Secured("ROLE_USER")
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String getEdit(Model model) {
    	
    	Tenant tenant = (Tenant) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	EditTenantForm editTenantForm = new EditTenantForm();
    	editTenantForm.setCompanyName(tenant.getCompanyName());
    	editTenantForm.setSubdomain(tenant.getSubdomain());
    	
		model.addAttribute("editTenantForm", editTenantForm);	
		model.addAttribute("changePasswordForm", new PasswordForm());
		return "account/edit";
    } 
    
    @Secured("ROLE_USER")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String postEdit(@ModelAttribute("editTenantForm") EditTenantForm editTenantForm,
    							Model model,
    							BindingResult result) {

    	Tenant tenant = (Tenant) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  		
		editTenantForm.setEmail(tenant.getEmail());
		
    	EditTenantFormValidator editValidator = new EditTenantFormValidator();   	
    	editValidator.validate(editTenantForm, result);
    	
    	if (result.hasErrors()) {
    		return "edit";
    	}
    	
		tenant.setCompanyName(editTenantForm.getCompanyName());
		tenant.setSubdomain(editTenantForm.getSubdomain()); 	
    	tenantService.save(tenant);
    	
    	return "redirect:overview";
    }
    
    @RequestMapping(value = "/savepassword", method = RequestMethod.POST)
    public String savePassword( @ModelAttribute("changePasswordForm") PasswordForm changePasswordForm,
									Model model,
									BindingResult result) {
    	   	
    	PasswordFormValidator validator = new PasswordFormValidator();
    	validator.validate(changePasswordForm, result); 
    	   	
    	if (result.hasErrors()) {
    		return "account/edit";
    	}
    	
    	Tenant tenant = (Tenant) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	tenant.setPassword(changePasswordForm.getPassword());
    	tenantService.save(tenant);
    	return "redirect:overview";    	
    }
    
    @RequestMapping(value = "/forgot", method = RequestMethod.GET)
    public String getForgot(Model model) {
    	
    	model.addAttribute("forgotForm", new ForgotForm());
    	return "account/forgot";
    }
    
    @RequestMapping(value = "/forgot", method = RequestMethod.POST)
    public String forgotPost(@ModelAttribute("forgotForm") ForgotForm forgotForm,
    							Model model,
    							BindingResult result) {
    	ForgotFormValidator validator = new ForgotFormValidator();
    	validator.validate(forgotForm, result);
    	if (result.hasErrors()) {
    		return "account/forgot";
    	}
    	
    	Tenant tenant = tenantService.getByEmail(forgotForm.getEmail());
        SecureRandom random = new SecureRandom();
        String forgotKey = new BigInteger(130, random).toString(32);
    	
        tenant.setForgotKey(forgotKey);
        
        tenantService.save(tenant);
    	
		try {
			
	        Map<String, Object> replacements = new HashMap<String, Object>();
	        replacements.put("baseUrl", configService.getProperty("site.baseurl"));
			replacements.put("forgotkey", forgotKey);
			
			emailService.sendSimpleMail(forgotForm.getEmail(), "forgot", replacements);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "account/forgotsent";
    	
    }
    
    @RequestMapping(value = "/reset", method = RequestMethod.GET)
    public String getReset(@RequestParam(value="key", required=true) String forgotKey,  
									Model model) {
    	    	
    	PasswordForm passwordForm = new PasswordForm();
    	
    	model.addAttribute("resetForm", passwordForm);
    	
    	return "account/reset";
    }
    
    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    public String resetPost(@RequestParam(value="key", required=true) String forgotKey,
    						@ModelAttribute("resetForm") PasswordForm resetForm,
							Model model,
							BindingResult result) {
    	
    	PasswordFormValidator validator = new PasswordFormValidator();
    	validator.validate(resetForm, result);
    	if (result.hasErrors()) {
    		return "account/reset";
    	}

    	
    	
    	Tenant tenant = tenantService.getByForgotKey(forgotKey);
    	if (tenant == null) {
    		model.addAttribute("error", "Invalid reset key");
    		return "errormessage";
    	}    	

    	tenant.setPassword(resetForm.getPassword());
    	tenant.setForgotKey("");
    	
    	tenantService.save(tenant);
    	
    	return "account/reseted";
    } 
    
    @Secured("ROLE_USER")
    @RequestMapping(value = "/overview", method = RequestMethod.GET)
    public String overview(Model model) {
    	
    	Tenant tenant = (Tenant) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	
    	
    	model.addAttribute("etmsite", configService.getProperty("site.etmsite"));
    	model.addAttribute("tenant", tenant);
    	return "account/overview";
    }
    
}
