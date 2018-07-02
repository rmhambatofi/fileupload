package fr.manitra.fileupload.exception;

public class FileUploadException extends RuntimeException {

	private static final long serialVersionUID = 464716813559329948L;
	
	public FileUploadException(String message) {
		super(message);
	}
	
	public FileUploadException(String message, Throwable th) {
		super(message, th);
	}
}
