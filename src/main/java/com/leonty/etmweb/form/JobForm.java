package com.leonty.etmweb.form;

import com.leonty.etmweb.domain.Job;

public class JobForm {

	private String title;
	private String wage;
	
	public JobForm() {}
	
	public JobForm(Job job) {
		this.title = job.getTitle();
		this.wage = job.getWage().toString();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWage() {
		return wage;
	}

	public void setWage(String wage) {
		this.wage = wage;
	}
	
	public Job getJob() {
		return new Job(title, Double.valueOf(wage));
	}	
}
