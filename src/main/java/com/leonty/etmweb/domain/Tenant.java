package com.leonty.etmweb.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Tenant")
public class Tenant {

    @Id
    @GeneratedValue	
	private Integer id;
	
	private String companyName;
	
	@Column(name="email")
	private String email;
	
	private String password;

	@Column(name="forgotKey")
	private String forgotKey = null;
	
	@Column(name="confirmationKey")
	private String confirmationKey = null;
	
	@OneToOne
	private Settings settings;

	private Boolean deleted = false;
	
	private Integer role = 2;
	
	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

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
	
	public Tenant(String companyName, String email,
			String password) {
		
		this.companyName = companyName;
		this.email = email;
		this.password = password;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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

	public Settings getSettings() {
		
		if (settings == null)
			 return new Settings();
		
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}	
	
	public Integer getId() {
		return id;
	}	
}
