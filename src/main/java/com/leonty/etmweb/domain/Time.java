package com.leonty.etmweb.domain;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="TimeEntry")
public class Time {

    @Id
    @GeneratedValue		
	private Integer id;
    
    @ManyToOne
    private Job job;

    @ManyToOne
    private Employee employee;     
    
    private Date in;
    
    private Date out;   
    
	@Column(name="tenantId")
	private Integer tenantId;

	
	public Time() {}
	
	public Time(Employee employee, Job job, Date in, Integer tenantId) {
		this.employee = employee;
		this.job = job;
		this.in = in;		
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

	public Date getIn() {
		return in;
	}

	public void setIn(Date in) {
		this.in = in;
	}

	public Date getOut() {
		return out;
	}

	public void setOut(Date out) {
		this.out = out;
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
