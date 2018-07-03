package fr.manitra.fileupload.crypto;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class CryptoHandler {
	
	public byte[] processFile(CipherMode cipherMode, String pass, InputStream inputStream) throws Exception {
		
		// Generating the AES Key
		byte[] salt = new byte[8];
		SecureRandom srandom = new SecureRandom(salt);
		srandom.nextBytes(salt);
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
		KeySpec spec = new PBEKeySpec(pass.toCharArray(), salt, 10000, 128);
		SecretKey tmp = factory.generateSecret(spec);
		SecretKeySpec skey = new SecretKeySpec(tmp.getEncoded(), "AES");
		
		// Generate the Initialization Vector 
		byte[] iv = new byte[128/8];
		srandom.nextBytes(iv);
		IvParameterSpec ivspec = new IvParameterSpec(iv);
		
		// Processing the File
		Cipher ci = Cipher.getInstance("AES/CBC/PKCS5Padding");
		ci.init(cipherMode.getMode(), skey, ivspec);
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		byte[] ibuf = new byte[1024];
	    int len;
	    while ((len = inputStream.read(ibuf)) != -1) {
	        byte[] obuf = ci.update(ibuf, 0, len);
	        if ( obuf != null ) {
	        	out.write(obuf);
	        }
	    }
	    byte[] obuf = ci.doFinal();
	    if ( obuf != null ) {
	    	out.write(obuf);
	    }
		
	    return obuf;
		
//       Key secretKey = new SecretKeySpec(pass.getBytes(), "AES");
//       Cipher cipher = Cipher.getInstance("AES");
//       cipher.init(cipherMode.getMode(), secretKey);
//
//       ByteArrayOutputStream buffer = new ByteArrayOutputStream();
//       int nRead;
//       byte[] data = new byte[1024];
//       while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
//           buffer.write(data, 0, nRead);
//       }
//       buffer.flush();
//       
//       byte[] inputBytes = buffer.toByteArray();
//       byte[] outputBytes = cipher.doFinal(inputBytes);
//     
//       inputStream.close();
//       return outputBytes;
	}
}
