package com.app.dojo.repositories;

import com.app.dojo.models.Day;
import com.app.dojo.models.Hour;
import com.app.dojo.models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
    boolean existsScheduleByDaysAndHours(Day day, Hour hour);
}
