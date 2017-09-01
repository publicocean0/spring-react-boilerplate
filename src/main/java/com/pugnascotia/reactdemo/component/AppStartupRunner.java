package com.pugnascotia.reactdemo.component;

import com.pugnascotia.reactdemo.model.User;
import com.pugnascotia.reactdemo.repository.UserRepository;
import com.pugnascotia.reactdemo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;



@Component
public class AppStartupRunner implements ApplicationRunner {
	final Logger logger = LoggerFactory.getLogger(AppStartupRunner.class);
	@Autowired
	private UserRepository userRepository;
    @Override
    public void run(ApplicationArguments args) throws Exception {
		User obj =userRepository.findByUsername("admin");
		if (obj==null) {
			logger.info("Adding admin user");
			userRepository.save(new User("admin","admin","admin","admin",new String[]{"USER","ADMIN"}));
		} else logger.info("Admin user "+obj.toString());

	}
}
