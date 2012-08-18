package com.leonty.etmweb.form;

import com.leonty.etmweb.domain.Tenant;

public class TenantForm {

	private String companyName;
	
	private String subdomain;
	
	private String email;
	
	private String password;
	
	private String repeatPassword;

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

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}
	
	public Tenant getTenant() {
		return new Tenant(companyName, subdomain, email,
				password);
	}
}
