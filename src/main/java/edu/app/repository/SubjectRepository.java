package edu.app.repository;

import edu.app.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    Subject findSubjectById(Long id);

    Subject findSubjectByNameOfTheSubject(String nameOfTheSubject);

}
