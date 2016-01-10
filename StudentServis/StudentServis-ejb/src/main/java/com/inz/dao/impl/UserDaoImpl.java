package com.inz.dao.impl;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.inz.dao.UserDao;
import com.inz.model.User;

@Stateless
@Local(UserDao.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class UserDaoImpl implements UserDao {

	@PersistenceContext(name="primary")
	private EntityManager em;
	
	@Override
	public List<User> getUsers() {
		return em.createNamedQuery("user.findAll").getResultList();
	}

	@Override
	public User getUser(int id) {
		return (User) em.createNamedQuery("user.byId").setParameter("userId", id).getSingleResult();
	}

	@Override
	public User getUser(String login) {
		return (User) em.createNamedQuery("user.byLogin").setParameter("login", login).getSingleResult();
	}

	@Override
	public void updateUser(User user) {
		em.merge(user);
	}

	@Override
	public void deleteUser(int id) {
		User u = getUser(id);
		em.remove(u);
	}

	@Override
	public void deleteUser(String login) {
		User u = getUser(login);
		em.remove(u);
	}

	@Override
	public void addUser(String login, String name, String surname, String email,
			String password) {
		User u = new User();
		u.setLogin(login);
		u.setName(name);
		u.setSurname(surname);
		u.setEmail(email);
		u.setPassword(password);
		u.setUserId(generateId());
		em.persist(u);
	}

	@Override
	public void addUser(User user) {
		if(user.getUserId().equals(null)){
			user.setUserId(generateId());
		}
		em.persist(user);
	}
	
	private int generateId(){
		List<User> users = getUsers();
		int id=0;
		List<Integer> ints = new LinkedList<Integer>();
		for(User usr:users){
			ints.add(usr.getUserId());
		}
		int i=1;
		while(true){
			if(ints.contains(i)) i++;
			else{
				id=i;
				break;
			}
		}
		return id;
	}
}
