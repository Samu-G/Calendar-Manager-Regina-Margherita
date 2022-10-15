package edu.app.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import edu.app.models.Day;
import edu.app.models.Student;
import edu.app.models.Subject;
import edu.app.repository.DayRepository;
import edu.app.repository.StudentRepository;
import edu.app.repository.SubjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    private final DayRepository dayRepository;

    private final SubjectRepository subjectRepository;

    public Student getStudentByNameAndSurname(String name, String surname) {
        return studentRepository.findStudentByNameAndSurname(name, surname);
    }

    @PostMapping
    public void addStudent(ObjectNode json) {
        String name = json.get("name").textValue();
        name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        String surname = json.get("surname").textValue();
        surname = surname.substring(0, 1).toUpperCase() + surname.substring(1).toLowerCase();
        String fiscalCode = json.get("fiscalCode").textValue();
        fiscalCode = fiscalCode.toUpperCase();
        String emailAddress = json.get("emailAddress").textValue();
        emailAddress = emailAddress.toLowerCase();
        boolean isPresent = true;
        List<Day> daysOfPresenceOfTheStudent = new ArrayList<>();
        daysOfPresenceOfTheStudent.add(dayRepository.findDayByDayName("Monday"));
        daysOfPresenceOfTheStudent.add(dayRepository.findDayByDayName("Tuesday"));
        daysOfPresenceOfTheStudent.add(dayRepository.findDayByDayName("Wednesday"));
        daysOfPresenceOfTheStudent.add(dayRepository.findDayByDayName("Thursday"));
        daysOfPresenceOfTheStudent.add(dayRepository.findDayByDayName("Friday"));
        List<Subject> subjectsFollowed = new ArrayList<>();

        Student aNewStudent = new Student(null, name, surname, fiscalCode, emailAddress, isPresent,
                daysOfPresenceOfTheStudent, subjectsFollowed);

        studentRepository.save(aNewStudent);
    }

    @PostMapping
    public void removeStudent(ObjectNode json) {
        Student toRemove = studentRepository.findStudentsById(json.get("studentId").asLong());
        studentRepository.delete(toRemove);
    }

    @PostMapping
    public void setFiscalCodeToStudent(ObjectNode json) {
        Student toEdit = studentRepository.findStudentsById(json.get("studentId").asLong());
        toEdit.setFiscalCode(json.get("newFiscalCode").textValue());
        studentRepository.save(toEdit);
    }

    @PostMapping
    public void setEmailAddressToStudent(ObjectNode json) {
        Student toEdit = studentRepository.findStudentsById(json.get("studentId").asLong());
        toEdit.setEmailAddress(json.get("newEmailAddress").textValue());
        studentRepository.save(toEdit);
    }

    @PostMapping
    public void setPresenceToStudent(ObjectNode json) {
        Student toEdit = studentRepository.findStudentsById(json.get("studentId").asLong());
        toEdit.setPresent(json.get("isPresent").asBoolean());
        studentRepository.save(toEdit);
    }

    @PostMapping
    public List<String> getNameOfTheDaysOfPresenceFromStudent(ObjectNode json) {
        List<String> nameOfTheDaysOfPresenceList = new ArrayList<>();
        Student student = studentRepository.findStudentsById(json.get("studentId").asLong());
        for (Day day : student.getDaysOfPresence()) {
            nameOfTheDaysOfPresenceList.add(day.getDayName());
        }
        return nameOfTheDaysOfPresenceList;
    }

    @PostMapping
    public List<String> getSubjectFollowedByTheStudent(ObjectNode json) {
        List<String> nameOfTheSubjectsFollowed = new ArrayList<>();
        Student student = studentRepository.findStudentsById(json.get("studentId").asLong());
        for (Subject subject : student.getSubjectsFollowed()) {
            nameOfTheSubjectsFollowed.add(subject.getNameOfTheSubject());
        }
        return nameOfTheSubjectsFollowed;
    }

    @PostMapping
    public List<String> getSubjectNotFollowedByTheStudent(ObjectNode json) {
        List<Subject> allSubjects = subjectRepository.findAll();
        List<String> nameOfTheSubjectsNotFollowed = new ArrayList<>();
        Student student = studentRepository.findStudentsById(json.get("studentId").asLong());
        for (Subject subject : allSubjects) {
            if (!student.getSubjectsFollowed().contains(subject)) {
                nameOfTheSubjectsNotFollowed.add(subject.getNameOfTheSubject());
            }
        }
        return nameOfTheSubjectsNotFollowed;
    }

    @PostMapping
    public void removeSubjectFollowedByTheStudent(ObjectNode json) {
        Student student = studentRepository.findStudentsById(json.get("studentId").asLong());
        Subject toRemove = subjectRepository.findSubjectByNameOfTheSubject(json.get("subjectName").textValue());
        student.getSubjectsFollowed().remove(toRemove);
        studentRepository.save(student);
    }

    @PostMapping
    public void addSubjectFollowedByTheStudent(ObjectNode json) {
        Student student = studentRepository.findStudentsById(json.get("studentId").asLong());
        Subject toAdd = subjectRepository.findSubjectByNameOfTheSubject(json.get("subjectName").textValue());
        student.getSubjectsFollowed().add(toAdd);
        studentRepository.save(student);
    }

    @PostMapping
    public void setDaysOfAttendanceToStudent(ObjectNode json) {
        Student student = studentRepository.findStudentsById(json.get("studentId").asLong());
        student.getDaysOfPresence().clear();
        List<Day> daysOfPresence = student.getDaysOfPresence();
        for (JsonNode node : json.get("daysList")) {
            switch (node.textValue()) {
                case "Lunedì" -> daysOfPresence.add(dayRepository.findDayByDayName("Monday"));
                case "Martedì" -> daysOfPresence.add(dayRepository.findDayByDayName("Tuesday"));
                case "Mercoledì" -> daysOfPresence.add(dayRepository.findDayByDayName("Wednesday"));
                case "Giovedì" -> daysOfPresence.add(dayRepository.findDayByDayName("Thursday"));
                case "Venerdì" -> daysOfPresence.add(dayRepository.findDayByDayName("Friday"));
            }
        }
        studentRepository.save(student);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping
    public Student getStudentById(Long id) { return studentRepository.findStudentsById(id);}

    @GetMapping
    public List<Student> getAllStudentsPresentOnMonday() {
        List<Student> studentsList = getAllStudents();
        List<Student> studentsPresentOnMonday = new ArrayList<>();
        for (Student s : studentsList) {
            if (s.getDaysOfPresence().contains(dayRepository.findDayByDayName("Monday"))) {
                studentsPresentOnMonday.add(s);
            }
        }
        return studentsPresentOnMonday;
    }

    @GetMapping
    public List<Student> getAllStudentsPresentOnTuesday() {
        List<Student> studentsList = getAllStudents();
        List<Student> studentsPresentOnTuesday = new ArrayList<>();
        for (Student s : studentsList) {
            if (s.getDaysOfPresence().contains(dayRepository.findDayByDayName("Tuesday"))) {
                studentsPresentOnTuesday.add(s);
            }
        }
        return studentsPresentOnTuesday;
    }

    @GetMapping
    public List<Student> getAllStudentsPresentOnWednesday() {
        List<Student> studentsList = getAllStudents();
        List<Student> studentsPresentOnWednesday = new ArrayList<>();
        for (Student s : studentsList) {
            if (s.getDaysOfPresence().contains(dayRepository.findDayByDayName("Wednesday"))) {
                studentsPresentOnWednesday.add(s);
            }
        }
        return studentsPresentOnWednesday;
    }

    @GetMapping
    public List<Student> getAllStudentsPresentOnThursday() {
        List<Student> studentsList = getAllStudents();
        List<Student> studentsPresentOnThursday = new ArrayList<>();
        for (Student s : studentsList) {
            if (s.getDaysOfPresence().contains(dayRepository.findDayByDayName("Thursday"))) {
                studentsPresentOnThursday.add(s);
            }
        }
        return studentsPresentOnThursday;
    }

    @GetMapping
    public List<Student> getAllStudentsPresentOnFriday() {
        List<Student> studentsList = getAllStudents();
        List<Student> studentsPresentOnFriday = new ArrayList<>();
        for (Student s : studentsList) {
            if (s.getDaysOfPresence().contains(dayRepository.findDayByDayName("Friday"))) {
                studentsPresentOnFriday.add(s);
            }
        }
        return studentsPresentOnFriday;
    }


    @PostMapping
    public void flipPresentToStudent(Student student) {
        studentRepository.flipPresentToStudent(student);
    }

    public List<Student> fetchSchedulableStudentByDayName(String dayName) {
        List<Student> allStudent = studentRepository.findAll();
        List<Student> schedulableStudent = new ArrayList<>();
        for (Student s : allStudent) {
            if (s.getDaysOfPresence().contains(dayRepository.findDayByDayName(dayName))) {
                if(s.isPresent()) {
                    schedulableStudent.add(s);
                }
            }
        }
        System.out.println(dayName);
        System.out.println(schedulableStudent);
        return schedulableStudent;
    }

    public List<Student> getAllStudentsPresentByDayName(String dayNameCalendar) {
        List<Student> allStudent = studentRepository.findAll();
        List<Student> result = new ArrayList<>();
        Day day = dayRepository.findDayByDayName(dayNameCalendar);
        for(Student s : allStudent) {
            if(s.getDaysOfPresence().contains(day) && s.isPresent()) {
                result.add(s);
            }
        }
        return result;
    }
}
