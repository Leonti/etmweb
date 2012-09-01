package com.leonty.etmweb.controller;

import java.util.ArrayList;

import javax.annotation.Resource;

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
import com.leonty.etmweb.domain.Settings;
import com.leonty.etmweb.domain.Tenant;
import com.leonty.etmweb.form.SettingsForm;
import com.leonty.etmweb.service.SettingsService;
import com.leonty.etmweb.service.TenantService;
import com.leonty.etmweb.validator.SettingsFormValidator;

@Controller
@Secured("ROLE_USER")
@RequestMapping("/settings")
public class SettingsController {

	@Resource(name="settingsService")
	SettingsService settingsService;	

	@Resource(name="tenantService")
	TenantService tenantService;		
	
	@Autowired
	SettingsFormValidator overtimeSettingsFormValidator;	
	
	@RequestMapping(value = "/overview", method = RequestMethod.GET)
	public String index(Model model) {
		
		Tenant tenant = ((AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getTenant();
		
		model.addAttribute("timeZones", DateTimeZone.getAvailableIDs());
		model.addAttribute("settingsForm", new SettingsForm(tenant.getSettings()));
		
		return "settings";
	}	

	@RequestMapping(value = "/overview", method = RequestMethod.POST)
	public String indexPost(@ModelAttribute("settingsForm") SettingsForm settingsForm, 
			Model model,
			BindingResult result) {

		overtimeSettingsFormValidator.validate(settingsForm, result); 
        if (result.hasErrors()) { 
        	return "settings";
        } 		
		
		Tenant tenant = ((AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getTenant();
		
		Settings settings = settingsForm.getSettings(tenant.getSettings());	
		settingsService.save(settings);		
		tenant.setSettings(settings);		
		tenantService.save(tenant);
		
		return "redirect:overview";
	}	
	
}
