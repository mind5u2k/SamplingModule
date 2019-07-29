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
@Table(name = "SAMPLING_MAPPING")
public class SamplingMapping implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SAMPLING_MAPPING_ID ")
	private int id;

	@ManyToOne
	@JoinColumn(name = "REGION")
	private InventoryMaster region;

	@ManyToOne
	@JoinColumn(name = "CENTER")
	private InventoryMaster center;

	@ManyToOne
	@JoinColumn(name = "CLIENT")
	private InventoryMaster client;

	@ManyToOne
	@JoinColumn(name = "PROCESS")
	private InventoryMaster process;

	@ManyToOne
	private SamplingTemplate samplingTemplate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public InventoryMaster getRegion() {
		return region;
	}

	public void setRegion(InventoryMaster region) {
		this.region = region;
	}

	public InventoryMaster getCenter() {
		return center;
	}

	public void setCenter(InventoryMaster center) {
		this.center = center;
	}

	public InventoryMaster getClient() {
		return client;
	}

	public void setClient(InventoryMaster client) {
		this.client = client;
	}

	public InventoryMaster getProcess() {
		return process;
	}

	public void setProcess(InventoryMaster process) {
		this.process = process;
	}

	public SamplingTemplate getSamplingTemplate() {
		return samplingTemplate;
	}

	public void setSamplingTemplate(SamplingTemplate samplingTemplate) {
		this.samplingTemplate = samplingTemplate;
	}

}
