package com.ison.app.shared.dto;


public class FormBuilderDto {

	private String autogenFormId;

	private String templateKey;

	private String status;

	private String title;

	private String templateValue;
	
	public String getAutogenFormId() {
		return autogenFormId;
	}

	public void setAutogenFormId(String autogenFormId) {
		this.autogenFormId = autogenFormId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTemplateKey() {
		return templateKey;
	}

	public void setTemplateKey(String templateKey) {
		this.templateKey = templateKey;
	}

	public String getTemplateValue() {
		return templateValue;
	}

	public void setTemplateValue(String templateValue) {
		this.templateValue = templateValue;
	}

}
