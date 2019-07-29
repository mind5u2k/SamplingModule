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
@Table(name = "UPLOAD_REQUEST_DETAILS")
public class UploadRequestDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sequence_upload_request")
	private int id;

	@Column(name = "file_name")
	private String file_name;

	@ManyToOne
	@JoinColumn(name = "REGIONS")
	private InventoryMaster Region;

	@ManyToOne
	@JoinColumn(name = "CENTERS")
	private InventoryMaster center;

	@ManyToOne
	@JoinColumn(name = "CLIENTS")
	private InventoryMaster client;

	@ManyToOne
	@JoinColumn(name = "PROCESSS")
	private InventoryMaster process;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public InventoryMaster getRegion() {
		return Region;
	}

	public void setRegion(InventoryMaster region) {
		Region = region;
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

}
