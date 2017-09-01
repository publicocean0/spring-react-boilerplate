package com.pugnascotia.reactdemo.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class SecUserDetails implements UserDetails {

    private User user;

    public SecUserDetails(User user) {
		this.user = user;
    }
	public Collection<? extends GrantedAuthority> getAuthorities(){
    	ArrayList<GrantedAuthority> list=new ArrayList<>();
        for(final String r:user.getRoles()) list.add(new GrantedAuthority() {
			@Override
			public String getAuthority() {
				return r;
			}
		});

    	return list;
	}

	public String getPassword(){return user.getPassword();}

	public  String getUsername(){return user.getUsername();}

	public  boolean isAccountNonExpired(){return true;}

	public  boolean isAccountNonLocked(){return !user.isLocked();}

	public  boolean isCredentialsNonExpired(){return true;}

	public boolean isEnabled(){return user.isEnabled();};
}
