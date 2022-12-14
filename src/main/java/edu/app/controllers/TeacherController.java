package edu.app.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import edu.app.models.AttendanceRules;
import edu.app.models.Teacher;
import edu.app.services.TeacherService;
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
public class TeacherController {

    private final TeacherService teacherService;

    /*Get, Post, Delete teacher management*/
    @PostMapping("/admin/addTeacher")
    public void addTeacher(@RequestBody ObjectNode json) {
        String name = json.get("name").textValue();
        name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        String surname = json.get("surname").textValue();
        surname = surname.substring(0, 1).toUpperCase() + surname.substring(1).toLowerCase();
        String emailAddress = json.get("emailAddress").textValue().toLowerCase();
        teacherService.addTeacher(name, surname, emailAddress);
    }

    @PostMapping("/admin/removeTeacher")
    public void removeTeacher(@RequestBody ObjectNode json) {
        Long teacherId = json.get("teacherId").asLong();
        teacherService.removeTeacher(teacherId);
    }

    @RequestMapping("/admin/getAllTeachers")
    public List<Teacher> getAllTeachers() {
        List<Teacher> teacherList = teacherService.getAllTeachers();
        Collections.sort(teacherList);
        return teacherList;
    }

    @RequestMapping("admin/getTeacherById")
    public Teacher getTeacherById(@RequestBody Long teacherId) {
        return teacherService.getTeacherById(teacherId);
    }

    /********************/

    /*Active teacher, email address management*/
    @PostMapping("/admin/setEmailAddressToTeacher")
    public void setEmailAddressToTeacher(@RequestBody ObjectNode json) {
        Long teacherId = json.get("teacherId").asLong();
        String emailAddress = json.get("newEmailAddress").textValue().toLowerCase();
        teacherService.setEmailAddressToTeacher(teacherId, emailAddress);
    }

    @PostMapping("/admin/setActiveToTeacher")
    public void setActiveToTeacher(@RequestBody ObjectNode json) {
        Long teacherId = json.get("teacherId").asLong();
        boolean isActive = json.get("isActive").asBoolean();
        teacherService.setActiveToTeacher(teacherId, isActive);
    }

    /********************/

    /*Days of presence management */
    @PostMapping("/admin/getNameOfTheDaysOfPresenceFromTeacher")
    public List<String> getNameOfTheDaysOfPresenceFromTeacher(@RequestBody ObjectNode json) {
        Long teacherId = json.get("teacherId").asLong();
        return TeacherController.adaptDaysNameInItalian(
                teacherService.getNameOfTheDaysOfPresenceFromTeacher(teacherId)
        );
    }

    @PostMapping("/admin/setDaysOfPresenceToTeacher")
    public void setDaysOfPresenceToTeacher(@RequestBody ObjectNode json) {
        Long teacherId = json.get("teacherId").asLong();
        JsonNode daysList = json.get("daysList");
        teacherService.setDaysOfAttendanceToTeacher(teacherId, daysList);
    }

    /********************/

    /*Subject management */
    @PostMapping("/admin/getSubjectByTeacher")
    public List<String> getSubjectByTeacher(@RequestBody ObjectNode json) {
        Long teacherId = json.get("teacherId").asLong();
        return teacherService.getSubjectByTeacher(teacherId);
    }

    @PostMapping("/admin/getSubjectNotTeachByTeacher")
    public List<String> getSubjectNotTeachByTeacher(@RequestBody ObjectNode json) {
        Long teacherId = json.get("teacherId").asLong();
        return teacherService.getSubjectNotTeachByTeacher(teacherId);
    }

    @PostMapping("/admin/removeSubjectTeachByTeacher")
    public void removeSubjectTeachByTeacher(@RequestBody ObjectNode json) {
        Long teacherId = json.get("teacherId").asLong();
        String subjectName = json.get("subjectName").textValue();
        teacherService.removeSubjectTeachByTeacher(teacherId, subjectName);
    }

    @PostMapping("/admin/addSubjectTeachByTeacher")
    public void addSubjectTeachByTeacher(@RequestBody ObjectNode json) {
        Long teacherId = json.get("teacherId").asLong();
        String subjectName = json.get("subjectName").textValue();
        teacherService.addSubjectTeachByTeacher(teacherId, subjectName);
    }

    /********************/

    /*Attendance rules management*/
    @PostMapping("/admin/addAttendanceRules")
    public void addAttendanceRules(@RequestBody ObjectNode json) {
        Long teacherId = json.get("teacherId").asLong();
        String dayName = adaptDayNameToEnglish(json.get("dayName").textValue());
        String beginTime = json.get("beginTime").textValue();
        String endTime = json.get("endTime").textValue();
        teacherService.addAttendanceRules(teacherId, dayName, beginTime, endTime);
    }

    @PostMapping("/admin/fetchAttendanceRules")
    public List<AttendanceRules> fetchAttendanceRules(@RequestBody ObjectNode json) {
        Long teacherId = json.get("teacherId").asLong();
        String dayName = adaptDayNameToEnglish(json.get("dayName").textValue());
        return teacherService.fetchAttendanceRules(teacherId, dayName);
    }

    @PostMapping("/admin/removeAttendanceRule")
    public void removeAttendanceRule(@RequestBody ObjectNode json) {
        Long teacherId = json.get("teacherId").asLong();
        Long attendanceId = json.get("attendanceId").asLong();
        teacherService.removeAttendanceRule(teacherId, attendanceId);
    }

    /********************/

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

    public static List<String> adaptDaysNameInItalian(List<String> dayNameList) {
        Collections.replaceAll(dayNameList, "Monday", "Luned??");
        Collections.replaceAll(dayNameList, "Tuesday", "Marted??");
        Collections.replaceAll(dayNameList, "Wednesday", "Mercoled??");
        Collections.replaceAll(dayNameList, "Thursday", "Gioved??");
        Collections.replaceAll(dayNameList, "Friday", "Venerd??");
        return dayNameList;
    }

    public static String adaptDayNameToEnglish(String toAdapt) {
        String adapted;
        switch (toAdapt) {
            case "Luned??" -> adapted = "Monday";
            case "Marted??" -> adapted = "Tuesday";
            case "Mercoled??" -> adapted = "Wednesday";
            case "Gioved??" -> adapted = "Thursday";
            case "Venerd??" -> adapted = "Friday";
            default -> adapted = "toAdapt";
        }
        return adapted;
    }
}

