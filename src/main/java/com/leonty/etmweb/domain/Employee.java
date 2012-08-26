package com.leonty.etmweb.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Employee")
public class Employee {

    @Id
    @GeneratedValue		
	private Integer id;
    
	private String name;
	
	private String code;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "employee_job",
		joinColumns = { 
			@JoinColumn(name = "employee_id") }, 
		inverseJoinColumns = { 
			@JoinColumn(name = "job_id") })	
	private Set<Job> jobs = new HashSet<Job> ();
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Time> times;
	
	@Column(name="tenantId")
	private Integer tenantId;
	
	public Employee() {}
	
	public Employee(String name, String code) {
		this.name = name;
		this.code = code;
	}
	
	public Integer getTenantId() {
		return tenantId;
	}
	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getId() {
		return id;
	}

	public Set<Job> getJobs() {
		return jobs;
	}

	public void setJobs(Set<Job> jobs) {
		this.jobs = jobs;
	}

	public List<Time> getTimes() {
		return times;
	}

	public void setTimes(List<Time> times) {
		this.times = times;
	}	
}
