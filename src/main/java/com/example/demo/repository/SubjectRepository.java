package com.example.demo.repository;

import com.example.demo.models.student.Student;
import com.example.demo.models.subjects.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    public Subject getSubjectsById(Long id);

    public Subject getSubjectsByNameOfTheSubject(String s);

    public Subject findSubjectByNameOfTheSubject(String s);
    public Subject findSubjectByNameOfTheSubjectAndYearOfTeaching(String nameOfTheSubject, int yearOfTeaching);

}
