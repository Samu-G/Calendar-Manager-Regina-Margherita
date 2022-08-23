package com.example.demo.repository;

import com.example.demo.models.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Time;

@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {
    public TimeSlot findTimeSlotByTimeSlotName(String timeSlotName);
}
