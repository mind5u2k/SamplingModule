package com.ison.app.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ison.app.message.response.GenericResponse;
import com.ison.app.model.SamplingMapping;
import com.ison.app.model.SamplingTemplate;
import com.ison.app.services.SamplingService;
import com.ison.app.util.SamplingUtil;

@RestController
@RequestMapping("/sampling")
public class SamplingRestController {

	@Autowired
	SamplingService samplingService;

	@RequestMapping(value = "/templates", method = RequestMethod.GET, produces = { "application/json" })
	public List<SamplingTemplate> templates(HttpServletRequest request) throws Exception {
		return samplingService.getTemplate();
	}

	@RequestMapping(value = "/SaveTemplate", method = RequestMethod.POST, consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<GenericResponse> saveTemplate(@RequestBody String template) {
		GenericResponse genericResponse = null;
		boolean st = true;
		try {
			SamplingTemplate samplingTemplate = SamplingUtil.getSamplingTamplate(template);
			SamplingTemplate st1 = samplingService.addNewTemplate(samplingTemplate);
			genericResponse = new GenericResponse();
			genericResponse.setStatus(200);
			genericResponse.setError("Success");
			genericResponse.setMessage(String.valueOf(samplingTemplate.getId()));
			genericResponse.setValue(String.valueOf(st1.getId()));
			return ResponseEntity.ok(new GenericResponse(genericResponse));
		} catch (Exception e) {
			e.printStackTrace();
			genericResponse = new GenericResponse();
			genericResponse.setStatus(500);
			genericResponse.setError(e.getMessage());
			genericResponse.setMessage("Getting Error while saving template");
			genericResponse.setValue(String.valueOf(st));
			return ResponseEntity.ok(new GenericResponse(genericResponse));
		}
	}

	@RequestMapping(value = "/samplingMappings", method = RequestMethod.GET, produces = { "application/json" })
	public List<SamplingMapping> getSamplingMappings(HttpServletRequest request) throws Exception {
		return samplingService.getSamplingMappings();
	}

	@RequestMapping(value = "/saveSamplingMapping", method = RequestMethod.POST, consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<GenericResponse> saveSamplingMapping(@RequestBody String input) {
		GenericResponse genericResponse = new GenericResponse();

		try {
			SamplingMapping samplingMapping = SamplingUtil.getSamplingMapping(input);
			samplingService.saveSamplingMapping(samplingMapping);
			genericResponse = new GenericResponse();
			genericResponse.setStatus(200);
			genericResponse.setError("Success");
			return ResponseEntity.ok(new GenericResponse(genericResponse));
		} catch (Exception e) {
			return new ResponseEntity<GenericResponse>(new GenericResponse(genericResponse), HttpStatus.BAD_REQUEST);
		}

	}

}
