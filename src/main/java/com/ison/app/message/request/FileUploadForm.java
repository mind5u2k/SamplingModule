package com.ison.app.message.request;

import org.springframework.web.multipart.MultipartFile;
 
public class FileUploadForm {
    private MultipartFile file;
    public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getSubModuleName() {
		return subModuleName;
	}
	public void setSubModuleName(String subModuleName) {
		this.subModuleName = subModuleName;
	}
	private String moduleName;
    private String subModuleName;
}

