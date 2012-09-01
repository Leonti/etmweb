package com.leonty.etmweb.service;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leonty.etmweb.domain.Settings;

@Service("settingsService")
@Transactional
public class SettingsService {

    @Autowired
    private SessionFactory sessionFactory;
    
	public void save(Settings settings) {
		sessionFactory.getCurrentSession().saveOrUpdate(settings);
	}
	
	public Settings getSettingsById(Integer id, Integer tenantId) {

    	return (Settings) sessionFactory.getCurrentSession()
    			.createQuery("FROM Settings os WHERE os.id = ? AND os.tenantId = ?")
    			.setLong(0, id)
    			.setLong(1, tenantId)
    			.setMaxResults(1)
    			.uniqueResult();
	} 	
}
