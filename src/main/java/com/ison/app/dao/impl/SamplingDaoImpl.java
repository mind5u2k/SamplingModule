package com.ison.app.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ison.app.dao.SamplingDao;
import com.ison.app.model.CLIDData;
import com.ison.app.model.CRMData;
import com.ison.app.model.CliCrmMapping;
import com.ison.app.model.SamplingMapping;
import com.ison.app.model.SamplingRecord;
import com.ison.app.model.SamplingTemplate;
import com.ison.app.model.UploadRequestDetails;

@Repository
public class SamplingDaoImpl implements SamplingDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Logger logger = Logger.getLogger(DemoDAOImpl.class);

	@Override
	public List<SamplingTemplate> getTemplate() throws Exception {
		String selectQuery = "FROM SamplingTemplate";
		return sessionFactory.getCurrentSession().createQuery(selectQuery, SamplingTemplate.class).getResultList();
	}

	@Override
	public SamplingTemplate addNewTemplate(SamplingTemplate samplingTemplate) {
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(samplingTemplate);
			session.getTransaction().commit();
			session.close();
			System.out.println("sampling template id is [" + samplingTemplate.getId() + "]");
			return samplingTemplate;
		} catch (Exception ex) {
			logger.error(ex);
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean addNewMapping(SamplingMapping samplingMapping) {
		try {
			sessionFactory.getCurrentSession().persist(samplingMapping);
			return true;
		} catch (Exception ex) {
			logger.error(ex);
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public List<SamplingMapping> getSamplingMappings() {
		String selectQuery = "FROM SamplingMapping";
		return sessionFactory.getCurrentSession().createQuery(selectQuery, SamplingMapping.class).getResultList();
	}

	@Override
	public SamplingMapping getSamplingMappingByInventory(int region, int center, int client, int process) {
		String selectQuery = "FROM SamplingMapping WHERE uploadRequestDetails.id = :seqId ORDER BY id DESC";
		return sessionFactory.getCurrentSession().createQuery(selectQuery, SamplingMapping.class)
				.setParameter("region", region).setParameter("center", center).setParameter("client", client)
				.setParameter("process", process).getSingleResult();
	}

	@Override
	public boolean saveSamplingMapping(SamplingMapping samplingMapping) {
		try {
			sessionFactory.getCurrentSession().save(samplingMapping);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<CRMData> getAllCRMData() {
		String selectQuery = "FROM CRMData";
		return sessionFactory.getCurrentSession().createQuery(selectQuery, CRMData.class).getResultList();
	}

	@Override
	public List<CRMData> getCRMsbyUploadSeqId(int seqId) {
		String selectQuery = "FROM CRMData WHERE uploadRequestDetails.id = :seqId ORDER BY id DESC";
		return sessionFactory.getCurrentSession().createQuery(selectQuery, CRMData.class).setParameter("seqId", seqId)
				.getResultList();
	}

	@Override
	public List<CLIDData> getAllCLIData() {
		String selectQuery = "FROM CLIDData";
		return sessionFactory.getCurrentSession().createQuery(selectQuery, CLIDData.class).getResultList();
	}

	@Override
	public List<CLIDData> getCLIsbyUploadSeqId(int seqId) {
		String selectQuery = "FROM CLIDData WHERE uploadRequestDetails.id = :seqId ORDER BY id DESC";
		return sessionFactory.getCurrentSession().createQuery(selectQuery, CLIDData.class).setParameter("seqId", seqId)
				.getResultList();
	}

	@Override
	public List<CliCrmMapping> getCliCrmMappings() {
		String selectQuery = "FROM CliCrmMapping";
		return sessionFactory.getCurrentSession().createQuery(selectQuery, CliCrmMapping.class).getResultList();
	}

	@Override
	public List<CliCrmMapping> getCliCrmMappingsBySeqId(int seqId) {
		String selectQuery = "FROM CliCrmMapping WHERE uploadRequestDetails.id = :seqId ORDER BY id DESC";
		return sessionFactory.getCurrentSession().createQuery(selectQuery, CliCrmMapping.class)
				.setParameter("seqId", seqId).getResultList();
	}

	@Override
	public List<UploadRequestDetails> getAllUploadRequest() {
		String selectQuery = "FROM UploadRequestDetails";
		return sessionFactory.getCurrentSession().createQuery(selectQuery, UploadRequestDetails.class).getResultList();
	}

	@Override
	public UploadRequestDetails getUploadRequestById(int id) {
		try {
			return sessionFactory.getCurrentSession().get(UploadRequestDetails.class, id);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}

	@Override
	public SamplingRecord addNewSamplingRecord(SamplingRecord record) {

		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(record);
			session.getTransaction().commit();
			session.close();
			return record;
		} catch (Exception ex) {
			logger.error(ex);
			ex.printStackTrace();
			return null;
		}

	}

}
