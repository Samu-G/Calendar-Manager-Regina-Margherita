package com.example.demo.repository;

import com.example.demo.models.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    public Student getStudentsById(Long id);
    public Student findStudentByNameAndSurname(String name, String surname);

    @Modifying
    @Transactional
    @Query("UPDATE Student s SET s.isPresent = ?1 WHERE s.id = ?2")
    public void setIsPresentByStudentId(String isPresent, Long studentId);

    default void flipPresentToStudent(Student student) {
        Student s = getStudentsById(student.getId());
        if (s.getIsPresent().equals("Si")) {
            setIsPresentByStudentId("No", student.getId());
        } else if (s.getIsPresent().equals("No")) {
            setIsPresentByStudentId("Si", student.getId());
        }
    }
}
