package com.example.demo.controllers;

import com.example.demo.models.Student;
import com.example.demo.models.Teacher;
import com.example.demo.services.StudentService;
import com.fasterxml.jackson.databind.node.ObjectNode;
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

    @RequestMapping("/admin/getAllStudentsPresentOnMonday")
    public List<Student> getAllStudentsPresentOnMonday() {
        List<Student> studentsPresentOnMonday = studentService.getAllStudentsPresentOnMonday();
        Collections.sort(studentsPresentOnMonday);
        return studentsPresentOnMonday;
    }

    @RequestMapping("/admin/getAllStudentsPresentOnTuesday")
    public List<Student> getAllStudentsPresentOnTuesday() {
        List<Student> studentsPresentOnTuesday = studentService.getAllStudentsPresentOnTuesday();
        Collections.sort(studentsPresentOnTuesday);
        return studentsPresentOnTuesday;
    }

    @RequestMapping("/admin/getAllStudentsPresentOnWednesday")
    public List<Student> getAllStudentsPresentOnWednesday() {
        List<Student> studentsPresentOnWednesday = studentService.getAllStudentsPresentOnWednesday();
        Collections.sort(studentsPresentOnWednesday);
        return studentsPresentOnWednesday;
    }

    @RequestMapping("/admin/getAllStudentsPresentOnThursday")
    public List<Student> getAllStudentsPresentOnThursday() {
        List<Student> studentsPresentOnThursday = studentService.getAllStudentsPresentOnThursday();
        Collections.sort(studentsPresentOnThursday);
        return studentsPresentOnThursday;
    }

    @RequestMapping("/admin/getAllStudentsPresentOnFriday")
    public List<Student> getAllTeachersPresentOnFriday() {
        List<Student> studentsPresentOnFriday = studentService.getAllStudentsPresentOnFriday();
        Collections.sort(studentsPresentOnFriday);
        return studentsPresentOnFriday;
    }


    @PostMapping("/admin/addStudent")
    public void addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
    }

    @PostMapping("admin/flipPresentToStudent")
    public void flipPresentToStudent(@RequestBody Student student) {
        studentService.flipPresentToStudent(student);
    }

//    @PostMapping("/admin/setDayOfPresentToStudent")
//    public void setDaysOfPresenceToStudent(@RequestBody ObjectNode json) {
//        studentService.setDaysOfPresenceToStudent(json);
//    }
//
//    @PostMapping("/admin/setCurrentYearToStudent")
//    public void setCurrentYearToStudent(@RequestBody ObjectNode json) {
//        studentService.setCurrentYearToStudent(json);
//    }

    @PostMapping("/admin/setFiscalCodeToStudent")
    public void setFiscalCodeToStudent(@RequestBody ObjectNode json) {
        studentService.setFiscalCodeToStudent(json);
    }

}
