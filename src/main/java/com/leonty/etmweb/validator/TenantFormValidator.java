package com.leonty.etmweb.validator;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.leonty.etmweb.form.TenantForm;
import com.leonty.etmweb.service.TenantService;

@Component
public class TenantFormValidator implements Validator {	

	@Resource(name="tenantService")
	TenantService tenantService;
	
	@Override
	public boolean supports(@SuppressWarnings("rawtypes") Class clazz) {
		return TenantForm.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "companyName", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "subdomain", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "repeatPassword", "field.required");

		TenantForm tenantForm = (TenantForm) obj;
		
		if (tenantService.getBySubdomain(tenantForm.getSubdomain()) != null) {
			errors.rejectValue("subdomain", "subdomainregistered");
		}
		
		if (tenantService.getByEmail(tenantForm.getEmail()) != null) {
			errors.rejectValue("email", "userregistered");
		}
		
		if (!tenantForm.getPassword().equals(tenantForm.getRepeatPassword())) {
			errors.rejectValue("repeatPassword", "passwordsnotequal");
		}
	}

}
