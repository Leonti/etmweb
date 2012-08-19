package com.leonty.etmweb.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.leonty.etmweb.form.EditTenantForm;

@Component
public class EditTenantFormValidator implements Validator {	
	
	@Override
	public boolean supports(@SuppressWarnings("rawtypes") Class clazz) {
		return EditTenantForm.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "companyName", "field.required");
	}
}
