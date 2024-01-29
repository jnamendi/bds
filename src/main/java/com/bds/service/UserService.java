package com.bds.service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.bds.model.UserData;
import com.bds.util.DBSValueEncryptor;
import com.bds.util.DefaultSecureStorage;

@Stateless
public class UserService {
    @PersistenceContext
    private EntityManager entityManager;
    
    @Inject
    private DBSValueEncryptor passwordEncoder;
   
	
	public void create(UserData user) {
        String password = user.getPassword();
		try {
			passwordEncoder.setSecretKey(DefaultSecureStorage.INSTANCE.getLocalSecretKey());
			password = Base64.getEncoder().encodeToString(passwordEncoder.encryptValue(password.getBytes(StandardCharsets.UTF_8)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        user.setPassword(password);
        user.setId(null);
        entityManager.persist(user);
	}
	
    public UserData getByUserName(String userName) {
    	UserData user = null;
    	user = (UserData)entityManager
        .createQuery("FROM UserData u where u.userName = ?1", UserData.class).setParameter(1, userName)
        .getSingleResult();
        return user;
    }

    public String decript(String password) throws Exception {
    	passwordEncoder.setSecretKey(DefaultSecureStorage.INSTANCE.getLocalSecretKey());
    	String decoded = new String(passwordEncoder.decryptValue(Base64.getDecoder().decode(password)));
    	return decoded;
    }
}
