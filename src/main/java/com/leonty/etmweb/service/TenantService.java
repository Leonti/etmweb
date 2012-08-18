package com.leonty.etmweb.service;

import java.util.logging.Logger;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leonty.etmweb.domain.Tenant;

@Service("tenantService")
public class TenantService {

	protected static Logger logger = Logger.getLogger("tenant service");
	
    @Autowired
    private SessionFactory sessionFactory;
	
    @Transactional
	public void save(Tenant tenant) {
		sessionFactory.getCurrentSession().saveOrUpdate(tenant);
	}

    @Transactional
	public Tenant getBySubdomain(String subdomain) {
    	
    	return (Tenant) sessionFactory.getCurrentSession()
    			.createQuery("FROM Tenant t WHERE t.subdomain = ? AND t.deleted != 1")
    			.setString(0, subdomain)
    			.setMaxResults(1)
    			.uniqueResult();
	}

    @Transactional
	public Tenant getByEmail(String email) {

    	return (Tenant) sessionFactory.getCurrentSession()
    			.createQuery("FROM Tenant t WHERE t.email = ? AND t.deleted != 1")
    			.setString(0, email)
    			.setMaxResults(1)
    			.uniqueResult();
	}

    @Transactional
	public Tenant getByForgotKey(String forgotKey) {
		
    	return (Tenant) sessionFactory.getCurrentSession()
    			.createQuery("FROM Tenant t WHERE t.forgotKey = ? AND t.deleted != 1")
    			.setString(0, forgotKey)
    			.setMaxResults(1)
    			.uniqueResult();
	}

    @Transactional
	public Tenant getByConfirmationKey(String confirmationKey) {
		
    	return (Tenant) sessionFactory.getCurrentSession()
    			.createQuery("FROM Tenant t WHERE t.confirmationKey = ? AND t.deleted != 1")
    			.setString(0, confirmationKey)
    			.setMaxResults(1)
    			.uniqueResult();
	}	
}
