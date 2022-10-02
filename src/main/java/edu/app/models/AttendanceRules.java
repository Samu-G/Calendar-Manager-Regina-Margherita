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
public class AttendanceRules {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String dayName;
    private String beginTime;
    private String endTime;
}
