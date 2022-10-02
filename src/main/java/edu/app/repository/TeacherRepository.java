package edu.app.repository;

import edu.app.models.Subject;
import edu.app.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Teacher findTeacherById(Long id);

    Teacher findTeacherByNameAndSurname(String name, String surname);

    default void addSubjectToTeacher(Long teacherId, Subject subject) {
        Teacher t = findTeacherById(teacherId);
        t.getSubjectsTeached().add(subject);
        save(t);
    }
}

