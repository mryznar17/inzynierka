package com.inz.dao;

import java.sql.Timestamp;
import java.util.List;

import com.inz.model.Event;

public interface EventDao {
	List<Event> getEvents();
	Event getEvent(int id);
	void updateEvent(Event event);
	void deleteEvent(int id);
	void addEvent(Timestamp time, String name, String desc, int cal_id);
	void addEvent(Event event);
}
