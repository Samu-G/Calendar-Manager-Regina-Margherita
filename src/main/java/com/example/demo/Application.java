package com.example.demo;

import com.example.demo.models.student.Gender;
import com.example.demo.models.student.Student;
import com.example.demo.models.user.Account;
import com.example.demo.models.user.Role;
import com.example.demo.services.AccountService;
import com.example.demo.services.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(AccountService accountService, StudentService studentService) {
		/*Role definition*/
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

			/*Student example*/
			Student s = new Student(null, "MARIO", "ROSSI", Gender.MALE);
			if(studentService.getStudentByNameAndSurname(s.getName(), s.getSurname())  == null) {
				studentService.addStudent(s);
			}

		};
	}

}
