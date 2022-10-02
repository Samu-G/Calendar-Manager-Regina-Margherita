package com.example.demo.repository;

import com.example.demo.models.TimeSlotAttendanceRules;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeSlotAttendanceRulesRepository  extends JpaRepository<TimeSlotAttendanceRules, Long> {
    TimeSlotAttendanceRules findTimeSlotAttendanceRulesById(Long id);
}
