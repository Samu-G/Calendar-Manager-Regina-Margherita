package com.example.demo.controllers;

import com.example.demo.models.student.Student;
import com.example.demo.services.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @RequestMapping("/admin/getAllStudent")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping("/admin/addStudent")
    public void addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
    }

}
