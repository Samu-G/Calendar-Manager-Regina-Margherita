package com.example.demo;

import com.example.demo.models.student.Student;
import com.example.demo.models.users.Role;
import com.example.demo.services.AccountService;
import com.example.demo.services.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
/*import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;*/

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/*@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
*/
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
			List<String> subjectList = new ArrayList<>();
			subjectList.add("matematica");
			subjectList.add("fisica");
			List<String> feedbackList = new ArrayList<>();
			feedbackList.add("feedback di prova1");
			feedbackList.add("feedback di prova2");
			Student a = new Student(null, "NOME1", "COGNOME1", "1",
					"Si", "Si", "Si", "Si", "Si", "Si");
			if(studentService.getStudentByNameAndSurname(a.getName(), a.getSurname())  == null) {
				studentService.addStudent(a);
			}

			/*Student example*/
			Student b =new Student(null, "NOME2", "COGNOME2", "2",
					"No", "Si", "Si", "Si", "Si", "Si");
			if(studentService.getStudentByNameAndSurname(b.getName(), b.getSurname())  == null) {
				studentService.addStudent(b);
			}

		};
	}

}
