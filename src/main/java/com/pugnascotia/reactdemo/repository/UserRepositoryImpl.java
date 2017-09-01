package com.pugnascotia.reactdemo.repository;


import com.pugnascotia.reactdemo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Override
	public User save(User user) {
		String p=user.getPassword();
		user.setPassword(passwordEncoder.encode(p));

	mongoTemplate.save(user);
              user.setPassword(p);
		return user;
	}



	@Override
	public void delete(User c) {
        mongoTemplate.remove(c);
	}


	public User findOne(String id){
		Query query = new Query(Criteria.where("id").is(id));
       return  mongoTemplate.findOne(query,User.class);
	}

	@Override
	public Iterable<User> findAll() {

		return mongoTemplate.findAll(User.class);
	}
	public User findByUsername(String username){
		Query query = new Query(Criteria.where("username").is(username));
		return  mongoTemplate.findOne(query,User.class);
	}
}
