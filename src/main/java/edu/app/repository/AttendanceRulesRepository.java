package edu.app.repository;

import edu.app.models.AttendanceRules;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRulesRepository extends JpaRepository<AttendanceRules, Long> {
    AttendanceRules findTimeSlotAttendanceRulesById(Long id);


}
