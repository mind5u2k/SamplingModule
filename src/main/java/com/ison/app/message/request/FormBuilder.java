package com.ison.app.message.request;


public class FormBuilder {

	private String templateKey;

	private String title;

	private String templateValue;

	private String status;
	
	private String autogenFormId;
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getAutogenFormId() {
		return autogenFormId;
	}

	public void setAutogenFormId(String autogenFormId) {
		this.autogenFormId = autogenFormId;
	}

}
