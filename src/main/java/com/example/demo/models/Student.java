package com.example.demo.models;

import lombok.*;

import javax.persistence.*;

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

    //generic data
    private String name;
    private String surname;
    private String fiscalCode;
    private String emailAddress;
    private boolean isPresent;

    //attendance
    @ManyToMany
    private List<Day> daysOfPresence;

    // subjects followed
    @ManyToMany
    private List<Subject> subjectsFollowed;

    @Override
    public int compareTo(Student other) {
        return this.name.compareTo(other.name);
    }
}
