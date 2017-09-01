package com.pugnascotia.reactdemo.repository;

import com.pugnascotia.reactdemo.model.Comment;
import com.pugnascotia.reactdemo.model.User;

public interface UserRepository {

	Iterable<User> findAll();

	User save(User comment);

	User findOne(String id);

	User findByUsername(String username);

	void delete(User c);


}
