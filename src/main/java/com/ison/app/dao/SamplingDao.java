package com.ison.app.dao;

import java.util.List;

import com.ison.app.model.CLIDData;
import com.ison.app.model.CRMData;
import com.ison.app.model.CliCrmMapping;
import com.ison.app.model.SamplingMapping;
import com.ison.app.model.SamplingRecord;
import com.ison.app.model.SamplingTemplate;
import com.ison.app.model.UploadRequestDetails;

public interface SamplingDao {

	public List<SamplingTemplate> getTemplate() throws Exception;

	SamplingTemplate addNewTemplate(SamplingTemplate samplingTemplate);

	boolean addNewMapping(SamplingMapping samplingMapping);

	public List<SamplingMapping> getSamplingMappings();

	public SamplingMapping getSamplingMappingByInventory(int region, int center, int client, int process);

	boolean saveSamplingMapping(SamplingMapping samplingMapping);

	public List<CRMData> getAllCRMData();

	public List<CRMData> getCRMsbyUploadSeqId(int seqId);

	public List<CLIDData> getAllCLIData();

	public List<CLIDData> getCLIsbyUploadSeqId(int seqId);

	public List<CliCrmMapping> getCliCrmMappings();

	public List<CliCrmMapping> getCliCrmMappingsBySeqId(int seqId);

	public List<UploadRequestDetails> getAllUploadRequest();

	public UploadRequestDetails getUploadRequestById(int id);

	public SamplingRecord addNewSamplingRecord(SamplingRecord record);

}
