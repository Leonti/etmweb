package com.leonty.etmweb.validator;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.leonty.etmweb.domain.Tenant;
import com.leonty.etmweb.form.EditTenantForm;
import com.leonty.etmweb.service.TenantService;

@Component
public class EditTenantFormValidator implements Validator {

	@Resource(name="tenantService")
	TenantService tenantService;	
	
	@Override
	public boolean supports(@SuppressWarnings("rawtypes") Class clazz) {
		return EditTenantForm.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "companyName", "field.required");

		EditTenantForm editTenantForm = (EditTenantForm) obj;
		
		Tenant tenant = tenantService.getBySubdomain(editTenantForm.getSubdomain());
		if (tenant != null 
				&& !tenant.getEmail().equals(editTenantForm.getEmail())) {
			errors.rejectValue("subdomain", "subdomainregistered");
		}
	}
}
