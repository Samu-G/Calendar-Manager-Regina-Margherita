package com.example.demo.controllers;

import com.example.demo.models.subjects.Subject;
import com.example.demo.services.SubjectService;
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
public class SubjectController {

    private final SubjectService subjectService;

    @RequestMapping("/admin/getAllSubjects")
    public List<Subject> getAllSubjects() {
        List<Subject> subjectList = subjectService.getAllSubjects();
        Collections.sort(subjectList);
        return subjectList;
    }

    @RequestMapping("/admin/addSubjectToStudent")
    public void addSubjectToStudent(@RequestBody ObjectNode json) {
        subjectService.addSubjectToStudent(json);
    }
}
