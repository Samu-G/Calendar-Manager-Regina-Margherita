package com.example.demo.repository;

import com.example.demo.models.teacher.Teacher;
import com.example.demo.models.timeSlot.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    public Teacher findTeacherById(Long id);

    public Teacher findTeacherByNameAndSurname(String name, String surname);
}

