package fr.manitra.fileupload.crypto;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CryptoHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(CryptoHandler.class);
	
	public void encryptFile(String pass, InputStream inputStream, String fileName, OutputStream output) throws Exception {
		LOGGER.info("Encrypting file: [{}] ... ", fileName);
		Key secretKey = new SecretKeySpec(pass.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		
	    CipherOutputStream encryptedOutput = new CipherOutputStream(output, cipher);
		copy(inputStream, encryptedOutput);
		
		encryptedOutput.close();
		inputStream.close();
		output.close();
		
		LOGGER.info("[{}] encrypted! ", fileName);
	}
	
	public ByteArrayOutputStream decryptFile(String pass, String fileName) throws Exception {
		LOGGER.info("Decrypting file: [{}] ... ", fileName);
		FileInputStream input = new FileInputStream(fileName);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		Key secretKey = new SecretKeySpec(pass.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, secretKey);

		CipherInputStream is = new CipherInputStream(input, cipher);
		copy(is, output);
		
		is.close();
		input.close();
		
		LOGGER.info("[{}] decrypted", fileName);
		
		return output;
	}

	private void copy(InputStream is, OutputStream os) throws IOException {
		int i;
		byte[] b = new byte[1024];
		while((i=is.read(b))!=-1) {
			os.write(b, 0, i);
		}
	}
}
