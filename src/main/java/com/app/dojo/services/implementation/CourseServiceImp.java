package com.app.dojo.services.implementation;

import com.app.dojo.builders.builderModels.CourseBuilder;
import com.app.dojo.constants.Message;
import com.app.dojo.dtos.CourseDTO;
import com.app.dojo.dtos.CourseResponse;
import com.app.dojo.exception.errors.BadRequest;
import com.app.dojo.models.Course;
import com.app.dojo.models.Level;
import com.app.dojo.models.Room;
import com.app.dojo.models.Schedule;
import com.app.dojo.repositories.CourseRepository;
import com.app.dojo.services.Interfaces.CourseService;
import com.app.dojo.services.Interfaces.LevelService;
import com.app.dojo.services.Interfaces.RoomService;
import com.app.dojo.services.Interfaces.ScheduleServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImp  implements CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private LevelService levelService;
    @Autowired
    private ScheduleServcie scheduleServcie;
    @Autowired
    private RoomService roomService;

    @Override
    public Course create(CourseDTO courseDTO) throws Exception {
        if(!courseDTO.getFinishDate().after(courseDTO.getStartDate())) throw new BadRequest(Message.MESSAGE_BAD_REQUEST_COURSES_DATE);
        if(courseRepository.existsCourseByName(courseDTO.getName())) throw  new BadRequest(Message.MESSAGE_BAD_REQUEST_COURSES_NAME);

        Level levelFound=this.findLevel(courseDTO.getLevel());
        List<Room> roomsFound=this.findRooms(courseDTO.getRooms());
        List<Schedule> schedulesFound=this.findSchedule(courseDTO.getSchedules());
        hasCourseWithSimilarInformation(levelFound,roomsFound,schedulesFound);

        return this.courseRepository.save(
                new CourseBuilder()
                        .setPrice(courseDTO.getPrice())
                        .setName(courseDTO.getName())
                        .setStartDate(courseDTO.getStartDate())
                        .setFinishDate(courseDTO.getFinishDate())
                        .setLevel(levelFound)
                        .setRooms(roomsFound)
                        .setSchedules(schedulesFound)
                        .build()
        );
    }

    @Override
    public Course getOne(Long id) {
        return null;
    }

    @Override
    public Course getByName(String name) {
        return null;
    }

    @Override
    public CourseResponse getAll() {
        return null;
    }

    @Override
    public Course update(Long id, CourseDTO courseDTO) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
    protected Level findLevel(Long id){
        return this.levelService.getOne(id);
    }

    protected List<Room> findRooms(List<Long> rooms)throws Exception{
        List<Room> roomsFound=new ArrayList<>();
        for (Long room:rooms){
            roomsFound.add(this.roomService.findById(room));
        }
        return roomsFound;
    }

    protected List<Schedule> findSchedule(List<Long> schedules){
        List<Schedule> schedulesFound=new ArrayList<>();
        for(Long schedule:schedules){
            schedulesFound.add(this.scheduleServcie.findOne(schedule));
        }
        return schedulesFound;
    }

    protected void hasCourseWithSimilarInformation(Level level,List<Room> rooms, List<Schedule> schedules){
        for(Room room:rooms){
            for (Schedule schedule: schedules){
                boolean isPresent=this.courseRepository.existsCourseByLevelAndRoomsAndSchedules(level,room,schedule);
                if (isPresent) throw  new BadRequest(Message.MESSAGE_BAD_REQUEST_COURSES_SIMILAR_SCHEDULE.formatted(level.getName(),room.getRoomName(),schedule.getDayName(), schedule.getHoursClass()));
            }
        }
    }
}
