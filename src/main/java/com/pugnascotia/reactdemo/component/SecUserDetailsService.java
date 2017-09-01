package com.pugnascotia.reactdemo.component;

import com.pugnascotia.reactdemo.model.SecUserDetails;
import com.pugnascotia.reactdemo.model.User;
import com.pugnascotia.reactdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.security.core.userdetails.UserDetails;
@Component
public class SecUserDetailsService implements UserDetailsService {

    @Autowired
	private UserRepository userRepository;

    @Override
    public  UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /*Here add user data layer fetching from the MongoDB.
          I have used userRepository*/
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException(username);
        }else{
            UserDetails details = new SecUserDetails(user);
            return details;
        }
    }
}
