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

@Transactional
@Service("timeService")
public class TimeService {

    @Autowired
    private SessionFactory sessionFactory;
	
	public void save(Time time) {
		sessionFactory.getCurrentSession().saveOrUpdate(time);
	}
    
	@SuppressWarnings("unchecked")	
	public void signInEmployee(Employee employee, Job job, Date inTime, Integer tenantId) {

		ArrayList<Time> times = new ArrayList<Time>(sessionFactory.getCurrentSession().createQuery(
		"FROM Time AS time WHERE " +
		"time.employee = ? AND time.outTime = NULL " +
		"AND tenantId = ?" +
		"ORDER BY time.inTime DESC")
		.setParameter(0, employee)
		.setParameter(1, tenantId)
		.setMaxResults(1)
		.list());		
		
		// Employee is currently "punched in" in some job -punch him out
		if (times.size() > 0) {
			Time timeAlreadyIn = times.get(0);
			timeAlreadyIn.setOutTime(inTime);
		}		
		
		Time time = new Time(employee, job, inTime, tenantId);
		save(time);	
	}
	
	public void signOutEmployee(Employee employee, Date outTime, Integer tenantId) {
		
		// only proceed if employee is currently working
		if (getJobAtTime(employee, outTime, tenantId) != null) {
			
			// get current working time entry
			Time time = (Time) sessionFactory.getCurrentSession().createQuery(
				"FROM Time AS time WHERE time.employee = ? AND time.tenantId = ? ORDER BY time.inTime DESC")
				.setParameter(0, employee)
				.setParameter(1, tenantId)
				.setMaxResults(1)
				.uniqueResult();
				
				// update it's time out to given time
				time.setOutTime(outTime);
				save(time);		
		}
	}

	@SuppressWarnings("unchecked")
	public List<Time> getTimeForEmployee(Employee employee, Date start, Date end, Integer tenantId) {
		
		// Select time entries which started after (or at the same time as new period)
		// or those that started before but still going on
 		return new ArrayList<Time>(sessionFactory.getCurrentSession().createQuery(
				"FROM Time AS time WHERE time.employee = ? " +
				"AND (time.inTime >= ? " +
				"OR ((time.outTime = NULL OR time.outTime > ?) AND time.inTime < ?)) " +
				"AND time.inTime <= ? " +
				"AND time.tenantId = ?" +
				"ORDER BY time.inTime ASC")
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
			"FROM Time AS time WHERE time.employee = :employee " +
			"AND (time.outTime = NULL OR time.outTime < :pointInTime) AND time.inTime >= :pointInTime " +
			"AND time.tenantId = :tenantId")
			.setParameter("employee", employee)
			.setDate("pointInTime", pointInTime)
			.setInteger("tenantId", tenantId)
			.setMaxResults(1)
			.uniqueResult();
 		
 		if (time == null)
 			return null;
 		
 		return time.getJob();		
	}	
	
}
