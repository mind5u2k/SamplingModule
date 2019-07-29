package com.ison.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SAMPLING_RECORD")
public class SamplingRecord implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SAMPLING_RECORD_ID")
	private int id;

	@ManyToOne
	@JoinColumn(name = "SEQ_ID")
	private UploadRequestDetails uploadRequestDetails;

	@Column(name = "MSISDN")
	private String msisdn;

	@Column(name = "AGENT_ID")
	private String agentId;

	@Column(name = "AGENT_CATEGORY")
	private String agentCategory;

	@Column(name = "CALL_TYPE")
	private String callType;

	@Column(name = "CALL_NATURE")
	private String callNature;

	@Column(name = "REQUEST_TYPE")
	private String requestType;

	@Column(name = "STATUS")
	private String status;

	public UploadRequestDetails getUploadRequestDetails() {
		return uploadRequestDetails;
	}

	public void setUploadRequestDetails(UploadRequestDetails uploadRequestDetails) {
		this.uploadRequestDetails = uploadRequestDetails;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getAgentCategory() {
		return agentCategory;
	}

	public void setAgentCategory(String agentCategory) {
		this.agentCategory = agentCategory;
	}

	public String getCallType() {
		return callType;
	}

	public void setCallType(String callType) {
		this.callType = callType;
	}

	public String getCallNature() {
		return callNature;
	}

	public void setCallNature(String callNature) {
		this.callNature = callNature;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
