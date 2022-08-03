package com.example.demo;

import com.example.demo.models.user.Role;
import com.example.demo.services.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner run(AccountService accountService) {
		return args ->{
			if(accountService.findRoleByName("ROLE_ADMIN") == null) {
				accountService.saveRole(new Role(null, "ROLE_ADMIN"));
			}
			if(accountService.findRoleByName("ROLE_TEACHER") == null) {
				accountService.saveRole(new Role(null, "ROLE_TEACHER"));
			}
			if(accountService.findRoleByName("ROLE_STUDENT") == null) {
				accountService.saveRole(new Role(null, "ROLE_STUDENT"));
			}
		};
	}

}
