package com.example.demo.repository;

import com.example.demo.models.CalendarTimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarTimeSlotRepository extends JpaRepository<CalendarTimeSlot, Long> {
}
