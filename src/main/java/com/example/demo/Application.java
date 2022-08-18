package com.example.demo;

import com.example.demo.models.student.Student;
import com.example.demo.models.subjects.Subject;
import com.example.demo.models.teacher.Teacher;
import com.example.demo.models.timeSlot.TimeSlot;
import com.example.demo.models.users.Role;
import com.example.demo.repository.TimeSlotRepository;
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
                          TeacherService teacherService,
                          TimeSlotRepository timeSlotRepository) {

        return args -> {
            /*Role definition*/
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
            Subject italiano = new Subject(null, "ITALIANO");
            Subject storia = new Subject(null, "STORIA");
            Subject latino = new Subject(null, "LATINO");
            Subject greco = new Subject(null, "GRECO");
            Subject matematica = new Subject(null, "MATEMATICA");
            Subject fisica = new Subject(null, "FISICA");
            Subject scienzeDellaTerra = new Subject(null, "SCIENZE DELLA TERRA");
            Subject chimica = new Subject(null, "CHIMICA");
            Subject biologia = new Subject(null, "BIOLOGIA");
            Subject dirittoEdEconomia = new Subject(null, "DIRITTO ED ECONOMIA");
            Subject english = new Subject(null, "ENGLISH");
            Subject deutsch = new Subject(null, "DEUTSCH");


            if (subjectService.getSubjectByName("ITALIANO") == null) subjectService.addSubject(italiano);
            if (subjectService.getSubjectByName("STORIA") == null) subjectService.addSubject(storia);
            if (subjectService.getSubjectByName("LATINO") == null) subjectService.addSubject(latino);
            if (subjectService.getSubjectByName("GRECO") == null) subjectService.addSubject(greco);
            if (subjectService.getSubjectByName("MATEMATICA") == null) subjectService.addSubject(matematica);
            if (subjectService.getSubjectByName("FISICA") == null) subjectService.addSubject(fisica);
            if (subjectService.getSubjectByName("SCIENZE DELLA TERRA") == null)
                subjectService.addSubject(scienzeDellaTerra);
            if (subjectService.getSubjectByName("CHIMICA") == null) subjectService.addSubject(chimica);
            if (subjectService.getSubjectByName("BIOLOGIA") == null) subjectService.addSubject(biologia);
            if (subjectService.getSubjectByName("DIRITTO ED ECONOMIA") == null)
                subjectService.addSubject(dirittoEdEconomia);
            if (subjectService.getSubjectByName("ENGLISH") == null) subjectService.addSubject(english);
            if (subjectService.getSubjectByName("DEUTSCH") == null) subjectService.addSubject(deutsch);

            List<Subject> subjectList = new ArrayList<>();
            subjectList.add(subjectService.getSubjectByName("ITALIANO"));
            subjectList.add(subjectService.getSubjectByName("LATINO"));

            /*Student example*/

            Student a = new Student(null, "NOME1", "COGNOME1", "no codice fiscale", "1",
                    "Si", "Si", "Si", "Si", "Si", "Si", subjectList);
            if (studentService.getStudentByNameAndSurname(a.getName(), a.getSurname()) == null) {
                studentService.addStudent(a);
            }

            Student b = new Student(null, "NOME2", "COGNOME2", "no codice fiscale", "2",
                    "No", "Si", "Si", "Si", "Si", "Si", subjectList);
            if (studentService.getStudentByNameAndSurname(b.getName(), b.getSurname()) == null) {
                studentService.addStudent(b);
            }

            /*time slot example*/
            TimeSlot from900to930 = new TimeSlot(null, Time.valueOf("09:00:00"), Time.valueOf("09:30:00"));
            timeSlotRepository.save(from900to930);


            List<TimeSlot> timeSlotList = new ArrayList<>();
            timeSlotList.add(from900to930);



            /*teacher example*/

            Teacher teacher1 = new Teacher(null, "NOME1", "COGNOME1",
                    true, true, true, true, true
                    , timeSlotList, subjectList);
            teacherService.addTeacher(teacher1);

            Teacher teacher2 = new Teacher(null, "NOME2", "COGNOME2",
                    true, true, false, true, false
                    , timeSlotList, subjectList);
            teacherService.addTeacher(teacher2);


        };


    }

}
