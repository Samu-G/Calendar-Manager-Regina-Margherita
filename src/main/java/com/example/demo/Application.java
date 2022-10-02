package com.example.demo;

import com.example.demo.models.*;
import com.example.demo.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner run(StudentRepository studentRepository,
                          SubjectRepository subjectRepository,
                          TeacherRepository teacherRepository,
                          DayRepository dayRepository) {

        return args -> {
            createSubjectExample(subjectRepository);

            // Creazione dei giorni con le relative fasce orarie
            List<Day> dayList = new ArrayList<>();
            dayList.add(createMonday(dayRepository));
            dayList.add(createTuesday(dayRepository));
            dayList.add(createWednesday(dayRepository));
            dayList.add(createThursday(dayRepository));
            dayList.add(createFriday(dayRepository));

            createProvaDocenteExample(subjectRepository, teacherRepository, dayList);
            provaStudente(studentRepository, subjectRepository, dayList);
        };
    }

    private static void createSubjectExample(SubjectRepository subjectRepository) {
        Subject italiano = new Subject(null, "Italiano");
        Subject storia = new Subject(null, "Storia");
        Subject latino = new Subject(null, "Latino");
        Subject greco = new Subject(null, "Greco");
        Subject matematica = new Subject(null, "Matematica");
        Subject fisica = new Subject(null, "Fisica");
        Subject scienzeDellaTerra = new Subject(null, "Scienze della terra");
        Subject chimica = new Subject(null, "Chimica");
        Subject biologia = new Subject(null, "Biologia");
        Subject dirittoEdEconomia = new Subject(null, "Diritto ed economia");
        Subject inglese = new Subject(null, "Inglese");
        Subject francese = new Subject(null, "Francese");
        Subject spagnolo = new Subject(null, "Spagnolo");
        Subject tedesco = new Subject(null, "Tedesco");
        Subject filosofia = new Subject(null, "Filosofia");
        Subject scienzeUmane = new Subject(null, "Scienze umane");
        Subject economia = new Subject(null, "Economia");
        Subject informatica = new Subject(null, "Informatica");
        Subject storiaDellArte = new Subject(null, "Storia dell'arte");


        if (subjectRepository.findSubjectByNameOfTheSubject("Italiano") == null) subjectRepository.save(italiano);
        if (subjectRepository.findSubjectByNameOfTheSubject("Storia") == null) subjectRepository.save(storia);
        if (subjectRepository.findSubjectByNameOfTheSubject("Latino") == null) subjectRepository.save(latino);
        if (subjectRepository.findSubjectByNameOfTheSubject("Greco") == null) subjectRepository.save(greco);
        if (subjectRepository.findSubjectByNameOfTheSubject("Matematica") == null) subjectRepository.save(matematica);
        if (subjectRepository.findSubjectByNameOfTheSubject("Fisica") == null) subjectRepository.save(fisica);
        if (subjectRepository.findSubjectByNameOfTheSubject("Scienze della terra") == null)
            subjectRepository.save(scienzeDellaTerra);
        if (subjectRepository.findSubjectByNameOfTheSubject("Chimica") == null) subjectRepository.save(chimica);
        if (subjectRepository.findSubjectByNameOfTheSubject("Biologia") == null) subjectRepository.save(biologia);
        if (subjectRepository.findSubjectByNameOfTheSubject("Diritto ed economia") == null)
            subjectRepository.save(dirittoEdEconomia);
        if (subjectRepository.findSubjectByNameOfTheSubject("Inglese") == null) subjectRepository.save(inglese);
        if (subjectRepository.findSubjectByNameOfTheSubject("Francese") == null) subjectRepository.save(francese);
        if (subjectRepository.findSubjectByNameOfTheSubject("Spagnolo") == null) subjectRepository.save(spagnolo);
        if (subjectRepository.findSubjectByNameOfTheSubject("Tedesco") == null) subjectRepository.save(tedesco);
        if (subjectRepository.findSubjectByNameOfTheSubject("Filosofia") == null) subjectRepository.save(filosofia);
        if (subjectRepository.findSubjectByNameOfTheSubject("Scienze umane") == null) subjectRepository.save(scienzeUmane);
        if (subjectRepository.findSubjectByNameOfTheSubject("Economia") == null) subjectRepository.save(economia);
        if (subjectRepository.findSubjectByNameOfTheSubject("Informatica") == null) subjectRepository.save(informatica);
        if (subjectRepository.findSubjectByNameOfTheSubject("Storia dell'arte") == null) subjectRepository.save(storiaDellArte);
    }

    private static Day createMonday(DayRepository dayRepository) {
        Day monday = new Day(null, "Monday");
        if (dayRepository.findDayByDayName("Monday") == null)
            dayRepository.save(monday);

        return dayRepository.findDayByDayName("Monday");
    }

    private static Day createTuesday(DayRepository dayRepository) {
        Day tuesday = new Day(null, "Tuesday");
        if (dayRepository.findDayByDayName("Tuesday") == null)
            dayRepository.save(tuesday);

        return dayRepository.findDayByDayName("Tuesday");
    }

    private static Day createWednesday(DayRepository dayRepository) {
        Day wednesday = new Day(null, "Wednesday");
        if (dayRepository.findDayByDayName("Wednesday") == null)
            dayRepository.save(wednesday);

        return dayRepository.findDayByDayName("Wednesday");
    }

    private static Day createThursday(DayRepository dayRepository) {
        Day thursday = new Day(null, "Thursday");
        if (dayRepository.findDayByDayName("Thursday") == null)
            dayRepository.save(thursday);

        return dayRepository.findDayByDayName("Thursday");
    }

    private static Day createFriday(DayRepository dayRepository) {
        Day friday = new Day(null, "Friday");
        if (dayRepository.findDayByDayName("Friday") == null)
            dayRepository.save(friday);

        return dayRepository.findDayByDayName("Friday");
    }

    private static void provaStudente(StudentRepository studentRepository, SubjectRepository subjectRepository, List<Day> days) {
        String name = "Prova";
        String surname = "Studente";
        String fiscalCode = "PRVSRD97P28H612L";
        String emailAddress = "prova.studente@gmail.com";
        boolean isPresent = true;
        List<Day> daysOfPresenceOfTheStudent = new ArrayList<>();
        List<Subject> subjectsFollowed = new ArrayList<>();

        daysOfPresenceOfTheStudent.add(days.get(0));
        daysOfPresenceOfTheStudent.add(days.get(1));
        daysOfPresenceOfTheStudent.add(days.get(2));
        daysOfPresenceOfTheStudent.add(days.get(3));
        daysOfPresenceOfTheStudent.add(days.get(4));

        subjectsFollowed.add(subjectRepository.findSubjectByNameOfTheSubject("Italiano"));
        subjectsFollowed.add(subjectRepository.findSubjectByNameOfTheSubject("Storia"));

        Student provaStudente = new Student(null, name, surname, fiscalCode, emailAddress,  isPresent,
                daysOfPresenceOfTheStudent, subjectsFollowed);

        if(studentRepository.findStudentByNameAndSurname(name, surname) == null)
            studentRepository.save(provaStudente);
    }

    private static void createProvaDocenteExample(SubjectRepository subjectRepository, TeacherRepository teacherRepository, List<Day> days) {
        String name = "Prova";
        String surname = "Docente";
        String emailAddress = "prova.docente@gmail.com";
        List<Day> daysOfPresenceOfTheTeacher = new ArrayList<>();
        List<TimeSlotAttendanceRules> timeSlotsOfPresence = new ArrayList<>();
        List<Subject> subjectsTeached = new ArrayList<>();

        daysOfPresenceOfTheTeacher.add(days.get(0));
        daysOfPresenceOfTheTeacher.add(days.get(1));
        daysOfPresenceOfTheTeacher.add(days.get(2));
        daysOfPresenceOfTheTeacher.add(days.get(3));
        daysOfPresenceOfTheTeacher.add(days.get(4));

        subjectsTeached.add(subjectRepository.findSubjectByNameOfTheSubject("Italiano"));
        subjectsTeached.add(subjectRepository.findSubjectByNameOfTheSubject("Storia"));

        Teacher provaDocente = new Teacher(null, name, surname, emailAddress, true, daysOfPresenceOfTheTeacher,
                timeSlotsOfPresence, subjectsTeached);

        if(teacherRepository.findTeacherByNameAndSurname(name, surname) == null)
            teacherRepository.save(provaDocente);
    }
}
