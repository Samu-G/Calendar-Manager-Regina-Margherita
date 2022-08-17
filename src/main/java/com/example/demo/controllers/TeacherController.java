package com.example.demo.controllers;

import com.example.demo.models.teacher.Teacher;
import com.example.demo.services.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
@AllArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @RequestMapping("/admin/getAllTeachers")
    public List<Teacher> getAllTeachers() {
        List<Teacher> teacherList = teacherService.getAllTeachers();
        Collections.sort(teacherList);
        return teacherList;
    }

}
