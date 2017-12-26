package com.newpos.crypto.aes;

import com.newpos.crypto.aes.AES;
import com.newpos.crypto.format.*;
// get aes pin block or
// parse aes pin block to plain block.
public class PinBlock {
	private byte[] Acc;
	private byte[] Key;
	private byte[] Pin;
	private String Mode;
	private byte[] CipherPinBlock;
	private boolean encryptEnd = false;
	private boolean decryptEnd = false;

	PinBlock(byte[] account, byte[] key, String mode) {
		Acc = account;
		Key = key;
		Mode = mode;
	}

	public void setPlainPin(byte[] plainPin) {
		Pin = plainPin;
	}

	public void setCipherPin(byte[] cipherPin) {
		CipherPinBlock = cipherPin;
	}

	public byte[] getPinBlock() {
		if (encryptEnd)
			return CipherPinBlock;
		else {
			this.CipherPinBlock = getCipherBlock(Acc, Key, Pin, Mode);
			this.encryptEnd = true;
			return CipherPinBlock;
		}
	}
	
	public byte[] parsePinBlock() {
		if (decryptEnd)
			return Pin;
		else {
			this.Pin = getPlainBlock(Acc, Key, CipherPinBlock, Mode);
			this.decryptEnd = true;
			return Pin;
		}
	}

	// @function 加密PIN,得到密文PINBLOCK
	// @param	account:16字节的plain account block
	// 			key:16字节的密钥块
	// 			pin:16字节的PIN block
	// 			mode:ECB or CBC
	public static byte[] getCipherBlock(final byte[] account, final byte[] key, final byte[] pin, final String mode) {
		byte[] formatPin = new byte[16];
		byte[] iv = new byte[16];
		ByteOperation.memcpy(formatPin, pin, pin.length);
		
		AES aesObj = AES.getInstance();
		byte[] cipherBlock1 = aesObj.encrypt(key, mode, iv, formatPin);
		byte[] cipherBlock2 = ByteOperation.xor(cipherBlock1, account, 16);

		return aesObj.encrypt(key, mode, iv, cipherBlock2);
		
	}

	// @function 解析PIN,得到明文PINBLOCK
	// @param	account:16字节的plain account block
	// 			key:16字节的密钥块
	// 			pin:16字节的密文PIN block
	// 			mode:ECB or CBC
	public static byte[] getPlainBlock(final byte[] account, final byte[] key, final byte[] pin, final String mode) {
		byte[] iv = new byte[16];
		AES aesObj = AES.getInstance();
		byte[] cipherBlock2 = aesObj.decrypt(key, mode, iv, pin);  
		byte[] cipherBlock1 = ByteOperation.xor(cipherBlock2, account, 16);
		return aesObj.decrypt(key, mode, iv, cipherBlock1);
	}
}

