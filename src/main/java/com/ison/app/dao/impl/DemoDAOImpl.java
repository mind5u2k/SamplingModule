package com.ison.app.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ison.app.dao.DemoDAO;
import com.ison.app.model.SamplingTemplate;

@Repository
public class DemoDAOImpl implements DemoDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private Logger logger = Logger.getLogger(DemoDAOImpl.class);

	@Override
	public List<SamplingTemplate> getTemplate() throws Exception {
		String selectQuery = "FROM SamplingTemplate";
		return sessionFactory.getCurrentSession().createQuery(selectQuery, SamplingTemplate.class).getResultList();
	}

	@Override
	public boolean addNewTemplate(SamplingTemplate samplingTemplate) {
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(samplingTemplate);
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (Exception ex) {
			logger.error(ex);
			ex.printStackTrace();
			return false;
		}
	}

}
