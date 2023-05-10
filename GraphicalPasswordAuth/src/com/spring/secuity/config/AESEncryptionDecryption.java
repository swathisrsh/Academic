package com.spring.secuity.config;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AESEncryptionDecryption {

	private static SecretKeySpec secretKey;
	private static byte[] key;

	private static String aesKey = "NYITProj";

	public static void setKey(final String aesKey) {
		MessageDigest sha = null;
		try {
			key = aesKey.getBytes("UTF-8");
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16);
			secretKey = new SecretKeySpec(key, "AES");
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public static String encrypt(final String strToEncrypt) {
		try {
			setKey(aesKey);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
		} catch (Exception e) {
			System.out.println("Error while encrypting: " + e.toString());
		}
		return null;
	}

	public static String decrypt(final String strToDecrypt) {
		try {
			setKey(aesKey);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
		} catch (Exception e) {
			System.out.println("Error while decrypting: " + e.toString());
		}
		return null;
	}

	public static byte[] encryptPdfFile(byte[] content) {
		Cipher cipher;
		byte[] encrypted = null;
		try {
			setKey(aesKey);
			cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			encrypted = cipher.doFinal(content);
		} catch (Exception e) {

			System.out.println("Error while encrypting: " + e.toString());
		}
		return encrypted;

	}

	public static byte[] decryptPdfFile(byte[] textCryp) {
		Cipher cipher;
		byte[] decrypted = null;
		try {
			setKey(aesKey);
			cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			decrypted = cipher.doFinal(textCryp);

		} catch (Exception e) {

			System.out.println("Error while decrypting: " + e.toString());
		}
		return decrypted;
	}
}
