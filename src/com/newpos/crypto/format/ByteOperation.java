package com.newpos.crypto.format;

public class ByteOperation {
	public static byte[] xor(byte[] block1, byte[] block2, int length) {
		byte[] tmp = new byte[length];
		for (int i = 0; i < length; i++) 
			tmp[i] = (byte)(block1[i] ^ block2[i]);
		return tmp;
	}

	public static void memset(byte[] src, byte to, int length) {
		for (int i = 0; i < length; i++) 
			src[i] = to;
	}

	public static void memcpy(byte[] to, byte[] from, int length) {
		for (int i = 0; i < length; i++)
			to[i] = from[i];
	}
	
}
