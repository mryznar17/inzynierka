package com.inz.jsf;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import com.inz.dao.UserDao;
import com.inz.model.User;

@ManagedBean(name = "userMan", eager = true)
@SessionScoped
public class UserManager {
	
	@EJB private UserDao dao;
	
	private String login;
	private String name;
	private String surname;
	private String password;
	private String email;
	
	public void ManagedBean(){};
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String addUser(){
		dao.addUser(login, name, surname, email, password);
		return "index";
	}
	
	public void validateLogin(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		  String login = (String) value;
		  String message = "Błąd walidacji: ";
		  boolean err = false;
		  if ( login.length() > 30 || login.length() < 5 ) {
			  message=message+"Login musi mieś długość od 5 do 30 znaków.";
			  err=true;
		  } else if(validateLoginHelper(login)){
			  message=message+"Użytkownik o podanym loginie znajduje się już w bazie danych.";
			  err=true;
		  }
		  
		  if(err){
			  FacesMessage msg = new FacesMessage(message);
		      throw new ValidatorException(msg);
		  }
		}
	
	private boolean validateLoginHelper(String login){
		List<User> users = dao.getUsers();
		for(User u:users){
			if(u.getLogin().equals(login)) return true;
		}
		return false;
	}
	
}
