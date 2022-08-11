package com.example.demo.services;

import com.example.demo.models.student.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.services.interfaces.StudentServiceInterface;
import com.example.demo.services.interfaces.UserServiceInterface;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class StudentService implements StudentServiceInterface {

    private final StudentRepository studentRepository;

    @Override
    public Student getStudentByNameAndSurname(String name, String surname) {
        return studentRepository.findStudentByNameAndSurname(name, surname);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @PostMapping
    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    @PostMapping
    public void flipPresentToStudent(Student student) {
        studentRepository.flipPresentToStudent(student);
    }

    @PostMapping
    public void setDaysOfPresenceToStudent(ObjectNode json) {
        Long studentId = json.get("id").asLong();
        JsonNode daysOfPresence = json.get("day");
        ArrayNode arrayNode = (ArrayNode) daysOfPresence;
        System.out.println(arrayNode);
        System.out.println(arrayNode.size());
        List<String> dayOfPresenceList = new ArrayList<>();
        for (int i = 0; i < arrayNode.size(); i++) {
            String dayName = arrayNode.get(i).toString();
            StringBuilder sb = new StringBuilder(dayName);
            sb.deleteCharAt(dayName.length() - 1);
            sb.deleteCharAt(0);
            dayName = sb.toString();
            System.out.println(dayName);
            dayOfPresenceList.add(dayName);
        }
        if(dayOfPresenceList.contains("Lunedi")) {
            studentRepository.setMondayIsPresentToStudent(studentId, "Si");
        } else {
            studentRepository.setMondayIsPresentToStudent(studentId, "No");
        }
        if(dayOfPresenceList.contains("Martedi")) {
            studentRepository.setTuesdayIsPresentToStudent(studentId, "Si");
        } else {
            studentRepository.setTuesdayIsPresentToStudent(studentId, "No");
        }
        if(dayOfPresenceList.contains("Mercoledi")) {
            studentRepository.setWednesdayIsPresentToStudent(studentId, "Si");
        } else {
            studentRepository.setWednesdayIsPresentToStudent(studentId, "No");
        }
        if(dayOfPresenceList.contains("Giovedi")) {
            studentRepository.setThursdayIsPresentToStudent(studentId, "Si");
        } else {
            studentRepository.setThursdayIsPresentToStudent(studentId, "No");
        }
        if(dayOfPresenceList.contains("Venerdi")) {
            studentRepository.setFridayIsPresentToStudent(studentId, "Si");
        } else {
            studentRepository.setFridayIsPresentToStudent(studentId, "No");
        }
    }

    @PostMapping
    public void setCurrentYearToStudent(ObjectNode json) {
        Long studentId = json.get("id").asLong();
        String currentYear = json.get("currentYear").toString();
        StringBuilder sb = new StringBuilder(json.get("currentYear").toString());
        sb.deleteCharAt(currentYear.length() - 1);
        sb.deleteCharAt(0);
        currentYear = sb.toString();
        System.out.println(currentYear);
        studentRepository.setCurrentYearToStudent(studentId, currentYear);
    }
}
