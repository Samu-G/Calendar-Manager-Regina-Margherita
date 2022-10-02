package edu.app.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.AUTO;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Subject implements Comparable<Subject> {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String nameOfTheSubject;

    @Override
    public int compareTo(Subject other) {
        return this.nameOfTheSubject.compareTo(other.nameOfTheSubject);
    }
}
