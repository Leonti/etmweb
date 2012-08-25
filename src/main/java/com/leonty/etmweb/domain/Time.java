package com.leonty.etmweb.domain;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Time")
public class Time {

    @Id
    @GeneratedValue		
	private Integer id;
    
    @ManyToOne
    private Job job;

    @ManyToOne
    private Employee employee;     
    
    private Date inTime;
    
    private Date outTime;   
    
	@Column(name="tenantId")
	private Integer tenantId;
	
	public Time() {}
	
	public Time(Employee employee, Job job, Date inTime, Integer tenantId) {
		this.employee = employee;
		this.job = job;
		this.inTime = inTime;		
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
		return inTime;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}

	public Date getOutTime() {
		return outTime;
	}

	public void setOutTime(Date outTime) {
		this.outTime = outTime;
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
