package com.leonty.etmweb.form;

import com.leonty.etmweb.domain.Employee;

public class EmployeeForm {

	private String name;
	private String code;
	
	public EmployeeForm() {}
	
	public EmployeeForm(Employee employee) {
		this.name = employee.getName();
		this.code = employee.getCode();
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
	
	public Employee getEmployee() {
		return new Employee(getName(), getCode());
	}
}
