package com.example.demo.services;

import com.example.demo.models.Subject;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.SubjectRepository;
import com.example.demo.repository.TeacherRepository;
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

    private final TeacherRepository teacherRepository;

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

    @PostMapping
    public void addSubjectToTeacher(ObjectNode json) {
        Long teacherId = json.get("id").asLong();
        String subjectName = json.get("subjectName").toString();
        StringBuilder sb = new StringBuilder(subjectName);
        sb.deleteCharAt(subjectName.length() - 1);
        sb.deleteCharAt(0);
        subjectName = sb.toString();
        System.out.println(subjectName);
        Subject subject = subjectRepository.findSubjectByNameOfTheSubject(subjectName);
        teacherRepository.addSubjectToTeacher(teacherId, subject);
    }

    @PostMapping
    public void deleteSubjectById(Long id) {
        System.out.println(id);
        Subject subject = subjectRepository.findSubjectById(id);
        subjectRepository.delete(subject);
    }

    @PostMapping
    public void saveSubjectByName(ObjectNode json) {
        String subjectName = json.get("name").toString();
        StringBuilder sb = new StringBuilder(subjectName);
        sb.deleteCharAt(subjectName.length() - 1);
        sb.deleteCharAt(0);
        subjectName = sb.toString();
        subjectName= subjectName.toUpperCase();
        System.out.println(subjectName);
        Subject alreadyExsist = subjectRepository.findSubjectByNameOfTheSubject(subjectName);
        if(alreadyExsist == null) {
            Subject newSubject = new Subject(null, subjectName);
            subjectRepository.save(newSubject);
        }
    }


}
