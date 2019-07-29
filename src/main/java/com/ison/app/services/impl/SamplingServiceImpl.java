package com.ison.app.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ison.app.dao.SamplingDao;
import com.ison.app.model.AgentCategory;
import com.ison.app.model.CLIDData;
import com.ison.app.model.CRMData;
import com.ison.app.model.CallMixSamplingPer;
import com.ison.app.model.CliCrmMapping;
import com.ison.app.model.SamplingMapping;
import com.ison.app.model.SamplingRecord;
import com.ison.app.model.SamplingTemplate;
import com.ison.app.model.UploadRequestDetails;
import com.ison.app.services.SamplingService;
import com.ison.app.util.SamplingUtil;

@Service
public class SamplingServiceImpl implements SamplingService {

	@Autowired
	SamplingDao samplingDao;

	@Override
	public List<SamplingTemplate> getTemplate() throws Exception {
		return samplingDao.getTemplate();
	}

	@Override
	public SamplingTemplate addNewTemplate(SamplingTemplate samplingTemplate) {
		return samplingDao.addNewTemplate(samplingTemplate);
	}

	@Override
	public boolean addNewMapping(SamplingMapping samplingMapping) {
		return samplingDao.addNewMapping(samplingMapping);
	}

	@Override
	public List<SamplingMapping> getSamplingMappings() {
		return samplingDao.getSamplingMappings();
	}

	@Override
	public SamplingMapping getSamplingMappingByInventory(int region, int center, int client, int process) {
		return samplingDao.getSamplingMappingByInventory(region, center, client, process);
	}

	@Override
	public boolean saveSamplingMapping(SamplingMapping samplingMapping) {
		return samplingDao.saveSamplingMapping(samplingMapping);
	}

	@Override
	public List<CRMData> getAllCRMData() {
		return samplingDao.getAllCRMData();
	}

	@Override
	public List<CRMData> getCRMsbyUploadSeqId(int seqId) {
		return samplingDao.getCRMsbyUploadSeqId(seqId);
	}

	@Override
	public List<CLIDData> getAllCLIData() {
		return samplingDao.getAllCLIData();
	}

	@Override
	public List<CLIDData> getCLIsbyUploadSeqId(int seqId) {
		return samplingDao.getCLIsbyUploadSeqId(seqId);
	}

	@Override
	public List<CliCrmMapping> getCliCrmMappings() {
		return samplingDao.getCliCrmMappings();
	}

	@Override
	public List<CliCrmMapping> getCliCrmMappingsBySeqId(int seqId) {
		return samplingDao.getCliCrmMappingsBySeqId(seqId);
	}

	@Override
	public List<UploadRequestDetails> getAllUploadRequest() {
		return samplingDao.getAllUploadRequest();
	}

	@Override
	public UploadRequestDetails getUploadRequestById(int id) {
		return samplingDao.getUploadRequestById(id);
	}

	@Override
	public List<SamplingRecord> generateSamplingRecords(int id) {

		UploadRequestDetails requestDetails = samplingDao.getUploadRequestById(id);

		List<CRMData> allCRMs = samplingDao.getCRMsbyUploadSeqId(id);
		List<CLIDData> allClIds = samplingDao.getCLIsbyUploadSeqId(id);
		List<CliCrmMapping> mapping = samplingDao.getCliCrmMappingsBySeqId(id);

		SamplingMapping samplingMapping = samplingDao.getSamplingMappingByInventory(requestDetails.getRegion().getId(),
				requestDetails.getCenter().getId(), requestDetails.getClient().getId(),
				requestDetails.getProcess().getId());

		List<SamplingRecord> samplingRecords = new ArrayList<SamplingRecord>();

		for (CLIDData cli : allClIds) {
			List<CRMData> crms = SamplingUtil.getCrmsOfClis(cli.getAgentId(), mapping, allCRMs);
			for (CRMData crm : crms) {
				SamplingRecord samplingRecord = new SamplingRecord();
				samplingRecord.setUploadRequestDetails(requestDetails);
				if (samplingMapping.getSamplingTemplate().getCallDurations() != null) {
					samplingRecord.setCallType(SamplingUtil.getCalltype(cli.getHandlingTime(),
							samplingMapping.getSamplingTemplate().getCallDurations()));
				} else {
					samplingRecord.setCallType(null);
				}

				if (samplingMapping.getSamplingTemplate().getCallNatures() != null) {
					samplingRecord.setCallNature(SamplingUtil.getCallNature(cli.getStartTime(),
							samplingMapping.getSamplingTemplate().getCallNatures()));
				} else {
					samplingRecord.setCallNature(null);
				}

				samplingRecord.setRequestType(crm.getCaseType());
				samplingRecord.setAgentId(cli.getAgentId());
				samplingRecord.setAgentCategory("");
				samplingRecord.setMsisdn(crm.getMsisdn());
				samplingRecord.setStatus(SamplingUtil.SAMPLING_RECORD_STATUS_NEW);
				samplingRecord = samplingDao.addNewSamplingRecord(samplingRecord);
				samplingRecords.add(samplingRecord);
			}
		}

		return null;
	}

