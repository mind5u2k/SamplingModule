package com.ison.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SAMPLING_CALL_MIX")
public class CallMixCategory implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CALL_MIX_ID")
	private int id;

	@Column(name = "AUDIT_CATEGORY")
	private String auditCategory;

	@Column(name = "AUDIT_TYPE")
	private String audit_type;

	@Column(name = "PERCENTAGE")
	private int percentage;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuditCategory() {
		return auditCategory;
	}

	public void setAuditCategory(String auditCategory) {
		this.auditCategory = auditCategory;
	}

	public String getAudit_type() {
		return audit_type;
	}

	public void setAudit_type(String audit_type) {
		this.audit_type = audit_type;
	}

	public int getPercentage() {
		return percentage;
	}

	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}

}
