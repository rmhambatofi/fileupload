package fr.manitra.fileupload.resources;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;

import fr.manitra.fileupload.api.FileData;

@Path("/utils")
@Produces(MediaType.APPLICATION_JSON)
public class FileUploadResource {
	
	private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public FileUploadResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    public FileData sayHello(@QueryParam("name") Optional<String> name) {
        final String value = String.format(template, name.orElse(defaultName));
        return new FileData("" + counter.incrementAndGet(), value);
    }
}
