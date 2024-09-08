package com.src.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.src.entity.User;

@Service
public interface UserService {

	User save(User user);
	List<User> getAllUser();
	User getUser(String username);
	
}
