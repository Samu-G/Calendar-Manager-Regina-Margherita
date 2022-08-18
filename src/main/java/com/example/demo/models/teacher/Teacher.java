package com.example.demo.models.teacher;

import com.example.demo.models.student.Student;
import com.example.demo.models.subjects.Subject;
import com.example.demo.models.timeSlot.TimeSlot;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import java.util.List;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher implements Comparable<Teacher> {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    //generic data
    private String name;
    private String surname;

    //attendance and teaching data
    private boolean mondayIsPresent;
    private boolean tuesdayIsPresent;
    private boolean wednesdayIsPresent;
    private boolean thursdayIsPresent;
    private boolean fridayIsPresent;

    @ManyToMany
    private List<TimeSlot> timeSlotsOfPresence;

    @ManyToMany
    private List<Subject> subjectTeached;

    @Override
    public int compareTo(Teacher other) {
        return this.name.compareTo(other.name);
    }

}
