package com.pugnascotia.reactdemo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;


@Document(collection = "user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private List<String> roles;


    private String name;

    private String email;


    private String username;


    private String password;


    private boolean enabled;

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	private boolean locked;

	public void setName(String name) {this.name = name;}
	public String getName() {return name;}
	public void setEmail(String email) {this.email = email;}
	public String getEmail() {return email;}
	public void setUsername(String username) {this.username = username;}
	public String getUsername() {return username;}
	public void setPassword(String password) {this.password = password;}
	public String getPassword() {return password;}
	public void setEnabled(boolean enabled) {this.enabled = enabled;}
	public boolean isEnabled() {return enabled;}
	public void setLocked(boolean locked) {this.locked = locked;}
	public boolean isLocked() {return locked;}

	public String getId() { return id; }
	public void setId(String id) { this.id = id; }

	public User( String name, String email, String username, String password,String[] roles) {
		this.roles = Arrays.asList(roles);
		this.name = name;
		this.email = email;
		this.username = username;
		this.enabled=true;
		this.password = password;
	}

	public User() {
	}

	@Override
	public String toString() {
		return "User{" +
			"id='" + id + '\'' +
			", roles=" + roles +
			", name='" + name + '\'' +
			", email='" + email + '\'' +
			", username='" + username + '\'' +
			", password='" + password + '\'' +
			", enabled=" + enabled +
			", locked=" + locked +
			'}';
	}
}
