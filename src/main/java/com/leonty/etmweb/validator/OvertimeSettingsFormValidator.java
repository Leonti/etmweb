package com.leonty.etmweb.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.leonty.etmweb.form.OvertimeSettingsForm;

@Component
public class OvertimeSettingsFormValidator implements Validator {

	@Override
	public boolean supports(@SuppressWarnings("rawtypes") Class clazz) {
		return OvertimeSettingsForm.class.equals(clazz);
	}	

	@Override
	public void validate(Object obj, Errors errors) {
		
		String [] fields = new String[] {
				"dayRegularOvertimeLimit", 
				"dayExtraOvertimeLimit",
				"consecutiveDaysLimit",
				"weekOvertimeLimit",
				"regularOvertimeMultiplier",
				"extraOvertimeMultiplier"
				};

		for (String field : fields) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, field, "field.required");			
		}
		
		OvertimeSettingsForm overtimeSettingsForm = (OvertimeSettingsForm) obj;
		
		if (!overtimeSettingsForm.getDayRegularOvertimeLimit().matches("-?\\d+(\\.\\d+)?")) {
			errors.rejectValue("dayRegularOvertimeLimit", "field.notnumeric");
		}		
		if (!overtimeSettingsForm.getDayExtraOvertimeLimit().matches("-?\\d+(\\.\\d+)?")) {
			errors.rejectValue("dayExtraOvertimeLimit", "field.notnumeric");
		}		
		if (!overtimeSettingsForm.getConsecutiveDaysLimit().matches("-?\\d+(\\.\\d+)?")) {
			errors.rejectValue("consecutiveDaysLimit", "field.notnumeric");
		}	
		if (!overtimeSettingsForm.getWeekOvertimeLimit().matches("-?\\d+(\\.\\d+)?")) {
			errors.rejectValue("weekOvertimeLimit", "field.notnumeric");
		}			
		if (!overtimeSettingsForm.getRegularOvertimeMultiplier().matches("-?\\d+(\\.\\d+)?")) {
			errors.rejectValue("regularOvertimeMultiplier", "field.notnumeric");
		}		
		if (!overtimeSettingsForm.getExtraOvertimeMultiplier().matches("-?\\d+(\\.\\d+)?")) {
			errors.rejectValue("extraOvertimeMultiplier", "field.notnumeric");
		}			
		
	}		
	
}
