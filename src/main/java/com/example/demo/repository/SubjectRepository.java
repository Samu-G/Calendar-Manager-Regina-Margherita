package com.example.demo.repository;

import com.example.demo.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    public Subject findSubjectById(Long id);

    public Subject findSubjectByNameOfTheSubject(String nameOfTheSubject);

}
