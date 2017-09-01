package com.pugnascotia.reactdemo.repository;

import com.pugnascotia.reactdemo.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class CommentRepositoryImpl implements CommentRepository {

	@Autowired
	MongoTemplate mongoTemplate;


	@Override
	public Comment save(Comment comment) {


	mongoTemplate.save(comment);

		return comment;
	}



	@Override
	public void delete(Comment c) {
        mongoTemplate.remove(c);
	}


	public Comment findOne(String id){
		Query query = new Query(Criteria.where("id").is(id));
       return  mongoTemplate.findOne(query,Comment.class);
	}

	@Override
	public Iterable<Comment> findAll() {

		return mongoTemplate.findAll(Comment.class);
	}
}
