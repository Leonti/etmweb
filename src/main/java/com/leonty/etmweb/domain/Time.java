package com.leonty.etmweb.domain;


import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;

import com.leonty.etm.calculation.TimeEntry;

@Entity
@Table(name="Time")
public class Time implements TimeEntry {

    @Id
    @GeneratedValue		
	private Integer id;
    
    @ManyToOne
    private Job job;

    @ManyToOne
    private Employee employee;     
    
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime timeIn;

    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime timeOut;     
    
    private String timeZone;
    
	@Column(name="tenantId")
	private Integer tenantId;
	
	public Time() {}
	
	public Time(Employee employee, Job job, DateTime timeIn, Integer tenantId) {
		this.employee = employee;
		this.job = job;
		setTimeIn(timeIn);		
		this.tenantId = tenantId;
		this.timeZone = timeIn.getZone().getID();
	}
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
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

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	@Override
	public BigDecimal getWage() {
		return new BigDecimal(job.getWage());
	}

	@Override
	public String getJobTitle() {
		return job.getTitle();
	}


    public void setTimeIn(DateTime timeIn) {
		this.timeIn = timeIn.withZone(DateTimeZone.UTC).toLocalDateTime();
	}	
	
	@Override
	public DateTime getTimeIn() {	
		return timeIn.toDateTime(DateTimeZone.UTC).withZone(DateTimeZone.forID(getTimeZone()));
	}

	@Override
	public DateTime getTimeOut() {
		
		if (timeOut == null)
			return null;
		
		return timeOut.toDateTime(DateTimeZone.UTC).withZone(DateTimeZone.forID(getTimeZone()));
	}

	@Override
	public void setTimeOut(DateTime timeOut) {
		this.timeOut = timeOut.withZone(DateTimeZone.UTC).toLocalDateTime();		
	}
}
