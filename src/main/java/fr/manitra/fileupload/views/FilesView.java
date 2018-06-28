package fr.manitra.fileupload.views;

import java.util.List;

import fr.manitra.fileupload.core.FileInfo;
import io.dropwizard.views.View;

public class FilesView extends View {

	private List<FileInfo> fileInfos;
	
	public enum Template {
        
		FREEMARKER("freemarker/files.ftl"),
        MUSTACHE("mustache/files.mustache");

        private String templateName;

        Template(String templateName) {
            this.templateName = templateName;
        }

        public String getTemplateName() {
            return templateName;
        }
    }

    public FilesView(FilesView.Template template, List<FileInfo> fileInfos) {
        super(template.getTemplateName());
        this.fileInfos = fileInfos;
    }

    public List<FileInfo> getFileInfos() {
        return fileInfos;
    }
}
