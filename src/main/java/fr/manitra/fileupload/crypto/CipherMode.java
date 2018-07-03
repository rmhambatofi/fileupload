package fr.manitra.fileupload.crypto;

import javax.crypto.Cipher;

public enum CipherMode {
	
	ENCRYPT(Cipher.ENCRYPT_MODE), DECRYPT(Cipher.ENCRYPT_MODE);
	
	private int mode;
	
	private CipherMode(int mode) {
		this.mode = mode;
	}

	public int getMode() {
		return mode;
	}
}
