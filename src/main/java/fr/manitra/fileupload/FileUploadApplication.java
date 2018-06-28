package fr.manitra.fileupload;

import fr.manitra.fileupload.health.TemplateHealthCheck;
import fr.manitra.fileupload.resources.FileUploadResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

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
        bootstrap.addBundle(new ViewBundle<>());
    }

    @Override
    public void run(final FileUploadConfiguration configuration,
                    final Environment environment) {
    	
    	final FileUploadResource resource = new FileUploadResource(configuration.getTemplate(), configuration.getDefaultName());
    	environment.jersey().register(resource);
    	
    	final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
    	environment.healthChecks().register("template", healthCheck);
    	environment.jersey().register(resource);
    }

}
