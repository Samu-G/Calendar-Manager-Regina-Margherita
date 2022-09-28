package com.example.demo.controllers;

import com.example.demo.models.Subject;
import com.example.demo.services.SubjectService;
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
public class SubjectController {

    private final SubjectService subjectService;

    @RequestMapping("/admin/getAllSubjects")
    public List<Subject> getAllSubjects() {
        List<Subject> subjectList = subjectService.getAllSubjects();
        Collections.sort(subjectList);
        return subjectList;
    }

    @PostMapping("/admin/addSubject")
    public void addSubject(@RequestBody ObjectNode json) {
        String subjectName = json.get("subjectName").textValue();
        subjectService.saveSubjectByName(subjectName);
    }

    @RequestMapping("/admin/deleteSubjectById")
    public void deleteSubjectById(@RequestBody Long id) {
        subjectService.deleteSubjectById(id);
    }

    @RequestMapping("/admin/addSubjectToStudent")
    public void addSubjectToStudent(@RequestBody ObjectNode json) {
        subjectService.addSubjectToStudent(json);
    }

    @RequestMapping("/admin/addSubjectToTeacher")
    public void addSubjectToTeacher(@RequestBody ObjectNode json) {
        subjectService.addSubjectToTeacher(json);
    }
}
