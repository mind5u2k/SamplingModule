package com.ison.app.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ison.app.dao.FileUploadDAO;

@Repository
public class FileUploadDAOImpl implements FileUploadDAO {

	/*
	 * @Override public void getUploadParameters(String moduleName,String
	 * subModuleName) throws Exception{
	 * 
	 * }
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional("indTransactionManager")
	public Map<String, List<List<String>>> insertFileData(List<String> insertQueryValuesList, StringBuilder insertQuery,
			List<List<String>> successRecordsList) throws Exception {
		HashMap resultMap = null;
		List<List<String>> failureRecordsList = new ArrayList<>();
		List<List<String>> tempSuccessList = new ArrayList<>();
		List<List<String>> mastTransIdList = new ArrayList<>();
		try {
			for (int i = 0; i < insertQueryValuesList.size(); i++) {
				Object[] insertStatus = fileDataExecuteQuery(insertQuery.toString() + insertQueryValuesList.get(i));
				int status = (int) insertStatus[0];
				if (status <= 0) {
					int k = (successRecordsList.get(i)).size();
					String errReason = String.valueOf(insertStatus[1]);
					(successRecordsList.get(i)).set(k - 1, errReason);
					failureRecordsList.add((ArrayList) successRecordsList.get(i));
				} else {
					tempSuccessList.add((ArrayList) successRecordsList.get(i));
				}
			}

		} catch (Exception e) {

		}
		resultMap = new HashMap();
		resultMap.put("successRecordsList", tempSuccessList);
		resultMap.put("failureRecordsList", failureRecordsList);
		resultMap.put("mastTransId", mastTransIdList);
		return resultMap;

	}

	public Object[] fileDataExecuteQuery(String insertQuery) throws Exception {
		Object[] resultObj = new Object[2];
		int insertStatus = 0;
		try {
		} catch (Exception e) {
			if (e.getCause() instanceof java.sql.SQLIntegrityConstraintViolationException) {
				resultObj[1] = "Unique Constraint Violation Exception";
			} else {
				resultObj[1] = e.getMessage();
			}
		}

		resultObj[0] = insertStatus;
		return resultObj;
	}

}
