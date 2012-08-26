package com.leonty.etmweb.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
@Table(name="Job")
public class Job {

    @Id
    @GeneratedValue		
	private Integer id;	
	
    private String title;
    private Double wage;
    
	@Column(name="tenantId")
	private Integer tenantId;
    
    public Job() {}
    
    public Job(String title, Double wage) {
    	this.title = title;
    	this.wage = wage;
    }
    
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Double getWage() {
		return wage;
	}
	public void setWage(Double wage) {
		this.wage = wage;
	}
	public Integer getId() {
		return id;
	}
	public Integer getTenantId() {
		return tenantId;
	}

	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}
	
	@Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31). // two randomly chosen prime numbers
            append(title).
            append(wage).
            append(tenantId).
            toHashCode();
    }
	
	@Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (obj.getClass() != getClass())
            return false;

        Job job = (Job) obj;
        return new EqualsBuilder().
            append(title, job.getTitle()).
            append(wage, job.getWage()).
            append(tenantId, job.getTenantId()).
            isEquals();
    }	
}
