package com.pugnascotia.reactdemo.repository;

import com.pugnascotia.reactdemo.model.Comment;

public interface CommentRepository {

	Iterable<Comment> findAll();

	Comment save(Comment comment);

	Comment findOne(String id);

	void delete(Comment c);


}
