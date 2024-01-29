package com.bds.util;

import javax.crypto.SecretKey;
import javax.ejb.Local;

@Local
public interface DBASecureStorage {

    boolean useSecurePreferences();

    SecretKey getLocalSecretKey();
}