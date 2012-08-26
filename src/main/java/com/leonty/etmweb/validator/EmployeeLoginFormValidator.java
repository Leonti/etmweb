package com.leonty.etmweb.validator;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.leonty.etmweb.form.EmployeeLoginForm;
import com.leonty.etmweb.form.TenantForm;
import com.leonty.etmweb.service.EmployeeService;

@Component
public class EmployeeLoginFormValidator implements Validator {

	@Resource(name="employeeService")
	EmployeeService employeeService;
	
	private Integer tenantId;
	
	@Override
	public boolean supports(@SuppressWarnings("rawtypes") Class clazz) {
		return EmployeeLoginForm.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code", "field.required");

		EmployeeLoginForm employeeLoginForm = (EmployeeLoginForm) obj;
		
		if (employeeService.getByCode(employeeLoginForm.getCode(), tenantId) == null) {
			errors.rejectValue("code", "time.invalidCode");
		}
	}

	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}
	
}