	public void applySamplingRulesAgentIdWise(List<SamplingRecord> samplingRecords, SamplingTemplate samplingTemplate) {

		Collections.shuffle(samplingRecords);

		List<String> agentIds = (samplingRecords.stream().map(SamplingRecord::getAgentId).collect(Collectors.toList()))
				.stream().distinct().collect(Collectors.toList());
		if (samplingTemplate.getCallMixCategories() == null) {
			for (String agentId : agentIds) {
				List<SamplingRecord> recordsByAgentId = samplingRecords.stream()
						.filter(d -> d.getAgentId().equals(agentId)).collect(Collectors.toList());
				String agentCategory = recordsByAgentId.get(0).getAgentCategory();
				for (AgentCategory cat : samplingTemplate.getAgentCategories()) {
					if (agentCategory.equals(SamplingUtil.AGENT_CATEGORY_A)) {
						recordsByAgentId = recordsByAgentId.stream().limit(cat.getNoOfCalls())
								.collect(Collectors.toList());
					} else if (agentCategory.equals(SamplingUtil.AGENT_CATEGORY_B)) {
						recordsByAgentId = recordsByAgentId.stream().limit(cat.getNoOfCalls())
								.collect(Collectors.toList());
					} else if (agentCategory.equals(SamplingUtil.AGENT_CATEGORY_C)) {
						recordsByAgentId = recordsByAgentId.stream().limit(cat.getNoOfCalls())
								.collect(Collectors.toList());
					} else if (agentCategory.equals(SamplingUtil.AGENT_CATEGORY_D)) {
						recordsByAgentId = recordsByAgentId.stream().limit(cat.getNoOfCalls())
								.collect(Collectors.toList());
					}
				}
			}
		}

	}

	public void applySamplingRulesCategoryWise(List<SamplingRecord> samplingRecords,
			SamplingTemplate samplingTemplate) {
		Collections.shuffle(samplingRecords);

		List<SamplingRecord> recordsByCategoryA = samplingRecords.stream()
				.filter(d -> d.getAgentCategory().equals(SamplingUtil.AGENT_CATEGORY_A)).collect(Collectors.toList());
		List<SamplingRecord> recordsByCategoryB = samplingRecords.stream()
				.filter(d -> d.getAgentCategory().equals(SamplingUtil.AGENT_CATEGORY_B)).collect(Collectors.toList());
		List<SamplingRecord> recordsByCategoryC = samplingRecords.stream()
				.filter(d -> d.getAgentCategory().equals(SamplingUtil.AGENT_CATEGORY_C)).collect(Collectors.toList());
		List<SamplingRecord> recordsByCategoryD = samplingRecords.stream()
				.filter(d -> d.getAgentCategory().equals(SamplingUtil.AGENT_CATEGORY_D)).collect(Collectors.toList());

		if (samplingTemplate.getCallMixCategories() == null) {
			for (AgentCategory cat : samplingTemplate.getAgentCategories()) {
				if (cat.getCategory().equals(SamplingUtil.AGENT_CATEGORY_A)) {
					recordsByCategoryA = recordsByCategoryA.stream().limit(cat.getNoOfCalls())
							.collect(Collectors.toList());
				} else if (cat.getCategory().equals(SamplingUtil.AGENT_CATEGORY_B)) {
					recordsByCategoryB = recordsByCategoryB.stream().limit(cat.getNoOfCalls())
							.collect(Collectors.toList());
				} else if (cat.getCategory().equals(SamplingUtil.AGENT_CATEGORY_C)) {
					recordsByCategoryC = recordsByCategoryC.stream().limit(cat.getNoOfCalls())
							.collect(Collectors.toList());
				} else if (cat.getCategory().equals(SamplingUtil.AGENT_CATEGORY_D)) {
					recordsByCategoryD = recordsByCategoryD.stream().limit(cat.getNoOfCalls())
							.collect(Collectors.toList());
				}
			}
		} else {
			for (AgentCategory cat : samplingTemplate.getAgentCategories()) {
				if (cat.getCategory().equals(SamplingUtil.AGENT_CATEGORY_A)) {
					CallMixSamplingPer CallMixSamplingPer = SamplingUtil
							.getCallMixSamplingPerByCategory(samplingTemplate, recordsByCategoryA.size());
					int requiredCalls = cat.getNoOfCalls();
					generateFinalRecordsCategoryWise(recordsByCategoryA, CallMixSamplingPer, requiredCalls);
				} else if (cat.getCategory().equals(SamplingUtil.AGENT_CATEGORY_B)) {
					CallMixSamplingPer CallMixSamplingPer = SamplingUtil
							.getCallMixSamplingPerByCategory(samplingTemplate, recordsByCategoryB.size());
					int requiredCalls = cat.getNoOfCalls();
					generateFinalRecordsCategoryWise(recordsByCategoryB, CallMixSamplingPer, requiredCalls);
				} else if (cat.getCategory().equals(SamplingUtil.AGENT_CATEGORY_C)) {
					CallMixSamplingPer CallMixSamplingPer = SamplingUtil
							.getCallMixSamplingPerByCategory(samplingTemplate, recordsByCategoryC.size());
					int requiredCalls = cat.getNoOfCalls();
					generateFinalRecordsCategoryWise(recordsByCategoryC, CallMixSamplingPer, requiredCalls);
				} else if (cat.getCategory().equals(SamplingUtil.AGENT_CATEGORY_D)) {
					CallMixSamplingPer CallMixSamplingPer = SamplingUtil
							.getCallMixSamplingPerByCategory(samplingTemplate, recordsByCategoryD.size());
					int requiredCalls = cat.getNoOfCalls();
					generateFinalRecordsCategoryWise(recordsByCategoryD, CallMixSamplingPer, requiredCalls);
				}
			}

		}
	}

