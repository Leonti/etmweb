package com.leonty.etmweb.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leonty.etmweb.domain.Employee;

@Service("employeeService")
public class EmployeeService {

    @Autowired
    private SessionFactory sessionFactory;
	
    @Transactional
	public void save(Employee employee) {
		sessionFactory.getCurrentSession().saveOrUpdate(employee);
	}	
    
    @SuppressWarnings("unchecked")
	@Transactional
	public List<Employee> getList(Integer tenantId) {
    	
    	return (ArrayList<Employee>) sessionFactory.getCurrentSession()
    			.createQuery("FROM Employee e WHERE e.tenantId = ? AND e.deleted != 1")
    			.setLong(0, tenantId).list();
	}
    
    @Transactional
	public Employee getByCode(String code, Integer tenantId) {

    	return (Employee) sessionFactory.getCurrentSession()
    			.createQuery("FROM Employee e WHERE e.code = ? AND e.tenantId = ? AND e.deleted != 1")
    			.setString(0, code)
    			.setLong(1, tenantId)
    			.setMaxResults(1)
    			.uniqueResult();
	}
    
    @Transactional
	public Employee getById(Integer id, Integer tenantId) {

    	return (Employee) sessionFactory.getCurrentSession()
    			.createQuery("FROM Employee e WHERE e.id = ? AND e.tenantId = ? AND e.deleted != 1")
    			.setLong(0, id)
    			.setLong(1, tenantId)
    			.setMaxResults(1)
    			.uniqueResult();
	}    
}
