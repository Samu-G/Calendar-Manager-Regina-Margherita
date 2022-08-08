package com.example.demo.controllers;

import com.example.demo.models.student.Student;
import com.example.demo.services.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @RequestMapping("/admin/getAllStudent")
    public List<Student> getAllStudents() {
        List<Student> studentList = studentService.getAllStudents();
        Collections.sort(studentList);
        return studentList;
    }

    @PostMapping("/admin/addStudent")
    public void addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
    }

    @PostMapping("admin/flipPresentToStudent")
    public void flipPresentToStudent(@RequestBody Student student) {
        studentService.flipPresentToStudent(student);
    }

}
