package com.bds.util;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.ejb.Stateless;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Stateless
public class DefaultValueEncryptor implements DBSValueEncryptor {

    public static final String CIPHER_NAME = "AES/CBC/PKCS5Padding";
    public static final String KEY_ALGORITHM = "AES";

    private SecretKey secretKey;
    private Cipher cipher;

    public static SecretKey makeSecretKeyFromPassword(String password) {        
        byte[] bytes = password.getBytes(StandardCharsets.UTF_8);
        byte[] passBytes = Arrays.copyOf(bytes, 16);
        return new SecretKeySpec(passBytes, KEY_ALGORITHM);
    }

    @Override
    public void setSecretKey(SecretKey secretKey) {
        this.secretKey = secretKey;
        try {
            this.cipher = Cipher.getInstance(CIPHER_NAME);
        } catch (Exception e) {
            throw new IllegalStateException("Internal error during encrypted init", e);
        }
    }

    @Override
    public byte[] encryptValue( byte[] value) throws Exception {
        try {
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] iv = cipher.getIV();

            ByteArrayOutputStream resultBuffer = new ByteArrayOutputStream();
            try (CipherOutputStream cipherOut = new CipherOutputStream(resultBuffer, cipher)) {
                resultBuffer.write(iv);
                cipherOut.write(value);
            }
            return resultBuffer.toByteArray();
        } catch (Exception e) {
            throw new Exception("Error encrypting value", e);
        }
    }

    @Override
    public byte[] decryptValue( byte[] value) throws Exception {
        try (InputStream byteStream = new ByteArrayInputStream(value)) {
            byte[] fileIv = new byte[16];
            byteStream.read(fileIv);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(fileIv));

            try (CipherInputStream cipherIn = new CipherInputStream(byteStream, cipher)) {
                ByteArrayOutputStream resultBuffer = new ByteArrayOutputStream();
                FileUtils.copyStream(cipherIn, resultBuffer);
                return resultBuffer.toByteArray();
            }

        } catch (Exception e) {
            throw new Exception("Error decrypting value", e);
        }
    }
}
