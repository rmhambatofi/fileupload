package fr.manitra.fileupload.resources;

import java.io.IOException;
import java.io.OutputStream;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;

public class FileOutput implements StreamingOutput {
	
	private final byte[] data;
	
	public FileOutput(byte[] data) {
		this.data = data;
	}

	@Override
	public void write(OutputStream output) throws IOException, WebApplicationException {
        output.write(data);
        output.flush();
	}
}
