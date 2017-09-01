package com.pugnascotia.reactdemo.service;


import com.pugnascotia.reactdemo.model.Comment;
import com.pugnascotia.reactdemo.repository.CommentRepository;
import com.pugnascotia.reactdemo.utils.Functions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;

	public List<Comment> findAll() {
		return Functions.map(commentRepository.findAll(), c -> c);
	}

	public Comment findOne(String id) {
		return commentRepository.findOne(id);
	}

	@Transactional(readOnly = false)
	public Comment save(Comment entity) {
		return commentRepository.save(entity);
	}

	@Transactional(readOnly = false)
	public void delete(Comment entity) {
		commentRepository.delete(entity);
	}

}

