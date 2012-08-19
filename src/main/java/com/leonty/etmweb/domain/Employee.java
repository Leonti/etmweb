package com.leonty.etmweb.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Employee")
public class Employee {

    @Id
    @GeneratedValue		
	private Integer id;
    
	private String name;
	
	private String code;
	
	@Column(name="tenantId")
	private Integer tenantId;
	
	private boolean deleted = false;
	
	public Employee() {}
	
	public Employee(String name, String code) {
		this.name = name;
		this.code = code;
	}
	
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
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
}
