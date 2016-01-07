package com.inz.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.inz.model.Role;
import com.inz.model.User;

public class Test {

	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration().configure()
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
		session.close();
	}

}
