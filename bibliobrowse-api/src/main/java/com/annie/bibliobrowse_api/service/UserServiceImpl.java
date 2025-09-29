package com.annie.bibliobrowse_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.annie.bibliobrowse_api.domain.User;
import com.annie.bibliobrowse_api.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
  @Autowired
	private UserRepository userRepository;
	
  @Override
	public List<User>getAllUsers() {
		return (List<User>) userRepository.findAll();
	}
	
  @Override
	public User getUser(Long id) {
		return userRepository.findById(id).get();
	}
	
  @Override
	public User createUser(User user) {
		return userRepository.save(user);
	}

  @Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}
	
  @Override
	public String deleteUser(User user) {
		userRepository.delete(user);
		return "User with id " + user.getId() + " is deleted Successfully";
	}
}
