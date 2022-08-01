package com.example.demo.services;

import com.example.demo.models.student.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.services.interfaces.StudentServiceInterface;
import com.example.demo.services.interfaces.UserServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@AllArgsConstructor
@Service
public class StudentService implements StudentServiceInterface {

    private final StudentRepository studentRepository;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @PostMapping
    public void addStudent(Student student) {
        //controlla se la email è già stata usata
        studentRepository.save(student);
    }
}
