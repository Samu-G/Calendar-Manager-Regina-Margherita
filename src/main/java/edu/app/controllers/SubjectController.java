package edu.app.controllers;

import edu.app.models.Student;
import edu.app.models.Subject;
import edu.app.models.Teacher;
import edu.app.services.StudentService;
import edu.app.services.SubjectService;
import edu.app.services.TeacherService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final StudentService studentService;

    private final TeacherService teacherService;

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

    @RequestMapping("/admin/isDeletableSubjectById")
    public ResponseEntity<?> isDeletableSubjectById(@RequestBody ObjectNode json) {
        Long id = json.get("id").longValue();
        boolean notDeletable = true;
        Subject subject = subjectService.getSubjectById(id);
        List<Student> studentList = studentService.getAllStudents();
        List<Teacher> teacherList = teacherService.getAllTeachers();
        for (Student s: studentList) {
            if(s.getSubjectsFollowed().contains(subject)) {
                notDeletable = false;
                break;
            }
        }
        for (Teacher t: teacherList) {
            if(t.getSubjectsTeached().contains(subject)) {
                notDeletable = false;
                break;
            }
        }

        return new ResponseEntity<>(notDeletable, HttpStatus.OK);
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
