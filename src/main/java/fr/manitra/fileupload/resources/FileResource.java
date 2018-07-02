package fr.manitra.fileupload.resources;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.manitra.fileupload.core.FileInfo;
import fr.manitra.fileupload.exception.FileUploadException;
import fr.manitra.fileupload.views.FilesView;
import fr.manitra.fileupload.views.FilesView.Template;

@Path("/files")
@Produces(MediaType.APPLICATION_JSON)
public class FileResource {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FileResource.class);
	
	private final String backupPath;
	
	public FileResource(String backupPath) {
		this.backupPath = backupPath;
	}

	@GET
    @Path("/")
    @Produces(MediaType.TEXT_HTML)
    public FilesView getPersonViewFreemarker() {
		return new FilesView(Template.FREEMARKER, getFiles());
    }

	private List<FileInfo> getFiles() {
		File backupDir = new File(backupPath);
		
		LOGGER.info("Reading directory {} ", backupDir);
		
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
			LOGGER.warn("Unable to read attributes for file {} ", file.getName());
		}
		return new FileInfo(file.getName(), file.length(), null);
	}
}
