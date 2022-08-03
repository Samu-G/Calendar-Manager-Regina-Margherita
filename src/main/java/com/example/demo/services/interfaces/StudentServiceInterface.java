package com.example.demo.services.interfaces;

import com.example.demo.models.student.Student;

import java.util.List;

public interface StudentServiceInterface {
    public Student getStudentByNameAndSurname(String name, String surname);
    public List<Student> getAllStudents();
    public void addStudent(Student student);
}
