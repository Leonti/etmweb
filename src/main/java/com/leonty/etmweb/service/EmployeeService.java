package com.leonty.etmweb.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leonty.etmweb.domain.Employee;
import com.leonty.etmweb.domain.Job;

@Service("employeeService")
@Transactional
public class EmployeeService {

    @Autowired
    private SessionFactory sessionFactory;
	

	public void save(Employee employee) {
		sessionFactory.getCurrentSession().saveOrUpdate(employee);
	}	
    
    public void delete(Employee employee) {
    	sessionFactory.getCurrentSession().delete(employee);
    }
    
    @SuppressWarnings("unchecked")
	public List<Employee> getList(Integer tenantId) {
    	
    	return (ArrayList<Employee>) sessionFactory.getCurrentSession()
    			.createQuery("FROM Employee e WHERE e.tenantId = ?")
    			.setLong(0, tenantId).list();
	}
    
	public Employee getByCode(String code, Integer tenantId) {

    	return (Employee) sessionFactory.getCurrentSession()
    			.createQuery("FROM Employee e WHERE e.code = ? AND e.tenantId = ?")
    			.setString(0, code)
    			.setLong(1, tenantId)
    			.setMaxResults(1)
    			.uniqueResult();
	}
    
	public Employee getById(Integer id, Integer tenantId) {

    	return (Employee) sessionFactory.getCurrentSession()
    			.createQuery("FROM Employee e WHERE e.id = ? AND e.tenantId = ?")
    			.setLong(0, id)
    			.setLong(1, tenantId)
    			.setMaxResults(1)
    			.uniqueResult();
	} 
    
    public void addJob(Employee employee, Job job) {
    	
    	employee.getJobs().add(job);
    	
    	save(employee);
    }
    
    public void removeJob(Employee employee, Job job) {
    	
    	employee.getJobs().remove(job);
    	
    	save(employee);
    }    
}
