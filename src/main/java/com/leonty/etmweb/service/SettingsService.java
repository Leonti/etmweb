package com.leonty.etmweb.service;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leonty.etmweb.domain.OvertimeSettings;

@Service("settingsService")
@Transactional
public class SettingsService {

    @Autowired
    private SessionFactory sessionFactory;
    
	public void save(OvertimeSettings overtimeSettings) {
		sessionFactory.getCurrentSession().saveOrUpdate(overtimeSettings);
	}
	
	public OvertimeSettings getOvertimeSettingsById(Integer id, Integer tenantId) {

    	return (OvertimeSettings) sessionFactory.getCurrentSession()
    			.createQuery("FROM OvertimeSettings os WHERE os.id = ? AND os.tenantId = ?")
    			.setLong(0, id)
    			.setLong(1, tenantId)
    			.setMaxResults(1)
    			.uniqueResult();
	} 	
}
