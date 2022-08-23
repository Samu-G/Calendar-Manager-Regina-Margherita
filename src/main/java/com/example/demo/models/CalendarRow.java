package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalendarRow {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @OneToOne
    private Teacher teacherInvolved;

    @OneToMany
    private List<Student> studentsInvolved;

    @ManyToMany
    private List<CalendarTimeSlot> calendarTimeSlots;
}
