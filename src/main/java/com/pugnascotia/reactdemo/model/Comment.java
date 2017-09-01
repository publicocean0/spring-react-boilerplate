package com.pugnascotia.reactdemo.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Simple data container class. We need a no-args constructor so that Jackson
 * can deserialise these.
 */

@Document(collection = "comment")
public class Comment {
	@Id
	protected String id;
	protected String author;
	protected String content;

	public Comment(String author, String content) {
		this.author=author;
		this.content=content;
	}

	protected Comment() {

	}

	public String getId() {
		return id;
	}

	public String getAuthor() {
		return author;
	}

	public String getContent() {
		return content;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
