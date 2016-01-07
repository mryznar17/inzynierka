package com.inz.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQueries({
	@NamedQuery(name="user.findAll", query="SELECT u FROM User u"),
	@NamedQuery(name="user.byId", query="select u from User as u where u.userId=:userId"),
	@NamedQuery(name="user.byLogin", query="select u from User as u where u.login=:login"),
})
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="user_id", columnDefinition = "serial")
	private Integer userId;

	private String email;

	private String login;

	private String name;

	private String password;

	private String surname;

	//bi-directional many-to-one association to Calendar
	@OneToMany(mappedBy="user")
	private List<Calendar> calendars;

	//bi-directional many-to-one association to File
	@OneToMany(mappedBy="user")
	private List<File> files;

	//bi-directional many-to-one association to SharedFile
	@OneToMany(mappedBy="user")
	private List<SharedFile> sharedFiles;

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="role_id")
	private Role role;

	public User() {
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public List<Calendar> getCalendars() {
		return this.calendars;
	}

	public void setCalendars(List<Calendar> calendars) {
		this.calendars = calendars;
	}

	public Calendar addCalendar(Calendar calendar) {
		getCalendars().add(calendar);
		calendar.setUser(this);

		return calendar;
	}

	public Calendar removeCalendar(Calendar calendar) {
		getCalendars().remove(calendar);
		calendar.setUser(null);

		return calendar;
	}

	public List<File> getFiles() {
		return this.files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

	public File addFile(File file) {
		getFiles().add(file);
		file.setUser(this);

		return file;
	}

	public File removeFile(File file) {
		getFiles().remove(file);
		file.setUser(null);

		return file;
	}

	public List<SharedFile> getSharedFiles() {
		return this.sharedFiles;
	}

	public void setSharedFiles(List<SharedFile> sharedFiles) {
		this.sharedFiles = sharedFiles;
	}

	public SharedFile addSharedFile(SharedFile sharedFile) {
		getSharedFiles().add(sharedFile);
		sharedFile.setUser(this);

		return sharedFile;
	}

	public SharedFile removeSharedFile(SharedFile sharedFile) {
		getSharedFiles().remove(sharedFile);
		sharedFile.setUser(null);

		return sharedFile;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}