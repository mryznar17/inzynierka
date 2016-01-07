package com.inz.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the calendar database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Calendar.findAll", query="SELECT c FROM Calendar c"),
	@NamedQuery(name="Calendar.byId", query="SELECT c FROM Calendar as c where c.calId=:calId")
})
public class Calendar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cal_id")
	private Integer calId;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	//bi-directional many-to-one association to Event
	@OneToMany(mappedBy="calendar")
	private List<Event> events;

	public Calendar() {
	}

	public Integer getCalId() {
		return this.calId;
	}

	public void setCalId(Integer calId) {
		this.calId = calId;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Event> getEvents() {
		return this.events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public Event addEvent(Event event) {
		getEvents().add(event);
		event.setCalendar(this);

		return event;
	}

	public Event removeEvent(Event event) {
		getEvents().remove(event);
		event.setCalendar(null);

		return event;
	}

}