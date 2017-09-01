package com.pugnascotia.reactdemo.service;


import com.pugnascotia.reactdemo.model.User;
import com.pugnascotia.reactdemo.repository.UserRepository;
import com.pugnascotia.reactdemo.utils.Functions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> findAll() {
		return Functions.map(userRepository.findAll(), c -> c);
	}

	public User findOne(String id) {
		return userRepository.findOne(id);
	}

	@Transactional(readOnly = false)
	public User save(User entity) {
		return userRepository.save(entity);
	}

	@Transactional(readOnly = false)
	public void delete(User entity) {
		userRepository.delete(entity);
	}

}

