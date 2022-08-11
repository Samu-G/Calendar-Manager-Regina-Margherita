package com.example.demo.services;

import com.example.demo.models.subjects.Subject;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.SubjectRepository;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@AllArgsConstructor
@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;

    private final StudentRepository studentRepository;

    public Subject getSubjectByNameAndYear(String name, int year) {
        return subjectRepository.findSubjectByNameOfTheSubjectAndYearOfTeaching(name, year);
    }

    @PostMapping
    public void addSubject(Subject subject) {
        subjectRepository.save(subject);
    }

    @GetMapping
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @PostMapping
    public void addSubjectToStudent(ObjectNode json) {
        Long studentId = json.get("id").asLong();
        String subjectName = json.get("subjectName").toString();
        System.out.println(studentId);
        StringBuilder sb = new StringBuilder(subjectName);
        sb.deleteCharAt(subjectName.length() - 1);
        sb.deleteCharAt(0);
        subjectName = sb.toString();
        System.out.println(subjectName);
        Subject subject = subjectRepository.findSubjectByNameOfTheSubject(subjectName);
        studentRepository.addSubjectToStudent(studentId, subject);
    }
}
