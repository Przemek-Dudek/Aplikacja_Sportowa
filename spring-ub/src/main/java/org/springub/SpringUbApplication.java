package org.springub;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springub.model.User;
import org.springub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
public class SpringUbApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(SpringUbApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user = new User();
		user.setLogin("TestLogin");
		user.setFirstName("TestName");
		user.setLastName("TestLastName");
		user.setEmail("test@test.pl");
		user.setPasswordHash("TestPassword");

		userService.createUser(user);
	}
}
