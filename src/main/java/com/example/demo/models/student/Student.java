package com.example.demo.models.student;

import com.example.demo.models.subjects.Subject;
import lombok.*;

import javax.persistence.*;
import javax.transaction.Transactional;

import java.util.Comparator;
import java.util.List;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Comparable<Student> {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    /*Compulsory field*/
    private String name;
    private String surname;
    private String fiscalCode;

    /*Not compulsory field*/
    private String currentYear;
    private String isPresent;
    private String lun;
    private String mar;
    private String mer;
    private String gio;
    private String ven;
    @ManyToMany
    private List<Subject> subjectsFollowedList;

    @Override
    public int compareTo(Student other) {
        return this.name.compareTo(other.name);
    }
}
