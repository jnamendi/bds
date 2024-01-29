package com.bds.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.Base64;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import com.bds.model.UserData;
import com.bds.service.UserService;

@Named @ViewScoped
public class UserDataBean implements Serializable {
    @Inject
    private UserService userService;
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 2462785095967906689L;
	private UserData newUser = new UserData();
    private UserData user = new UserData();
    
    public void save() {
    	userService.create(newUser);
    	newUser = new UserData();
    	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User Added"));
    	PrimeFaces.current().ajax().update("form:messages");
    }
    
    public void login() {
    	String password = "";
    	FacesContext facesContext = FacesContext.getCurrentInstance();
		try {
			UserData userToLogin = userService.getByUserName(user.getUserName());
			if(userToLogin != null) {
				password = userService.decript(user.getPassword());
		    	if(userToLogin.getPassword().equals(password)) {
		    		  facesContext.getExternalContext().redirect("reader.xhtml");
		    	} else {
		    		facesContext.addMessage((String)null, new FacesMessage("Username or Password incorrect"));
		    	}
	    	} else {
	    		facesContext.addMessage((String)null, new FacesMessage("User not registered"));
	    	}
			facesContext.getExternalContext().redirect("reader.xhtml");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				facesContext.getExternalContext().redirect("reader.xhtml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		  
    }

	public UserData getNewUser() {
		return newUser;
	}

	public void setNewUser(UserData newUser) {
		this.newUser = newUser;
	}

	public UserData getUser() {
		return user;
	}

	public void setUser(UserData user) {
		this.user = user;
	}
    
    
}
