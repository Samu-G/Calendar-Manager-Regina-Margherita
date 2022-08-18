package com.example.demo.services;

import com.example.demo.models.teacher.Teacher;
import com.example.demo.models.timeSlot.TimeSlot;
import com.example.demo.repository.TeacherRepository;
import com.example.demo.repository.TimeSlotRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Time;
import java.util.List;

@AllArgsConstructor
@Service
public class TeacherService {

    private TimeSlotRepository timeSlotRepository;

    private TeacherRepository teacherRepository;

    @PostMapping
    public void addTeacher(Teacher teacher) {
        String normalizedName = teacher.getName().substring(0,1).toUpperCase() + teacher.getName().substring(1).toLowerCase();
        String normalizedSurname = teacher.getSurname().substring(0,1).toUpperCase() + teacher.getSurname().substring(1).toLowerCase();
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
}
