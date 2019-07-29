package com.ison.app.controller;

import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ison.app.message.request.FormBuilder;
import com.ison.app.message.response.GenericResponse;
import com.ison.app.services.FormBuilderService;
import com.ison.app.shared.dto.FormBuilderDto;

@RestController
@RequestMapping("/formBuilder")
public class FormBuilderController {

	@Autowired
	FormBuilderService formService;

	@PostMapping("/createForm")
	public ResponseEntity<GenericResponse> insertFormDetails(@RequestBody String input) throws Exception {
		GenericResponse genericResponse = null;
		try {
			JSONObject jsonObj = new JSONObject(input);
			FormBuilder formBuilder = new FormBuilder();
			formBuilder.setTemplateKey(jsonObj.getString("key"));
			formBuilder.setTemplateValue(jsonObj.getJSONObject("value").toString());
			formBuilder.setTitle(jsonObj.getString("title"));
			formBuilder.setStatus(jsonObj.getString("status"));
			if (formService.templateNameExists(formBuilder.getTitle())) {
				genericResponse = new GenericResponse();
				genericResponse.setStatus(HttpStatus.BAD_REQUEST.value());
				genericResponse.setError("Failure");
				genericResponse.setMessage("Failure -> Template Name is already Exists!");
				return new ResponseEntity<GenericResponse>(new GenericResponse(genericResponse), HttpStatus.OK);
			}
			FormBuilderDto formBuilderDto = new FormBuilderDto();
			BeanUtils.copyProperties(formBuilder, formBuilderDto);
			formBuilderDto = formService.insertFormDetails(formBuilderDto);
			genericResponse = new GenericResponse();
			if (!formBuilderDto.getAutogenFormId().isEmpty()) {
				genericResponse.setStatus(1007);
				genericResponse.setError("Success");
				genericResponse.setMessage("New Template Created!.");
				genericResponse.setValue(formBuilderDto);
				return ResponseEntity.ok(new GenericResponse(genericResponse));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@PostMapping("/update")
	public ResponseEntity<GenericResponse> updateFormTemplate(@RequestBody String input) throws Exception {
		JSONObject jsonObj = new JSONObject(input);
		FormBuilder formBuilder = new FormBuilder();
		formBuilder.setTemplateKey(jsonObj.getString("key"));
		formBuilder.setTemplateValue(jsonObj.getJSONObject("value").toString());
		formBuilder.setTitle(jsonObj.getString("title"));
		formBuilder.setStatus(jsonObj.getString("status"));
		formBuilder.setAutogenFormId(jsonObj.getString("id"));
		FormBuilderDto formBuilderDto = new FormBuilderDto();
		BeanUtils.copyProperties(formBuilder, formBuilderDto);
		boolean updateStatus = formService.updateFormTemplate(formBuilderDto);
		GenericResponse genericResponse = null;
		if (updateStatus) {
			genericResponse = new GenericResponse();
			genericResponse.setStatus(1005);
			genericResponse.setError("Success");
			genericResponse.setMessage("Success -> Template Updated successfully!");
		}
		return ResponseEntity.ok(new GenericResponse(genericResponse));
	}

}
