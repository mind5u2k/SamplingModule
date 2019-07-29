package com.ison.app.dao;

import java.util.List;

import com.ison.app.model.SamplingTemplate;

public interface DemoDAO {

	public List<SamplingTemplate> getTemplate() throws Exception;

	boolean addNewTemplate(SamplingTemplate samplingTemplate);

}
