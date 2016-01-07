package com.inz.dao.impl;

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
		return em.createNamedQuery("user.findAll")
				.getResultList();
	}

	@Override
	public User getUser(int id) {
		return null;
	}

	@Override
	public User getUser(String login) {
		return (User) em.createNamedQuery("user.byLogin").setParameter("login", login).getSingleResult();
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUser(String login) {
		// TODO Auto-generated method stub

	}

}
