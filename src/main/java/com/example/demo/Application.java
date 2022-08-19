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
            createRoleExample(accountService);
            createSubjectExample(subjectService);

            List<Subject> subjectList = new ArrayList<>();
            subjectList.add(subjectService.getSubjectByName("ITALIANO"));
            subjectList.add(subjectService.getSubjectByName("LATINO"));

            createStudentExample(studentService, subjectList);
            createTimeSlotExample(timeSlotRepository);
            createTeacherExample(teacherService, timeSlotRepository, subjectList);
        };


    }

    private static void createStudentExample(StudentService studentService, List<Subject> subjectList) {
        Student a = new Student(null, "Mario", "Rossi", "non ancora inserito", "1",
                "Si", "Si", "Si", "Si", "Si", "Si", subjectList);
        if (studentService.getStudentByNameAndSurname(a.getName(), a.getSurname()) == null) {
            studentService.addStudent(a);
        }

        Student b = new Student(null, "Giorgio", "Gomma", "non ancora inserito", "2",
                "No", "Si", "Si", "Si", "Si", "Si", subjectList);
        if (studentService.getStudentByNameAndSurname(b.getName(), b.getSurname()) == null) {
            studentService.addStudent(b);
        }
    }

    private static void createTeacherExample(TeacherService teacherService, TimeSlotRepository timeSlotRepository, List<Subject> subjectList) {
        List<TimeSlot> mondayTimeSlots = new ArrayList<>();
        mondayTimeSlots.add(timeSlotRepository.findTimeSlotByBeginTimeAndEndTime(Time.valueOf("08:30:00"), Time.valueOf("09:00:00")));
        List<TimeSlot> tuesdayTimeSlots = new ArrayList<>();
        tuesdayTimeSlots.add(timeSlotRepository.findTimeSlotByBeginTimeAndEndTime(Time.valueOf("08:30:00"), Time.valueOf("09:00:00")));
        List<TimeSlot> wednesdayTimeSlots = new ArrayList<>();
        wednesdayTimeSlots.add(timeSlotRepository.findTimeSlotByBeginTimeAndEndTime(Time.valueOf("13:30:00"), Time.valueOf("14:00:00")));
        List<TimeSlot> thursdayTimeSlots = new ArrayList<>();
        thursdayTimeSlots.add(timeSlotRepository.findTimeSlotByBeginTimeAndEndTime(Time.valueOf("13:30:00"), Time.valueOf("14:00:00")));
        List<TimeSlot> fridayTimeSlots = new ArrayList<>();
        fridayTimeSlots.add(timeSlotRepository.findTimeSlotByBeginTimeAndEndTime(Time.valueOf("13:30:00"), Time.valueOf("14:00:00")));

        Teacher giovanni_muciaccia = new Teacher(null, "Giovanni", "Muciaccia",
                true, true, true, true, true,
                mondayTimeSlots, tuesdayTimeSlots, wednesdayTimeSlots, thursdayTimeSlots, fridayTimeSlots,
                subjectList);

        if (teacherService.getTeacherByNameAndSurname("Giovanni", "Muciaccia") == null) {
            teacherService.addTeacher(giovanni_muciaccia);
        }

        Teacher samuel_girardello = new Teacher(null, "Samuel", "Girardello",
                true, true, true, true, true,
                mondayTimeSlots, tuesdayTimeSlots, wednesdayTimeSlots, thursdayTimeSlots, fridayTimeSlots,
                subjectList);

        if (teacherService.getTeacherByNameAndSurname("Samuel", "Girardello") == null) {
            teacherService.addTeacher(samuel_girardello);
        }
    }

    private static void createTimeSlotExample(TimeSlotRepository timeSlotRepository) {
        TimeSlot from_08_30_to_09_00 = new TimeSlot(null, Time.valueOf("08:30:00"), Time.valueOf("09:00:00"));
        if (timeSlotRepository.findTimeSlotByBeginTimeAndEndTime(Time.valueOf("08:30:00"), Time.valueOf("09:00:00")) == null) {
            timeSlotRepository.save(from_08_30_to_09_00);
        }
        TimeSlot from_90_00_to_09_30 = new TimeSlot(null, Time.valueOf("09:00:00"), Time.valueOf("09:30:00"));
        if (timeSlotRepository.findTimeSlotByBeginTimeAndEndTime(Time.valueOf("09:00:00"), Time.valueOf("09:30:00")) == null) {
            timeSlotRepository.save(from_90_00_to_09_30);
        }
        TimeSlot from_90_30_to_10_00 = new TimeSlot(null, Time.valueOf("09:30:00"), Time.valueOf("10:00:00"));
        if (timeSlotRepository.findTimeSlotByBeginTimeAndEndTime(Time.valueOf("09:30:00"), Time.valueOf("10:00:00")) == null) {
            timeSlotRepository.save(from_90_30_to_10_00);
        }
        TimeSlot from_10_00_to_10_30 = new TimeSlot(null, Time.valueOf("10:00:00"), Time.valueOf("10:30:00"));
        if (timeSlotRepository.findTimeSlotByBeginTimeAndEndTime(Time.valueOf("10:00:00"), Time.valueOf("10:30:00")) == null) {
            timeSlotRepository.save(from_10_00_to_10_30);
        }
        TimeSlot from_10_30_to_11_00 = new TimeSlot(null, Time.valueOf("10:30:00"), Time.valueOf("11:00:00"));
        if (timeSlotRepository.findTimeSlotByBeginTimeAndEndTime(Time.valueOf("10:30:00"), Time.valueOf("11:00:00")) == null) {
            timeSlotRepository.save(from_10_30_to_11_00);
        }
        TimeSlot from_11_00_to_11_30 = new TimeSlot(null, Time.valueOf("11:00:00"), Time.valueOf("11:30:00"));
        if (timeSlotRepository.findTimeSlotByBeginTimeAndEndTime(Time.valueOf("11:00:00"), Time.valueOf("11:30:00")) == null) {
            timeSlotRepository.save(from_11_00_to_11_30);
        }
        TimeSlot from_11_30_to_12_00 = new TimeSlot(null, Time.valueOf("11:30:00"), Time.valueOf("12:00:00"));
        if (timeSlotRepository.findTimeSlotByBeginTimeAndEndTime(Time.valueOf("11:30:00"), Time.valueOf("12:00:00")) == null) {
            timeSlotRepository.save(from_11_30_to_12_00);
        }
        TimeSlot from_12_00_to_12_30 = new TimeSlot(null, Time.valueOf("12:00:00"), Time.valueOf("12:30:00"));
        if (timeSlotRepository.findTimeSlotByBeginTimeAndEndTime(Time.valueOf("12:00:00"), Time.valueOf("12:30:00")) == null) {
            timeSlotRepository.save(from_12_00_to_12_30);
        }
        TimeSlot from_12_30_to_13_00 = new TimeSlot(null, Time.valueOf("12:30:00"), Time.valueOf("13:00:00"));
        if (timeSlotRepository.findTimeSlotByBeginTimeAndEndTime(Time.valueOf("12:30:00"), Time.valueOf("13:00:00")) == null) {
            timeSlotRepository.save(from_12_30_to_13_00);
        }
        TimeSlot from_13_00_to_13_30 = new TimeSlot(null, Time.valueOf("13:00:00"), Time.valueOf("13:30:00"));
        if (timeSlotRepository.findTimeSlotByBeginTimeAndEndTime(Time.valueOf("13:00:00"), Time.valueOf("13:30:00")) == null) {
            timeSlotRepository.save(from_13_00_to_13_30);
        }
        TimeSlot from_13_30_to_14_00 = new TimeSlot(null, Time.valueOf("13:30:00"), Time.valueOf("14:00:00"));
        if (timeSlotRepository.findTimeSlotByBeginTimeAndEndTime(Time.valueOf("13:30:00"), Time.valueOf("14:00:00")) == null) {
            timeSlotRepository.save(from_13_30_to_14_00);
        }
        TimeSlot from_14_00_to_14_30 = new TimeSlot(null, Time.valueOf("14:00:00"), Time.valueOf("14:30:00"));
        if (timeSlotRepository.findTimeSlotByBeginTimeAndEndTime(Time.valueOf("14:00:00"), Time.valueOf("14:30:00")) == null) {
            timeSlotRepository.save(from_14_00_to_14_30);
        }
        TimeSlot from_14_30_to_15_00 = new TimeSlot(null, Time.valueOf("14:30:00"), Time.valueOf("15:00:00"));
        if (timeSlotRepository.findTimeSlotByBeginTimeAndEndTime(Time.valueOf("14:30:00"), Time.valueOf("15:00:00")) == null) {
            timeSlotRepository.save(from_14_30_to_15_00);
        }
        TimeSlot from_15_00_to_15_30 = new TimeSlot(null, Time.valueOf("15:00:00"), Time.valueOf("15:30:00"));
        if (timeSlotRepository.findTimeSlotByBeginTimeAndEndTime(Time.valueOf("15:00:00"), Time.valueOf("15:30:00")) == null) {
            timeSlotRepository.save(from_15_00_to_15_30);
        }
        TimeSlot from_15_30_to_16_00 = new TimeSlot(null, Time.valueOf("15:30:00"), Time.valueOf("16:00:00"));
        if (timeSlotRepository.findTimeSlotByBeginTimeAndEndTime(Time.valueOf("15:30:00"), Time.valueOf("16:00:00")) == null) {
            timeSlotRepository.save(from_15_30_to_16_00);
        }
    }

    private static void createRoleExample(AccountService accountService) {
        if (accountService.findRoleByName("ROLE_ADMIN") == null) {
            accountService.saveRole(new Role(null, "ROLE_ADMIN"));
        }
        if (accountService.findRoleByName("ROLE_TEACHER") == null) {
            accountService.saveRole(new Role(null, "ROLE_TEACHER"));
        }
        if (accountService.findRoleByName("ROLE_STUDENT") == null) {
            accountService.saveRole(new Role(null, "ROLE_STUDENT"));
        }
    }

    private static void createSubjectExample(SubjectService subjectService) {
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
    }

}
