package fr.manitra.fileupload.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FileData {
	
	@JsonProperty
	private String name;
	
	@JsonProperty
	private String mimeType;
	
	public FileData() {
	}
	
	public FileData(String name, String mimeType) {
		super();
		this.name = name;
		this.mimeType = mimeType;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getMimeType() {
		return mimeType;
	}
	
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
}
