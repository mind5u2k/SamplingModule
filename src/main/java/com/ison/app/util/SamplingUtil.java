package com.ison.app.util;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ison.app.model.AgentCategory;
import com.ison.app.model.CRMData;
import com.ison.app.model.CallDuration;
import com.ison.app.model.CallMixCategory;
import com.ison.app.model.CallMixSamplingPer;
import com.ison.app.model.CallNature;
import com.ison.app.model.CliCrmMapping;
import com.ison.app.model.InventoryMaster;
import com.ison.app.model.SamplingMapping;
import com.ison.app.model.SamplingTemplate;

public class SamplingUtil {

	public static final String AGENT_CATEGORY_A = "A";
	public static final String AGENT_CATEGORY_B = "B";
	public static final String AGENT_CATEGORY_C = "C";
	public static final String AGENT_CATEGORY_D = "D";

	public static final String CALL_TYPE_LONG = "Long";
	public static final String CALL_TYPE_SHORT = "Short";
	public static final String CALL_TYPE_MEDIUM = "Medium";

	public static List<String> getAllCallTypes() {
		List<String> allCallTypes = new ArrayList<String>();
		allCallTypes.add(CALL_TYPE_LONG);
		allCallTypes.add(CALL_TYPE_MEDIUM);
		allCallTypes.add(CALL_TYPE_SHORT);
		return allCallTypes;
	}

	public static final String CALL_NATURE_MORNING = "Morning";
	public static final String CALL_NATURE_AFTERNOON = "Afternoon";
	public static final String CALL_NATURE_EVENING = "Evening";
	public static final String CALL_NATURE_NIGHT = "Night";

	public static List<String> getAllNatures() {
		List<String> allNatures = new ArrayList<String>();
		allNatures.add(CALL_NATURE_MORNING);
		allNatures.add(CALL_NATURE_AFTERNOON);
		allNatures.add(CALL_NATURE_EVENING);
		allNatures.add(CALL_NATURE_NIGHT);
		return allNatures;
	}

	public static final String REQUEST_TYPE_Q = "Q";
	public static final String REQUEST_TYPE_R = "R";
	public static final String REQUEST_TYPE_C = "C";

	public static List<String> getAllRequestType() {
		List<String> allRequestTypes = new ArrayList<String>();
		allRequestTypes.add(REQUEST_TYPE_Q);
		allRequestTypes.add(REQUEST_TYPE_R);
		allRequestTypes.add(REQUEST_TYPE_C);
		return allRequestTypes;
	}

	public static final String SAMPLING_RECORD_STATUS_NEW = "New";

	public static SamplingMapping getSamplingMapping(String mapping) {

		SamplingMapping samplingMapping = new SamplingMapping();
		JSONObject jsonObj = new JSONObject(mapping);

		InventoryMaster region = new InventoryMaster();
		region.setId(jsonObj.getInt("regionId"));
		samplingMapping.setRegion(region);

		InventoryMaster center = new InventoryMaster();
		center.setId(jsonObj.getInt("centerId"));
		samplingMapping.setCenter(center);

		InventoryMaster client = new InventoryMaster();
		client.setId(jsonObj.getInt("clientId"));
		samplingMapping.setClient(client);

		InventoryMaster process = new InventoryMaster();
		process.setId(jsonObj.getInt("processId"));
		samplingMapping.setProcess(process);

		SamplingTemplate samplingTemplate = new SamplingTemplate();
		samplingTemplate.setId(jsonObj.getInt("templateId"));
		samplingMapping.setSamplingTemplate(samplingTemplate);

		return samplingMapping;
	}

