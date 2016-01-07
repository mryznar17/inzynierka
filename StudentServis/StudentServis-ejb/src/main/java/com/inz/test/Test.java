package com.inz.test;

import java.util.List;

import javax.ejb.EJB;

import com.inz.dao.UserDao;
import com.inz.model.User;

public class Test {

	@EJB private transient UserDao Dao;
	
	public UserDao getDao() {
		return Dao;
	}

	public void setDao(UserDao dao) {
		Dao = dao;
	}

	public static void main(String[] args) {
		
		Test test = new Test();
		List<User> users = test.getDao().getUsers();
		for(User u:users){
			System.out.println(u);
		}
/*		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Role role = new Role();
		role.setRoleId(3);
		role.setRoleName("user1");
//		User user = new User();
//		user.se
		//session.save(user);
		session.save(role);
		session.getTransaction().commit();
		session.close();*/
	}

}
