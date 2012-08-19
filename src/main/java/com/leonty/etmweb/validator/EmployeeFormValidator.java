package com.leonty.etmweb.validator;

import javax.annotation.Resource;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.leonty.etmweb.domain.AuthenticatedUser;
import com.leonty.etmweb.domain.Employee;
import com.leonty.etmweb.domain.Tenant;
import com.leonty.etmweb.form.EmployeeForm;
import com.leonty.etmweb.service.EmployeeService;

@Component
public class EmployeeFormValidator implements Validator {

	private Integer employeeId;
	
	@Resource(name="employeeService")
	EmployeeService employeeService;

	@Override
	public boolean supports(@SuppressWarnings("rawtypes") Class clazz) {
		return EmployeeForm.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code", "field.required");
		
		EmployeeForm employeeForm = (EmployeeForm) obj;
		
		Tenant tenant = ((AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getTenant();
		
		Employee employee = employeeService.getByCode(employeeForm.getCode(), tenant.getId());
		if (employee != null
				&& !employee.getId().equals(employeeId)) {
			
			errors.rejectValue("code", "employee.codeunique");
		}	
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	
}
