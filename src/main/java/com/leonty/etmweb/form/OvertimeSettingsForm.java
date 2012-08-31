package com.leonty.etmweb.form;

import com.leonty.etmweb.domain.OvertimeSettings;

public class OvertimeSettingsForm {

	private String dayRegularOvertimeLimit;
	private String dayExtraOvertimeLimit;
	
	private String consecutiveDaysLimit = "7";
	private String weekOvertimeLimit;
	
	private String regularOvertimeMultiplier;
	private String extraOvertimeMultiplier;
	
	
	public OvertimeSettingsForm() {}
	
	public OvertimeSettingsForm(OvertimeSettings overtimeSettings) {
		this.dayRegularOvertimeLimit = overtimeSettings.getDayRegularOvertimeLimit().toString();
		this.dayExtraOvertimeLimit = overtimeSettings.getDayExtraOvertimeLimit().toString();
		this.consecutiveDaysLimit = overtimeSettings.getConsecutiveDaysLimit().toString();
		this.weekOvertimeLimit = overtimeSettings.getWeekOvertimeLimit().toString();
		this.regularOvertimeMultiplier = overtimeSettings.getRegularOvertimeMultiplier().toString();
		this.extraOvertimeMultiplier = overtimeSettings.getExtraOvertimeMultiplier().toString();		
	}
	

	
	public String getDayRegularOvertimeLimit() {
		return dayRegularOvertimeLimit;
	}

	public void setDayRegularOvertimeLimit(String dayRegularOvertimeLimit) {
		this.dayRegularOvertimeLimit = dayRegularOvertimeLimit;
	}

	public String getDayExtraOvertimeLimit() {
		return dayExtraOvertimeLimit;
	}

	public void setDayExtraOvertimeLimit(String dayExtraOvertimeLimit) {
		this.dayExtraOvertimeLimit = dayExtraOvertimeLimit;
	}

	public String getConsecutiveDaysLimit() {
		return consecutiveDaysLimit;
	}

	public void setConsecutiveDaysLimit(String consecutiveDaysLimit) {
		this.consecutiveDaysLimit = consecutiveDaysLimit;
	}

	public String getWeekOvertimeLimit() {
		return weekOvertimeLimit;
	}

	public void setWeekOvertimeLimit(String weekOvertimeLimit) {
		this.weekOvertimeLimit = weekOvertimeLimit;
	}

	public String getRegularOvertimeMultiplier() {
		return regularOvertimeMultiplier;
	}

	public void setRegularOvertimeMultiplier(String regularOvertimeMultiplier) {
		this.regularOvertimeMultiplier = regularOvertimeMultiplier;
	}

	public String getExtraOvertimeMultiplier() {
		return extraOvertimeMultiplier;
	}

	public void setExtraOvertimeMultiplier(String extraOvertimeMultiplier) {
		this.extraOvertimeMultiplier = extraOvertimeMultiplier;
	}

	public OvertimeSettings getOvertimeSettings(Integer tenantId) {	
		
		return new OvertimeSettings(Double.valueOf(getDayRegularOvertimeLimit()), 
				Double.valueOf(getDayExtraOvertimeLimit()), Integer.valueOf(getConsecutiveDaysLimit()), 
				Double.valueOf(getWeekOvertimeLimit()), Double.valueOf(getRegularOvertimeMultiplier()),
				Double.valueOf(getExtraOvertimeMultiplier()), tenantId);
	}
}
