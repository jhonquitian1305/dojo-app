package com.app.dojo.repositories;

import com.app.dojo.models.Course;
import com.app.dojo.models.GroupClass;
import com.app.dojo.models.Room;
import com.app.dojo.models.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupClassRepository extends JpaRepository<GroupClass,Long> {
    boolean existsGroupClassByNameClass(String name);
    boolean existsGroupClassByRoomsAndSchedules(Room room, Schedule schedule);
    Page<GroupClass> findByCourse(Course course, Pageable pageable);
    Page<GroupClass> findByRooms(Room room, Pageable pageable);
    Page<GroupClass> findBySchedules(Schedule schedule, Pageable pageable);
}
