package com.bds.util;

import javax.crypto.SecretKey;
import javax.ejb.Local;

@Local
public interface DBSValueEncryptor {
	
	void setSecretKey(SecretKey secretKey);

    byte[] encryptValue(byte[] value)throws Exception;

    byte[] decryptValue( byte[] value) throws Exception;

}
