package com.leonty.etmweb.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="OvertimeSettings")
public class OvertimeSettings {

    @Id
    @GeneratedValue		
	private Integer id;	
	
	private Double dayRegularOvertimeLimit;
	private Double dayExtraOvertimeLimit;
	
	private Integer consecutiveDaysLimit = 7;
	private Double weekOvertimeLimit;
	
	private Double regularOvertimeMultiplier;
	private Double extraOvertimeMultiplier;

	@Column(name="tenantId")
	private Integer tenantId;

	
	
	public OvertimeSettings(Double dayRegularOvertimeLimit,
			Double dayExtraOvertimeLimit, int consecutiveDaysLimit,
			Double weekOvertimeLimit, Double regularOvertimeMultiplier,
			Double extraOvertimeMultiplier, Integer tenantId) {
		super();
		this.dayRegularOvertimeLimit = dayRegularOvertimeLimit;
		this.dayExtraOvertimeLimit = dayExtraOvertimeLimit;
		this.consecutiveDaysLimit = consecutiveDaysLimit;
		this.weekOvertimeLimit = weekOvertimeLimit;
		this.regularOvertimeMultiplier = regularOvertimeMultiplier;
		this.extraOvertimeMultiplier = extraOvertimeMultiplier;
		this.tenantId = tenantId;
	}

	public OvertimeSettings() {}

	public Double getDayRegularOvertimeLimit() {
		return dayRegularOvertimeLimit;
	}
	
	public Long getDayRegularOvertimeLimitInSeconds() {
		return new BigDecimal(dayRegularOvertimeLimit).multiply(new BigDecimal(60*60)).longValueExact();
	}
	
	public void setDayRegularOvertimeLimit(Double dayRegularOvertimeLimit) {
		this.dayRegularOvertimeLimit = dayRegularOvertimeLimit;
	}

	public Double getDayExtraOvertimeLimit() {
		return dayExtraOvertimeLimit;
	}

	public Long getDayExtraOvertimeLimitInSeconds() {
		return new BigDecimal(dayExtraOvertimeLimit).multiply(new BigDecimal(60*60)).longValueExact();
	}	
	
	public void setDayExtraOvertimeLimit(Double dayExtraOvertimeLimit) {
		this.dayExtraOvertimeLimit = dayExtraOvertimeLimit;
	}

	public Integer getConsecutiveDaysLimit() {
		return consecutiveDaysLimit;
	}

	public void setConsecutiveDaysLimit(Integer consecutiveDaysLimit) {
		this.consecutiveDaysLimit = consecutiveDaysLimit;
	}

	public Double getWeekOvertimeLimit() {
		return weekOvertimeLimit;
	}
	
	public Long getWeekOvertimeLimitInSeconds() {
		return new BigDecimal(weekOvertimeLimit).multiply(new BigDecimal(60*60)).longValueExact();
	}	

	public void setWeekOvertimeLimit(Double weekOvertimeLimit) {
		this.weekOvertimeLimit = weekOvertimeLimit;
	}

	public Double getRegularOvertimeMultiplier() {
		return regularOvertimeMultiplier;
	}

	public void setRegularOvertimeMultiplier(Double regularOvertimeMultiplier) {
		this.regularOvertimeMultiplier = regularOvertimeMultiplier;
	}

	public Double getExtraOvertimeMultiplier() {
		return extraOvertimeMultiplier;
	}		
	
	public void setExtraOvertimeMultiplier(Double extraOvertimeMultiplier) {
		this.extraOvertimeMultiplier = extraOvertimeMultiplier;
	}

	public Integer getTenantId() {
		return tenantId;
	}

	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}

	public Integer getId() {
		return id;
	}
	
}
