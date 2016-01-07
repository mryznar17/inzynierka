package com.inz.dao;

import java.util.List;

import com.inz.model.Calendar;

public interface CalendarDao {
	List<Calendar> getCalendars();
	Calendar getCalendar(int id);
	Calendar getCalendarbyUserId(String Id);
	void updateCalendar(Calendar calendar);
	void deleteCalendar(int id);
	void deleteUserCalendar(String id);
	void addCalendar(int user_id);
}
