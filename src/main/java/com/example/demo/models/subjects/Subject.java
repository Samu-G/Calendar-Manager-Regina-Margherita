package com.example.demo.models.subjects;

import com.example.demo.models.student.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subject implements Comparable<Subject> {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String nameOfTheSubject;
    private int yearOfTeaching;

    @Override
    public int compareTo(Subject other) {
        return this.nameOfTheSubject.compareTo(other.nameOfTheSubject);
    }
}
