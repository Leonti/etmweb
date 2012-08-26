package com.leonty.etmweb.domain;


import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.leonty.calculation.TimeEntry;

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
    
    private Date timeIn;
    
    public void setTimeIn(Date timeIn) {
		this.timeIn = timeIn;
	}

    @Override
	public void setTimeOut(Date timeOut) {
		this.timeOut = timeOut;
	}

	private Date timeOut;   
    
	@Column(name="tenantId")
	private Integer tenantId;
	
	public Time() {}
	
	public Time(Employee employee, Job job, Date timeIn, Integer tenantId) {
		this.employee = employee;
		this.job = job;
		this.timeIn = timeIn;		
		this.tenantId = tenantId;
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

	public Date getInTime() {
		return timeIn;
	}

	public void setInTime(Date inTime) {
		this.timeIn = inTime;
	}

	public Date getOutTime() {
		return timeOut;
	}

	public void setOutTime(Date outTime) {
		this.timeOut = outTime;
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

	@Override
	public Date getTimeIn() {
		return timeIn;
	}

	@Override
	public Date getTimeOut() {
		return timeOut;
	}

	@Override
	public BigDecimal getWage() {
		return new BigDecimal(job.getWage());
	}
}