	private List<SamplingRecord> generateFinalRecordsCategoryWise(List<SamplingRecord> allRecordsByCategory,
			CallMixSamplingPer per, int requiredCalls) {
		List<SamplingRecord> records = new ArrayList<SamplingRecord>();

		List<SamplingRecord> t = allRecordsByCategory;
		if (t.size() < requiredCalls) {
			records.addAll(allRecordsByCategory);
			return records;
		}

		// ========== Morning Long Calls with all request types ============
		List<SamplingRecord> finalRecords = allRecordsByCategory.stream()
				.filter(d -> d.getCallNature().equals(SamplingUtil.CALL_NATURE_MORNING)
						&& d.getCallType().equals(SamplingUtil.CALL_TYPE_LONG)
						&& d.getRequestType().equals(SamplingUtil.REQUEST_TYPE_Q))
				.collect(Collectors.toList());
		t.removeAll(finalRecords);
		finalRecords = finalRecords.stream().limit((long) per.getMorningLongQ()).collect(Collectors.toList());
		if (t.size() + finalRecords.size() <= requiredCalls) {
			records.addAll(allRecordsByCategory);
			return records;
		}
		records.addAll(finalRecords);

		finalRecords = allRecordsByCategory.stream()
				.filter(d -> d.getCallNature().equals(SamplingUtil.CALL_NATURE_MORNING)
						&& d.getCallType().equals(SamplingUtil.CALL_TYPE_LONG)
						&& d.getRequestType().equals(SamplingUtil.REQUEST_TYPE_R))
				.collect(Collectors.toList());
		t.removeAll(finalRecords);
		finalRecords = finalRecords.stream().limit((long) per.getMorningLongR()).collect(Collectors.toList());
		if (t.size() + finalRecords.size() + records.size() <= requiredCalls) {
			records.addAll(t);
			return records;
		}
		records.addAll(finalRecords);

		finalRecords = allRecordsByCategory.stream()
				.filter(d -> d.getCallNature().equals(SamplingUtil.CALL_NATURE_MORNING)
						&& d.getCallType().equals(SamplingUtil.CALL_TYPE_LONG)
						&& d.getRequestType().equals(SamplingUtil.REQUEST_TYPE_C))
				.collect(Collectors.toList());
		t.removeAll(finalRecords);
		finalRecords = finalRecords.stream().limit((long) per.getMorningLongC()).collect(Collectors.toList());
		if (t.size() + finalRecords.size() + records.size() <= requiredCalls) {
			records.addAll(t);
			return records;
		}
		records.addAll(finalRecords);

		// ======= Morning Medium Calls with all request types ==========
		finalRecords = allRecordsByCategory.stream()
				.filter(d -> d.getCallNature().equals(SamplingUtil.CALL_NATURE_MORNING)
						&& d.getCallType().equals(SamplingUtil.CALL_TYPE_MEDIUM)
						&& d.getRequestType().equals(SamplingUtil.REQUEST_TYPE_Q))
				.collect(Collectors.toList());
		t.removeAll(finalRecords);
		finalRecords = finalRecords.stream().limit((long) per.getMorningMediumQ()).collect(Collectors.toList());
		if (t.size() + finalRecords.size() + records.size() <= requiredCalls) {
			records.addAll(t);
			return records;
		}
		records.addAll(finalRecords);

		finalRecords = allRecordsByCategory.stream()
				.filter(d -> d.getCallNature().equals(SamplingUtil.CALL_NATURE_MORNING)
						&& d.getCallType().equals(SamplingUtil.CALL_TYPE_MEDIUM)
						&& d.getRequestType().equals(SamplingUtil.REQUEST_TYPE_R))
				.collect(Collectors.toList());
		t.removeAll(finalRecords);
		finalRecords = finalRecords.stream().limit((long) per.getMorningMediumR()).collect(Collectors.toList());
		if (t.size() + finalRecords.size() + records.size() <= requiredCalls) {
			records.addAll(t);
			return records;
		}
		records.addAll(finalRecords);

		finalRecords = allRecordsByCategory.stream()
				.filter(d -> d.getCallNature().equals(SamplingUtil.CALL_NATURE_MORNING)
						&& d.getCallType().equals(SamplingUtil.CALL_TYPE_MEDIUM)
						&& d.getRequestType().equals(SamplingUtil.REQUEST_TYPE_C))
				.collect(Collectors.toList());
		t.removeAll(finalRecords);
		finalRecords = finalRecords.stream().limit((long) per.getMorningMediumC()).collect(Collectors.toList());
		if (t.size() + finalRecords.size() + records.size() <= requiredCalls) {
			records.addAll(t);
			return records;
		}
		records.addAll(finalRecords);

		// ======== Morning Short Calls with all request types ==========
		finalRecords = allRecordsByCategory.stream()
				.filter(d -> d.getCallNature().equals(SamplingUtil.CALL_NATURE_MORNING)
						&& d.getCallType().equals(SamplingUtil.CALL_TYPE_SHORT)
						&& d.getRequestType().equals(SamplingUtil.REQUEST_TYPE_Q))
				.collect(Collectors.toList());
		t.removeAll(finalRecords);
		finalRecords = finalRecords.stream().limit((long) per.getMorningShortQ()).collect(Collectors.toList());
		if (t.size() + finalRecords.size() + records.size() <= requiredCalls) {
			records.addAll(t);
			return records;
		}
		records.addAll(finalRecords);

		finalRecords = allRecordsByCategory.stream()
				.filter(d -> d.getCallNature().equals(SamplingUtil.CALL_NATURE_MORNING)
						&& d.getCallType().equals(SamplingUtil.CALL_TYPE_SHORT)
						&& d.getRequestType().equals(SamplingUtil.REQUEST_TYPE_R))
				.collect(Collectors.toList());
		t.removeAll(finalRecords);
		finalRecords = finalRecords.stream().limit((long) per.getMorningShortR()).collect(Collectors.toList());
		if (t.size() + finalRecords.size() + records.size() <= requiredCalls) {
			records.addAll(t);
			return records;
		}
		records.addAll(finalRecords);

		finalRecords = allRecordsByCategory.stream()
				.filter(d -> d.getCallNature().equals(SamplingUtil.CALL_NATURE_MORNING)
						&& d.getCallType().equals(SamplingUtil.CALL_TYPE_SHORT)
						&& d.getRequestType().equals(SamplingUtil.REQUEST_TYPE_C))
				.collect(Collectors.toList());
		t.removeAll(finalRecords);
		finalRecords = finalRecords.stream().limit((long) per.getMorningShortC()).collect(Collectors.toList());
		if (t.size() + finalRecords.size() + records.size() <= requiredCalls) {
			records.addAll(t);
			return records;
		}
		records.addAll(finalRecords);

		// ===== Afternoon Long Calls with all request types ===========
		finalRecords = allRecordsByCategory.stream()
				.filter(d -> d.getCallNature().equals(SamplingUtil.CALL_NATURE_AFTERNOON)
						&& d.getCallType().equals(SamplingUtil.CALL_TYPE_LONG)
						&& d.getRequestType().equals(SamplingUtil.REQUEST_TYPE_Q))
				.collect(Collectors.toList());
		t.removeAll(finalRecords);
		finalRecords = finalRecords.stream().limit((long) per.getAfternoonLongQ()).collect(Collectors.toList());
		if (t.size() + finalRecords.size() <= requiredCalls) {
			records.addAll(allRecordsByCategory);
			return records;
		}
		records.addAll(finalRecords);

		finalRecords = allRecordsByCategory.stream()
				.filter(d -> d.getCallNature().equals(SamplingUtil.CALL_NATURE_AFTERNOON)
						&& d.getCallType().equals(SamplingUtil.CALL_TYPE_LONG)
						&& d.getRequestType().equals(SamplingUtil.REQUEST_TYPE_R))
				.collect(Collectors.toList());
		t.removeAll(finalRecords);
		finalRecords = finalRecords.stream().limit((long) per.getAfternoonLongR()).collect(Collectors.toList());
		if (t.size() + finalRecords.size() + records.size() <= requiredCalls) {
			records.addAll(t);
			return records;
		}
		records.addAll(finalRecords);

		finalRecords = allRecordsByCategory.stream()
				.filter(d -> d.getCallNature().equals(SamplingUtil.CALL_NATURE_AFTERNOON)
						&& d.getCallType().equals(SamplingUtil.CALL_TYPE_LONG)
						&& d.getRequestType().equals(SamplingUtil.REQUEST_TYPE_C))
				.collect(Collectors.toList());
		t.removeAll(finalRecords);
		finalRecords = finalRecords.stream().limit((long) per.getAfternoonLongC()).collect(Collectors.toList());
		if (t.size() + finalRecords.size() + records.size() <= requiredCalls) {
			records.addAll(t);
			return records;
		}
		records.addAll(finalRecords);

		// ============== Afternoon Medium Calls with all request types ==========
		finalRecords = allRecordsByCategory.stream()
				.filter(d -> d.getCallNature().equals(SamplingUtil.CALL_NATURE_AFTERNOON)
						&& d.getCallType().equals(SamplingUtil.CALL_TYPE_MEDIUM)
						&& d.getRequestType().equals(SamplingUtil.REQUEST_TYPE_Q))
				.collect(Collectors.toList());
		t.removeAll(finalRecords);
		finalRecords = finalRecords.stream().limit((long) per.getAfternoonMediumQ()).collect(Collectors.toList());
		if (t.size() + finalRecords.size() + records.size() <= requiredCalls) {
			records.addAll(t);
			return records;
		}
		records.addAll(finalRecords);

		finalRecords = allRecordsByCategory.stream()
				.filter(d -> d.getCallNature().equals(SamplingUtil.CALL_NATURE_AFTERNOON)
						&& d.getCallType().equals(SamplingUtil.CALL_TYPE_MEDIUM)
						&& d.getRequestType().equals(SamplingUtil.REQUEST_TYPE_R))
				.collect(Collectors.toList());
		t.removeAll(finalRecords);
		finalRecords = finalRecords.stream().limit((long) per.getAfternoonMediumR()).collect(Collectors.toList());
		if (t.size() + finalRecords.size() + records.size() <= requiredCalls) {
			records.addAll(t);
			return records;
		}
		records.addAll(finalRecords);

		finalRecords = allRecordsByCategory.stream()
				.filter(d -> d.getCallNature().equals(SamplingUtil.CALL_NATURE_AFTERNOON)
						&& d.getCallType().equals(SamplingUtil.CALL_TYPE_MEDIUM)
						&& d.getRequestType().equals(SamplingUtil.REQUEST_TYPE_C))
				.collect(Collectors.toList());
		t.removeAll(finalRecords);
		finalRecords = finalRecords.stream().limit((long) per.getAfternoonMediumC()).collect(Collectors.toList());
		if (t.size() + finalRecords.size() + records.size() <= requiredCalls) {
			records.addAll(t);
			return records;
		}
		records.addAll(finalRecords);

		// ============== Afternoon Short Calls with all request types ==========
		finalRecords = allRecordsByCategory.stream()
				.filter(d -> d.getCallNature().equals(SamplingUtil.CALL_NATURE_AFTERNOON)
						&& d.getCallType().equals(SamplingUtil.CALL_TYPE_SHORT)
						&& d.getRequestType().equals(SamplingUtil.REQUEST_TYPE_Q))
				.collect(Collectors.toList());
		t.removeAll(finalRecords);
		finalRecords = finalRecords.stream().limit((long) per.getAfternoonShortQ()).collect(Collectors.toList());
		if (t.size() + finalRecords.size() + records.size() <= requiredCalls) {
			records.addAll(t);
			return records;
		}
		records.addAll(finalRecords);

		finalRecords = allRecordsByCategory.stream()
				.filter(d -> d.getCallNature().equals(SamplingUtil.CALL_NATURE_AFTERNOON)
						&& d.getCallType().equals(SamplingUtil.CALL_TYPE_SHORT)
						&& d.getRequestType().equals(SamplingUtil.REQUEST_TYPE_R))
				.collect(Collectors.toList());
		t.removeAll(finalRecords);
		finalRecords = finalRecords.stream().limit((long) per.getAfternoonShortR()).collect(Collectors.toList());
		if (t.size() + finalRecords.size() + records.size() <= requiredCalls) {
			records.addAll(t);
			return records;
		}
		records.addAll(finalRecords);

		finalRecords = allRecordsByCategory.stream()
				.filter(d -> d.getCallNature().equals(SamplingUtil.CALL_NATURE_AFTERNOON)
						&& d.getCallType().equals(SamplingUtil.CALL_TYPE_SHORT)
						&& d.getRequestType().equals(SamplingUtil.REQUEST_TYPE_C))
				.collect(Collectors.toList());
		t.removeAll(finalRecords);
		finalRecords = finalRecords.stream().limit((long) per.getAfternoonShortC()).collect(Collectors.toList());
		if (t.size() + finalRecords.size() + records.size() <= requiredCalls) {
			records.addAll(t);
			return records;
		}
		records.addAll(finalRecords);

		// ===== Evening Long Calls with all request types ===========
		finalRecords = allRecordsByCategory.stream()
				.filter(d -> d.getCallNature().equals(SamplingUtil.CALL_NATURE_EVENING)
						&& d.getCallType().equals(SamplingUtil.CALL_TYPE_LONG)
						&& d.getRequestType().equals(SamplingUtil.REQUEST_TYPE_Q))
				.collect(Collectors.toList());
		t.removeAll(finalRecords);
		finalRecords = finalRecords.stream().limit((long) per.getEveningLongQ()).collect(Collectors.toList());
		if (t.size() + finalRecords.size() <= requiredCalls) {
			records.addAll(allRecordsByCategory);
			return records;
		}
		records.addAll(finalRecords);

		finalRecords = allRecordsByCategory.stream()
				.filter(d -> d.getCallNature().equals(SamplingUtil.CALL_NATURE_EVENING)
						&& d.getCallType().equals(SamplingUtil.CALL_TYPE_LONG)
						&& d.getRequestType().equals(SamplingUtil.REQUEST_TYPE_R))
				.collect(Collectors.toList());
		t.removeAll(finalRecords);
		finalRecords = finalRecords.stream().limit((long) per.getEveningLongR()).collect(Collectors.toList());
		if (t.size() + finalRecords.size() + records.size() <= requiredCalls) {
			records.addAll(t);
			return records;
		}
		records.addAll(finalRecords);

		finalRecords = allRecordsByCategory.stream()
				.filter(d -> d.getCallNature().equals(SamplingUtil.CALL_NATURE_EVENING)
						&& d.getCallType().equals(SamplingUtil.CALL_TYPE_LONG)
						&& d.getRequestType().equals(SamplingUtil.REQUEST_TYPE_C))
				.collect(Collectors.toList());
		t.removeAll(finalRecords);
		finalRecords = finalRecords.stream().limit((long) per.getEveningLongC()).collect(Collectors.toList());
		if (t.size() + finalRecords.size() + records.size() <= requiredCalls) {
			records.addAll(t);
			return records;
		}
		records.addAll(finalRecords);

		// ============== Evening Medium Calls with all request types ==========
		finalRecords = allRecordsByCategory.stream()
				.filter(d -> d.getCallNature().equals(SamplingUtil.CALL_NATURE_EVENING)
						&& d.getCallType().equals(SamplingUtil.CALL_TYPE_MEDIUM)
						&& d.getRequestType().equals(SamplingUtil.REQUEST_TYPE_Q))
				.collect(Collectors.toList());
		t.removeAll(finalRecords);
		finalRecords = finalRecords.stream().limit((long) per.getEveningMediumQ()).collect(Collectors.toList());
		if (t.size() + finalRecords.size() + records.size() <= requiredCalls) {
			records.addAll(t);
			return records;
		}
		records.addAll(finalRecords);

		finalRecords = allRecordsByCategory.stream()
				.filter(d -> d.getCallNature().equals(SamplingUtil.CALL_NATURE_EVENING)
						&& d.getCallType().equals(SamplingUtil.CALL_TYPE_MEDIUM)
						&& d.getRequestType().equals(SamplingUtil.REQUEST_TYPE_R))
				.collect(Collectors.toList());
		t.removeAll(finalRecords);
		finalRecords = finalRecords.stream().limit((long) per.getEveningMediumR()).collect(Collectors.toList());
		if (t.size() + finalRecords.size() + records.size() <= requiredCalls) {
			records.addAll(t);
			return records;
		}
		records.addAll(finalRecords);

		finalRecords = allRecordsByCategory.stream()
				.filter(d -> d.getCallNature().equals(SamplingUtil.CALL_NATURE_EVENING)
						&& d.getCallType().equals(SamplingUtil.CALL_TYPE_MEDIUM)
						&& d.getRequestType().equals(SamplingUtil.REQUEST_TYPE_C))
				.collect(Collectors.toList());
		t.removeAll(finalRecords);
		finalRecords = finalRecords.stream().limit((long) per.getEveningMediumC()).collect(Collectors.toList());
		if (t.size() + finalRecords.size() + records.size() <= requiredCalls) {
			records.addAll(t);
			return records;
		}
		records.addAll(finalRecords);

		// ============== Evening Short Calls with all request types ==========
		finalRecords = allRecordsByCategory.stream()
				.filter(d -> d.getCallNature().equals(SamplingUtil.CALL_NATURE_EVENING)
						&& d.getCallType().equals(SamplingUtil.CALL_TYPE_SHORT)
						&& d.getRequestType().equals(SamplingUtil.REQUEST_TYPE_Q))
				.collect(Collectors.toList());
		t.removeAll(finalRecords);
		finalRecords = finalRecords.stream().limit((long) per.getEveningShortQ()).collect(Collectors.toList());
		if (t.size() + finalRecords.size() + records.size() <= requiredCalls) {
			records.addAll(t);
			return records;
		}
		records.addAll(finalRecords);

		finalRecords = allRecordsByCategory.stream()
				.filter(d -> d.getCallNature().equals(SamplingUtil.CALL_NATURE_EVENING)
						&& d.getCallType().equals(SamplingUtil.CALL_TYPE_SHORT)
						&& d.getRequestType().equals(SamplingUtil.REQUEST_TYPE_R))
				.collect(Collectors.toList());
		t.removeAll(finalRecords);
		finalRecords = finalRecords.stream().limit((long) per.getEveningShortR()).collect(Collectors.toList());
		if (t.size() + finalRecords.size() + records.size() <= requiredCalls) {
			records.addAll(t);
			return records;
		}
		records.addAll(finalRecords);

		finalRecords = allRecordsByCategory.stream()
				.filter(d -> d.getCallNature().equals(SamplingUtil.CALL_NATURE_EVENING)
						&& d.getCallType().equals(SamplingUtil.CALL_TYPE_SHORT)
						&& d.getRequestType().equals(SamplingUtil.REQUEST_TYPE_C))
				.collect(Collectors.toList());
		t.removeAll(finalRecords);
		finalRecords = finalRecords.stream().limit((long) per.getEveningShortC()).collect(Collectors.toList());
		if (t.size() + finalRecords.size() + records.size() <= requiredCalls) {
			records.addAll(t);
			return records;
		}
		records.addAll(finalRecords);

		// ===== Night Long Calls with all request types ===========
		finalRecords = allRecordsByCategory.stream()
				.filter(d -> d.getCallNature().equals(SamplingUtil.CALL_NATURE_NIGHT)
						&& d.getCallType().equals(SamplingUtil.CALL_TYPE_LONG)
						&& d.getRequestType().equals(SamplingUtil.REQUEST_TYPE_Q))
				.collect(Collectors.toList());
		t.removeAll(finalRecords);
		finalRecords = finalRecords.stream().limit((long) per.getNightLongQ()).collect(Collectors.toList());
		if (t.size() + finalRecords.size() <= requiredCalls) {
			records.addAll(allRecordsByCategory);
			return records;
		}
		records.addAll(finalRecords);

		finalRecords = allRecordsByCategory.stream()
				.filter(d -> d.getCallNature().equals(SamplingUtil.CALL_NATURE_NIGHT)
						&& d.getCallType().equals(SamplingUtil.CALL_TYPE_LONG)
						&& d.getRequestType().equals(SamplingUtil.REQUEST_TYPE_R))
				.collect(Collectors.toList());
		t.removeAll(finalRecords);
		finalRecords = finalRecords.stream().limit((long) per.getNightLongR()).collect(Collectors.toList());
		if (t.size() + finalRecords.size() + records.size() <= requiredCalls) {
			records.addAll(t);
			return records;
		}
		records.addAll(finalRecords);

		finalRecords = allRecordsByCategory.stream()
				.filter(d -> d.getCallNature().equals(SamplingUtil.CALL_NATURE_NIGHT)
						&& d.getCallType().equals(SamplingUtil.CALL_TYPE_LONG)
						&& d.getRequestType().equals(SamplingUtil.REQUEST_TYPE_C))
				.collect(Collectors.toList());
		t.removeAll(finalRecords);
		finalRecords = finalRecords.stream().limit((long) per.getNightLongC()).collect(Collectors.toList());
		if (t.size() + finalRecords.size() + records.size() <= requiredCalls) {
			records.addAll(t);
			return records;
		}
		records.addAll(finalRecords);

		// ============== Night Medium Calls with all request types ==========
		finalRecords = allRecordsByCategory.stream()
				.filter(d -> d.getCallNature().equals(SamplingUtil.CALL_NATURE_NIGHT)
						&& d.getCallType().equals(SamplingUtil.CALL_TYPE_MEDIUM)
						&& d.getRequestType().equals(SamplingUtil.REQUEST_TYPE_Q))
				.collect(Collectors.toList());
		t.removeAll(finalRecords);
		finalRecords = finalRecords.stream().limit((long) per.getNightMediumQ()).collect(Collectors.toList());
		if (t.size() + finalRecords.size() + records.size() <= requiredCalls) {
			records.addAll(t);
			return records;
		}
		records.addAll(finalRecords);

		finalRecords = allRecordsByCategory.stream()
				.filter(d -> d.getCallNature().equals(SamplingUtil.CALL_NATURE_NIGHT)
						&& d.getCallType().equals(SamplingUtil.CALL_TYPE_MEDIUM)
						&& d.getRequestType().equals(SamplingUtil.REQUEST_TYPE_R))
				.collect(Collectors.toList());
		t.removeAll(finalRecords);
		finalRecords = finalRecords.stream().limit((long) per.getNightMediumR()).collect(Collectors.toList());
		if (t.size() + finalRecords.size() + records.size() <= requiredCalls) {
			records.addAll(t);
			return records;
		}
		records.addAll(finalRecords);

		finalRecords = allRecordsByCategory.stream()
				.filter(d -> d.getCallNature().equals(SamplingUtil.CALL_NATURE_NIGHT)
						&& d.getCallType().equals(SamplingUtil.CALL_TYPE_MEDIUM)
						&& d.getRequestType().equals(SamplingUtil.REQUEST_TYPE_C))
				.collect(Collectors.toList());
		t.removeAll(finalRecords);
		finalRecords = finalRecords.stream().limit((long) per.getNightMediumC()).collect(Collectors.toList());
		if (t.size() + finalRecords.size() + records.size() <= requiredCalls) {
			records.addAll(t);
			return records;
		}
		records.addAll(finalRecords);

		// ============== Night Short Calls with all request types ==========
		finalRecords = allRecordsByCategory.stream()
				.filter(d -> d.getCallNature().equals(SamplingUtil.CALL_NATURE_NIGHT)
						&& d.getCallType().equals(SamplingUtil.CALL_TYPE_SHORT)
						&& d.getRequestType().equals(SamplingUtil.REQUEST_TYPE_Q))
				.collect(Collectors.toList());
		t.removeAll(finalRecords);
		finalRecords = finalRecords.stream().limit((long) per.getNightShortQ()).collect(Collectors.toList());
		if (t.size() + finalRecords.size() + records.size() <= requiredCalls) {
			records.addAll(t);
			return records;
		}
		records.addAll(finalRecords);

		finalRecords = allRecordsByCategory.stream()
				.filter(d -> d.getCallNature().equals(SamplingUtil.CALL_NATURE_NIGHT)
						&& d.getCallType().equals(SamplingUtil.CALL_TYPE_SHORT)
						&& d.getRequestType().equals(SamplingUtil.REQUEST_TYPE_R))
				.collect(Collectors.toList());
		t.removeAll(finalRecords);
		finalRecords = finalRecords.stream().limit((long) per.getNightShortR()).collect(Collectors.toList());
		if (t.size() + finalRecords.size() + records.size() <= requiredCalls) {
			records.addAll(t);
			return records;
		}
		records.addAll(finalRecords);

		finalRecords = allRecordsByCategory.stream()
				.filter(d -> d.getCallNature().equals(SamplingUtil.CALL_NATURE_NIGHT)
						&& d.getCallType().equals(SamplingUtil.CALL_TYPE_SHORT)
						&& d.getRequestType().equals(SamplingUtil.REQUEST_TYPE_C))
				.collect(Collectors.toList());
		t.removeAll(finalRecords);
		finalRecords = finalRecords.stream().limit((long) per.getNightShortC()).collect(Collectors.toList());
		if (t.size() + finalRecords.size() + records.size() <= requiredCalls) {
			records.addAll(t);
			return records;
		}
		records.addAll(finalRecords);

		return records;
	}

}
