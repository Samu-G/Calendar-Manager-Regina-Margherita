package com.example.demo.services;

import com.example.demo.models.Student;
import com.example.demo.models.Teacher;
import com.example.demo.repository.DayRepository;
import com.example.demo.repository.StudentRepository;
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

    public Student getStudentByNameAndSurname(String name, String surname) {
        return studentRepository.findStudentByNameAndSurname(name, surname);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping
    public List<Student> getAllStudentsPresentOnMonday() {
        List<Student> studentsList = getAllStudents();
        List<Student> studentsPresentOnMonday = new ArrayList<>();
        for(Student s : studentsList) {
            if(s.getDaysOfPresence().contains(dayRepository.findDayByDayName("Monday"))) {
                studentsPresentOnMonday.add(s);
            }
        }
        return studentsPresentOnMonday;
    }

    @GetMapping
    public List<Student> getAllStudentsPresentOnTuesday() {
        List<Student> studentsList = getAllStudents();
        List<Student> studentsPresentOnTuesday = new ArrayList<>();
        for(Student s : studentsList) {
            if(s.getDaysOfPresence().contains(dayRepository.findDayByDayName("Tuesday"))) {
                studentsPresentOnTuesday.add(s);
            }
        }
        return studentsPresentOnTuesday;
    }

    @GetMapping
    public List<Student> getAllStudentsPresentOnWednesday() {
        List<Student> studentsList = getAllStudents();
        List<Student> studentsPresentOnWednesday = new ArrayList<>();
        for(Student s : studentsList) {
            if(s.getDaysOfPresence().contains(dayRepository.findDayByDayName("Wednesday"))) {
                studentsPresentOnWednesday.add(s);
            }
        }
        return studentsPresentOnWednesday;
    }

    @GetMapping
    public List<Student> getAllStudentsPresentOnThursday() {
        List<Student> studentsList = getAllStudents();
        List<Student> studentsPresentOnThursday = new ArrayList<>();
        for(Student s : studentsList) {
            if(s.getDaysOfPresence().contains(dayRepository.findDayByDayName("Thursday"))) {
                studentsPresentOnThursday.add(s);
            }
        }
        return studentsPresentOnThursday;
    }

    @GetMapping
    public List<Student> getAllStudentsPresentOnFriday() {
        List<Student> studentsList = getAllStudents();
        List<Student> studentsPresentOnFriday = new ArrayList<>();
        for(Student s : studentsList) {
            if(s.getDaysOfPresence().contains(dayRepository.findDayByDayName("Friday"))) {
                studentsPresentOnFriday.add(s);
            }
        }
        return studentsPresentOnFriday;
    }

    @PostMapping
    public void addStudent(Student student) {
        String normalizedName = student.getName().substring(0,1).toUpperCase() + student.getName().substring(1).toLowerCase();
        String normalizedSurname = student.getSurname().substring(0,1).toUpperCase() + student.getSurname().substring(1).toLowerCase();
        student.setName(normalizedName);
        student.setSurname(normalizedSurname);
        studentRepository.save(student);
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

    public void setFiscalCodeToStudent(ObjectNode json) {
        Long studentId = json.get("id").asLong();
        String fiscalCode = json.get("fiscalCode").toString();
        StringBuilder sb = new StringBuilder(fiscalCode);
        sb.deleteCharAt(fiscalCode.length() - 1);
        sb.deleteCharAt(0);
        fiscalCode = sb.toString().toUpperCase();
        System.out.println(fiscalCode);
        studentRepository.setFiscalCodeToStudent(studentId, fiscalCode);
    }
}
