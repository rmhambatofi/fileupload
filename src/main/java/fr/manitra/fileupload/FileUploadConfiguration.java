package fr.manitra.fileupload;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.*;

import java.util.Collections;
import java.util.Map;

import javax.validation.constraints.*;

public class FileUploadConfiguration extends Configuration {
	
	@NotEmpty
    private String backupPath;
    
    @NotNull
    private Map<String, Map<String, String>> viewRendererConfiguration = Collections.emptyMap();
    
    @JsonProperty("viewRendererConfiguration")
    public Map<String, Map<String, String>> getViewRendererConfiguration() {
        return viewRendererConfiguration;
    }

    @JsonProperty("viewRendererConfiguration")
    public void setViewRendererConfiguration(Map<String, Map<String, String>> viewRendererConfiguration) {
        this.viewRendererConfiguration = viewRendererConfiguration;
    }

    @JsonProperty("backupPath")
	public String getBackupPath() {
		return backupPath;
	}

    @JsonProperty("backupPath")
	public void setBackupPath(String backupPath) {
		this.backupPath = backupPath;
	}
}
