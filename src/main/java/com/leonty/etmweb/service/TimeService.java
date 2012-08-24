package com.leonty.etmweb.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leonty.etmweb.domain.Employee;
import com.leonty.etmweb.domain.Job;
import com.leonty.etmweb.domain.Time;

@Service("timeEntryService")
public class TimeService {

    @Autowired
    private SessionFactory sessionFactory;
	
    @Transactional
	public void save(Time timeEntry) {
		sessionFactory.getCurrentSession().saveOrUpdate(timeEntry);
	}
    
	@SuppressWarnings("unchecked")	
	public void signInEmployee(Employee employee, Job job, Date in, Integer tenantId) {

		ArrayList<Time> times = new ArrayList<Time>(sessionFactory.getCurrentSession().createQuery(
		"FROM Time AS time WHERE " +
		"time.employee = ? AND time.out = NULL " +
		"AND tenantId = ?" +
		"ORDER BY time.in DESC")
		.setParameter(0, employee)
		.setParameter(1, tenantId)
		.setMaxResults(1)
		.list());		
		
		// Employee is currently "punched in" in some job -punch him out
		if (times.size() > 0) {
			Time timeAlreadyIn = times.get(0);
			timeAlreadyIn.setOut(in);
		}		
		
		Time time = new Time(employee, job, in, tenantId);
		save(time);	
	}
	
	public void signOutEmployee(Employee employee, Date out, Integer tenantId) {
		
		// only proceed if employee is currently working
		if (getJobAtTime(employee, out, tenantId) != null) {
			
			// get current working time entry
			Time time = (Time) sessionFactory.getCurrentSession().createQuery(
				"FROM Time AS time WHERE time.employee = ? AND time.tenantId = ? ORDER BY time.in DESC")
				.setParameter(0, employee)
				.setParameter(1, tenantId)
				.setMaxResults(1)
				.uniqueResult();
				
				// update it's time out to given time
				time.setOut(out);
				save(time);		
		}
	}

	@SuppressWarnings("unchecked")
	public List<Time> getTimeForEmployee(Employee employee, Date start, Date end, Integer tenantId) {
		
		// Select time entries which started after (or at the same time as new period)
		// or those that started before but still going on
 		return new ArrayList<Time>(sessionFactory.getCurrentSession().createQuery(
				"FROM Time AS time WHERE time.employee = ? " +
				"AND (time.in >= ? " +
				"OR ((time.out = NULL OR time.out > ?) AND time.in < ?)) " +
				"AND time.in <= ? " +
				"AND time.tenantId = ?" +
				"ORDER BY time.in ASC")
				.setParameter(0, employee)
				.setParameter(1, start)
				.setParameter(2, start)
				.setParameter(3, start)
				.setParameter(4, end)
				.setParameter(5, tenantId)
				.list());	
	}	
	
	public Job getJobAtTime(Employee employee, Date pointInTime, Integer tenantId) {

 		Time time = (Time) sessionFactory.getCurrentSession().createQuery(
			"FROM Time AS time WHERE time.employee = ? " +
			"AND ((time.out = NULL OR time.out > ?) AND time.in <= ?)) " +
			"AND time.tenantId = ?")
			.setParameter(0, employee)
			.setParameter(1, pointInTime)
			.setParameter(2, pointInTime)
			.setParameter(3, tenantId)
			.setMaxResults(1)
			.uniqueResult();
 		
 		if (time == null)
 			return null;
 		
 		return time.getJob();		
	}	
	
}
