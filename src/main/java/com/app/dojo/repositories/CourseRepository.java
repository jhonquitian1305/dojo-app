package com.app.dojo.repositories;

import com.app.dojo.models.Course;
import com.app.dojo.models.Level;
import com.app.dojo.models.Room;
import com.app.dojo.models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    Optional<Course> findByName(String name);
    boolean existsCourseByName(String name);
    boolean existsCourseByLevelsAndRoomsAndSchedules(Level level, Room room, Schedule schedule);
}
