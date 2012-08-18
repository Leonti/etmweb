package com.leonty.etmweb.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Tenant")
public class Tenant {

    @Id
    @GeneratedValue	
	private Integer id;
	
	private String companyName;

	@Column(name="subdomain")
	private String subdomain;
	
	@Column(name="email")
	private String email;
	
	private String password;

	@Column(name="forgotKey")
	private String forgotKey;
	
	@Column(name="confirmationKey")
	private String confirmationKey;
	
	private Boolean deleted = false;
	
	public String getForgotKey() {
		return forgotKey;
	}

	public void setForgotKey(String forgotKey) {
		this.forgotKey = forgotKey;
	}

	public String getConfirmationKey() {
		return confirmationKey;
	}

	public void setConfirmationKey(String confirmationKey) {
		this.confirmationKey = confirmationKey;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Tenant() {}	
	
	public Tenant(String companyName, String subdomain, String email,
			String password) {
		
		this.companyName = companyName;
		this.subdomain = subdomain;
		this.email = email;
		this.password = password;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getSubdomain() {
		return subdomain;
	}

	public void setSubdomain(String subdomain) {
		this.subdomain = subdomain;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getId() {
		return id;
	}	
}
