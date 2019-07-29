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
@Table(name = "CRM_DATA")
public class CRMData implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SEQUENCE_CRM")
	private int id;

	@ManyToOne
	@JoinColumn(name = "SEQ_ID")
	private UploadRequestDetails uploadRequestDetails;

	@Column(name = "MSISDN")
	private String msisdn;

	@Column(name = "AGENT_ID")
	private String agentId;

	@Column(name = "DATE")
	private String date;

	@Column(name = "START_TIME")
	private String startTime;

	@Column(name = "END_TIME")
	private String endTime;

	@Column(name = "CASE_TYPE")
	private String caseType;

	@Column(name = "CALL_TYPE")
	private String callType;

	@Column(name = "CALL_SUB_TYPE_1")
	private String callSubType1;

	@Column(name = "CALL_SUB_TYPE_2")
	private String callSubType2;

	@Column(name = "CALL_SUB_TYPE_3")
	private String callSubType3;

	@Column(name = "CALL_SUB_TYPE_4")
	private String callSubType4;

	@Column(name = "SR_NO")
	private String srNo;

	@Column(name = "CREATED_BY")
	private String createdBy;

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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getCaseType() {
		return caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	public String getCallType() {
		return callType;
	}

	public void setCallType(String callType) {
		this.callType = callType;
	}

	public String getCallSubType1() {
		return callSubType1;
	}

	public void setCallSubType1(String callSubType1) {
		this.callSubType1 = callSubType1;
	}

	public String getCallSubType2() {
		return callSubType2;
	}

	public void setCallSubType2(String callSubType2) {
		this.callSubType2 = callSubType2;
	}

	public String getCallSubType3() {
		return callSubType3;
	}

	public void setCallSubType3(String callSubType3) {
		this.callSubType3 = callSubType3;
	}

	public String getCallSubType4() {
		return callSubType4;
	}

	public void setCallSubType4(String callSubType4) {
		this.callSubType4 = callSubType4;
	}

	public String getSrNo() {
		return srNo;
	}

	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

}
