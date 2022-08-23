package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.sql.Time;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeSlot implements Comparable<TimeSlot>{
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    //generic data
    private String timeSlotName;
    private Time beginTime;
    private Time endTime;

    @Override
    public int compareTo(TimeSlot other) {
        return Long.compare(this.getBeginTime().getTime(), other.getBeginTime().getTime());
    }
}
