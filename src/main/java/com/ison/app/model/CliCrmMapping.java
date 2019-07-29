package com.ison.app.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CLI_CRM_MAPPING_DATA")
public class CliCrmMapping implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SEQUENCE_CLI_CRM")
	private int id;

	@ManyToOne
	@JoinColumn(name = "SEQ_ID")
	private UploadRequestDetails uploadRequestDetails;

	@Column(name = "EMP_ID")
	private String empId;

	@Column(name = "AAVAYA_ID")
	private String aavayaId;

	@Column(name = "CRM_ID")
	private String crmId;

	@Column(name = "EMAIL_ID")
	private String emailId;

	@Column(name = "RECORDER_ID")
	private String recorderId;

	@Column(name = "AGENT_NAME")
	private String agentName;

	@Column(name = "TL_NAME")
	private String tlName;

	@Column(name = "AM_NAME")
	private String amName;

	@Column(name = "DOJ")
	private String doj;

	@Column(name = "SKILL_SET_NAME")
	private String skillSetName;

	@Column(name = "TENURE")
	private String tenure;

	@Column(name = "GEOGRAPHY")
	private String geography;

	@Column(name = "LOCATION")
	private String location;

	@Column(name = "CLIENT")
	private String client;

	@Column(name = "PROCESS")
	private String process;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "CREATED_AT")
	private Timestamp createdAt;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getAavayaId() {
		return aavayaId;
	}

	public void setAavayaId(String aavayaId) {
		this.aavayaId = aavayaId;
	}

	public String getCrmId() {
		return crmId;
	}

	public void setCrmId(String crmId) {
		this.crmId = crmId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getRecorderId() {
		return recorderId;
	}

	public void setRecorderId(String recorderId) {
		this.recorderId = recorderId;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getTlName() {
		return tlName;
	}

	public void setTlName(String tlName) {
		this.tlName = tlName;
	}

	public String getAmName() {
		return amName;
	}

	public void setAmName(String amName) {
		this.amName = amName;
	}

	public String getDoj() {
		return doj;
	}

	public void setDoj(String doj) {
		this.doj = doj;
	}

	public String getSkillSetName() {
		return skillSetName;
	}

	public void setSkillSetName(String skillSetName) {
		this.skillSetName = skillSetName;
	}

	public String getTenure() {
		return tenure;
	}

	public void setTenure(String tenure) {
		this.tenure = tenure;
	}

	public String getGeography() {
		return geography;
	}

	public void setGeography(String geography) {
		this.geography = geography;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

}
