package com.bds.util;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.ejb.Stateless;

@Stateless
public class DefaultSecureStorage implements DBASecureStorage {

    private static final byte[] LOCAL_KEY_CACHE = new byte[] { -70, -69, 74, -97, 119, 74, -72, 83, -55, 108, 45, 101, 61, -2, 84, 74 };

    public static DefaultSecureStorage INSTANCE = new DefaultSecureStorage();

    @Override
    public boolean useSecurePreferences() {
        return false;
    }


    @Override
    public SecretKey getLocalSecretKey() {
        return new SecretKeySpec(LOCAL_KEY_CACHE, "AES");
    }

}
