package edu.app;

import edu.app.models.*;
import edu.app.repository.*;
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
                          AttendanceRulesRepository attendanceRulesRepository,
                          DayRepository dayRepository) {

        return args -> {
            createSubjectExample(subjectRepository);

            List<Day> dayList = new ArrayList<>();
            Day monday = createMonday(dayRepository);
            Day tuesday = createTuesday(dayRepository);
            Day wednesday = createWednesday(dayRepository);
            Day thursday = createThursday(dayRepository);
            Day friday = createFriday(dayRepository);
            dayList.add(monday);
            dayList.add(tuesday);
            dayList.add(wednesday);
            dayList.add(thursday);
            dayList.add(friday);

//            studentExample_Activated_Lun_ItaStoLatino(studentRepository, subjectRepository, dayList);
//            studentExample_Activated_LunMarMerGioVen_ItaStoLatino(studentRepository, subjectRepository, dayList);
//            studentExample_Activated_LunMarMerGioVen_ItaStoLatino_WithFiscalCode(studentRepository, subjectRepository, dayList);
//            studentExample_Activated_LunMarMer_EngFraDe(studentRepository, subjectRepository, dayList);
//            studentExample_Activated_NoDay_EngFraDe(studentRepository, subjectRepository, dayList);
//            studentExample_Deactivated_NoDay_EngFraDe(studentRepository, subjectRepository, dayList);
//            studentExample_Activated_GioVen_NoSubject(studentRepository, subjectRepository, dayList);
//            studentExample_Deactivated_LunMarMerGioVen_NoSubject(studentRepository, subjectRepository, dayList);
//
//            teacherExample_Activated_LunMarMer_ItaStoLatino(teacherRepository, subjectRepository, attendanceRulesRepository, dayList);
//            teacherExample_Activated_GioVen_ItaStoLatino(teacherRepository, subjectRepository, attendanceRulesRepository, dayList);
//            teacherExample_Deactivated_LunMarMerGioVen_EcoInfoMate(teacherRepository, subjectRepository, attendanceRulesRepository, dayList);
//            teacherExample_Activated_LunMarMerGioVen_EcoInfoMate(teacherRepository, subjectRepository, attendanceRulesRepository, dayList);
//            teacherExample_Activated_LunMarMerGioVen_EngFraDe(teacherRepository, subjectRepository, attendanceRulesRepository, dayList);
//            teacherExample_Activated_LunMarMerGioVen_EngFraDe_fragmentedAttendanceRules(teacherRepository, subjectRepository, attendanceRulesRepository, dayList);
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
        if (subjectRepository.findSubjectByNameOfTheSubject("Scienze umane") == null)
            subjectRepository.save(scienzeUmane);
        if (subjectRepository.findSubjectByNameOfTheSubject("Economia") == null) subjectRepository.save(economia);
        if (subjectRepository.findSubjectByNameOfTheSubject("Informatica") == null) subjectRepository.save(informatica);
        if (subjectRepository.findSubjectByNameOfTheSubject("Storia dell'arte") == null)
            subjectRepository.save(storiaDellArte);
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

    private static void studentExample_Activated_Lun_ItaStoLatino(StudentRepository studentRepository, SubjectRepository subjectRepository, List<Day> days) {
        boolean activated = true;
        List<Day> daysOfPresenceOfTheStudent = new ArrayList<>();
        daysOfPresenceOfTheStudent.add(days.get(0));
        List<Subject> subjectsFollowed = new ArrayList<>();
        subjectsFollowed.add(subjectRepository.findSubjectByNameOfTheSubject("Italiano"));
        subjectsFollowed.add(subjectRepository.findSubjectByNameOfTheSubject("Storia"));
        subjectsFollowed.add(subjectRepository.findSubjectByNameOfTheSubject("Latino"));
        Student s = new Student(null, "Ita-storia-latino", "Lun-attivo", "", "", activated, daysOfPresenceOfTheStudent, subjectsFollowed);
        if (studentRepository.findStudentByNameAndSurname("Ita-storia-latino", "Lun-attivo") == null)
            studentRepository.save(s);
    }

    private static void studentExample_Activated_LunMarMerGioVen_ItaStoLatino(StudentRepository studentRepository, SubjectRepository subjectRepository, List<Day> days) {
        boolean activated = true;
        List<Day> daysOfPresenceOfTheStudent = new ArrayList<>();
        daysOfPresenceOfTheStudent.add(days.get(0));
        daysOfPresenceOfTheStudent.add(days.get(1));
        daysOfPresenceOfTheStudent.add(days.get(2));
        daysOfPresenceOfTheStudent.add(days.get(3));
        daysOfPresenceOfTheStudent.add(days.get(4));
        List<Subject> subjectsFollowed = new ArrayList<>();
        subjectsFollowed.add(subjectRepository.findSubjectByNameOfTheSubject("Italiano"));
        subjectsFollowed.add(subjectRepository.findSubjectByNameOfTheSubject("Storia"));
        subjectsFollowed.add(subjectRepository.findSubjectByNameOfTheSubject("Latino"));
        Student s = new Student(null, "Ita-storia-latino", "Lun-mar-mer-gio-ven-attivo", "", "", activated, daysOfPresenceOfTheStudent, subjectsFollowed);
        if (studentRepository.findStudentByNameAndSurname("Ita-storia-latino", "Lun-mar-mer-gio-ven-attivo") == null)
            studentRepository.save(s);
    }

    private static void studentExample_Activated_LunMarMerGioVen_ItaStoLatino_WithFiscalCode(StudentRepository studentRepository, SubjectRepository subjectRepository, List<Day> days) {
        boolean activated = true;
        List<Day> daysOfPresenceOfTheStudent = new ArrayList<>();
        daysOfPresenceOfTheStudent.add(days.get(0));
        daysOfPresenceOfTheStudent.add(days.get(1));
        daysOfPresenceOfTheStudent.add(days.get(2));
        daysOfPresenceOfTheStudent.add(days.get(3));
        daysOfPresenceOfTheStudent.add(days.get(4));
        List<Subject> subjectsFollowed = new ArrayList<>();
        subjectsFollowed.add(subjectRepository.findSubjectByNameOfTheSubject("Italiano"));
        subjectsFollowed.add(subjectRepository.findSubjectByNameOfTheSubject("Storia"));
        subjectsFollowed.add(subjectRepository.findSubjectByNameOfTheSubject("Latino"));
        Student s = new Student(null, "Ita-storia-latino", "Lun-mar-mer-gio-ven-attivo", "SSLKCC91H54D868V", "", activated, daysOfPresenceOfTheStudent, subjectsFollowed);
        if (studentRepository.findStudentByNameAndSurname("Ita-storia-latino", "Lun-mar-mer-gio-ven-attivo") == null)
            studentRepository.save(s);
    }

    private static void studentExample_Activated_LunMarMer_EngFraDe(StudentRepository studentRepository, SubjectRepository subjectRepository, List<Day> days) {
        boolean activated = true;
        List<Day> daysOfPresenceOfTheStudent = new ArrayList<>();
        daysOfPresenceOfTheStudent.add(days.get(0));
        daysOfPresenceOfTheStudent.add(days.get(1));
        daysOfPresenceOfTheStudent.add(days.get(2));
        List<Subject> subjectsFollowed = new ArrayList<>();
        subjectsFollowed.add(subjectRepository.findSubjectByNameOfTheSubject("Inglese"));
        subjectsFollowed.add(subjectRepository.findSubjectByNameOfTheSubject("Francese"));
        subjectsFollowed.add(subjectRepository.findSubjectByNameOfTheSubject("Tedesco"));
        Student s = new Student(null, "Inglese-francese-tedesco", "Lun-mar-mer-attivo", "", "", activated, daysOfPresenceOfTheStudent, subjectsFollowed);
        if (studentRepository.findStudentByNameAndSurname("Inglese-francese-tedesco", "Lun-mar-mer-attivo") == null)
            studentRepository.save(s);
    }

    private static void studentExample_Activated_NoDay_EngFraDe(StudentRepository studentRepository, SubjectRepository subjectRepository, List<Day> days) {
        boolean activated = true;
        List<Day> daysOfPresenceOfTheStudent = new ArrayList<>();
        // no day of presence
        List<Subject> subjectsFollowed = new ArrayList<>();
        subjectsFollowed.add(subjectRepository.findSubjectByNameOfTheSubject("Inglese"));
        subjectsFollowed.add(subjectRepository.findSubjectByNameOfTheSubject("Francese"));
        subjectsFollowed.add(subjectRepository.findSubjectByNameOfTheSubject("Tedesco"));
        Student s = new Student(null, "Inglese-francese-tedesco", "No-giorni-attivo", "", "", activated, daysOfPresenceOfTheStudent, subjectsFollowed);
        if (studentRepository.findStudentByNameAndSurname("Inglese-francese-tedesco", "No-giorni-attivo") == null)
            studentRepository.save(s);
    }

    private static void studentExample_Deactivated_NoDay_EngFraDe(StudentRepository studentRepository, SubjectRepository subjectRepository, List<Day> days) {
        boolean activated = false;
        List<Day> daysOfPresenceOfTheStudent = new ArrayList<>();
        // no day of presence
        List<Subject> subjectsFollowed = new ArrayList<>();
        subjectsFollowed.add(subjectRepository.findSubjectByNameOfTheSubject("Inglese"));
        subjectsFollowed.add(subjectRepository.findSubjectByNameOfTheSubject("Francese"));
        subjectsFollowed.add(subjectRepository.findSubjectByNameOfTheSubject("Tedesco"));
        Student s = new Student(null, "Inglese-francese-tedesco", "No-giorni-disattivo", "", "", activated, daysOfPresenceOfTheStudent, subjectsFollowed);
        if (studentRepository.findStudentByNameAndSurname("Inglese-francese-tedesco", "No-giorni-disattivo") == null)
            studentRepository.save(s);
    }

    private static void studentExample_Activated_GioVen_NoSubject(StudentRepository studentRepository, SubjectRepository subjectRepository, List<Day> days) {
        boolean activated = true;
        List<Day> daysOfPresenceOfTheStudent = new ArrayList<>();
        daysOfPresenceOfTheStudent.add(days.get(3));
        daysOfPresenceOfTheStudent.add(days.get(4));
        List<Subject> subjectsFollowed = new ArrayList<>();
        // no subject followed
        Student s = new Student(null, "Nessuna-materia-seguita", "Gio-ven-attivo", "", "", activated, daysOfPresenceOfTheStudent, subjectsFollowed);
        if (studentRepository.findStudentByNameAndSurname("Nessuna-materia-seguita", "Gio-ven-attivo") == null)
            studentRepository.save(s);
    }

    private static void studentExample_Deactivated_LunMarMerGioVen_NoSubject(StudentRepository studentRepository, SubjectRepository subjectRepository, List<Day> days) {
        boolean activated = false;
        List<Day> daysOfPresenceOfTheStudent = new ArrayList<>();
        daysOfPresenceOfTheStudent.add(days.get(0));
        daysOfPresenceOfTheStudent.add(days.get(1));
        daysOfPresenceOfTheStudent.add(days.get(2));
        daysOfPresenceOfTheStudent.add(days.get(3));
        daysOfPresenceOfTheStudent.add(days.get(4));
        List<Subject> subjectsFollowed = new ArrayList<>();
        // no subject followed
        Student s = new Student(null, "Nessuna-materia-seguita", "Lun-mar-mer-gio-ven-disattivo", "", "", activated, daysOfPresenceOfTheStudent, subjectsFollowed);
        if (studentRepository.findStudentByNameAndSurname("Nessuna-materia-seguita", "Lun-mar-mer-gio-ven-disattivo") == null)
            studentRepository.save(s);
    }

    private static void teacherExample_Activated_LunMarMer_ItaStoLatino(TeacherRepository teacherRepository, SubjectRepository subjectRepository, AttendanceRulesRepository attendanceRulesRepository, List<Day> days) {
        boolean activated = true;
        List<Day> daysOfPresenceOfTheTeacher = new ArrayList<>();
        daysOfPresenceOfTheTeacher.add(days.get(0));
        daysOfPresenceOfTheTeacher.add(days.get(1));
        daysOfPresenceOfTheTeacher.add(days.get(2));
        List<Subject> subjectsTeached = new ArrayList<>();
        subjectsTeached.add(subjectRepository.findSubjectByNameOfTheSubject("Italiano"));
        subjectsTeached.add(subjectRepository.findSubjectByNameOfTheSubject("Storia"));
        subjectsTeached.add(subjectRepository.findSubjectByNameOfTheSubject("Latino"));
        List<AttendanceRules> attendanceRules = new ArrayList<>();
        attendanceRules.add(attendanceRulesRepository.save(new AttendanceRules(null, "Monday", "08:00", "17:00")));
        attendanceRules.add(attendanceRulesRepository.save(new AttendanceRules(null, "Tuesday", "08:00", "17:00")));
        attendanceRules.add(attendanceRulesRepository.save(new AttendanceRules(null, "Wednesday", "08:00", "17:00")));
        attendanceRules.add(attendanceRulesRepository.save(new AttendanceRules(null, "Thursday", "08:00", "17:00")));
        attendanceRules.add(attendanceRulesRepository.save(new AttendanceRules(null, "Friday", "08:00", "17:00")));
        Teacher t = new Teacher(null, "Ita-storia-latino", "Lun-mar-mer-attivo", "", activated, daysOfPresenceOfTheTeacher, attendanceRules, subjectsTeached);
        if (teacherRepository.findTeacherByNameAndSurname("Ita-storia-latino", "Lun-mar-mer-attivo") == null) {
            teacherRepository.save(t);
        }
    }

    private static void teacherExample_Activated_GioVen_ItaStoLatino(TeacherRepository teacherRepository, SubjectRepository subjectRepository, AttendanceRulesRepository attendanceRulesRepository, List<Day> days) {
        boolean activated = true;
        List<Day> daysOfPresenceOfTheTeacher = new ArrayList<>();
        daysOfPresenceOfTheTeacher.add(days.get(3));
        daysOfPresenceOfTheTeacher.add(days.get(4));
        List<Subject> subjectsTeached = new ArrayList<>();
        subjectsTeached.add(subjectRepository.findSubjectByNameOfTheSubject("Italiano"));
        subjectsTeached.add(subjectRepository.findSubjectByNameOfTheSubject("Storia"));
        subjectsTeached.add(subjectRepository.findSubjectByNameOfTheSubject("Latino"));
        List<AttendanceRules> attendanceRules = new ArrayList<>();
        attendanceRules.add(attendanceRulesRepository.save(new AttendanceRules(null, "Monday", "08:00", "17:00")));
        attendanceRules.add(attendanceRulesRepository.save(new AttendanceRules(null, "Tuesday", "08:00", "17:00")));
        attendanceRules.add(attendanceRulesRepository.save(new AttendanceRules(null, "Wednesday", "08:00", "17:00")));
        attendanceRules.add(attendanceRulesRepository.save(new AttendanceRules(null, "Thursday", "08:00", "17:00")));
        attendanceRules.add(attendanceRulesRepository.save(new AttendanceRules(null, "Friday", "08:00", "17:00")));
        Teacher t = new Teacher(null, "Ita-storia-latino", "Gio-ven-attivo", "", activated, daysOfPresenceOfTheTeacher, attendanceRules, subjectsTeached);
        if (teacherRepository.findTeacherByNameAndSurname("Ita-storia-latino", "Gio-ven-attivo") == null) {
            teacherRepository.save(t);
        }
    }

    private static void teacherExample_Deactivated_LunMarMerGioVen_EcoInfoMate(TeacherRepository teacherRepository, SubjectRepository subjectRepository, AttendanceRulesRepository attendanceRulesRepository, List<Day> days) {
        boolean activated = false;
        List<Day> daysOfPresenceOfTheTeacher = new ArrayList<>();
        daysOfPresenceOfTheTeacher.add(days.get(0));
        daysOfPresenceOfTheTeacher.add(days.get(1));
        daysOfPresenceOfTheTeacher.add(days.get(2));
        daysOfPresenceOfTheTeacher.add(days.get(3));
        daysOfPresenceOfTheTeacher.add(days.get(4));
        List<Subject> subjectsTeached = new ArrayList<>();
        subjectsTeached.add(subjectRepository.findSubjectByNameOfTheSubject("Economia"));
        subjectsTeached.add(subjectRepository.findSubjectByNameOfTheSubject("Informatica"));
        subjectsTeached.add(subjectRepository.findSubjectByNameOfTheSubject("Matematica"));
        List<AttendanceRules> attendanceRules = new ArrayList<>();
        attendanceRules.add(attendanceRulesRepository.save(new AttendanceRules(null, "Monday", "08:00", "17:00")));
        attendanceRules.add(attendanceRulesRepository.save(new AttendanceRules(null, "Tuesday", "08:00", "17:00")));
        attendanceRules.add(attendanceRulesRepository.save(new AttendanceRules(null, "Wednesday", "08:00", "17:00")));
        attendanceRules.add(attendanceRulesRepository.save(new AttendanceRules(null, "Thursday", "08:00", "17:00")));
        attendanceRules.add(attendanceRulesRepository.save(new AttendanceRules(null, "Friday", "08:00", "17:00")));
        Teacher t = new Teacher(null, "Economia-informatica-matematica", "Lun-mar-mer-gio-ven-disattivo", "", activated, daysOfPresenceOfTheTeacher, attendanceRules, subjectsTeached);
        if (teacherRepository.findTeacherByNameAndSurname("Economia-informatica-matematica", "Lun-mar-mer-gio-ven-disattivo") == null) {
            teacherRepository.save(t);
        }
    }

    private static void teacherExample_Activated_LunMarMerGioVen_EcoInfoMate(TeacherRepository teacherRepository, SubjectRepository subjectRepository, AttendanceRulesRepository attendanceRulesRepository, List<Day> days) {
        boolean activated = true;
        List<Day> daysOfPresenceOfTheTeacher = new ArrayList<>();
        daysOfPresenceOfTheTeacher.add(days.get(0));
        daysOfPresenceOfTheTeacher.add(days.get(1));
        daysOfPresenceOfTheTeacher.add(days.get(2));
        daysOfPresenceOfTheTeacher.add(days.get(3));
        daysOfPresenceOfTheTeacher.add(days.get(4));
        List<Subject> subjectsTeached = new ArrayList<>();
        subjectsTeached.add(subjectRepository.findSubjectByNameOfTheSubject("Economia"));
        subjectsTeached.add(subjectRepository.findSubjectByNameOfTheSubject("Informatica"));
        subjectsTeached.add(subjectRepository.findSubjectByNameOfTheSubject("Matematica"));
        List<AttendanceRules> attendanceRules = new ArrayList<>();
        attendanceRules.add(attendanceRulesRepository.save(new AttendanceRules(null, "Monday", "08:00", "17:00")));
        attendanceRules.add(attendanceRulesRepository.save(new AttendanceRules(null, "Tuesday", "08:00", "17:00")));
        attendanceRules.add(attendanceRulesRepository.save(new AttendanceRules(null, "Wednesday", "08:00", "17:00")));
        attendanceRules.add(attendanceRulesRepository.save(new AttendanceRules(null, "Thursday", "08:00", "17:00")));
        attendanceRules.add(attendanceRulesRepository.save(new AttendanceRules(null, "Friday", "08:00", "17:00")));
        Teacher t = new Teacher(null, "Economia-informatica-matematica", "Lun-mar-mer-gio-ven-attivo", "", activated, daysOfPresenceOfTheTeacher, attendanceRules, subjectsTeached);
        if (teacherRepository.findTeacherByNameAndSurname("Economia-informatica-matematica", "Lun-mar-mer-gio-ven-attivo") == null) {
            teacherRepository.save(t);
        }
    }

    private static void teacherExample_Activated_LunMarMerGioVen_EngFraDe(TeacherRepository teacherRepository, SubjectRepository subjectRepository, AttendanceRulesRepository attendanceRulesRepository, List<Day> days) {
        boolean activated = true;
        List<Day> daysOfPresenceOfTheTeacher = new ArrayList<>();
        daysOfPresenceOfTheTeacher.add(days.get(0));
        daysOfPresenceOfTheTeacher.add(days.get(1));
        daysOfPresenceOfTheTeacher.add(days.get(2));
        daysOfPresenceOfTheTeacher.add(days.get(3));
        daysOfPresenceOfTheTeacher.add(days.get(4));
        List<Subject> subjectsTeached = new ArrayList<>();
        subjectsTeached.add(subjectRepository.findSubjectByNameOfTheSubject("Inglese"));
        subjectsTeached.add(subjectRepository.findSubjectByNameOfTheSubject("Francese"));
        subjectsTeached.add(subjectRepository.findSubjectByNameOfTheSubject("Tedesco"));
        List<AttendanceRules> attendanceRules = new ArrayList<>();
        attendanceRules.add(attendanceRulesRepository.save(new AttendanceRules(null, "Monday", "08:00", "17:00")));
        attendanceRules.add(attendanceRulesRepository.save(new AttendanceRules(null, "Tuesday", "08:00", "17:00")));
        attendanceRules.add(attendanceRulesRepository.save(new AttendanceRules(null, "Wednesday", "08:00", "17:00")));
        attendanceRules.add(attendanceRulesRepository.save(new AttendanceRules(null, "Thursday", "08:00", "17:00")));
        attendanceRules.add(attendanceRulesRepository.save(new AttendanceRules(null, "Friday", "08:00", "17:00")));
        Teacher t = new Teacher(null, "Inglese-francese-tedesco", "Lun-mar-mer-gio-ven-attivo", "", activated, daysOfPresenceOfTheTeacher, attendanceRules, subjectsTeached);
        if (teacherRepository.findTeacherByNameAndSurname("Inglese-francese-tedesco", "Lun-mar-mer-gio-ven-attivo") == null) {
            teacherRepository.save(t);
        }
    }

    private static void teacherExample_Activated_LunMarMerGioVen_EngFraDe_fragmentedAttendanceRules(TeacherRepository teacherRepository, SubjectRepository subjectRepository, AttendanceRulesRepository attendanceRulesRepository, List<Day> days) {
        boolean activated = true;
        List<Day> daysOfPresenceOfTheTeacher = new ArrayList<>();
        daysOfPresenceOfTheTeacher.add(days.get(0));
        daysOfPresenceOfTheTeacher.add(days.get(1));
        daysOfPresenceOfTheTeacher.add(days.get(2));
        daysOfPresenceOfTheTeacher.add(days.get(3));
        daysOfPresenceOfTheTeacher.add(days.get(4));
        List<Subject> subjectsTeached = new ArrayList<>();
        subjectsTeached.add(subjectRepository.findSubjectByNameOfTheSubject("Inglese"));
        subjectsTeached.add(subjectRepository.findSubjectByNameOfTheSubject("Francese"));
        subjectsTeached.add(subjectRepository.findSubjectByNameOfTheSubject("Tedesco"));
        List<AttendanceRules> attendanceRules = new ArrayList<>();
        attendanceRules.add(attendanceRulesRepository.save(new AttendanceRules(null, "Monday", "08:00", "12:00")));
        attendanceRules.add(attendanceRulesRepository.save(new AttendanceRules(null, "Monday", "14:00", "16:00")));

        attendanceRules.add(attendanceRulesRepository.save(new AttendanceRules(null, "Tuesday", "10:00", "12:00")));
        attendanceRules.add(attendanceRulesRepository.save(new AttendanceRules(null, "Tuesday", "15:00", "17:00")));

        attendanceRules.add(attendanceRulesRepository.save(new AttendanceRules(null, "Wednesday", "08:00", "17:00")));
        attendanceRules.add(attendanceRulesRepository.save(new AttendanceRules(null, "Thursday", "08:00", "17:00")));
        attendanceRules.add(attendanceRulesRepository.save(new AttendanceRules(null, "Friday", "08:00", "17:00")));

        Teacher t = new Teacher(null, "Inglese-francese-tedesco", "Lun 8/12 14/16 - mar 10/12 15/17 - mer-gio-ven-attivo", "", activated, daysOfPresenceOfTheTeacher, attendanceRules, subjectsTeached);
        if (teacherRepository.findTeacherByNameAndSurname("Inglese-francese-tedesco", "Lun-mar-mer-gio-ven-attivo") == null) {
            teacherRepository.save(t);
        }
    }
}
