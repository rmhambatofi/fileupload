package fr.manitra.fileupload.health;

import com.codahale.metrics.health.HealthCheck;

public class FileuploadHealthCheck extends HealthCheck {
	
	private final String backupPath;

    public FileuploadHealthCheck(String backupPath) {
        this.backupPath = backupPath;
    }

    @Override
    protected Result check() throws Exception {
        final String path = String.format(backupPath, "TEST");
        if (!path.contains("TEST")) {
            return Result.unhealthy("Backup path doesn't not specified!");
        }
        return Result.healthy();
    }
}
