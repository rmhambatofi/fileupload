package fr.manitra.fileupload;

import fr.manitra.fileupload.resources.FileUploadResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class FileUploadApplication extends Application<FileUploadConfiguration> {

    public static void main(final String[] args) throws Exception {
        new FileUploadApplication().run(args);
    }

    @Override
    public String getName() {
        return "Upload and download file application";
    }

    @Override
    public void initialize(final Bootstrap<FileUploadConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final FileUploadConfiguration configuration,
                    final Environment environment) {
    	
    	final FileUploadResource resource = new FileUploadResource(configuration.getTemplate(), configuration.getDefaultName());
    	environment.jersey().register(resource);
    }

}
