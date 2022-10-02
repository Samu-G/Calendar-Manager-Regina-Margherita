package edu.app.repository;

import edu.app.models.Student;
import edu.app.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findStudentsById(Long id);

    Student findStudentByNameAndSurname(String name, String surname);

    @Modifying
    @Transactional
    @Query("UPDATE Student s SET s.isPresent = ?1 WHERE s.id = ?2")
    void setIsPresentByStudentId(String isPresent, Long studentId);

    default void flipPresentToStudent(Student student) {
        Student s = findStudentsById(student.getId());
        if (s.isPresent()) {
            setIsPresentByStudentId("No", student.getId());
        } else {
            setIsPresentByStudentId("Si", student.getId());
        }
    }

    default void addSubjectToStudent(Long studentId, Subject subject) {
        Student s = findStudentsById(studentId);
        System.out.println(s.getSubjectsFollowed());
        s.getSubjectsFollowed().add(subject);
        System.out.println(s.getSubjectsFollowed());
        save(s);
    }
}
