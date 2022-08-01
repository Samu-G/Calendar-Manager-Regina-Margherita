package com.example.demo.services.interfaces;

import com.example.demo.models.student.Student;

import java.util.List;

public interface StudentServiceInterface {
    public List<Student> getAllStudents();
    public void addStudent(Student student);
}
