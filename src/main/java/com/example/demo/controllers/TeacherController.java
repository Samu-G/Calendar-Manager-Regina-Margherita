package com.example.demo.controllers;

import com.example.demo.models.Teacher;
import com.example.demo.services.TeacherService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
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

    @RequestMapping("/admin/getAllTeachersPresentOnMonday")
    public List<Teacher> getAllTeachersPresentOnMonday() {
        List<Teacher> teachersPresentOnMonday = teacherService.getAllTeachersPresentOnMonday();
        Collections.sort(teachersPresentOnMonday);
        return teachersPresentOnMonday;
    }

    @RequestMapping("/admin/getAllTeachersPresentOnTuesday")
    public List<Teacher> getAllTeachersPresentOnTuesday() {
        List<Teacher> teachersPresentOnTuesday = teacherService.getAllTeachersPresentOnTuesday();
        Collections.sort(teachersPresentOnTuesday);
        return teachersPresentOnTuesday;
    }

    @RequestMapping("/admin/getAllTeachersPresentOnWednesday")
    public List<Teacher> getAllTeachersPresentOnWednesday() {
        List<Teacher> teachersPresentOnWednesday = teacherService.getAllTeachersPresentOnWednesday();
        Collections.sort(teachersPresentOnWednesday);
        return teachersPresentOnWednesday;
    }

    @RequestMapping("/admin/getAllTeachersPresentOnThursday")
    public List<Teacher> getAllTeachersPresentOnThursday() {
        List<Teacher> teachersPresentOnThursday = teacherService.getAllTeachersPresentOnThursday();
        Collections.sort(teachersPresentOnThursday);
        return teachersPresentOnThursday;
    }

    @RequestMapping("/admin/getAllTeachersPresentOnFriday")
    public List<Teacher> getAllTeachersPresentOnFriday() {
        List<Teacher> teachersPresentOnFriday = teacherService.getAllTeachersPresentOnFriday();
        Collections.sort(teachersPresentOnFriday);
        return teachersPresentOnFriday;
    }

    @RequestMapping("/admin/deleteSubjectFromTheTeacher")
    public void deleteSubjectFromTheTeacher(@RequestBody ObjectNode json) {
        teacherService.deleteSubjectFromTheTeacher(json);
    }

//    @RequestMapping("admin/setDayOfAttendanceToTeacher")
//    public void setDayOfAttendanceToTeacher(@RequestBody ObjectNode json) {
//        teacherService.setDayOfAttendanceToTeacher(json);
//    }

    @RequestMapping("/admin/setTimeSlotToTeacher")
    public void setTimeSlotToTeacher(@RequestBody ObjectNode json) {
        System.out.println(json);
    }

}
