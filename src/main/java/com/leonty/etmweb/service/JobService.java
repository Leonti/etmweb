package com.leonty.etmweb.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leonty.etmweb.domain.Job;

@Service("jobService")
@Transactional
public class JobService {

    @Autowired
    private SessionFactory sessionFactory;
    
	public void save(Job job) {
		sessionFactory.getCurrentSession().saveOrUpdate(job);
	}
	
	public void delete(Job job) {
		sessionFactory.getCurrentSession().delete(job);
	}
    
    @SuppressWarnings("unchecked")
	public List<Job> getList(Integer tenantId) {
    	
    	return (ArrayList<Job>) sessionFactory.getCurrentSession()
    			.createQuery("FROM Job j WHERE j.tenantId = ?")
    			.setLong(0, tenantId).list();
	} 
    
	public Job getById(Integer id, Integer tenantId) {

    	return (Job) sessionFactory.getCurrentSession()
    			.createQuery("FROM Job j WHERE j.id = ? AND j.tenantId = ?")
    			.setLong(0, id)
    			.setLong(1, tenantId)
    			.setMaxResults(1)
    			.uniqueResult();
	}      
}
