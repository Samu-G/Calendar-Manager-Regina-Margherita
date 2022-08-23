package com.example.demo.repository;

import com.example.demo.models.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {

    public Calendar findCalendarByReferenceDate(Date referenceDate);
}
