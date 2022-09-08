package com.example.demo.services;

import com.example.demo.models.Day;
import com.example.demo.models.Student;
import com.example.demo.models.Subject;
import com.example.demo.models.Teacher;
import com.example.demo.repository.DayRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.SubjectRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
        int currentYear = 1;
        boolean isPresent = true;
        List<Day> daysOfPresenceOfTheStudent = new ArrayList<>();
        daysOfPresenceOfTheStudent.add(dayRepository.findDayByDayName("Monday"));
        daysOfPresenceOfTheStudent.add(dayRepository.findDayByDayName("Tuesday"));
        daysOfPresenceOfTheStudent.add(dayRepository.findDayByDayName("Wednesday"));
        daysOfPresenceOfTheStudent.add(dayRepository.findDayByDayName("Thursday"));
        daysOfPresenceOfTheStudent.add(dayRepository.findDayByDayName("Friday"));
        List<Subject> subjectsFollowed = new ArrayList<>();

        Student aNewStudent = new Student(null, name, surname, fiscalCode, emailAddress, currentYear, isPresent,
                daysOfPresenceOfTheStudent, subjectsFollowed);

        studentRepository.save(aNewStudent);
    }

    @PostMapping
    public void removeStudent(ObjectNode json) {
        Long studentId = json.get("studentId").asLong();
        Student toRemove = studentRepository.findStudentsById(studentId);
        studentRepository.delete(toRemove);
    }

    @PostMapping
    public void setFiscalCodeToStudent(ObjectNode json) {
        Long studentId = json.get("studentId").asLong();
        String fiscalCode = json.get("newFiscalCode").textValue();
        Student toEdit = studentRepository.findStudentsById(studentId);
        toEdit.setFiscalCode(fiscalCode);
        studentRepository.save(toEdit);
    }

    @PostMapping
    public void setEmailAddressToStudent(ObjectNode json) {
        Long studentId = json.get("studentId").asLong();
        String emailAddress = json.get("newEmailAddress").textValue();
        Student toEdit = studentRepository.findStudentsById(studentId);
        toEdit.setEmailAddress(emailAddress);
        studentRepository.save(toEdit);
    }

    @PostMapping
    public void setPresenceToStudent(ObjectNode json) {
        Long studentId = json.get("studentId").asLong();
        boolean isPresent = json.get("isPresent").asBoolean();
        Student toEdit = studentRepository.findStudentsById(studentId);
        toEdit.setPresent(isPresent);
        studentRepository.save(toEdit);
    }

    @GetMapping
    public List<String> getNameOfTheDaysOfPresenceFromStudent(ObjectNode json) {
        List<String> nameOfTheDaysOfPresenceList = new ArrayList<>();
        Long studentId = json.get("studentId").asLong();
        Student student = studentRepository.findStudentsById(studentId);
        for (Day day : student.getDaysOfPresence()) {
            nameOfTheDaysOfPresenceList.add(day.getDayName());
        }
        return nameOfTheDaysOfPresenceList;
    }

    @PostMapping
    public List<String> getSubjectFollowedByTheStudent(ObjectNode json) {
        List<String> nameOfTheSubjectsFollowed = new ArrayList<>();
        Long studentId = json.get("studentId").asLong();
        Student student = studentRepository.findStudentsById(studentId);
        for (Subject subject : student.getSubjectsFollowed()) {
            nameOfTheSubjectsFollowed.add(subject.getNameOfTheSubject());
        }
        return nameOfTheSubjectsFollowed;
    }

    @PostMapping
    public List<String> getSubjectNotFollowedByTheStudent(ObjectNode json) {
        List<Subject> allSubjects = subjectRepository.findAll();
        List<String> nameOfTheSubjectsNotFollowed = new ArrayList<>();
        Long studentId = json.get("studentId").asLong();
        Student student = studentRepository.findStudentsById(studentId);
        for (Subject subject : allSubjects) {
            if (!student.getSubjectsFollowed().contains(subject)) {
                nameOfTheSubjectsNotFollowed.add(subject.getNameOfTheSubject());
            }
        }
        return nameOfTheSubjectsNotFollowed;
    }

    @PostMapping
    public void removeSubjectFollowedByTheStudent(ObjectNode json) {
        Long studentId = json.get("studentId").asLong();
        String subjectName = json.get("subjectName").textValue();
        Student student = studentRepository.findStudentsById(studentId);
        Subject toRemove = subjectRepository.findSubjectByNameOfTheSubject(subjectName);
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
        Long studentId = json.get("studentId").asLong();
        Student student = studentRepository.findStudentsById(studentId);
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


//    @PostMapping
//    public void setDaysOfPresenceToStudent(ObjectNode json) {
//        Long studentId = json.get("id").asLong();
//        JsonNode daysOfPresence = json.get("day");
//        ArrayNode arrayNode = (ArrayNode) daysOfPresence;
//        System.out.println(arrayNode);
//        System.out.println(arrayNode.size());
//        List<String> dayOfPresenceList = new ArrayList<>();
//        for (int i = 0; i < arrayNode.size(); i++) {
//            String dayName = arrayNode.get(i).toString();
//            StringBuilder sb = new StringBuilder(dayName);
//            sb.deleteCharAt(dayName.length() - 1);
//            sb.deleteCharAt(0);
//            dayName = sb.toString();
//            System.out.println(dayName);
//            dayOfPresenceList.add(dayName);
//        }
//        if(dayOfPresenceList.contains("Lunedi")) {
//            studentRepository.setMondayIsPresentToStudent(studentId, "Si");
//        } else {
//            studentRepository.setMondayIsPresentToStudent(studentId, "No");
//        }
//        if(dayOfPresenceList.contains("Martedi")) {
//            studentRepository.setTuesdayIsPresentToStudent(studentId, "Si");
//        } else {
//            studentRepository.setTuesdayIsPresentToStudent(studentId, "No");
//        }
//        if(dayOfPresenceList.contains("Mercoledi")) {
//            studentRepository.setWednesdayIsPresentToStudent(studentId, "Si");
//        } else {
//            studentRepository.setWednesdayIsPresentToStudent(studentId, "No");
//        }
//        if(dayOfPresenceList.contains("Giovedi")) {
//            studentRepository.setThursdayIsPresentToStudent(studentId, "Si");
//        } else {
//            studentRepository.setThursdayIsPresentToStudent(studentId, "No");
//        }
//        if(dayOfPresenceList.contains("Venerdi")) {
//            studentRepository.setFridayIsPresentToStudent(studentId, "Si");
//        } else {
//            studentRepository.setFridayIsPresentToStudent(studentId, "No");
//        }
//    }
//
//    @PostMapping
//    public void setCurrentYearToStudent(ObjectNode json) {
//        Long studentId = json.get("id").asLong();
//        String currentYear = json.get("currentYear").toString();
//        StringBuilder sb = new StringBuilder(json.get("currentYear").toString());
//        sb.deleteCharAt(currentYear.length() - 1);
//        sb.deleteCharAt(0);
//        currentYear = sb.toString();
//        System.out.println(currentYear);
//        studentRepository.setCurrentYearToStudent(studentId, currentYear);
//    }


}
