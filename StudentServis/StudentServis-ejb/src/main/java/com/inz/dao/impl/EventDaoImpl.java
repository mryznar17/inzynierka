package com.inz.dao.impl;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.inz.dao.EventDao;
import com.inz.model.Calendar;
import com.inz.model.Event;

public class EventDaoImpl implements EventDao {

	@PersistenceContext(name="primary")
	private EntityManager em;
	
	@Override
	public List<Event> getEvents() {
		return em.createNamedQuery("Event.findAll").getResultList();
	}

	@Override
	public Event getEvent(int id) {
		return em.find(Event.class, id);
	}

	@Override
	public void updateEvent(Event event) {
		em.merge(event);
	}

	@Override
	public void deleteEvent(int id) {
		em.remove(getEvent(id));
	}

	@Override
	public void addEvent(Timestamp time, String name, String desc, int cal_id) {
		Event e = new Event();
		e.setDateTime(time);
		e.setEventName(name);
		e.setEventDesc(desc);
		Calendar c = em.find(Calendar.class, cal_id);
		e.setCalendar(c);
		e.setEventId(generateId());
		em.persist(e);
	}

	@Override
	public void addEvent(Event event) {
		if(event.getEventId().equals(null)) event.setEventId(generateId());
		em.persist(event);
	}

	private int generateId(){
		List<Event> event = getEvents();
		int id=0;
		List<Integer> ints = new LinkedList<Integer>();
		for(Event c:event){
			ints.add(c.getEventId());
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
