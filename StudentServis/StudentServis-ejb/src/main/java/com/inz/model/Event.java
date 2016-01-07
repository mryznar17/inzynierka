package com.inz.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the event database table.
 * 
 */
@Entity
@NamedQuery(name="Event.findAll", query="SELECT e FROM Event e")
public class Event implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="event_id")
	private Integer eventId;

	@Column(name="date_time")
	private Timestamp dateTime;

	@Column(name="event_desc")
	private String eventDesc;

	@Column(name="event_name")
	private String eventName;

	//bi-directional many-to-one association to Calendar
	@ManyToOne
	@JoinColumn(name="cal_id")
	private Calendar calendar;

	public Event() {
	}

	public Integer getEventId() {
		return this.eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public Timestamp getDateTime() {
		return this.dateTime;
	}

	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}

	public String getEventDesc() {
		return this.eventDesc;
	}

	public void setEventDesc(String eventDesc) {
		this.eventDesc = eventDesc;
	}

	public String getEventName() {
		return this.eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Calendar getCalendar() {
		return this.calendar;
	}

	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

}