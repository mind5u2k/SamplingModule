package com.ison.app.dao.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ison.app.constants.AppicationConstants;
import com.ison.app.dao.RolesDAO;
import com.ison.app.model.RoleName;
import com.ison.app.model.Roles;

@Repository
public class RolesDAOImpl implements RolesDAO {

	private Logger logger = Logger.getLogger(DemoDAOImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public Optional<Roles> findByName(RoleName roleName) {
		StringBuilder sqlQry = null;
		Optional<Roles> rolesOptional = null;
		List<Object[]> rolesList = null;
		String rolename = "";
		try {
		} catch (Exception e) {
			logger.info("Error :: DemoDAOImpl :: findByName() : " + e);
		} finally {
		}
		return rolesOptional;
	}

}
