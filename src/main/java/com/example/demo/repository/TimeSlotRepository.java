package com.example.demo.repository;

import com.example.demo.models.subjects.Subject;
import com.example.demo.models.timeSlot.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Time;

@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {

    public TimeSlot findTimeSlotById(Long id);

    public TimeSlot findTimeSlotByBeginTimeAndEndTime(Time begin, Time end);
}
