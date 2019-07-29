package com.ison.app.dao;

import java.util.List;
import java.util.Map;

public interface FileUploadDAO {
	/* public void getUploadParameters(String moduleName,String subModuleName) throws Exception;*/
	 public Map<String, List<List<String>>> insertFileData(List<String> insertQueryValuesList, StringBuilder insertQuery,
		      List<List<String>> successRecordsList) throws Exception;
}
