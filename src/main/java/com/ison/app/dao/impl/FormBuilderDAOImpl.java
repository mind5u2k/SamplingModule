package com.ison.app.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ison.app.constants.AppicationConstants;
import com.ison.app.dao.FormBuilderDAO;
import com.ison.app.model.FormTemplate;
import com.ison.app.shared.dto.FormBuilderDto;
import com.ison.app.util.CommonUtil;

@Repository
public class FormBuilderDAOImpl implements FormBuilderDAO {

	private Logger logger = Logger.getLogger(FormBuilderDAOImpl.class);

	@Override
	@Transactional(value = "firstTransactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public FormBuilderDto insertFormDetails(FormBuilderDto formBuilderDto) throws Exception {
		try {
		} catch (Exception e) {
			logger.info("Exception :: UserDAOImpl :: save() : " + e);
		} finally {
		}

		return formBuilderDto;
	}

	@Override
	public Boolean templateNameExists(String templateName) throws Exception {
		StringBuilder sqlQry = null;
		boolean user = false;
		List<Object[]> result = null;
		try {
		} catch (Exception e) {
			logger.info("Exception :: UserDAOImpl :: existsByUsername() : " + e);
		} finally {
		}
		return user;
	}

	@Override
	@Transactional(value = "firstTransactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean updateFormTemplate(FormBuilderDto formBuilderDto) throws Exception {
		boolean result = false;
		try {
		} catch (Exception e) {
			logger.info("Exception :: UserDAOImpl :: save() : " + e);
		} finally {
		}
		return result;
	}

}