	public static SamplingTemplate getSamplingTamplate(String template) {
		SamplingTemplate samplingTemplate = new SamplingTemplate();
		List<AgentCategory> agentCategories = new ArrayList<AgentCategory>();
		List<CallDuration> callDurations = new ArrayList<CallDuration>();
		List<CallNature> callNatures = new ArrayList<CallNature>();
		List<CallMixCategory> callMixCategories = new ArrayList<CallMixCategory>();

		JSONObject jsonObj = new JSONObject(template);
		samplingTemplate.setTemplateTitle(jsonObj.getString("templateTitle"));

		JSONArray categories = jsonObj.getJSONArray("agentCategories");
		for (int i = 0; i < categories.length(); i++) {
			JSONObject o = categories.getJSONObject(i);
			AgentCategory agentCategory = new AgentCategory();
			agentCategory.setCategory(o.getString("category"));
			agentCategory.setNoOfCalls(o.getInt("noOfCalls"));
			agentCategory.setMinScore(o.getInt("minScore"));
			agentCategory.setMaxScore(o.getInt("maxScore"));
			agentCategories.add(agentCategory);
		}

		JSONArray callDurationsArray = jsonObj.getJSONArray("callDurations");
		for (int i = 0; i < callDurationsArray.length(); i++) {
			JSONObject o = callDurationsArray.getJSONObject(i);
			CallDuration callDuration = new CallDuration();
			callDuration.setCallDuration(o.getString("callDuration"));
			callDuration.setMin(o.getInt("min"));
			callDuration.setMax(o.getInt("max"));
			callDurations.add(callDuration);
		}

		JSONArray callNaturesArray = jsonObj.getJSONArray("callNatures");
		for (int i = 0; i < callNaturesArray.length(); i++) {
			JSONObject o = callNaturesArray.getJSONObject(i);
			String[] startTime = o.getString("startTime").split(":");
			String[] endTime = o.getString("endTime").split(":");
			CallNature callNature = new CallNature();
			callNature.setCallNature(o.getString("callNature"));
			callNature.setStartTime(new Time(Integer.parseInt(startTime[0]), Integer.parseInt(startTime[1]),
					Integer.parseInt(startTime[2])));
			callNature.setEndTime(
					new Time(Integer.parseInt(endTime[0]), Integer.parseInt(endTime[1]), Integer.parseInt(endTime[2])));
			callNatures.add(callNature);
		}

		JSONArray callMixCategoryArray = jsonObj.getJSONArray("callMixCategories");
		for (int i = 0; i < callMixCategoryArray.length(); i++) {
			JSONObject o = callMixCategoryArray.getJSONObject(i);
			CallMixCategory callMixCategory = new CallMixCategory();
			callMixCategory.setAuditCategory(o.getString("auditCategory"));
			callMixCategory.setAudit_type(o.getString("audit_type"));
			callMixCategory.setPercentage(o.getInt("percentage"));
			callMixCategories.add(callMixCategory);
		}

		samplingTemplate.setAgentCategories(agentCategories);
		samplingTemplate.setCallDurations(callDurations);
		samplingTemplate.setCallMixCategories(callMixCategories);
		samplingTemplate.setCallNatures(callNatures);

		return samplingTemplate;
	}

	public static List<CRMData> getCrmsOfClis(String cliAgentId, List<CliCrmMapping> mappings, List<CRMData> crms) {
		List<CRMData> crmDataList = new ArrayList<CRMData>();
		List<CliCrmMapping> cliCrmMappings = mappings.stream().filter(d -> d.getAavayaId() == cliAgentId)
				.collect(Collectors.toList());
		for (CliCrmMapping map : cliCrmMappings) {
			crmDataList
					.addAll(crms.stream().filter(d -> d.getAgentId() == map.getCrmId()).collect(Collectors.toList()));
		}
		return crmDataList;
	}

	public static String getCalltype(String handlingTime, List<CallDuration> callDurations) {
		String callType = null;
		Double time = Double.parseDouble(handlingTime);
		for (CallDuration c : callDurations) {
			if (c.getMin() <= time && time <= c.getMax()) {
				callType = c.getCallDuration();
				break;
			}
		}
		return callType;
	}

