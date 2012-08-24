package com.leonty.etmweb.test;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.leonty.etmweb.domain.Employee;
import com.leonty.etmweb.domain.Job;
import com.leonty.etmweb.domain.Time;
import com.leonty.etmweb.service.EmployeeService;
import com.leonty.etmweb.service.JobService;
import com.leonty.etmweb.service.TimeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/testContext.xml")
public class TimeServiceTest {

	@Resource(name="employeeService")
	EmployeeService employeeService;	

	@Resource(name="jobService")
	JobService jobService;
	
	@Resource(name="timeService")
	TimeService timeService;	
	
    @Test
    public void testCurrentEntry() throws Exception {
    	
    	Employee employee = new Employee("Leonty", "1234");
    	employee.setTenantId(1); 	
    	employeeService.save(employee);
    	
    	Job job = new Job("First job", 8d);
    	job.setTenantId(1);
    	jobService.save(job);
    	
    	Time time = new Time(employee, job, new Date(), 1);
    	timeService.save(time);
    	
    	Job currentJob = timeService.getJobAtTime(employee, new Date(), 1);
    	
    	System.out.println(currentJob.getTitle());
    }
	
	
}
