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
@Table(name = "CLID_DATA")
public class CLIDData implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SEQUENCE_CLID")
	private int id;

	@ManyToOne
	@JoinColumn(name = "SEQ_ID")
	private UploadRequestDetails uploadRequestDetails;

	@Column(name = "MSISDN")
	private String msidsn;

	@Column(name = "AGENT_ID")
	private String agentId;

	@Column(name = "SKILL_SET_NAME")
	private String skillSetName;

	@Column(name = "END")
	private String end;

	@Column(name = "DATE")
	private String date;

	@Column(name = "START_TIME")
	private String startTime;

	@Column(name = "END_TIME")
	private String endTime;

	@Column(name = "HANDLING_TIME")
	private String handlingTime;

	@Column(name = "RELEASE_STATUS")
	private String releaseStatus;

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

	public String getMsidsn() {
		return msidsn;
	}

	public void setMsidsn(String msidsn) {
		this.msidsn = msidsn;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getSkillSetName() {
		return skillSetName;
	}

	public void setSkillSetName(String skillSetName) {
		this.skillSetName = skillSetName;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
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

	public String getHandlingTime() {
		return handlingTime;
	}

	public void setHandlingTime(String handlingTime) {
		this.handlingTime = handlingTime;
	}

	public String getReleaseStatus() {
		return releaseStatus;
	}

	public void setReleaseStatus(String releaseStatus) {
		this.releaseStatus = releaseStatus;
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
