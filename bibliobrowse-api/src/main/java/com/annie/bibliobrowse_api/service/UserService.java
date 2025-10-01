package com.annie.bibliobrowse_api.service;

import java.util.List;

import com.annie.bibliobrowse_api.entity.User;

public interface UserService {

  List<User>getAllUsers();
	User getUser(Long id);
	User createUser(User user);
	User updateUser(User user);
	String deleteUser(User user);
}
