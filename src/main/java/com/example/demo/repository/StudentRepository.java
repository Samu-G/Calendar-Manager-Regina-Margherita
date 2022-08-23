package com.example.demo.repository;

import com.example.demo.models.Student;
import com.example.demo.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    public Student findStudentsById(Long id);

    public Student findStudentByNameAndSurname(String name, String surname);

    @Modifying
    @Transactional
    @Query("UPDATE Student s SET s.isPresent = ?1 WHERE s.id = ?2")
    public void setIsPresentByStudentId(String isPresent, Long studentId);

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

//    @Modifying
//    @Transactional
//    @Query("UPDATE Student s SET s.lun = :isPresent WHERE s.id = :studentId")
//    void setMondayIsPresentToStudent(@Param("studentId") Long studentId, @Param("isPresent") String isPresent);
//
//    @Modifying
//    @Transactional
//    @Query("UPDATE Student s SET s.mar = :isPresent WHERE s.id = :studentId")
//    void setTuesdayIsPresentToStudent(@Param("studentId") Long studentId, @Param("isPresent") String isPresent);
//
//    @Modifying
//    @Transactional
//    @Query("UPDATE Student s SET s.mer = :isPresent WHERE s.id = :studentId")
//    void setWednesdayIsPresentToStudent(@Param("studentId") Long studentId, @Param("isPresent") String isPresent);
//
//    @Modifying
//    @Transactional
//    @Query("UPDATE Student s SET s.gio = :isPresent WHERE s.id = :studentId")
//    void setThursdayIsPresentToStudent(@Param("studentId") Long studentId, @Param("isPresent") String isPresent);
//
//    @Modifying
//    @Transactional
//    @Query("UPDATE Student s SET s.ven = :isPresent WHERE s.id = :studentId")
//    void setFridayIsPresentToStudent(@Param("studentId") Long studentId, @Param("isPresent") String isPresent);
//
//    @Modifying
//    @Transactional
//    @Query("UPDATE Student s SET s.currentYear = :currentYear WHERE s.id = :studentId")
//    void setCurrentYearToStudent(@Param("studentId") Long studentId, @Param("currentYear") String currentYear);
//

    default void setFiscalCodeToStudent(Long studentId, String fiscalCode) {
        Student s = findStudentsById(studentId);
        s.setFiscalCode(fiscalCode);
        save(s);
    }
}
