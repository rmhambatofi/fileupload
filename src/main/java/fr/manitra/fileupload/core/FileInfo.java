package fr.manitra.fileupload.core;

import java.util.Date;

public class FileInfo {
	
	private String fileName;
	private String mimeType;
	private String path;
	private Long size;
	private Date uploadDate;
	
	public FileInfo() {
	}
	
	public FileInfo(String fileName, Long size, Date uploadDate) {
		this.fileName = fileName;
		this.size = size;
		this.uploadDate = uploadDate;
	}

	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getMimeType() {
		return mimeType;
	}
	
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public FileInfo withFileName(String fileName) {
		this.setFileName(fileName);
		return this;
	}
	
	public FileInfo withPath(String path) {
		this.setPath(path);
		return this;
	}
	
	public FileInfo withMimeType(String mimeType) {
		this.setMimeType(mimeType);
		return this;
	}
	
	public FileInfo withSize(Long size) {
		this.setSize(size);
		return this;
	}
	
	public FileInfo withUploadDate(Date uploadDate) {
		this.setUploadDate(uploadDate);
		return this;
	}
}
