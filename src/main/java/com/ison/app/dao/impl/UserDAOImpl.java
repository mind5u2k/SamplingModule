package com.ison.app.dao.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ison.app.dao.UserDAO;
import com.ison.app.model.User;

@Repository
public class UserDAOImpl implements UserDAO {

	private Logger logger = Logger.getLogger(DemoDAOImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public Optional<User> findByUsername(String username) throws Exception {
		StringBuilder sqlQry = null;
		Optional<User> userOptional = null;

		List<Object[]> result = null;
		try {
		} catch (Exception e) {
			logger.info("Exception :: UserDAOImpl :: findByUsername() : " + e);
		} finally {
		}
		return userOptional;
	}

	@Override
	public Boolean existsByUsername(String username) throws Exception {
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
	public Boolean existsByEmail(String email) throws Exception {
		StringBuilder sqlQry = null;
		boolean user = false;
		List<Object[]> result = null;
		try {
		} catch (Exception e) {
			logger.info("Exception :: UserDAOImpl :: existsByEmail() : " + e);
		} finally {
		}
		return user;
	}

	@Override
	@Transactional(value = "firstTransactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public User save(User user) throws Exception {
		try {
		} catch (Exception e) {
			logger.info("Exception :: UserDAOImpl :: save() : " + e);
		} finally {
		}

		return user;
	}

}
