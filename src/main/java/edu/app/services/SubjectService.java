package edu.app.services;

import edu.app.models.Subject;
import edu.app.repository.StudentRepository;
import edu.app.repository.SubjectRepository;
import edu.app.repository.TeacherRepository;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
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
        List<Subject> subjectList = subjectRepository.findAll();
        Collections.sort(subjectList);
        return subjectList;
    }

    @GetMapping
    public Subject getSubjectById(Long id) {
        return subjectRepository.findSubjectById(id);
    }

    @PostMapping
    public void addSubjectToStudent(Long studentId, String subjectName) {
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
    public void addSubjectToTeacher(Long teacherId, String subjectName) {
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
    public void saveSubjectByName(String subjectName) {
        Subject alreadyExsist = subjectRepository.findSubjectByNameOfTheSubject(subjectName);
        if (alreadyExsist == null) {
            Subject newSubject = new Subject(null, subjectName);
            subjectRepository.save(newSubject);
        }
    }


}
