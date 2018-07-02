package fr.manitra.fileupload;

import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.spi.ExtendedExceptionMapper;

import com.github.mustachejava.MustacheNotFoundException;
import com.google.common.base.Throwables;

import fr.manitra.fileupload.health.FileuploadHealthCheck;
import fr.manitra.fileupload.resources.FileResource;
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
    	bootstrap.addBundle(new ViewBundle<FileUploadConfiguration>() {
            @Override
            public Map<String, Map<String, String>> getViewConfiguration(FileUploadConfiguration configuration) {
                return configuration.getViewRendererConfiguration();
            }
        });
    }

    @Override
    public void run(final FileUploadConfiguration configuration, final Environment environment) {
    	
    	final FileResource fileResource = new FileResource(configuration.getBackupPath());
    	environment.jersey().register(fileResource);
    	
    	final FileuploadHealthCheck healthCheck = new FileuploadHealthCheck(configuration.getBackupPath());
    	environment.healthChecks().register("template", healthCheck);
    	
    	environment.jersey().register(new ExtendedExceptionMapper<WebApplicationException>() {
    	    @Override
    	    public Response toResponse(WebApplicationException exception) {
    	        return Response.status(Response.Status.NOT_FOUND).build();
    	    }

    	    @Override
    	    public boolean isMappable(WebApplicationException e) {
    	        return Throwables.getRootCause(e).getClass() == MustacheNotFoundException.class;
    	    }
    	});
    }

}
