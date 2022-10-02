package com.example.demo.models;

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
public class TimeSlotAttendanceRules {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String timeSlotDay;
    private String beginTime;
    private String endTime;
}
