package com.leonty.etmweb.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.leonty.etmweb.form.ForgotForm;
import com.leonty.etmweb.service.TenantService;

public class ForgotFormValidator implements Validator {

	@Override
	public boolean supports(@SuppressWarnings("rawtypes") Class clazz) {
		return ForgotForm.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "field.required");
		
		ForgotForm forgotForm = (ForgotForm) obj;
		
		TenantService tenantService = new TenantService();
		
		if (tenantService.getByEmail(forgotForm.getEmail()) == null) {
			errors.rejectValue("email", "emailnotfound");
		}		
	}

}
