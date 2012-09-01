package com.leonty.etmweb.test;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.leonty.etm.calculation.TimeEntry;
import com.leonty.etmweb.domain.Employee;
import com.leonty.etmweb.domain.Job;
import com.leonty.etmweb.domain.Time;
import com.leonty.etmweb.service.EmployeeService;
import com.leonty.etmweb.service.JobService;
import com.leonty.etmweb.service.TimeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/testContext.xml")
@TransactionConfiguration(transactionManager="transactionManager")
public class TimeServiceTest {	
	
	@Resource(name="employeeService")
	EmployeeService employeeService;	

	@Resource(name="jobService")
	JobService jobService;
	
	@Resource(name="timeService")
	TimeService timeService;	
	
	@Qualifier("sessionFactory")
    @Autowired
    private SessionFactory sessionFactory;
	
	@Before
	@Transactional
	public void setUp() {
		Session session = SessionFactoryUtils.openSession(sessionFactory);
		session.createQuery("DELETE FROM Time").executeUpdate();
		session.createQuery("DELETE FROM Employee").executeUpdate();
		session.createQuery("DELETE FROM Job").executeUpdate();		
	}
	
    @Test
    public void testCurrentEntry() {
    	
    	Employee employee = new Employee("Leonty", "1234");
    	employee.setTenantId(1); 	
    	employeeService.save(employee);
    	
    	Job job = new Job("First job", 8d);
    	job.setTenantId(1);
    	jobService.save(job);
    	
    	Time time = new Time(employee, job, new DateTime(), 1);   	
    	timeService.save(time);
    	
    	Job currentJob = timeService.getJobAtTime(employee, new DateTime(), 1);
    	
    	Assert.assertNotNull("Job was not found", currentJob);
    	Assert.assertEquals("Job user logged in with is not the same as current", job, currentJob);
    }
	
    @Test
    public void testSignOut() {
    	
    	DateTime timeIn = new DateTime(2012, 8, 20, 8, 0);
    	DateTime timeOut = new DateTime(2012, 8, 20, 17, 0);
  
    	Employee employee = new Employee("Leonty", "1234");
    	employee.setTenantId(1); 	
    	employeeService.save(employee);
    	
    	Job job = new Job("First job", 8d);
    	job.setTenantId(1);
    	jobService.save(job);
    	
    	timeService.signInEmployee(employee, job, timeIn, 1);
    	
    	List<TimeEntry> times = timeService.getTimeForEmployee(employee, timeIn, timeOut, 1);   	
    	Assert.assertTrue("After signing in number of entries for employee is not 1", times.size() == 1);
  	
    	timeService.signOutEmployee(employee, timeOut, 1);
    	
    	times = timeService.getTimeForEmployee(employee, timeIn, timeOut, 1);    	
    	Assert.assertTrue("After signing out number of entries for employee is not 1", times.size() == 1);    	   
    	Assert.assertEquals("Date out is not the same as date used for signing out", timeOut, times.get(0).getTimeOut());

    	DateTime timeAfterSignOut = new DateTime(2012, 8, 20, 17, 10);
    	
    	Job nullJob = timeService.getJobAtTime(employee, timeAfterSignOut, 1);
    	
    	Assert.assertNull("Job after sign out should be null", nullJob);    	
    	
    }
	
}
