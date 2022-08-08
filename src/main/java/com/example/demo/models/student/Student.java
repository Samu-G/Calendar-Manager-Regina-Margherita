package com.example.demo.models.student;

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
    private String name;
    private String surname;
    private String currentYear;

    private String isPresent;

    private String lun;
    private String mar;
    private String mer;
    private String gio;
    private String ven;

    @Override
    public int compareTo(Student other) {
        return this.name.compareTo(other.name);
    }


//    @ElementCollection
//    List<String> subjectsList;
//    @ElementCollection
//    List<String> feedbackList;

//    public Student(String name, String surname, boolean isPresent, boolean lun, boolean mar, boolean mer, boolean gio, boolean ven) {
//        this.name = name;
//        this.surname = surname;
//        this.isPresent = isPresent;
//        this.lun = lun;
//        this.mar = mar;
//        this.mer = mer;
//        this.gio = gio;
//        this.ven = ven;
//        subjectsList = new ArrayList<>();
//        subjectsList.add("matematica");
//        subjectsList.add("fisica");
//        feedbackList = new ArrayList<>();
//        feedbackList.add("prova1");
//        feedbackList.add("prova2");
//    }
}
