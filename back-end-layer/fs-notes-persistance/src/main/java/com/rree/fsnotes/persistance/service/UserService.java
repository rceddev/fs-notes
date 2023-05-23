package com.rree.fsnotes.persistance.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rree.fsnotes.persistance.entity.User;
import com.rree.fsnotes.persistance.exception.CustomExceptionHandler;
import com.rree.fsnotes.persistance.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User getUserById(Integer id){
		return userRepository.findById(id)
				.orElseThrow(() -> new CustomExceptionHandler.UserNotFoundException("id:" + id + " Not found"));
	}
	
	public ResponseEntity<User> saveUser(User user) {
		User savedUser;
		try {
			savedUser = userRepository.save(user);
		}catch(DataIntegrityViolationException e) {
			throw new CustomExceptionHandler.UserAlreaydExistsException("User: " + user.getEmail() + "already exists");
		}
		return new ResponseEntity<>(savedUser, 	HttpStatus.OK);
		
	}
}
