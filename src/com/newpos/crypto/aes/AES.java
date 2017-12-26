package com.newpos.crypto.aes;

import java.util.*;
import javax.crypto.*;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;

import com.newpos.crypto.interfaces.*;
import com.newpos.crypto.format.HexToString;

//import com.newpos.crypto.*;
public class AES implements CryptoOperation {
	public byte[] encrypt(final byte[] key, String mode, byte[] iv, byte[] mesg) {
		if (key.length != 16 && key.length != 24 && key.length != 32) {
			throw new RuntimeException("Key Length ERR!");
		} 
	 	
		if (mode != "CBC" && mode != "ECB" && mode != "") {
			System.out.println(mode);
			throw new RuntimeException("Mode ERR!");
		}
		try{
			if (mode != "")
				mode = "AES/" + mode + "/NoPadding";
			else
				mode = "AES";
			//System.out.println("Before encode:" + HexToString.hexToStr(key));
			SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
			byte[] enCodeFormat = secretKey.getEncoded();
			//System.out.println("After encode:" + HexToString.hexToStr(enCodeFormat));
			SecretKeySpec secKey = new SecretKeySpec(enCodeFormat, "AES");

			Cipher cipher = Cipher.getInstance(mode);
			if (mode.contains("ECB"))
				cipher.init(Cipher.ENCRYPT_MODE, secKey);
			else 
				cipher.init(Cipher.ENCRYPT_MODE, secKey, new IvParameterSpec(iv));
			byte[] result = cipher.doFinal(mesg);
			return result;
		
		} catch(Exception e) {
			throw new RuntimeException("encrypt Failed!", e);
		}
	}

	public byte[] decrypt(final byte[] key, String mode, byte[] iv, byte[] mesg) {
		if (key.length != 16 && key.length != 24 && key.length != 32) {
			throw new RuntimeException("Key Length ERR!");
		} 
		if (mode != "CBC" && mode != "ECB" && mode != "") {
			System.out.println(mode);
			throw new RuntimeException("Mode ERR!");
		}
		try{
			if (mode != "")
				mode = "AES/" + mode + "/NoPadding";
			else
				mode = "AES";
			//System.out.println("Before encode:"+ HexToString.hexToStr(key));
			SecretKeySpec secret_key = new SecretKeySpec(key, "AES");
			byte[] encodeFormat = secret_key.getEncoded();
			//System.out.println("After encode:"+ HexToString.hexToStr(encodeFormat));
			SecretKeySpec secKey = new SecretKeySpec(encodeFormat, "AES");
	//		System.out.println(mode);
			Cipher cipherProvide = Cipher.getInstance(mode);
			if (mode.contains("ECB"))
				cipherProvide.init(Cipher.DECRYPT_MODE, secKey);
			else 
				cipherProvide.init(Cipher.DECRYPT_MODE, secKey, new IvParameterSpec(iv));
			byte[] result = cipherProvide.doFinal(mesg);
			return result;
		} catch(Exception e) {
			throw new RuntimeException("Decrypt Failed!", e);
		}		
	}

	public static AES getInstance() {
		return new AES();
	};

	public static void selfTest() {
		Scanner userInput = new Scanner(System.in);
		AES encipher = AES.getInstance();
		String strKey = "1111111111111111";
		byte[] newKey = new byte[]{0x00,0x00,0x00,0x00,0x00, 0x00, 0x00,0x00,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11};
		//String strMesg = "0123456789abcdef";
		byte[] aesKey = strKey.getBytes();
		byte[] newMesg = new byte[]{0x45,0x12,0x34,0x5a,(byte)0xaa,(byte)0xaa,(byte)0xaa,(byte)0xaa,(byte)0xff,(byte)0xac,0x4f,(byte)0x89,(byte)0xca,0x1a,(byte)0xb8,0x30};
		//byte[] mesg = strMesg.getBytes();
		//String mode = userInput.next();
		//Dencrypt msg by ECB MODE
		String mode = "ECB";
		//System.out.println(mode);
		byte[] iv = new String("0000000000000000").getBytes();
		byte[] cipherMsg = encipher.encrypt(newKey,mode, iv, newMesg);
		System.out.println("AES-ECB加密中...\n"+ HexToString.hexToStr(cipherMsg));
		byte[] plainMsg = encipher.decrypt(newKey, mode, iv, cipherMsg);
		System.out.println("AES-ECB解密中...\n"+ HexToString.hexToStr(plainMsg));

		//Dencrypt msg by CBC MODE
		mode = "CBC";
		cipherMsg = encipher.encrypt(newKey, mode, iv, newMesg);
		System.out.println("AES-CBC加密中...\n"+ HexToString.hexToStr(cipherMsg));
		plainMsg = encipher.decrypt(newKey, mode, iv, cipherMsg);
		System.out.println("AES-CBC密中...\n"+ HexToString.hexToStr(plainMsg));
	}
}


