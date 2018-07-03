package fr.manitra.fileupload.resources;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.glassfish.jersey.media.multipart.BodyPartEntity;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.manitra.fileupload.core.FileInfo;
import fr.manitra.fileupload.crypto.CipherMode;
import fr.manitra.fileupload.crypto.CryptoHandler;
import fr.manitra.fileupload.exception.FileUploadException;
import fr.manitra.fileupload.views.FilesView;
import fr.manitra.fileupload.views.FilesView.Template;

@Path("/files")
@Produces(MediaType.APPLICATION_JSON)
public class FileResource {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FileResource.class);
	
	private final String backupPath;
	private final String cipherPass;
	
	public FileResource(String backupPath, String cipherPass) {
		this.backupPath = backupPath;
		this.cipherPass = cipherPass;
	}

	@GET
    @Path("/")
    @Produces(MediaType.TEXT_HTML)
    public FilesView getPersonViewFreemarker() {
		return new FilesView(Template.FREEMARKER, getFiles());
    }
	
	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.TEXT_HTML)
	public FilesView upload(@FormDataParam("files") List<FormDataBodyPart> bodyParts, 
			@FormDataParam("files") FormDataContentDisposition fileDispositions) {
		
		if (StringUtils.isBlank(cipherPass)) {
			throw new FileUploadException("Password cannot be null!");
		}
		
		if (bodyParts != null && !bodyParts.isEmpty()) {
			bodyParts.forEach(bodyPart -> processBackup(cipherPass, bodyPart));
		}
		
		return new FilesView(Template.FREEMARKER, getFiles());
	}

	private List<FileInfo> getFiles() {
		File backupDir = new File(backupPath);
		
		LOGGER.info("Reading directory [{}] ", backupDir);
		
		if (!backupDir.isDirectory()) {
			throw new FileUploadException("Backup directory must be a directory");
		}
		
		File[] files = backupDir.listFiles();
		if (files != null && files.length > 0) {
			List<FileInfo> fileList = Stream.of(files)
					.filter(f -> !f.isHidden())
					.map(this::buildFileInfo)
					.collect(Collectors.toList());
			
			LOGGER.info("{} files found.", fileList.size());
			return fileList;
		}
		
		LOGGER.info("Backup directory is empty!");
		return new ArrayList<>(0);
	}
	
	private FileInfo buildFileInfo(File file) {
		BasicFileAttributes attributes;
		try {
			attributes = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(attributes.creationTime().toMillis());
			return new FileInfo(file.getName(), file.length(), cal.getTime());
		} catch (IOException e) {
			LOGGER.warn("Unable to read attributes for file [{}] ", file.getName());
		}
		return new FileInfo(file.getName(), file.length(), null);
	}
	
	private void saveFile(InputStream inputStream, String fileName, String password) throws Exception {
		
		StringBuilder pathName = new StringBuilder(this.backupPath).append("/").append(fileName);
		java.nio.file.Path path = FileSystems.getDefault().getPath(pathName.toString());
		
		CryptoHandler cryptoHandler = new CryptoHandler();
		byte[] encrypted = cryptoHandler.processFile(CipherMode.ENCRYPT, password, inputStream);
		inputStream.close();
		
		ByteArrayInputStream in = new ByteArrayInputStream(encrypted);
		Files.copy(in, path, StandardCopyOption.REPLACE_EXISTING);
	}
	
	private void processBackup(String password, FormDataBodyPart bodyPart) {
		String fileName = bodyPart.getContentDisposition().getFileName();
		BodyPartEntity bodyPartEntity = (BodyPartEntity) bodyPart.getEntity();
		try {
			saveFile(bodyPartEntity.getInputStream(), fileName, password);
			LOGGER.info("[{}] saved at [{}]", fileName, this.backupPath);
		} catch (Exception e) {
			throw new FileUploadException(e.getMessage(), e);
		}
	}
}
