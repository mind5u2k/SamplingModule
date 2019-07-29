package com.ison.app.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "SAMPLING_TEMPLATE")
public class SamplingTemplate implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TEMPLATE_ID")
	private int id;

	@Column(name = "TEMPLATE_NAME")
	private String templateTitle;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "TEMPLATE_CATEGORY_MAPPING", joinColumns = @JoinColumn(name = "TEMPLATE_ID"), inverseJoinColumns = @JoinColumn(name = "CATEGORY_ID"))
	@Fetch(value = FetchMode.SUBSELECT)
	private Collection<AgentCategory> agentCategories;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "TEMPLATE_DURATION_MAPPING", joinColumns = @JoinColumn(name = "TEMPLATE_ID"), inverseJoinColumns = @JoinColumn(name = "DURATION_ID"))
	@Fetch(value = FetchMode.SUBSELECT)
	private List<CallDuration> callDurations;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "TEMPLATE_NATURE_MAPPING", joinColumns = @JoinColumn(name = "TEMPLATE_ID"), inverseJoinColumns = @JoinColumn(name = "CALL_NATURE_ID"))
	@Fetch(value = FetchMode.SUBSELECT)
	private List<CallNature> callNatures;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "TEMPLATE_CALL_MIX_MAPPING", joinColumns = @JoinColumn(name = "TEMPLATE_ID"), inverseJoinColumns = @JoinColumn(name = "CALL_MIX_ID"))
	@Fetch(value = FetchMode.SUBSELECT)
	private List<CallMixCategory> callMixCategories;

	public List<CallMixCategory> getCallMixCategories() {
		return callMixCategories;
	}

	public void setCallMixCategories(List<CallMixCategory> callMixCategories) {
		this.callMixCategories = callMixCategories;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Collection<AgentCategory> getAgentCategories() {
		return agentCategories;
	}

	public void setAgentCategories(Collection<AgentCategory> agentCategories) {
		this.agentCategories = agentCategories;
	}

	public List<CallDuration> getCallDurations() {
		return callDurations;
	}

	public void setCallDurations(List<CallDuration> callDurations) {
		this.callDurations = callDurations;
	}

	public List<CallNature> getCallNatures() {
		return callNatures;
	}

	public void setCallNatures(List<CallNature> callNatures) {
		this.callNatures = callNatures;
	}

	public String getTemplateTitle() {
		return templateTitle;
	}

	public void setTemplateTitle(String templateTitle) {
		this.templateTitle = templateTitle;
	}

}
