package com.example.demo.models;

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
    private String fiscalCode;
    private String emailAddress;

    //attendance
    @ManyToMany
    private List<Day> daysOfPresence;
    @ManyToMany
    private List<TimeSlot> timeSlotsOfPresenceOnMonday;
    @ManyToMany
    private List<TimeSlot> timeSlotsOfPresenceOnTuesday;
    @ManyToMany
    private List<TimeSlot> timeSlotsOfPresenceOnWednesday;
    @ManyToMany
    private List<TimeSlot> timeSlotsOfPresenceOnThursday;
    @ManyToMany
    private List<TimeSlot> timeSlotsOfPresenceOnFriday;

    //subjects teached
    @ManyToMany
    private List<Subject> subjectsTeached;

    @Override
    public int compareTo(Teacher other) {
        return this.name.compareTo(other.name);
    }

}
