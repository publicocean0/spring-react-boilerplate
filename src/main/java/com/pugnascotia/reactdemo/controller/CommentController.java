package com.pugnascotia.reactdemo.controller;

import com.pugnascotia.reactdemo.model.Comment;
import com.pugnascotia.reactdemo.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.pugnascotia.reactdemo.utils.State.populateModel;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Handles requests for the "add a comment" page. This is handled
 * by our UI stack without any additional context.
 */

@Controller
@Slf4j
public class CommentController {
	private final CommentService service;

	@Inject
	public CommentController(CommentService repository) {
		this.service = repository;
	}


    @RequestMapping(value = "/add", method = GET)
    public String index(Model model, HttpServletRequest request) {
		populateModel(model, request);
        return "index";
    }
	/**
	 * Handles creating new comments and fetching all comments via AJAX.
	 */
	@RequestMapping(path = "/api/comments", method = POST, produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	public Comment add(@RequestBody Comment comment) {
		return service.save(comment);
	}

	@RequestMapping(path = "/api/comments", method = GET, produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Comment> comments() {
		// You shouldn't do this in a real app - you should page the data!
		return service.findAll();
	}
}
