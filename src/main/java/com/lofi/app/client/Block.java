package com.lofi.app.client;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Block {

	private static Logger logger = Logger.getLogger(Block.class.getName());

	private static String HEXES = "0123456789ABCDEF";

	private String hash;
	private String previousHash;
	private String data;
	private long timeStamp;
	private int nonce;

	public Block(String data, String previousHash, long timeStamp) {
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = timeStamp;
		this.hash = calculateBlockHash();
	}

	public String mineBlock(int prefix) {
		String prefixString = new String(new char[prefix]).replace('\0', '0');
		while (!hash.substring(0, prefix).equals(prefixString)) {
			nonce++;
			hash = calculateBlockHash();
		}
		return hash;
	}

	public String calculateBlockHash() {
		String dataToHash = previousHash + Long.toString(timeStamp) + Integer.toString(nonce) + data;
		MessageDigest digest = null;
		byte[] bytes = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			bytes = digest.digest(dataToHash.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
			logger.log(Level.SEVERE, ex.getMessage());
		}

		StringBuffer buffer = new StringBuffer();

		formatByteOfArrayToHexWithChar(bytes, buffer);


		logger.info("Result completed: " + buffer.toString());
		return buffer.toString();
	}

    void formatByteOfArrayToHexWithChar(byte[] bytes, StringBuffer buffer) {
    	// Get this from StackOverflow: 
    	// https://stackoverflow.com/questions/2817752/java-code-to-convert-byte-to-hexadecimal
		for (int i = 0; i < bytes.length; i++) {
			buffer.append(Character.forDigit((bytes[i] >> 4) & 0xF, 16));
			buffer.append(Character.forDigit((bytes[i] & 0xF), 16));
		}
	}
    
    void formatByteOfArrayToHexWithStringFormat(byte[] bytes, StringBuffer buffer) {
		for (byte b : bytes) {
			String formatContent = formatToHex(b);
			buffer.append(formatContent);
		}
	}

	String formatToHex(byte b) {
		// This String method does not work in GWT, because
		// it is not implemented, 
		// see https://groups.google.com/g/google-web-toolkit/c/-tYZuNh4nLI/m/5VMZsadsHb0J
		// String formatContent = String.format("%02x", b);
		String formatContent = Integer.toHexString(b & 0xFF);
		if (formatContent.length() == 1) {
			formatContent = "0".concat(formatContent);
		}
		return formatContent;
	}

	String getHex(byte[] raw) {
		final StringBuilder hex = new StringBuilder(2 * raw.length);
		
		for (final byte b : raw) {
			hex.append(HEXES.charAt((b & 0xF0) >> 4)).append(HEXES.charAt((b & 0x0F)));
		}
		return hex.toString();
	}

	public String getHash() {
		return this.hash;
	}

	public String getPreviousHash() {
		return this.previousHash;
	}

	public void setData(String data) {
		this.data = data;
	}
}
