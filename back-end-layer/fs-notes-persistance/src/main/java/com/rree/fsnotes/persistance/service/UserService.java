package com.rree.fsnotes.persistance.service;


import com.rree.fsnotes.persistance.model.UserModel;
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
	
	public ResponseEntity<UserModel> saveUser(User user) {
		User savedUser;
		try {
			savedUser = userRepository.save(user);
		}catch(DataIntegrityViolationException e) {
			throw new CustomExceptionHandler.UserAlreaydExistsException("User: " + user.getEmail() + " already exists");
		}

		UserModel responseUser = UserModel.builder()
				.firstName(savedUser.getFirstName())
				.lastName(savedUser.getLastName())
				.email(savedUser.getEmail())
				.id(savedUser.getId())
				.build();
		return new ResponseEntity<>(responseUser, 	HttpStatus.OK);
		
	}

	public User getUserByEmail(String email) {
		System.out.println("Finding email:" + email);
		return userRepository.findByEmail(email)
				.orElseThrow(() -> new CustomExceptionHandler.UserNotFoundException("email:" + email + " Not found"));
	}
}
