package com.app.dojo.repositories;

import com.app.dojo.models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
    Optional<Schedule> findByDayNameAndHoursClass(String dayName, String hoursClass);
}
