package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Calendar {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private Date referenceDate;

    @ManyToMany
    private List<Teacher> teachersInvolved;

    @ManyToMany
    private List<Student> studentsInvolved;

    @OneToMany
    private List<CalendarRow> calendarRows;

    @Override
    public String toString() {
        return "Calendar{" +
                "id=" + id +
                ", referenceDate=" + referenceDate +
                ", teachersInvolved=" + teachersInvolved +
                ", studentsInvolved=" + studentsInvolved +
                ", calendarRows=" + calendarRows +
                '}';
    }
}
