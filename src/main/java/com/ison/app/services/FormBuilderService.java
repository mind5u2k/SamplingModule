package com.ison.app.services;

import com.ison.app.shared.dto.FormBuilderDto;

public interface FormBuilderService {

	public FormBuilderDto insertFormDetails(FormBuilderDto formBuilderDto) throws Exception;
	Boolean templateNameExists(String templateName) throws Exception;
	public boolean updateFormTemplate(FormBuilderDto formBuilderDto) throws Exception;
}
