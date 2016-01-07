package com.inz.dao;

import java.util.List;

import com.inz.model.User;

public interface UserDao {
	List<User> getUsers();
	User getUser(int id);
	User getUser(String login);
	void updateUser(User user);
	void deleteUser(int id);
	void deleteUser(String login);
}
