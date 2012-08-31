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
import com.leonty.etmweb.domain.OvertimeSettings;
import com.leonty.etmweb.domain.Tenant;
import com.leonty.etmweb.form.OvertimeSettingsForm;
import com.leonty.etmweb.service.SettingsService;
import com.leonty.etmweb.service.TenantService;
import com.leonty.etmweb.validator.OvertimeSettingsFormValidator;

@Controller
@Secured("ROLE_USER")
@RequestMapping("/settings")
public class Settings {

	@Resource(name="settingsService")
	SettingsService settingsService;	

	@Resource(name="tenantService")
	TenantService tenantService;		
	
	@Autowired
	OvertimeSettingsFormValidator overtimeSettingsFormValidator;	
	
	@RequestMapping(value = "/overtime", method = RequestMethod.GET)
	public String index(Model model) {
		
		Tenant tenant = ((AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getTenant();
		
		model.addAttribute("overtimeSettingsForm", new OvertimeSettingsForm(tenant.getOvertimeSettings()));
		
		return "settings/overtime";
	}	

	@RequestMapping(value = "/overtime", method = RequestMethod.POST)
	public String indexPost(@ModelAttribute("overtimeSettingsForm") OvertimeSettingsForm overtimeSettingsForm, 
			Model model,
			BindingResult result) {

		overtimeSettingsFormValidator.validate(overtimeSettingsForm, result); 
        if (result.hasErrors()) { 
        	return "settings/overtime";
        } 		
		
		Tenant tenant = ((AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getTenant();
		
		OvertimeSettings overtimeSettings = overtimeSettingsForm.getOvertimeSettings(tenant.getId());	
		settingsService.save(overtimeSettings);
		
		tenant.setOvertimeSettings(overtimeSettings);		
		tenantService.save(tenant);
		
		model.addAttribute("overtimeSettingsForm", overtimeSettingsForm);
		
		return "redirect:overtime";
	}	
	
}