	public static String getCallNature(String startTime, List<CallNature> callNatures) {
		String callNature = null;
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		Date t = null;
		try {
			t = sdf.parse(startTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Time time = new Time(t.getTime());

		for (CallNature c : callNatures) {
			if (c.getStartTime().before(time) && c.getEndTime().after(time)) {
				callNature = c.getCallNature();
			}
		}
		return callNature;
	}

	public static double getCallMixValue(SamplingTemplate template, String auditType) {
		double val = 0;
		for (CallMixCategory c : template.getCallMixCategories()) {
			if (c.getAudit_type().equals(auditType)) {
				val = c.getPercentage();
			}
		}
		return val;
	}

	public static CallMixSamplingPer getCallMixSamplingPerByCategory(SamplingTemplate samplingTemplate,
			int totalCalls) {

		CallMixSamplingPer callMixSamplingPer = new CallMixSamplingPer();
		for (String callNature : SamplingUtil.getAllNatures()) {
			for (String callDuration : SamplingUtil.getAllCallTypes()) {
				for (String requestType : SamplingUtil.getAllRequestType()) {

					// =======================================================================================================
					// -------- Morning Long with all requestType
					if (callNature.contentEquals(SamplingUtil.CALL_NATURE_MORNING)
							&& callDuration.contentEquals(SamplingUtil.CALL_TYPE_LONG)
							&& requestType.contentEquals(SamplingUtil.REQUEST_TYPE_Q)) {
						callMixSamplingPer.setMorningLongQ(
								getPerOfCombination(totalCalls, samplingTemplate, SamplingUtil.CALL_NATURE_MORNING,
										SamplingUtil.CALL_TYPE_LONG, SamplingUtil.REQUEST_TYPE_Q));
					} else if (callNature.contentEquals(SamplingUtil.CALL_NATURE_MORNING)
							&& callDuration.contentEquals(SamplingUtil.CALL_TYPE_LONG)
							&& requestType.contentEquals(SamplingUtil.REQUEST_TYPE_R)) {
						callMixSamplingPer.setMorningLongR(
								getPerOfCombination(totalCalls, samplingTemplate, SamplingUtil.CALL_NATURE_MORNING,
										SamplingUtil.CALL_TYPE_LONG, SamplingUtil.REQUEST_TYPE_R));
					} else if (callNature.contentEquals(SamplingUtil.CALL_NATURE_MORNING)
							&& callDuration.contentEquals(SamplingUtil.CALL_TYPE_LONG)
							&& requestType.contentEquals(SamplingUtil.REQUEST_TYPE_C)) {
						callMixSamplingPer.setMorningLongC(
								getPerOfCombination(totalCalls, samplingTemplate, SamplingUtil.CALL_NATURE_MORNING,
										SamplingUtil.CALL_TYPE_LONG, SamplingUtil.REQUEST_TYPE_C));
					}

					// -------- Morning Medium with all requestType
					if (callNature.contentEquals(SamplingUtil.CALL_NATURE_MORNING)
							&& callDuration.contentEquals(SamplingUtil.CALL_TYPE_MEDIUM)
							&& requestType.contentEquals(SamplingUtil.REQUEST_TYPE_Q)) {
						callMixSamplingPer.setMorningMediumQ(
								getPerOfCombination(totalCalls, samplingTemplate, SamplingUtil.CALL_NATURE_MORNING,
										SamplingUtil.CALL_TYPE_MEDIUM, SamplingUtil.REQUEST_TYPE_Q));
					} else if (callNature.contentEquals(SamplingUtil.CALL_NATURE_MORNING)
							&& callDuration.contentEquals(SamplingUtil.CALL_TYPE_MEDIUM)
							&& requestType.contentEquals(SamplingUtil.REQUEST_TYPE_R)) {
						callMixSamplingPer.setMorningMediumR(
								getPerOfCombination(totalCalls, samplingTemplate, SamplingUtil.CALL_NATURE_MORNING,
										SamplingUtil.CALL_TYPE_MEDIUM, SamplingUtil.REQUEST_TYPE_R));
					} else if (callNature.contentEquals(SamplingUtil.CALL_NATURE_MORNING)
							&& callDuration.contentEquals(SamplingUtil.CALL_TYPE_MEDIUM)
							&& requestType.contentEquals(SamplingUtil.REQUEST_TYPE_C)) {
						callMixSamplingPer.setMorningMediumC(
								getPerOfCombination(totalCalls, samplingTemplate, SamplingUtil.CALL_NATURE_MORNING,
										SamplingUtil.CALL_TYPE_MEDIUM, SamplingUtil.REQUEST_TYPE_C));
					}

					// -------- Morning Short with all requestType
					if (callNature.contentEquals(SamplingUtil.CALL_NATURE_MORNING)
							&& callDuration.contentEquals(SamplingUtil.CALL_TYPE_SHORT)
							&& requestType.contentEquals(SamplingUtil.REQUEST_TYPE_Q)) {
						callMixSamplingPer.setMorningShortQ(
								getPerOfCombination(totalCalls, samplingTemplate, SamplingUtil.CALL_NATURE_MORNING,
										SamplingUtil.CALL_TYPE_SHORT, SamplingUtil.REQUEST_TYPE_Q));
					} else if (callNature.contentEquals(SamplingUtil.CALL_NATURE_MORNING)
							&& callDuration.contentEquals(SamplingUtil.CALL_TYPE_SHORT)
							&& requestType.contentEquals(SamplingUtil.REQUEST_TYPE_R)) {
						callMixSamplingPer.setMorningShortR(
								getPerOfCombination(totalCalls, samplingTemplate, SamplingUtil.CALL_NATURE_MORNING,
										SamplingUtil.CALL_TYPE_SHORT, SamplingUtil.REQUEST_TYPE_R));
					} else if (callNature.contentEquals(SamplingUtil.CALL_NATURE_MORNING)
							&& callDuration.contentEquals(SamplingUtil.CALL_TYPE_SHORT)
							&& requestType.contentEquals(SamplingUtil.REQUEST_TYPE_C)) {
						callMixSamplingPer.setMorningShortC(
								getPerOfCombination(totalCalls, samplingTemplate, SamplingUtil.CALL_NATURE_MORNING,
										SamplingUtil.CALL_TYPE_SHORT, SamplingUtil.REQUEST_TYPE_C));
					}

					// =============================================================================================================================
					// -------- Afternoon Long with all requestType
					if (callNature.contentEquals(SamplingUtil.CALL_NATURE_AFTERNOON)
							&& callDuration.contentEquals(SamplingUtil.CALL_TYPE_LONG)
							&& requestType.contentEquals(SamplingUtil.REQUEST_TYPE_Q)) {
						callMixSamplingPer.setAfternoonLongQ(
								getPerOfCombination(totalCalls, samplingTemplate, SamplingUtil.CALL_NATURE_AFTERNOON,
										SamplingUtil.CALL_TYPE_LONG, SamplingUtil.REQUEST_TYPE_Q));
					} else if (callNature.contentEquals(SamplingUtil.CALL_NATURE_AFTERNOON)
							&& callDuration.contentEquals(SamplingUtil.CALL_TYPE_LONG)
							&& requestType.contentEquals(SamplingUtil.REQUEST_TYPE_R)) {
						callMixSamplingPer.setAfternoonLongR(
								getPerOfCombination(totalCalls, samplingTemplate, SamplingUtil.CALL_NATURE_AFTERNOON,
										SamplingUtil.CALL_TYPE_LONG, SamplingUtil.REQUEST_TYPE_R));
					} else if (callNature.contentEquals(SamplingUtil.CALL_NATURE_AFTERNOON)
							&& callDuration.contentEquals(SamplingUtil.CALL_TYPE_LONG)
							&& requestType.contentEquals(SamplingUtil.REQUEST_TYPE_C)) {
						callMixSamplingPer.setAfternoonLongC(
								getPerOfCombination(totalCalls, samplingTemplate, SamplingUtil.CALL_NATURE_AFTERNOON,
										SamplingUtil.CALL_TYPE_LONG, SamplingUtil.REQUEST_TYPE_C));
					}

					// -------- Afternoon Medium with all requestType
					if (callNature.contentEquals(SamplingUtil.CALL_NATURE_AFTERNOON)
							&& callDuration.contentEquals(SamplingUtil.CALL_TYPE_MEDIUM)
							&& requestType.contentEquals(SamplingUtil.REQUEST_TYPE_Q)) {
						callMixSamplingPer.setAfternoonMediumQ(
								getPerOfCombination(totalCalls, samplingTemplate, SamplingUtil.CALL_NATURE_AFTERNOON,
										SamplingUtil.CALL_TYPE_MEDIUM, SamplingUtil.REQUEST_TYPE_Q));
					} else if (callNature.contentEquals(SamplingUtil.CALL_NATURE_AFTERNOON)
							&& callDuration.contentEquals(SamplingUtil.CALL_TYPE_MEDIUM)
							&& requestType.contentEquals(SamplingUtil.REQUEST_TYPE_R)) {
						callMixSamplingPer.setAfternoonMediumR(
								getPerOfCombination(totalCalls, samplingTemplate, SamplingUtil.CALL_NATURE_AFTERNOON,
										SamplingUtil.CALL_TYPE_MEDIUM, SamplingUtil.REQUEST_TYPE_R));
					} else if (callNature.contentEquals(SamplingUtil.CALL_NATURE_AFTERNOON)
							&& callDuration.contentEquals(SamplingUtil.CALL_TYPE_MEDIUM)
							&& requestType.contentEquals(SamplingUtil.REQUEST_TYPE_C)) {
						callMixSamplingPer.setAfternoonMediumC(
								getPerOfCombination(totalCalls, samplingTemplate, SamplingUtil.CALL_NATURE_AFTERNOON,
										SamplingUtil.CALL_TYPE_MEDIUM, SamplingUtil.REQUEST_TYPE_C));
					}

					// -------- Afternoon Short with all requestType
					if (callNature.contentEquals(SamplingUtil.CALL_NATURE_AFTERNOON)
							&& callDuration.contentEquals(SamplingUtil.CALL_TYPE_SHORT)
							&& requestType.contentEquals(SamplingUtil.REQUEST_TYPE_Q)) {
						callMixSamplingPer.setAfternoonShortQ(
								getPerOfCombination(totalCalls, samplingTemplate, SamplingUtil.CALL_NATURE_AFTERNOON,
										SamplingUtil.CALL_TYPE_SHORT, SamplingUtil.REQUEST_TYPE_Q));
					} else if (callNature.contentEquals(SamplingUtil.CALL_NATURE_AFTERNOON)
							&& callDuration.contentEquals(SamplingUtil.CALL_TYPE_SHORT)
							&& requestType.contentEquals(SamplingUtil.REQUEST_TYPE_R)) {
						callMixSamplingPer.setAfternoonShortR(
								getPerOfCombination(totalCalls, samplingTemplate, SamplingUtil.CALL_NATURE_AFTERNOON,
										SamplingUtil.CALL_TYPE_SHORT, SamplingUtil.REQUEST_TYPE_R));
					} else if (callNature.contentEquals(SamplingUtil.CALL_NATURE_AFTERNOON)
							&& callDuration.contentEquals(SamplingUtil.CALL_TYPE_SHORT)
							&& requestType.contentEquals(SamplingUtil.REQUEST_TYPE_C)) {
						callMixSamplingPer.setAfternoonShortC(
								getPerOfCombination(totalCalls, samplingTemplate, SamplingUtil.CALL_NATURE_AFTERNOON,
										SamplingUtil.CALL_TYPE_SHORT, SamplingUtil.REQUEST_TYPE_C));
					}

					// =============================================================================================================================
					// -------- Evening Long with all requestType
					if (callNature.contentEquals(SamplingUtil.CALL_NATURE_EVENING)
							&& callDuration.contentEquals(SamplingUtil.CALL_TYPE_LONG)
							&& requestType.contentEquals(SamplingUtil.REQUEST_TYPE_Q)) {
						callMixSamplingPer.setEveningLongQ(
								getPerOfCombination(totalCalls, samplingTemplate, SamplingUtil.CALL_NATURE_EVENING,
										SamplingUtil.CALL_TYPE_LONG, SamplingUtil.REQUEST_TYPE_Q));
					} else if (callNature.contentEquals(SamplingUtil.CALL_NATURE_EVENING)
							&& callDuration.contentEquals(SamplingUtil.CALL_TYPE_LONG)
							&& requestType.contentEquals(SamplingUtil.REQUEST_TYPE_R)) {
						callMixSamplingPer.setEveningLongR(
								getPerOfCombination(totalCalls, samplingTemplate, SamplingUtil.CALL_NATURE_EVENING,
										SamplingUtil.CALL_TYPE_LONG, SamplingUtil.REQUEST_TYPE_R));
					} else if (callNature.contentEquals(SamplingUtil.CALL_NATURE_EVENING)
							&& callDuration.contentEquals(SamplingUtil.CALL_TYPE_LONG)
							&& requestType.contentEquals(SamplingUtil.REQUEST_TYPE_C)) {
						callMixSamplingPer.setEveningLongC(
								getPerOfCombination(totalCalls, samplingTemplate, SamplingUtil.CALL_NATURE_EVENING,
										SamplingUtil.CALL_TYPE_LONG, SamplingUtil.REQUEST_TYPE_C));
					}

					// -------- Evening Medium with all requestType
					if (callNature.contentEquals(SamplingUtil.CALL_NATURE_EVENING)
							&& callDuration.contentEquals(SamplingUtil.CALL_TYPE_MEDIUM)
							&& requestType.contentEquals(SamplingUtil.REQUEST_TYPE_Q)) {
						callMixSamplingPer.setEveningMediumQ(
								getPerOfCombination(totalCalls, samplingTemplate, SamplingUtil.CALL_NATURE_EVENING,
										SamplingUtil.CALL_TYPE_MEDIUM, SamplingUtil.REQUEST_TYPE_Q));
					} else if (callNature.contentEquals(SamplingUtil.CALL_NATURE_EVENING)
							&& callDuration.contentEquals(SamplingUtil.CALL_TYPE_MEDIUM)
							&& requestType.contentEquals(SamplingUtil.REQUEST_TYPE_R)) {
						callMixSamplingPer.setEveningMediumR(
								getPerOfCombination(totalCalls, samplingTemplate, SamplingUtil.CALL_NATURE_EVENING,
										SamplingUtil.CALL_TYPE_MEDIUM, SamplingUtil.REQUEST_TYPE_R));
					} else if (callNature.contentEquals(SamplingUtil.CALL_NATURE_EVENING)
							&& callDuration.contentEquals(SamplingUtil.CALL_TYPE_MEDIUM)
							&& requestType.contentEquals(SamplingUtil.REQUEST_TYPE_C)) {
						callMixSamplingPer.setEveningMediumC(
								getPerOfCombination(totalCalls, samplingTemplate, SamplingUtil.CALL_NATURE_EVENING,
										SamplingUtil.CALL_TYPE_MEDIUM, SamplingUtil.REQUEST_TYPE_C));
					}

					// -------- Evening Short with all requestType
					if (callNature.contentEquals(SamplingUtil.CALL_NATURE_EVENING)
							&& callDuration.contentEquals(SamplingUtil.CALL_TYPE_SHORT)
							&& requestType.contentEquals(SamplingUtil.REQUEST_TYPE_Q)) {
						callMixSamplingPer.setEveningShortQ(
								getPerOfCombination(totalCalls, samplingTemplate, SamplingUtil.CALL_NATURE_EVENING,
										SamplingUtil.CALL_TYPE_SHORT, SamplingUtil.REQUEST_TYPE_Q));
					} else if (callNature.contentEquals(SamplingUtil.CALL_NATURE_EVENING)
							&& callDuration.contentEquals(SamplingUtil.CALL_TYPE_SHORT)
							&& requestType.contentEquals(SamplingUtil.REQUEST_TYPE_R)) {
						callMixSamplingPer.setEveningShortR(
								getPerOfCombination(totalCalls, samplingTemplate, SamplingUtil.CALL_NATURE_EVENING,
										SamplingUtil.CALL_TYPE_SHORT, SamplingUtil.REQUEST_TYPE_R));
					} else if (callNature.contentEquals(SamplingUtil.CALL_NATURE_EVENING)
							&& callDuration.contentEquals(SamplingUtil.CALL_TYPE_SHORT)
							&& requestType.contentEquals(SamplingUtil.REQUEST_TYPE_C)) {
						callMixSamplingPer.setEveningShortC(
								getPerOfCombination(totalCalls, samplingTemplate, SamplingUtil.CALL_NATURE_EVENING,
										SamplingUtil.CALL_TYPE_SHORT, SamplingUtil.REQUEST_TYPE_C));
					}

					// =============================================================================================================================
					// -------- Night Long with all requestType
					if (callNature.contentEquals(SamplingUtil.CALL_NATURE_NIGHT)
							&& callDuration.contentEquals(SamplingUtil.CALL_TYPE_LONG)
							&& requestType.contentEquals(SamplingUtil.REQUEST_TYPE_Q)) {
						callMixSamplingPer.setNightLongQ(
								getPerOfCombination(totalCalls, samplingTemplate, SamplingUtil.CALL_NATURE_NIGHT,
										SamplingUtil.CALL_TYPE_LONG, SamplingUtil.REQUEST_TYPE_Q));
					} else if (callNature.contentEquals(SamplingUtil.CALL_NATURE_NIGHT)
							&& callDuration.contentEquals(SamplingUtil.CALL_TYPE_LONG)
							&& requestType.contentEquals(SamplingUtil.REQUEST_TYPE_R)) {
						callMixSamplingPer.setNightLongR(
								getPerOfCombination(totalCalls, samplingTemplate, SamplingUtil.CALL_NATURE_NIGHT,
										SamplingUtil.CALL_TYPE_LONG, SamplingUtil.REQUEST_TYPE_R));
					} else if (callNature.contentEquals(SamplingUtil.CALL_NATURE_NIGHT)
							&& callDuration.contentEquals(SamplingUtil.CALL_TYPE_LONG)
							&& requestType.contentEquals(SamplingUtil.REQUEST_TYPE_C)) {
						callMixSamplingPer.setNightLongC(
								getPerOfCombination(totalCalls, samplingTemplate, SamplingUtil.CALL_NATURE_NIGHT,
										SamplingUtil.CALL_TYPE_LONG, SamplingUtil.REQUEST_TYPE_C));
					}

					// -------- Night Medium with all requestType
					if (callNature.contentEquals(SamplingUtil.CALL_NATURE_NIGHT)
							&& callDuration.contentEquals(SamplingUtil.CALL_TYPE_MEDIUM)
							&& requestType.contentEquals(SamplingUtil.REQUEST_TYPE_Q)) {
						callMixSamplingPer.setNightMediumQ(
								getPerOfCombination(totalCalls, samplingTemplate, SamplingUtil.CALL_NATURE_NIGHT,
										SamplingUtil.CALL_TYPE_MEDIUM, SamplingUtil.REQUEST_TYPE_Q));
					} else if (callNature.contentEquals(SamplingUtil.CALL_NATURE_NIGHT)
							&& callDuration.contentEquals(SamplingUtil.CALL_TYPE_MEDIUM)
							&& requestType.contentEquals(SamplingUtil.REQUEST_TYPE_R)) {
						callMixSamplingPer.setNightMediumR(
								getPerOfCombination(totalCalls, samplingTemplate, SamplingUtil.CALL_NATURE_NIGHT,
										SamplingUtil.CALL_TYPE_MEDIUM, SamplingUtil.REQUEST_TYPE_R));
					} else if (callNature.contentEquals(SamplingUtil.CALL_NATURE_NIGHT)
							&& callDuration.contentEquals(SamplingUtil.CALL_TYPE_MEDIUM)
							&& requestType.contentEquals(SamplingUtil.REQUEST_TYPE_C)) {
						callMixSamplingPer.setNightMediumC(
								getPerOfCombination(totalCalls, samplingTemplate, SamplingUtil.CALL_NATURE_NIGHT,
										SamplingUtil.CALL_TYPE_MEDIUM, SamplingUtil.REQUEST_TYPE_C));
					}

					// -------- Night Short with all requestType
					if (callNature.contentEquals(SamplingUtil.CALL_NATURE_NIGHT)
							&& callDuration.contentEquals(SamplingUtil.CALL_TYPE_SHORT)
							&& requestType.contentEquals(SamplingUtil.REQUEST_TYPE_Q)) {
						callMixSamplingPer.setNightShortQ(
								getPerOfCombination(totalCalls, samplingTemplate, SamplingUtil.CALL_NATURE_NIGHT,
										SamplingUtil.CALL_TYPE_SHORT, SamplingUtil.REQUEST_TYPE_Q));
					} else if (callNature.contentEquals(SamplingUtil.CALL_NATURE_NIGHT)
							&& callDuration.contentEquals(SamplingUtil.CALL_TYPE_SHORT)
							&& requestType.contentEquals(SamplingUtil.REQUEST_TYPE_R)) {
						callMixSamplingPer.setNightShortR(
								getPerOfCombination(totalCalls, samplingTemplate, SamplingUtil.CALL_NATURE_NIGHT,
										SamplingUtil.CALL_TYPE_SHORT, SamplingUtil.REQUEST_TYPE_R));
					} else if (callNature.contentEquals(SamplingUtil.CALL_NATURE_NIGHT)
							&& callDuration.contentEquals(SamplingUtil.CALL_TYPE_SHORT)
							&& requestType.contentEquals(SamplingUtil.REQUEST_TYPE_C)) {
						callMixSamplingPer.setNightShortC(
								getPerOfCombination(totalCalls, samplingTemplate, SamplingUtil.CALL_NATURE_NIGHT,
										SamplingUtil.CALL_TYPE_SHORT, SamplingUtil.REQUEST_TYPE_C));
					}
				}
			}
		}

		return callMixSamplingPer;
	}

	private static Double getPerOfCombination(int totalCalls, SamplingTemplate samplingTemplate, String callNature,
			String callType, String requestType) {
		Double val = 0.0;
		val = (((totalCalls * (SamplingUtil.getCallMixValue(samplingTemplate, callNature) / 100)))
				* (SamplingUtil.getCallMixValue(samplingTemplate, callType) / 100))
				* (SamplingUtil.getCallMixValue(samplingTemplate, requestType) / 100);

		return val;
	}

	public static void main(String[] args) {
		List<String> l = new ArrayList<String>();
		l.add("a0");
		l.add("a1");
		l.add("a2");
		l.add("a3");
		l.add("a4");
		l.add("a5");
		l.add("a6");
		l.add("a7");
		l.add("a8");
		l.add("a9");
		Collections.shuffle(l);

		l = l.stream().filter(d -> d == "a0" || d == "a1").collect(Collectors.toList());
		// System.out.println(l);

		List<String> l1 = l.stream().limit(4).collect(Collectors.toList());
		// System.out.println(l1);

		System.out.println(1235 % 100);

		double a = 122.2;
		long b = (long) a;
		System.out.println(b);
	}

}
