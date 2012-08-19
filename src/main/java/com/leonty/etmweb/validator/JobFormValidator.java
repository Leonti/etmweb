package com.leonty.etmweb.validator;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.leonty.etmweb.form.JobForm;
import com.leonty.etmweb.service.JobService;

@Component
public class JobFormValidator implements Validator {

	@Resource(name="jobService")
	JobService jobService;

	@Override
	public boolean supports(@SuppressWarnings("rawtypes") Class clazz) {
		return JobForm.class.equals(clazz);
	}	

	@Override
	public void validate(Object obj, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "wage", "field.required");
		
		JobForm jobForm = (JobForm) obj;
		
		if (!jobForm.getWage().matches("-?\\d+(\\.\\d+)?")) {
			errors.rejectValue("wage", "job.notnumeric");
		}		
	}	
}
