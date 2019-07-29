package com.ison.app.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the form_template database table.
 * 
 */
//@Entity
@Table(name="form_template")
@NamedQuery(name="FormTemplate.findAll", query="SELECT f FROM FormTemplate f")
public class FormTemplate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="autogen_form_id")
	private String autogenFormId;

	private String status;

	@Column(name="template_key")
	private String templateKey;

	@Lob
	@Column(name="template_value")
	private String templateValue;

	private String title;

	public FormTemplate() {
	}

	public String getAutogenFormId() {
		return this.autogenFormId;
	}

	public void setAutogenFormId(String autogenFormId) {
		this.autogenFormId = autogenFormId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTemplateKey() {
		return this.templateKey;
	}

	public void setTemplateKey(String templateKey) {
		this.templateKey = templateKey;
	}

	public String getTemplateValue() {
		return this.templateValue;
	}

	public void setTemplateValue(String templateValue) {
		this.templateValue = templateValue;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}