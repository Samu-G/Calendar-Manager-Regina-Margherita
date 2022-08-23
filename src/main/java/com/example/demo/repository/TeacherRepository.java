package com.example.demo.repository;

import com.example.demo.models.Subject;
import com.example.demo.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    public Teacher findTeacherById(Long id);

    public Teacher findTeacherByNameAndSurname(String name, String surname);

    default void addSubjectToTeacher(Long teacherId, Subject subject) {
        Teacher t = findTeacherById(teacherId);
        t.getSubjectsTeached().add(subject);
        save(t);
    }
}

