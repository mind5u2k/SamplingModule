package com.ison.app.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ison.app.dao.FormBuilderDAO;
import com.ison.app.services.FormBuilderService;
import com.ison.app.shared.dto.FormBuilderDto;

@Service
public class FormBuilderServiceImpl implements FormBuilderService {

	@Autowired
	FormBuilderDAO formBuilderDAO;
	
	@Override
	public FormBuilderDto insertFormDetails(FormBuilderDto formBuilderDto) throws Exception{
		return formBuilderDAO.insertFormDetails(formBuilderDto);
	}

	@Override
	public Boolean templateNameExists(String templateName) throws Exception {
		return formBuilderDAO.templateNameExists(templateName);
	}

	@Override
	public boolean updateFormTemplate(FormBuilderDto formBuilderDto) throws Exception {
		return formBuilderDAO.updateFormTemplate(formBuilderDto);
	}

}
