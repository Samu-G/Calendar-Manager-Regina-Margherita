package com.example.demo;

import com.example.demo.models.student.Student;
import com.example.demo.models.subjects.Subject;
import com.example.demo.models.teacher.Teacher;
import com.example.demo.models.timeSlot.TimeSlot;
import com.example.demo.models.users.Role;
import com.example.demo.services.AccountService;
import com.example.demo.services.StudentService;
import com.example.demo.services.SubjectService;
import com.example.demo.services.TeacherService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Time;
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
    CommandLineRunner run(AccountService accountService,
                          StudentService studentService,
                          SubjectService subjectService,
                          TeacherService teacherService) {
        /*Role definition*/
        return args -> {
            if (accountService.findRoleByName("ROLE_ADMIN") == null) {
                accountService.saveRole(new Role(null, "ROLE_ADMIN"));
            }
            if (accountService.findRoleByName("ROLE_TEACHER") == null) {
                accountService.saveRole(new Role(null, "ROLE_TEACHER"));
            }
            if (accountService.findRoleByName("ROLE_STUDENT") == null) {
                accountService.saveRole(new Role(null, "ROLE_STUDENT"));
            }

            /*Subject example*/
            Subject storia1 = new Subject(null, "STORIA", 1);
            Subject diritto2 = new Subject(null, "DIRITTO", 2);

            if (subjectService.getSubjectByNameAndYear("STORIA", 1) == null) {
                subjectService.addSubject(storia1);
            }
            if (subjectService.getSubjectByNameAndYear("DIRITTO", 2) == null) {
                subjectService.addSubject(diritto2);
            }

            List<Subject> subjectList = new ArrayList<>();
            subjectList.add(subjectService.getSubjectByNameAndYear("STORIA", 1));
            subjectList.add(subjectService.getSubjectByNameAndYear("DIRITTO", 2));

            /*Student example*/

            Student a = new Student(null, "NOME1", "COGNOME1", "1",
                    "Si", "Si", "Si", "Si", "Si", "Si", subjectList);
            if (studentService.getStudentByNameAndSurname(a.getName(), a.getSurname()) == null) {
                studentService.addStudent(a);
            }

            Student b = new Student(null, "NOME2", "COGNOME2", "2",
                    "No", "Si", "Si", "Si", "Si", "Si", subjectList);
            if (studentService.getStudentByNameAndSurname(b.getName(), b.getSurname()) == null) {
                studentService.addStudent(b);
            }

            /*teacher example*/

            /*time slot example*/
            TimeSlot from830to900 = new TimeSlot(null, Time.valueOf("09:00:00"), Time.valueOf("09:30:00"));
            if(teacherService.getTimeSlotByBeginTimeAndEndTime(Time.valueOf("09:00:00"),Time.valueOf("09:30:00")) == null) {
                teacherService.addTimeSlot(from830to900);
            }

            List <TimeSlot> timeSlotList = new ArrayList<>();
            timeSlotList.add(from830to900);

            Teacher teacher1 = new Teacher(null, "NOME1", "COGNOME1", false, timeSlotList, subjectList);
            if(teacherService.getTeacherByNameAndSurname("NOME1", "COGNOME1") == null) {
                teacherService.addTeacher(teacher1);
            }

        };
    }

}
