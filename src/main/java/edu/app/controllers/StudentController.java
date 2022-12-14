package edu.app.controllers;

import edu.app.models.Student;
import edu.app.services.StudentService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/admin/addStudent")
    public void addStudent(@RequestBody ObjectNode json) {
        String name = json.get("name").textValue();
        name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        String surname = json.get("surname").textValue();
        surname = surname.substring(0, 1).toUpperCase() + surname.substring(1).toLowerCase();
        String fiscalCode = json.get("fiscalCode").textValue();
        fiscalCode = fiscalCode.toUpperCase();
        String emailAddress = json.get("emailAddress").textValue();
        emailAddress = emailAddress.toLowerCase();
        studentService.addStudent(name, surname, fiscalCode, emailAddress);
    }

    @PostMapping("/admin/removeStudent")
    public void removeStudent(@RequestBody ObjectNode json) {
        Long studentId = json.get("studentId").asLong();
        studentService.removeStudent(studentId);
    }

    @PostMapping("/admin/setFiscalCodeToStudent")
    public void setFiscalCodeToStudent(@RequestBody ObjectNode json) {
        Long studentId = json.get("studentId").asLong();
        String newFiscalCode = json.get("newFiscalCode").textValue();
        studentService.setFiscalCodeToStudent(studentId, newFiscalCode);
    }

    @PostMapping("/admin/setEmailAddressToStudent")
    public void setEmailAddressToStudent(@RequestBody ObjectNode json) {
        Long studentId = json.get("studentId").asLong();
        String emailAddress = json.get("newEmailAddress").textValue();
        studentService.setEmailAddressToStudent(studentId, emailAddress);
    }

    @PostMapping("/admin/setPresenceToStudent")
    public void setPresenceToStudent(@RequestBody ObjectNode json) {
        Long studentId = json.get("studentId").asLong();
        boolean isPresent = json.get("isPresent").asBoolean();
        studentService.setPresenceToStudent(studentId, isPresent);
    }

    @PostMapping("/admin/getNameOfTheDaysOfPresenceFromStudent")
    public List<String> getNameOfTheDaysOfPresenceFromStudent(@RequestBody ObjectNode json) {
        Long studentId = json.get("studentId").asLong();
        return adaptDaysNameInItalian(studentService.getNameOfTheDaysOfPresenceFromStudent(studentId));
    }

    @PostMapping("/admin/getSubjectFollowedByTheStudent")
    public List<String> getSubjectFollowedByTheStudent(@RequestBody ObjectNode json) {
        Long studentId = json.get("studentId").asLong();
        return studentService.getSubjectFollowedByTheStudent(studentId);
    }

    @PostMapping("/admin/getSubjectNotFollowedByTheStudent")
    public List<String> getSubjectNotFollowedByTheStudent(@RequestBody ObjectNode json) {
        Long studentId = json.get("studentId").asLong();
        return studentService.getSubjectNotFollowedByTheStudent(studentId);
    }

    @PostMapping("/admin/removeSubjectFollowedByTheStudent")
    public void removeSubjectFollowedByTheStudent(@RequestBody ObjectNode json) {
        Long studentId = json.get("studentId").asLong();
        String subjectName = json.get("subjectName").textValue();
        studentService.removeSubjectFollowedByTheStudent(studentId, subjectName);
    }

    @PostMapping("/admin/addSubjectFollowedByTheStudent")
    public void addSubjectFollowedByTheStudent(@RequestBody ObjectNode json) {
        Long studentId = json.get("studentId").asLong();
        String subjectName = json.get("subjectName").textValue();
        studentService.addSubjectFollowedByTheStudent(studentId, subjectName);
    }

    @PostMapping("/admin/setDaysOfPresenceToStudent")
    public void setDaysOfPresenceToStudent(@RequestBody ObjectNode json) {
        studentService.setDaysOfAttendanceToStudent(json);
    }


    @RequestMapping("/admin/getAllStudent")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
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


    @PostMapping("admin/flipPresentToStudent")
    public void flipPresentToStudent(@RequestBody Student student) {
        studentService.flipPresentToStudent(student);
    }

    public static List<String> adaptDaysNameInItalian(List<String> dayNameList) {
        Collections.replaceAll(dayNameList, "Monday", "Lunedì");
        Collections.replaceAll(dayNameList, "Tuesday", "Martedì");
        Collections.replaceAll(dayNameList, "Wednesday", "Mercoledì");
        Collections.replaceAll(dayNameList, "Thursday", "Giovedì");
        Collections.replaceAll(dayNameList, "Friday", "Venerdì");
        return dayNameList;
    }

}
