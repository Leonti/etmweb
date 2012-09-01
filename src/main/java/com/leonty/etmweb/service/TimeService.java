package com.leonty.etmweb.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leonty.etm.calculation.TimeEntry;
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
	public void signInEmployee(Employee employee, Job job, Date timeIn, Integer tenantId) {

		ArrayList<Time> times = new ArrayList<Time>(sessionFactory.getCurrentSession().createQuery(
		"FROM Time AS time WHERE " +
		"time.employee = ? AND time.timeOut = NULL " +
		"AND tenantId = ?" +
		"ORDER BY time.timeIn DESC")
		.setParameter(0, employee)
		.setParameter(1, tenantId)
		.setMaxResults(1)
		.list());		
		
		// Employee is currently "punched in" in some job -punch him out
		if (times.size() > 0) {
			Time timeAlreadyIn = times.get(0);
			timeAlreadyIn.setTimeOut(timeIn);
		}		
		
		Time time = new Time(employee, job, timeIn, tenantId);
		save(time);	
	}
	
	public void signOutEmployee(Employee employee, Date timeOut, Integer tenantId) {
		
		// only proceed if employee is currently working
		if (getJobAtTime(employee, timeOut, tenantId) != null) {
			
			// get current working time entry
			Time time = (Time) sessionFactory.getCurrentSession().createQuery(
				"FROM Time AS time WHERE time.employee = ? AND time.tenantId = ? ORDER BY time.timeIn DESC")
				.setParameter(0, employee)
				.setParameter(1, tenantId)
				.setMaxResults(1)
				.uniqueResult();
				
				// update it's time out to given time
				time.setTimeOut(timeOut);
				save(time);		
		}
	}

	@SuppressWarnings("unchecked")
	public List<TimeEntry> getTimeForEmployee(Employee employee, Date start, Date end, Integer tenantId) {
		
		// Select time entries which started after (or at the same time as new period)
		// or those that started before but still going on
 		return new ArrayList<TimeEntry>(sessionFactory.getCurrentSession().createQuery(
				"FROM Time AS time WHERE time.employee = ? " +
				"AND (time.timeIn >= ? " +
				"OR ((time.timeOut = NULL OR time.timeOut > ?) AND time.timeIn < ?)) " +
				"AND time.timeIn <= ? " +
				"AND time.tenantId = ?" +
				"ORDER BY time.timeIn ASC")
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
			"AND (time.timeOut = NULL OR time.timeOut < :pointInTime) AND time.timeIn >= :pointInTime " +
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
