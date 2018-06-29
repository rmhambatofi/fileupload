package fr.manitra.fileupload.core;

public class FileInfo {
	
	private String fileName;
	private String mimeType;
	private String path;
	
	public FileInfo() {
	}
	
	public FileInfo(String fileName, String path, String mimeType) {
		this.fileName = fileName;
		this.mimeType = mimeType;
		this.path = path;
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
	
	public FileInfo withFileName(String fileName) {
		return fileName != null && fileName.equals(getFileName()) ? this : new FileInfo(fileName, getPath(), getMimeType());
	}
}
