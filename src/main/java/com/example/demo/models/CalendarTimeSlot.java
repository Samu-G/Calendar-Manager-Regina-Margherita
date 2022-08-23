package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.sql.Time;
import java.util.List;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalendarTimeSlot {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @ManyToOne
    private TimeSlot timeSlotReference;

    @ManyToMany
    private List<Student> studentsInvolved;
}
