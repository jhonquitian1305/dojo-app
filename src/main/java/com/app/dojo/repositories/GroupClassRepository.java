package com.app.dojo.repositories;

import com.app.dojo.models.Course;
import com.app.dojo.models.GroupClass;
import com.app.dojo.models.Room;
import com.app.dojo.models.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupClassRepository extends JpaRepository<GroupClass,Long> {
    boolean existsGroupClassByNameClass(String name);
    boolean existsGroupClassByRoomsAndSchedules(Room room, Schedule schedule);
    //@Query( value = "SELECT COUNT(*)=0 from group_class gc left outer join groups_rooms gr on gr.group_id=gc.id left outer JOIN groups_schedules gs on gs.group_id =gc.id where  gr.room_id =?1 and gs.schedule_id =?2 and gc.id!=?3",nativeQuery = true)
    boolean existsGroupClassByRoomsAndSchedulesAndIdNot(Room room, Schedule schedule,Long groupId);
    Page<GroupClass> findByCourse(Course course, Pageable pageable);
    Page<GroupClass> findByRooms(Room room, Pageable pageable);
    Page<GroupClass> findBySchedules(Schedule schedule, Pageable pageable);
}
