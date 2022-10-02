package edu.app.repository;

import edu.app.models.Day;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DayRepository  extends JpaRepository<Day, Long> {
    Day findDayByDayName(String dayName);
}
