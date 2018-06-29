package fr.manitra.fileupload.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.common.collect.Lists;

import fr.manitra.fileupload.core.FileInfo;
import fr.manitra.fileupload.views.FilesView;
import fr.manitra.fileupload.views.FilesView.Template;

@Path("/files")
@Produces(MediaType.APPLICATION_JSON)
public class FileResource {
	
	@GET
    @Path("/")
    @Produces(MediaType.TEXT_HTML)
    public FilesView getPersonViewFreemarker() {
		return new FilesView(Template.FREEMARKER, getFiles());
    }

	private List<FileInfo> getFiles() {
		ArrayList<FileInfo> fileList = Lists.newArrayList();
		fileList.add(new FileInfo("File 1", "/home/myuser/backup", ""));
		fileList.add(new FileInfo("File 2", "/home/myuser/backup", ""));
		fileList.add(new FileInfo("File 3", "/home/myuser/backup", ""));
		fileList.add(new FileInfo("File 4", "/home/myuser/backup", ""));
		return fileList;
	}
}
