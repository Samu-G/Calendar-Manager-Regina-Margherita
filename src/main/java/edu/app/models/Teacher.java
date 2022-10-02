package edu.app.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import java.util.List;

import static javax.persistence.GenerationType.AUTO;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Teacher implements Comparable<Teacher> {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    //generic data
    private String name;
    private String surname;
    private String emailAddress;
    private boolean isActive;

    //attendance
    @ManyToMany
    private List<Day> daysOfPresence;
    @ManyToMany
    private List<AttendanceRules> attendanceRules;

    //subjects teached
    @ManyToMany
    private List<Subject> subjectsTeached;

    @Override
    public int compareTo(Teacher other) {
        return this.name.compareTo(other.name);
    }

}
