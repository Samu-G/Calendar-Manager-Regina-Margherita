package com.example.demo;

import com.example.demo.models.*;
import com.example.demo.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
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
    CommandLineRunner run(StudentRepository studentRepository,
                          SubjectRepository subjectRepository,
                          TeacherRepository teacherRepository,
                          TimeSlotRepository timeSlotRepository,
                          DayRepository dayRepository,
                          CalendarRepository calendarRepository,
                          CalendarRowRepository calendarRowRepository,
                          CalendarTimeSlotRepository calendarTimeSlotRepository) {

        return args -> {
            createSubjectExample(subjectRepository);

            // Creazione dei giorni con le relative fasce orarie
            Day monday = createMonday(timeSlotRepository, dayRepository);
            Day tuesday = createTuesday(timeSlotRepository, dayRepository);
            Day wednesday = createWednesday(timeSlotRepository, dayRepository);
            Day thursday = createThursday(timeSlotRepository, dayRepository);
            Day friday = createFriday(timeSlotRepository, dayRepository);

            // Creazione del docente "Prova" "Docente"
//            Teacher provaDocente = createProvaDocenteExample(subjectRepository, teacherRepository, timeSlotRepository, monday, tuesday, wednesday, thursday);
//            Student provaStudente = provaStudente(studentRepository, subjectRepository, monday, tuesday, wednesday, thursday);
//
//            // Creazione di un calendario di prova
//            TimeSlot timeSlotReference = timeSlotRepository.findTimeSlotByTimeSlotName("monday_from_08_30_to_09_00");
//            List <Student> studentsInvolved_from_830_to_900 = new ArrayList<>();
//            studentsInvolved_from_830_to_900.add(studentRepository.findStudentByNameAndSurname("Prova", "Studente"));
//            CalendarTimeSlot from_830_to_900 = new CalendarTimeSlot(null, timeSlotReference, studentsInvolved_from_830_to_900);
//            calendarTimeSlotRepository.save(from_830_to_900);
//
//            Teacher teacherInvolved = teacherRepository.findTeacherByNameAndSurname("Prova", "Docente");
//            List<Student> studentsInvolved = new ArrayList<>();
//            List<CalendarTimeSlot> calendarTimeSlotList = new ArrayList<>();
//            calendarTimeSlotList.add(from_830_to_900);
//            CalendarRow calendarRow = new CalendarRow(null, teacherInvolved, studentsInvolved, calendarTimeSlotList);
//            calendarRowRepository.save(calendarRow);
//
//            Date referenceDate = new Date();
//            List<Teacher> teachersInvolvedForCalendar = new ArrayList<>();
//            List<Student> studentsInvolvedForCalendar = new ArrayList<>();
//            teachersInvolvedForCalendar.add(teacherRepository.findTeacherByNameAndSurname("Prova", "Docente"));
//            studentsInvolvedForCalendar.add(studentRepository.findStudentByNameAndSurname("Prova", "Studente"));
//            List<CalendarRow> calendarRowList = new ArrayList<>();
//            calendarRowList.add(calendarRow);
//            Calendar calendar = new Calendar(null, referenceDate, teachersInvolvedForCalendar, studentsInvolvedForCalendar, calendarRowList);
//            calendarRepository.save(calendar);
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

    private static Day createMonday(TimeSlotRepository timeSlotRepository, DayRepository dayRepository) {
        List<TimeSlot> mondayTimeSlots = new ArrayList<>();
        TimeSlot monday_from_08_30_to_09_00 = new TimeSlot(null, "monday_from_08_30_to_09_00", Time.valueOf("08:30:00"), Time.valueOf("09:00:00"));
        TimeSlot monday_from_90_00_to_09_30 = new TimeSlot(null, "monday_from_90_00_to_09_30", Time.valueOf("09:00:00"), Time.valueOf("09:30:00"));
        TimeSlot monday_from_90_30_to_10_00 = new TimeSlot(null, "monday_from_90_30_to_10_00", Time.valueOf("09:30:00"), Time.valueOf("10:00:00"));
        TimeSlot monday_from_10_00_to_10_30 = new TimeSlot(null, "monday_from_10_00_to_10_30", Time.valueOf("10:00:00"), Time.valueOf("10:30:00"));
        TimeSlot monday_from_10_30_to_11_00 = new TimeSlot(null, "monday_from_10_30_to_11_00", Time.valueOf("10:30:00"), Time.valueOf("11:00:00"));
        TimeSlot monday_from_11_00_to_11_30 = new TimeSlot(null, "monday_from_11_00_to_11_30", Time.valueOf("11:00:00"), Time.valueOf("11:30:00"));
        TimeSlot monday_from_11_30_to_12_00 = new TimeSlot(null, "monday_from_11_30_to_12_00", Time.valueOf("11:30:00"), Time.valueOf("12:00:00"));
        TimeSlot monday_from_12_00_to_12_30 = new TimeSlot(null, "monday_from_12_00_to_12_30", Time.valueOf("12:00:00"), Time.valueOf("12:30:00"));
        TimeSlot monday_from_12_30_to_13_00 = new TimeSlot(null, "monday_from_12_30_to_13_00", Time.valueOf("12:30:00"), Time.valueOf("13:00:00"));
        TimeSlot monday_from_13_00_to_13_30 = new TimeSlot(null, "monday_from_13_00_to_13_30", Time.valueOf("13:00:00"), Time.valueOf("13:30:00"));
        TimeSlot monday_from_13_30_to_14_00 = new TimeSlot(null, "monday_from_13_30_to_14_00", Time.valueOf("13:30:00"), Time.valueOf("14:00:00"));
        TimeSlot monday_from_14_00_to_14_30 = new TimeSlot(null, "monday_from_14_00_to_14_30", Time.valueOf("14:00:00"), Time.valueOf("14:30:00"));
        TimeSlot monday_from_14_30_to_15_00 = new TimeSlot(null, "monday_from_14_30_to_15_00", Time.valueOf("14:30:00"), Time.valueOf("15:00:00"));
        TimeSlot monday_from_15_00_to_15_30 = new TimeSlot(null, "monday_from_15_00_to_15_30", Time.valueOf("15:00:00"), Time.valueOf("15:30:00"));
        TimeSlot monday_from_15_30_to_16_00 = new TimeSlot(null, "monday_from_15_30_to_16_00", Time.valueOf("15:30:00"), Time.valueOf("16:00:00"));

        if (timeSlotRepository.findTimeSlotByTimeSlotName("monday_from_08_30_to_09_00") == null)
            timeSlotRepository.save(monday_from_08_30_to_09_00);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("monday_from_90_00_to_09_30") == null)
            timeSlotRepository.save(monday_from_90_00_to_09_30);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("monday_from_90_30_to_10_00") == null)
            timeSlotRepository.save(monday_from_90_30_to_10_00);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("monday_from_10_00_to_10_30") == null)
            timeSlotRepository.save(monday_from_10_00_to_10_30);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("monday_from_10_30_to_11_00") == null)
            timeSlotRepository.save(monday_from_10_30_to_11_00);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("monday_from_11_00_to_11_30") == null)
            timeSlotRepository.save(monday_from_11_00_to_11_30);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("monday_from_11_30_to_12_00") == null)
            timeSlotRepository.save(monday_from_11_30_to_12_00);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("monday_from_12_00_to_12_30") == null)
            timeSlotRepository.save(monday_from_12_00_to_12_30);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("monday_from_12_30_to_13_00") == null)
            timeSlotRepository.save(monday_from_12_30_to_13_00);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("monday_from_13_00_to_13_30") == null)
            timeSlotRepository.save(monday_from_13_00_to_13_30);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("monday_from_13_30_to_14_00") == null)
            timeSlotRepository.save(monday_from_13_30_to_14_00);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("monday_from_14_00_to_14_30") == null)
            timeSlotRepository.save(monday_from_14_00_to_14_30);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("monday_from_14_30_to_15_00") == null)
            timeSlotRepository.save(monday_from_14_30_to_15_00);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("monday_from_15_00_to_15_30") == null)
            timeSlotRepository.save(monday_from_15_00_to_15_30);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("monday_from_15_30_to_16_00") == null)
            timeSlotRepository.save(monday_from_15_30_to_16_00);

        mondayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("monday_from_08_30_to_09_00"));
        mondayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("monday_from_90_00_to_09_30"));
        mondayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("monday_from_90_30_to_10_00"));
        mondayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("monday_from_10_00_to_10_30"));
        mondayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("monday_from_10_30_to_11_00"));
        mondayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("monday_from_11_00_to_11_30"));
        mondayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("monday_from_11_30_to_12_00"));
        mondayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("monday_from_12_00_to_12_30"));
        mondayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("monday_from_12_30_to_13_00"));
        mondayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("monday_from_13_00_to_13_30"));
        mondayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("monday_from_13_30_to_14_00"));
        mondayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("monday_from_14_00_to_14_30"));
        mondayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("monday_from_14_30_to_15_00"));
        mondayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("monday_from_15_00_to_15_30"));
        mondayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("monday_from_15_30_to_16_00"));

        Day monday = new Day(null, "Monday", mondayTimeSlots);
        if (dayRepository.findDayByDayName("Monday") == null)
            dayRepository.save(monday);

        return dayRepository.findDayByDayName("Monday");
    }

    private static Day createTuesday(TimeSlotRepository timeSlotRepository, DayRepository dayRepository) {
        List<TimeSlot> tuesdayTimeSlots = new ArrayList<>();
        TimeSlot tuesday_from_08_30_to_09_00 = new TimeSlot(null, "tuesday_from_08_30_to_09_00", Time.valueOf("08:30:00"), Time.valueOf("09:00:00"));
        TimeSlot tuesday_from_90_00_to_09_30 = new TimeSlot(null, "tuesday_from_90_00_to_09_30", Time.valueOf("09:00:00"), Time.valueOf("09:30:00"));
        TimeSlot tuesday_from_90_30_to_10_00 = new TimeSlot(null, "tuesday_from_90_30_to_10_00", Time.valueOf("09:30:00"), Time.valueOf("10:00:00"));
        TimeSlot tuesday_from_10_00_to_10_30 = new TimeSlot(null, "tuesday_from_10_00_to_10_30", Time.valueOf("10:00:00"), Time.valueOf("10:30:00"));
        TimeSlot tuesday_from_10_30_to_11_00 = new TimeSlot(null, "tuesday_from_10_30_to_11_00", Time.valueOf("10:30:00"), Time.valueOf("11:00:00"));
        TimeSlot tuesday_from_11_00_to_11_30 = new TimeSlot(null, "tuesday_from_11_00_to_11_30", Time.valueOf("11:00:00"), Time.valueOf("11:30:00"));
        TimeSlot tuesday_from_11_30_to_12_00 = new TimeSlot(null, "tuesday_from_11_30_to_12_00", Time.valueOf("11:30:00"), Time.valueOf("12:00:00"));
        TimeSlot tuesday_from_12_00_to_12_30 = new TimeSlot(null, "tuesday_from_12_00_to_12_30", Time.valueOf("12:00:00"), Time.valueOf("12:30:00"));
        TimeSlot tuesday_from_12_30_to_13_00 = new TimeSlot(null, "tuesday_from_12_30_to_13_00", Time.valueOf("12:30:00"), Time.valueOf("13:00:00"));
        TimeSlot tuesday_from_13_00_to_13_30 = new TimeSlot(null, "tuesday_from_13_00_to_13_30", Time.valueOf("13:00:00"), Time.valueOf("13:30:00"));
        TimeSlot tuesday_from_13_30_to_14_00 = new TimeSlot(null, "tuesday_from_13_30_to_14_00", Time.valueOf("13:30:00"), Time.valueOf("14:00:00"));
        TimeSlot tuesday_from_14_00_to_14_30 = new TimeSlot(null, "tuesday_from_14_00_to_14_30", Time.valueOf("14:00:00"), Time.valueOf("14:30:00"));
        TimeSlot tuesday_from_14_30_to_15_00 = new TimeSlot(null, "tuesday_from_14_30_to_15_00", Time.valueOf("14:30:00"), Time.valueOf("15:00:00"));
        TimeSlot tuesday_from_15_00_to_15_30 = new TimeSlot(null, "tuesday_from_15_00_to_15_30", Time.valueOf("15:00:00"), Time.valueOf("15:30:00"));
        TimeSlot tuesday_from_15_30_to_16_00 = new TimeSlot(null, "tuesday_from_15_30_to_16_00", Time.valueOf("15:30:00"), Time.valueOf("16:00:00"));

        if (timeSlotRepository.findTimeSlotByTimeSlotName("tuesday_from_08_30_to_09_00") == null)
            timeSlotRepository.save(tuesday_from_08_30_to_09_00);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("tuesday_from_90_00_to_09_30") == null)
            timeSlotRepository.save(tuesday_from_90_00_to_09_30);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("tuesday_from_90_30_to_10_00") == null)
            timeSlotRepository.save(tuesday_from_90_30_to_10_00);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("tuesday_from_10_00_to_10_30") == null)
            timeSlotRepository.save(tuesday_from_10_00_to_10_30);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("tuesday_from_10_30_to_11_00") == null)
            timeSlotRepository.save(tuesday_from_10_30_to_11_00);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("tuesday_from_11_00_to_11_30") == null)
            timeSlotRepository.save(tuesday_from_11_00_to_11_30);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("tuesday_from_11_30_to_12_00") == null)
            timeSlotRepository.save(tuesday_from_11_30_to_12_00);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("tuesday_from_12_00_to_12_30") == null)
            timeSlotRepository.save(tuesday_from_12_00_to_12_30);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("tuesday_from_12_30_to_13_00") == null)
            timeSlotRepository.save(tuesday_from_12_30_to_13_00);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("tuesday_from_13_00_to_13_30") == null)
            timeSlotRepository.save(tuesday_from_13_00_to_13_30);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("tuesday_from_13_30_to_14_00") == null)
            timeSlotRepository.save(tuesday_from_13_30_to_14_00);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("tuesday_from_14_00_to_14_30") == null)
            timeSlotRepository.save(tuesday_from_14_00_to_14_30);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("tuesday_from_14_30_to_15_00") == null)
            timeSlotRepository.save(tuesday_from_14_30_to_15_00);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("tuesday_from_15_00_to_15_30") == null)
            timeSlotRepository.save(tuesday_from_15_00_to_15_30);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("tuesday_from_15_30_to_16_00") == null)
            timeSlotRepository.save(tuesday_from_15_30_to_16_00);

        tuesdayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("tuesday_from_08_30_to_09_00"));
        tuesdayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("tuesday_from_90_00_to_09_30"));
        tuesdayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("tuesday_from_90_30_to_10_00"));
        tuesdayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("tuesday_from_10_00_to_10_30"));
        tuesdayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("tuesday_from_10_30_to_11_00"));
        tuesdayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("tuesday_from_11_00_to_11_30"));
        tuesdayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("tuesday_from_11_30_to_12_00"));
        tuesdayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("tuesday_from_12_00_to_12_30"));
        tuesdayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("tuesday_from_12_30_to_13_00"));
        tuesdayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("tuesday_from_13_00_to_13_30"));
        tuesdayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("tuesday_from_13_30_to_14_00"));
        tuesdayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("tuesday_from_14_00_to_14_30"));
        tuesdayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("tuesday_from_14_30_to_15_00"));
        tuesdayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("tuesday_from_15_00_to_15_30"));
        tuesdayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("tuesday_from_15_30_to_16_00"));

        Day tuesday = new Day(null, "Tuesday", tuesdayTimeSlots);
        if (dayRepository.findDayByDayName("Tuesday") == null)
            dayRepository.save(tuesday);

        return dayRepository.findDayByDayName("Tuesday");
    }

    private static Day createWednesday(TimeSlotRepository timeSlotRepository, DayRepository dayRepository) {
        List<TimeSlot> wednesdayTimeSlots = new ArrayList<>();
        TimeSlot wednesday_from_08_30_to_09_00 = new TimeSlot(null, "wednesday_from_08_30_to_09_00", Time.valueOf("08:30:00"), Time.valueOf("09:00:00"));
        TimeSlot wednesday_from_90_00_to_09_30 = new TimeSlot(null, "wednesday_from_90_00_to_09_30", Time.valueOf("09:00:00"), Time.valueOf("09:30:00"));
        TimeSlot wednesday_from_90_30_to_10_00 = new TimeSlot(null, "wednesday_from_90_30_to_10_00", Time.valueOf("09:30:00"), Time.valueOf("10:00:00"));
        TimeSlot wednesday_from_10_00_to_10_30 = new TimeSlot(null, "wednesday_from_10_00_to_10_30", Time.valueOf("10:00:00"), Time.valueOf("10:30:00"));
        TimeSlot wednesday_from_10_30_to_11_00 = new TimeSlot(null, "wednesday_from_10_30_to_11_00", Time.valueOf("10:30:00"), Time.valueOf("11:00:00"));
        TimeSlot wednesday_from_11_00_to_11_30 = new TimeSlot(null, "wednesday_from_11_00_to_11_30", Time.valueOf("11:00:00"), Time.valueOf("11:30:00"));
        TimeSlot wednesday_from_11_30_to_12_00 = new TimeSlot(null, "wednesday_from_11_30_to_12_00", Time.valueOf("11:30:00"), Time.valueOf("12:00:00"));
        TimeSlot wednesday_from_12_00_to_12_30 = new TimeSlot(null, "wednesday_from_12_00_to_12_30", Time.valueOf("12:00:00"), Time.valueOf("12:30:00"));
        TimeSlot wednesday_from_12_30_to_13_00 = new TimeSlot(null, "wednesday_from_12_30_to_13_00", Time.valueOf("12:30:00"), Time.valueOf("13:00:00"));
        TimeSlot wednesday_from_13_00_to_13_30 = new TimeSlot(null, "wednesday_from_13_00_to_13_30", Time.valueOf("13:00:00"), Time.valueOf("13:30:00"));
        TimeSlot wednesday_from_13_30_to_14_00 = new TimeSlot(null, "wednesday_from_13_30_to_14_00", Time.valueOf("13:30:00"), Time.valueOf("14:00:00"));
        TimeSlot wednesday_from_14_00_to_14_30 = new TimeSlot(null, "wednesday_from_14_00_to_14_30", Time.valueOf("14:00:00"), Time.valueOf("14:30:00"));
        TimeSlot wednesday_from_14_30_to_15_00 = new TimeSlot(null, "wednesday_from_14_30_to_15_00", Time.valueOf("14:30:00"), Time.valueOf("15:00:00"));
        TimeSlot wednesday_from_15_00_to_15_30 = new TimeSlot(null, "wednesday_from_15_00_to_15_30", Time.valueOf("15:00:00"), Time.valueOf("15:30:00"));
        TimeSlot wednesday_from_15_30_to_16_00 = new TimeSlot(null, "wednesday_from_15_30_to_16_00", Time.valueOf("15:30:00"), Time.valueOf("16:00:00"));

        if (timeSlotRepository.findTimeSlotByTimeSlotName("wednesday_from_08_30_to_09_00") == null)
            timeSlotRepository.save(wednesday_from_08_30_to_09_00);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("wednesday_from_90_00_to_09_30") == null)
            timeSlotRepository.save(wednesday_from_90_00_to_09_30);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("wednesday_from_90_30_to_10_00") == null)
            timeSlotRepository.save(wednesday_from_90_30_to_10_00);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("wednesday_from_10_00_to_10_30") == null)
            timeSlotRepository.save(wednesday_from_10_00_to_10_30);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("wednesday_from_10_30_to_11_00") == null)
            timeSlotRepository.save(wednesday_from_10_30_to_11_00);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("wednesday_from_11_00_to_11_30") == null)
            timeSlotRepository.save(wednesday_from_11_00_to_11_30);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("wednesday_from_11_30_to_12_00") == null)
            timeSlotRepository.save(wednesday_from_11_30_to_12_00);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("wednesday_from_12_00_to_12_30") == null)
            timeSlotRepository.save(wednesday_from_12_00_to_12_30);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("wednesday_from_12_30_to_13_00") == null)
            timeSlotRepository.save(wednesday_from_12_30_to_13_00);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("wednesday_from_13_00_to_13_30") == null)
            timeSlotRepository.save(wednesday_from_13_00_to_13_30);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("wednesday_from_13_30_to_14_00") == null)
            timeSlotRepository.save(wednesday_from_13_30_to_14_00);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("wednesday_from_14_00_to_14_30") == null)
            timeSlotRepository.save(wednesday_from_14_00_to_14_30);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("wednesday_from_14_30_to_15_00") == null)
            timeSlotRepository.save(wednesday_from_14_30_to_15_00);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("wednesday_from_15_00_to_15_30") == null)
            timeSlotRepository.save(wednesday_from_15_00_to_15_30);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("wednesday_from_15_30_to_16_00") == null)
            timeSlotRepository.save(wednesday_from_15_30_to_16_00);

        wednesdayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("wednesday_from_08_30_to_09_00"));
        wednesdayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("wednesday_from_90_00_to_09_30"));
        wednesdayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("wednesday_from_90_30_to_10_00"));
        wednesdayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("wednesday_from_10_00_to_10_30"));
        wednesdayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("wednesday_from_10_30_to_11_00"));
        wednesdayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("wednesday_from_11_00_to_11_30"));
        wednesdayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("wednesday_from_11_30_to_12_00"));
        wednesdayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("wednesday_from_12_00_to_12_30"));
        wednesdayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("wednesday_from_12_30_to_13_00"));
        wednesdayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("wednesday_from_13_00_to_13_30"));
        wednesdayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("wednesday_from_13_30_to_14_00"));
        wednesdayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("wednesday_from_14_00_to_14_30"));
        wednesdayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("wednesday_from_14_30_to_15_00"));
        wednesdayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("wednesday_from_15_00_to_15_30"));
        wednesdayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("wednesday_from_15_30_to_16_00"));

        Day wednesday = new Day(null, "Wednesday", wednesdayTimeSlots);
        if (dayRepository.findDayByDayName("Wednesday") == null)
            dayRepository.save(wednesday);

        return dayRepository.findDayByDayName("Wednesday");
    }

    private static Day createThursday(TimeSlotRepository timeSlotRepository, DayRepository dayRepository) {
        List<TimeSlot> thursdayTimeSlots = new ArrayList<>();
        TimeSlot thursday_from_08_30_to_09_00 = new TimeSlot(null, "thursday_from_08_30_to_09_00", Time.valueOf("08:30:00"), Time.valueOf("09:00:00"));
        TimeSlot thursday_from_90_00_to_09_30 = new TimeSlot(null, "thursday_from_90_00_to_09_30", Time.valueOf("09:00:00"), Time.valueOf("09:30:00"));
        TimeSlot thursday_from_90_30_to_10_00 = new TimeSlot(null, "thursday_from_90_30_to_10_00", Time.valueOf("09:30:00"), Time.valueOf("10:00:00"));
        TimeSlot thursday_from_10_00_to_10_30 = new TimeSlot(null, "thursday_from_10_00_to_10_30", Time.valueOf("10:00:00"), Time.valueOf("10:30:00"));
        TimeSlot thursday_from_10_30_to_11_00 = new TimeSlot(null, "thursday_from_10_30_to_11_00", Time.valueOf("10:30:00"), Time.valueOf("11:00:00"));
        TimeSlot thursday_from_11_00_to_11_30 = new TimeSlot(null, "thursday_from_11_00_to_11_30", Time.valueOf("11:00:00"), Time.valueOf("11:30:00"));
        TimeSlot thursday_from_11_30_to_12_00 = new TimeSlot(null, "thursday_from_11_30_to_12_00", Time.valueOf("11:30:00"), Time.valueOf("12:00:00"));
        TimeSlot thursday_from_12_00_to_12_30 = new TimeSlot(null, "thursday_from_12_00_to_12_30", Time.valueOf("12:00:00"), Time.valueOf("12:30:00"));
        TimeSlot thursday_from_12_30_to_13_00 = new TimeSlot(null, "thursday_from_12_30_to_13_00", Time.valueOf("12:30:00"), Time.valueOf("13:00:00"));
        TimeSlot thursday_from_13_00_to_13_30 = new TimeSlot(null, "thursday_from_13_00_to_13_30", Time.valueOf("13:00:00"), Time.valueOf("13:30:00"));
        TimeSlot thursday_from_13_30_to_14_00 = new TimeSlot(null, "thursday_from_13_30_to_14_00", Time.valueOf("13:30:00"), Time.valueOf("14:00:00"));
        TimeSlot thursday_from_14_00_to_14_30 = new TimeSlot(null, "thursday_from_14_00_to_14_30", Time.valueOf("14:00:00"), Time.valueOf("14:30:00"));
        TimeSlot thursday_from_14_30_to_15_00 = new TimeSlot(null, "thursday_from_14_30_to_15_00", Time.valueOf("14:30:00"), Time.valueOf("15:00:00"));
        TimeSlot thursday_from_15_00_to_15_30 = new TimeSlot(null, "thursday_from_15_00_to_15_30", Time.valueOf("15:00:00"), Time.valueOf("15:30:00"));
        TimeSlot thursday_from_15_30_to_16_00 = new TimeSlot(null, "thursday_from_15_30_to_16_00", Time.valueOf("15:30:00"), Time.valueOf("16:00:00"));

        if (timeSlotRepository.findTimeSlotByTimeSlotName("thursday_from_08_30_to_09_00") == null)
            timeSlotRepository.save(thursday_from_08_30_to_09_00);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("thursday_from_90_00_to_09_30") == null)
            timeSlotRepository.save(thursday_from_90_00_to_09_30);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("thursday_from_90_30_to_10_00") == null)
            timeSlotRepository.save(thursday_from_90_30_to_10_00);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("thursday_from_10_00_to_10_30") == null)
            timeSlotRepository.save(thursday_from_10_00_to_10_30);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("thursday_from_10_30_to_11_00") == null)
            timeSlotRepository.save(thursday_from_10_30_to_11_00);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("thursday_from_11_00_to_11_30") == null)
            timeSlotRepository.save(thursday_from_11_00_to_11_30);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("thursday_from_11_30_to_12_00") == null)
            timeSlotRepository.save(thursday_from_11_30_to_12_00);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("thursday_from_12_00_to_12_30") == null)
            timeSlotRepository.save(thursday_from_12_00_to_12_30);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("thursday_from_12_30_to_13_00") == null)
            timeSlotRepository.save(thursday_from_12_30_to_13_00);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("thursday_from_13_00_to_13_30") == null)
            timeSlotRepository.save(thursday_from_13_00_to_13_30);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("thursday_from_13_30_to_14_00") == null)
            timeSlotRepository.save(thursday_from_13_30_to_14_00);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("thursday_from_14_00_to_14_30") == null)
            timeSlotRepository.save(thursday_from_14_00_to_14_30);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("thursday_from_14_30_to_15_00") == null)
            timeSlotRepository.save(thursday_from_14_30_to_15_00);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("thursday_from_15_00_to_15_30") == null)
            timeSlotRepository.save(thursday_from_15_00_to_15_30);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("thursday_from_15_30_to_16_00") == null)
            timeSlotRepository.save(thursday_from_15_30_to_16_00);

        thursdayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("thursday_from_08_30_to_09_00"));
        thursdayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("thursday_from_90_00_to_09_30"));
        thursdayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("thursday_from_90_30_to_10_00"));
        thursdayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("thursday_from_10_00_to_10_30"));
        thursdayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("thursday_from_10_30_to_11_00"));
        thursdayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("thursday_from_11_00_to_11_30"));
        thursdayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("thursday_from_11_30_to_12_00"));
        thursdayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("thursday_from_12_00_to_12_30"));
        thursdayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("thursday_from_12_30_to_13_00"));
        thursdayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("thursday_from_13_00_to_13_30"));
        thursdayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("thursday_from_13_30_to_14_00"));
        thursdayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("thursday_from_14_00_to_14_30"));
        thursdayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("thursday_from_14_30_to_15_00"));
        thursdayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("thursday_from_15_00_to_15_30"));
        thursdayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("thursday_from_15_30_to_16_00"));

        Day thursday = new Day(null, "Thursday", thursdayTimeSlots);
        if (dayRepository.findDayByDayName("Thursday") == null)
            dayRepository.save(thursday);

        return dayRepository.findDayByDayName("Thursday");
    }

    private static Day createFriday(TimeSlotRepository timeSlotRepository, DayRepository dayRepository) {
        List<TimeSlot> fridayTimeSlots = new ArrayList<>();
        TimeSlot friday_from_08_30_to_09_00 = new TimeSlot(null, "friday_from_08_30_to_09_00", Time.valueOf("08:30:00"), Time.valueOf("09:00:00"));
        TimeSlot friday_from_90_00_to_09_30 = new TimeSlot(null, "friday_from_90_00_to_09_30", Time.valueOf("09:00:00"), Time.valueOf("09:30:00"));
        TimeSlot friday_from_90_30_to_10_00 = new TimeSlot(null, "friday_from_90_30_to_10_00", Time.valueOf("09:30:00"), Time.valueOf("10:00:00"));
        TimeSlot friday_from_10_00_to_10_30 = new TimeSlot(null, "friday_from_10_00_to_10_30", Time.valueOf("10:00:00"), Time.valueOf("10:30:00"));
        TimeSlot friday_from_10_30_to_11_00 = new TimeSlot(null, "friday_from_10_30_to_11_00", Time.valueOf("10:30:00"), Time.valueOf("11:00:00"));
        TimeSlot friday_from_11_00_to_11_30 = new TimeSlot(null, "friday_from_11_00_to_11_30", Time.valueOf("11:00:00"), Time.valueOf("11:30:00"));
        TimeSlot friday_from_11_30_to_12_00 = new TimeSlot(null, "friday_from_11_30_to_12_00", Time.valueOf("11:30:00"), Time.valueOf("12:00:00"));
        TimeSlot friday_from_12_00_to_12_30 = new TimeSlot(null, "friday_from_12_00_to_12_30", Time.valueOf("12:00:00"), Time.valueOf("12:30:00"));
        TimeSlot friday_from_12_30_to_13_00 = new TimeSlot(null, "friday_from_12_30_to_13_00", Time.valueOf("12:30:00"), Time.valueOf("13:00:00"));
        TimeSlot friday_from_13_00_to_13_30 = new TimeSlot(null, "friday_from_13_00_to_13_30", Time.valueOf("13:00:00"), Time.valueOf("13:30:00"));
        TimeSlot friday_from_13_30_to_14_00 = new TimeSlot(null, "friday_from_13_30_to_14_00", Time.valueOf("13:30:00"), Time.valueOf("14:00:00"));
        TimeSlot friday_from_14_00_to_14_30 = new TimeSlot(null, "friday_from_14_00_to_14_30", Time.valueOf("14:00:00"), Time.valueOf("14:30:00"));
        TimeSlot friday_from_14_30_to_15_00 = new TimeSlot(null, "friday_from_14_30_to_15_00", Time.valueOf("14:30:00"), Time.valueOf("15:00:00"));
        TimeSlot friday_from_15_00_to_15_30 = new TimeSlot(null, "friday_from_15_00_to_15_30", Time.valueOf("15:00:00"), Time.valueOf("15:30:00"));
        TimeSlot friday_from_15_30_to_16_00 = new TimeSlot(null, "friday_from_15_30_to_16_00", Time.valueOf("15:30:00"), Time.valueOf("16:00:00"));

        if (timeSlotRepository.findTimeSlotByTimeSlotName("friday_from_08_30_to_09_00") == null)
            timeSlotRepository.save(friday_from_08_30_to_09_00);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("friday_from_90_00_to_09_30") == null)
            timeSlotRepository.save(friday_from_90_00_to_09_30);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("friday_from_90_30_to_10_00") == null)
            timeSlotRepository.save(friday_from_90_30_to_10_00);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("friday_from_10_00_to_10_30") == null)
            timeSlotRepository.save(friday_from_10_00_to_10_30);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("friday_from_10_30_to_11_00") == null)
            timeSlotRepository.save(friday_from_10_30_to_11_00);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("friday_from_11_00_to_11_30") == null)
            timeSlotRepository.save(friday_from_11_00_to_11_30);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("friday_from_11_30_to_12_00") == null)
            timeSlotRepository.save(friday_from_11_30_to_12_00);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("friday_from_12_00_to_12_30") == null)
            timeSlotRepository.save(friday_from_12_00_to_12_30);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("friday_from_12_30_to_13_00") == null)
            timeSlotRepository.save(friday_from_12_30_to_13_00);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("friday_from_13_00_to_13_30") == null)
            timeSlotRepository.save(friday_from_13_00_to_13_30);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("friday_from_13_30_to_14_00") == null)
            timeSlotRepository.save(friday_from_13_30_to_14_00);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("friday_from_14_00_to_14_30") == null)
            timeSlotRepository.save(friday_from_14_00_to_14_30);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("friday_from_14_30_to_15_00") == null)
            timeSlotRepository.save(friday_from_14_30_to_15_00);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("friday_from_15_00_to_15_30") == null)
            timeSlotRepository.save(friday_from_15_00_to_15_30);
        if (timeSlotRepository.findTimeSlotByTimeSlotName("friday_from_15_30_to_16_00") == null)
            timeSlotRepository.save(friday_from_15_30_to_16_00);

        fridayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("friday_from_08_30_to_09_00"));
        fridayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("friday_from_90_00_to_09_30"));
        fridayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("friday_from_90_30_to_10_00"));
        fridayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("friday_from_10_00_to_10_30"));
        fridayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("friday_from_10_30_to_11_00"));
        fridayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("friday_from_11_00_to_11_30"));
        fridayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("friday_from_11_30_to_12_00"));
        fridayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("friday_from_12_00_to_12_30"));
        fridayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("friday_from_12_30_to_13_00"));
        fridayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("friday_from_13_00_to_13_30"));
        fridayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("friday_from_13_30_to_14_00"));
        fridayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("friday_from_14_00_to_14_30"));
        fridayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("friday_from_14_30_to_15_00"));
        fridayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("friday_from_15_00_to_15_30"));
        fridayTimeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotName("friday_from_15_30_to_16_00"));

        Day friday = new Day(null, "Friday", fridayTimeSlots);
        if (dayRepository.findDayByDayName("Friday") == null)
            dayRepository.save(friday);

        return dayRepository.findDayByDayName("Friday");
    }

    private static Student provaStudente(StudentRepository studentRepository, SubjectRepository subjectRepository, Day monday, Day tuesday, Day wednesday, Day thursday) {
        String name = "Prova";
        String surname = "Studente";
        String fiscalCode = "PRVSRD97P28H612L";
        String emailAddress = "prova.studente@gmail.com";
        int currentYear = 1;
        boolean isPresent = true;
        List<Day> daysOfPresenceOfTheStudent = new ArrayList<>();
        List<Subject> subjectsFollowed = new ArrayList<>();

        daysOfPresenceOfTheStudent.add(monday);
        daysOfPresenceOfTheStudent.add(tuesday);
        daysOfPresenceOfTheStudent.add(wednesday);
        daysOfPresenceOfTheStudent.add(thursday);

        subjectsFollowed.add(subjectRepository.findSubjectByNameOfTheSubject("Italiano"));
        subjectsFollowed.add(subjectRepository.findSubjectByNameOfTheSubject("Storia"));

        Student provaStudente = new Student(null, name, surname, fiscalCode, emailAddress, 1, isPresent,
                daysOfPresenceOfTheStudent, subjectsFollowed);

        if(studentRepository.findStudentByNameAndSurname(name, surname) == null)
            studentRepository.save(provaStudente);

        return studentRepository.findStudentByNameAndSurname(name, surname);
    }

    private static Teacher createProvaDocenteExample(SubjectRepository subjectRepository, TeacherRepository teacherRepository, TimeSlotRepository timeSlotRepository, Day monday, Day tuesday, Day wednesday, Day thursday) {
        String name = "Prova";
        String surname = "Docente";
        String fiscalCode = "PRVDCN97P28H612L";
        String emailAddress = "prova.docente@gmail.com";
        List<Day> daysOfPresenceOfTheTeacher = new ArrayList<>();
        List<TimeSlot> timeSlotsOfPresenceOnMonday = new ArrayList<>();
        List<TimeSlot> timeSlotsOfPresenceOnTuesday = new ArrayList<>();
        List<TimeSlot> timeSlotsOfPresenceOnWednesday = new ArrayList<>();
        List<TimeSlot> timeSlotsOfPresenceOnThursday = new ArrayList<>();
        List<TimeSlot> timeSlotsOfPresenceOnFriday = new ArrayList<>();
        List<Subject> subjectsTeached = new ArrayList<>();

        daysOfPresenceOfTheTeacher.add(monday);
        daysOfPresenceOfTheTeacher.add(tuesday);
        daysOfPresenceOfTheTeacher.add(wednesday);
        daysOfPresenceOfTheTeacher.add(thursday);

        timeSlotsOfPresenceOnMonday.add(timeSlotRepository.findTimeSlotByTimeSlotName("monday_from_08_30_to_09_00"));
        timeSlotsOfPresenceOnTuesday.add(timeSlotRepository.findTimeSlotByTimeSlotName("monday_from_08_30_to_09_00"));
        timeSlotsOfPresenceOnWednesday.add(timeSlotRepository.findTimeSlotByTimeSlotName("monday_from_08_30_to_09_00"));
        timeSlotsOfPresenceOnThursday.add(timeSlotRepository.findTimeSlotByTimeSlotName("monday_from_08_30_to_09_00"));
        timeSlotsOfPresenceOnFriday.add(timeSlotRepository.findTimeSlotByTimeSlotName("monday_from_08_30_to_09_00"));

        subjectsTeached.add(subjectRepository.findSubjectByNameOfTheSubject("Italiano"));
        subjectsTeached.add(subjectRepository.findSubjectByNameOfTheSubject("Storia"));

        Teacher provaDocente = new Teacher(null, name, surname, fiscalCode, emailAddress, daysOfPresenceOfTheTeacher,
                timeSlotsOfPresenceOnMonday, timeSlotsOfPresenceOnTuesday, timeSlotsOfPresenceOnWednesday,
                timeSlotsOfPresenceOnThursday, timeSlotsOfPresenceOnFriday, subjectsTeached);

        if(teacherRepository.findTeacherByNameAndSurname(name, surname) == null)
            teacherRepository.save(provaDocente);

        return teacherRepository.findTeacherByNameAndSurname(name, surname);
    }
}
