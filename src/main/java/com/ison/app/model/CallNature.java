package com.ison.app.model;

import java.io.Serializable;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SAMPLING_CALL_NATURE")
public class CallNature implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CALL_NATURE_ID")
	private int id;

	@Column(name = "CALL_NATURE_NAME")
	private String callNature;

	@Column(name = "START_TIME")
	private Time startTime;

	@Column(name = "END_TIME")
	private Time endTime;

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCallNature() {
		return callNature;
	}

	public void setCallNature(String callNature) {
		this.callNature = callNature;
	}

}
