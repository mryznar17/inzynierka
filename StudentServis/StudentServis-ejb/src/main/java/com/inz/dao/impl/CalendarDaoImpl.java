package com.inz.dao.impl;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.inz.dao.CalendarDao;
import com.inz.dao.UserDao;
import com.inz.model.Calendar;
import com.inz.model.User;

public class CalendarDaoImpl implements CalendarDao {

	@PersistenceContext(name="primary")
	private EntityManager em;
	
	@EJB private UserDao Dao;
	
	@Override
	public List<Calendar> getCalendars() {
		return em.createNamedQuery("Calendar.findAll").getResultList();
	}

	@Override
	public Calendar getCalendar(int id) {
		return (Calendar) em.createNamedQuery("Calendar.byId").setParameter("calId", id).getSingleResult();
	}

	@Override
	public Calendar getCalendarbyUserId(String Id) {
		User user = em.find(User.class, Id);
		List<Calendar> cals = user.getCalendars();
		return cals.get(0);
	}

	@Override
	public void updateCalendar(Calendar calendar) {
		em.merge(calendar);
	}

	@Override
	public void deleteCalendar(int id) {
		em.remove(getCalendar(id));
	}

	@Override
	public void deleteUserCalendar(String id) {
		Calendar c = getCalendarbyUserId(id);
		em.remove(c);
	}

	@Override
	public void addCalendar(int user_id) {
		Calendar c = new Calendar();
		c.setCalId(generateId());
		c.setUser(Dao.getUser(user_id));
		em.persist(c);
	}

	private int generateId(){
		List<Calendar> cal = getCalendars();
		int id=0;
		List<Integer> ints = new LinkedList<Integer>();
		for(Calendar c:cal){
			ints.add(c.getCalId());
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
