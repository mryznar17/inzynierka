package com.inz.jsf;


import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.inz.dao.UserDao;
import com.inz.model.User;

@ManagedBean(name = "test", eager = true)
@SessionScoped
public class TestJSF {
	@EJB private UserDao Dao;
	
	public TestJSF(){
		
	}

	private String login="Wiesiek";

	public String getLogin() {
		login="Marek";
		User u = Dao.getUser("paulinka");
		login = u.getName();
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
	public String wynik(){
		return "testF";
	}
}
