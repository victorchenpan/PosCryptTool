package com.newpos.crypto.interfaces;

public interface CryptoOperation {
	public byte[] decrypt(final byte[] key, String mode, byte[] iv, byte[] mesg);

	public byte[] encrypt(final byte[] key, String mode, byte[] iv, byte[] mesg);
}
