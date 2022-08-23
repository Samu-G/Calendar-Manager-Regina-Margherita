package com.example.demo.repository;

import com.example.demo.models.CalendarRow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarRowRepository extends JpaRepository<CalendarRow, Long> {
}
