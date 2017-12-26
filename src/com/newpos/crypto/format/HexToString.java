package com.newpos.crypto.format;

public class HexToString {
	public static String hexToStr(byte[] mesg) {
		String result = "";
		String tmp = "";
		for (int i = 0; i < mesg.length; i++) {
			tmp = Integer.toHexString(mesg[i] & 0xff);
			if (tmp.length() == 1) {
				tmp = '0' + tmp;
			}
			result += tmp;
		}

		return result;
	}
	
	public static byte[] strToByteArray(String str)
	{
	    if (str == null) {
	        return null;
	    }
	    if (str.length() == 0) {
	        return new byte[0];
	    }
	    byte[] byteArray = new byte[str.length() / 2];
	    for (int i = 0; i < byteArray.length; i++){
	        String subStr = str.substring(2 * i, 2 * i + 2);
	        byteArray[i] = ((byte)Integer.parseInt(subStr, 16));
	    }
	    return byteArray;
	}
};
