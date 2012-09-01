package com.leonty.etmweb.form;

import com.leonty.etmweb.domain.Settings;

public class SettingsForm {

	private String dayRegularOvertimeLimit;
	private String dayExtraOvertimeLimit;
	
	private String consecutiveDaysLimit = "7";
	private String weekOvertimeLimit;
	
	private String regularOvertimeMultiplier;
	private String extraOvertimeMultiplier;
	
	private String timeZone;
	
	public SettingsForm() {}
	
	public SettingsForm(Settings settings) {
		this.dayRegularOvertimeLimit = settings.getDayRegularOvertimeLimit().toString();
		this.dayExtraOvertimeLimit = settings.getDayExtraOvertimeLimit().toString();
		this.consecutiveDaysLimit = settings.getConsecutiveDaysLimit().toString();
		this.weekOvertimeLimit = settings.getWeekOvertimeLimit().toString();
		this.regularOvertimeMultiplier = settings.getRegularOvertimeMultiplier().toString();
		this.extraOvertimeMultiplier = settings.getExtraOvertimeMultiplier().toString();
		this.timeZone = settings.getTimeZone();
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

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public Settings getSettings(Settings settings) {	

		settings.setDayRegularOvertimeLimit(Double.valueOf(getDayRegularOvertimeLimit()));
		settings.setDayExtraOvertimeLimit(Double.valueOf(getDayExtraOvertimeLimit()));
		settings.setConsecutiveDaysLimit(Integer.valueOf(getConsecutiveDaysLimit()));
		settings.setWeekOvertimeLimit(Double.valueOf(getWeekOvertimeLimit()));
		settings.setRegularOvertimeMultiplier(Double.valueOf(getRegularOvertimeMultiplier()));
		settings.setExtraOvertimeMultiplier(Double.valueOf(getExtraOvertimeMultiplier()));
		settings.setTimeZone(getTimeZone());
		
		return settings;
	}
}
