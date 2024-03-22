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
import com.bds.util.DefaultValueEncryptor;
import com.bds.util.TrippleDes;

@Stateless
public class UserService {
    @PersistenceContext
    private EntityManager entityManager;
	
	public void create(UserData user) {
        String password = user.getPassword();
        String encryptedPassword = null;
		try {
			TrippleDes td = new TrippleDes();
			//passwordEncoder.setSecretKey(DefaultSecureStorage.INSTANCE.getLocalSecretKey());
			//password = Base64.getEncoder().encodeToString(passwordEncoder.encryptValue(password.getBytes(StandardCharsets.UTF_8)));
			encryptedPassword = td.encrypt(password);   
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        user.setPassword(encryptedPassword);
        user.setId(null);
        entityManager.persist(user);
	}
	
    public UserData getByUserName(String userName) {
    	UserData user = null;
    	try {
	    	user = (UserData)entityManager
	        .createQuery("FROM UserData u where u.userName = ?1", UserData.class).setParameter(1, userName)
	        .getSingleResult();
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
        return user;
    }

    public String decript(String password) throws Exception {
    	TrippleDes td = new TrippleDes();
    	//String decoded = new String(passwordEncoder.decryptValue(Base64.getDecoder().decode(password)));
    	String decoded = td.decrypt(password);
    	return decoded;
    }
}
