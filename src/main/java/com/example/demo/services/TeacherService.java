package com.example.demo.services;

import com.example.demo.models.subjects.Subject;
import com.example.demo.models.teacher.Teacher;
import com.example.demo.models.timeSlot.TimeSlot;
import com.example.demo.repository.SubjectRepository;
import com.example.demo.repository.TeacherRepository;
import com.example.demo.repository.TimeSlotRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Time;
import java.util.Iterator;
import java.util.List;

@AllArgsConstructor
@Service
public class TeacherService {

    private final TimeSlotRepository timeSlotRepository;

    private final SubjectRepository subjectRepository;

    private final TeacherRepository teacherRepository;

    @PostMapping
    public void addTeacher(Teacher teacher) {
        String normalizedName = teacher.getName().substring(0, 1).toUpperCase() + teacher.getName().substring(1).toLowerCase();
        String normalizedSurname = teacher.getSurname().substring(0, 1).toUpperCase() + teacher.getSurname().substring(1).toLowerCase();
        teacher.setName(normalizedName);
        teacher.setSurname(normalizedSurname);
        teacherRepository.save(teacher);
    }

    @PostMapping
    public void addTimeSlot(TimeSlot timeSlot) {
        timeSlotRepository.save(timeSlot);
    }

    @GetMapping
    public TimeSlot getTimeSlotByBeginTimeAndEndTime(Time begin, Time end) {
        return timeSlotRepository.findTimeSlotByBeginTimeAndEndTime(begin, end);
    }

    @GetMapping
    public Teacher getTeacherByNameAndSurname(String name, String surname) {
        return teacherRepository.findTeacherByNameAndSurname(name, surname);
    }

    @GetMapping
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @PostMapping
    public void deleteSubjectFromTheTeacher(ObjectNode json) {
        Long teacherId = json.get("id").asLong();
        String subjectName = json.get("subjectName").toString();
        subjectName = subjectName.substring(5, subjectName.length() - 1);
        Teacher teacher = teacherRepository.findTeacherById(teacherId);
        Subject toRemove = subjectRepository.findSubjectByNameOfTheSubject(subjectName);
        teacher.getSubjectTeached().remove(toRemove);
        teacherRepository.save(teacher);
    }

    public void setDayOfAttendanceToTeacher(ObjectNode json) {
        Long teacherId = json.get("id").asLong();
        Iterator<JsonNode> presenceDaysList = json.get("presenceDaysList").elements();
        System.out.println(teacherId);
        Teacher teacher = teacherRepository.findTeacherById(teacherId);
        teacher.setMondayIsPresent(false);
        teacher.setTuesdayIsPresent(false);
        teacher.setWednesdayIsPresent(false);
        teacher.setThursdayIsPresent(false);
        teacher.setFridayIsPresent(false);
        while (presenceDaysList.hasNext()) {
            String specificDay = presenceDaysList.next().toString();
            specificDay = specificDay.substring(1, specificDay.length() - 1);
            switch (specificDay) {
                case "Lunedi" -> teacher.setMondayIsPresent(true);
                case "Martedi" -> teacher.setTuesdayIsPresent(true);
                case "Mercoledi" -> teacher.setWednesdayIsPresent(true);
                case "Giovedi" -> teacher.setThursdayIsPresent(true);
                case "Venerdi" -> teacher.setFridayIsPresent(true);
            }
        }
        teacherRepository.save(teacher);
    }
}
