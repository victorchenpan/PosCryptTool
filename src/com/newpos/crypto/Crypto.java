package com.newpos.crypto;

import java.util.*;
import com.newpos.crypto.aes.*;
import com.newpos.crypto.format.HexToString;
import com.newpos.crypto.selftest.*;

public class Crypto {
	public static void test(String[] args) {
		//AES.selfTest();	
		AesPinBlock.selfTest();	
	}
	
	public static String doDencrypt(String modeCrypt, String modeDenrypt, String key, String dataIn, String account) {
		String dataOut = null;
		if (modeCrypt == "AES-ECB" && modeDenrypt == "Decrypt") {
			dataOut = HexToString.hexToStr(PinBlock.getPlainBlock(HexToString.strToByteArray(account), HexToString.strToByteArray(key), HexToString.strToByteArray(dataIn), "ECB"));
		} else if (modeCrypt == "AES-ECB" && modeDenrypt == "Encrypt") {
			dataOut = HexToString.hexToStr(PinBlock.getCipherBlock(HexToString.strToByteArray(account), HexToString.strToByteArray(key), HexToString.strToByteArray(dataIn), "ECB"));
		}
		
		return dataOut;
	}
}

