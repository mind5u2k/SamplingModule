package com.ison.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SAMPLING_CATEGORY")
public class AgentCategory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CATEGORY_ID")
	private int id;

	@Column(name = "CATEGORY_NAME")
	private String category;

	@Column(name = "NO_OF_CALLS")
	private int noOfCalls;

	@Column(name = "MIN_SCORE")
	private int minScore;

	@Column(name = "MAX_SCORE")
	private int maxScore;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getNoOfCalls() {
		return noOfCalls;
	}

	public void setNoOfCalls(int noOfCalls) {
		this.noOfCalls = noOfCalls;
	}

	public int getMinScore() {
		return minScore;
	}

	public void setMinScore(int minScore) {
		this.minScore = minScore;
	}

	public int getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(int maxScore) {
		this.maxScore = maxScore;
	}

}
