package com.app.dojo.services.implementation;

import com.app.dojo.builders.builderDTO.GroupClassResponseBuilder;
import com.app.dojo.builders.builderModels.GroupClassBuilder;
import com.app.dojo.constants.Message;
import com.app.dojo.dtos.GroupClassDTO;
import com.app.dojo.dtos.GroupClassResponse;
import com.app.dojo.exception.errors.BadRequest;
import com.app.dojo.exception.errors.NotFoundException;
import com.app.dojo.models.Course;
import com.app.dojo.models.GroupClass;
import com.app.dojo.models.Room;
import com.app.dojo.models.Schedule;
import com.app.dojo.repositories.GroupClassRepository;
import com.app.dojo.services.Interfaces.CourseService;
import com.app.dojo.services.Interfaces.GroupClassService;
import com.app.dojo.services.Interfaces.RoomService;
import com.app.dojo.services.Interfaces.ScheduleServcie;
import com.app.dojo.services.strategyGroups.GroupsContext;
import com.app.dojo.services.strategyGroups.GroupsStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GroupClassServiceImp implements GroupClassService {

    @Autowired
    private GroupClassRepository groupClassRepository;
    @Autowired
    private CourseService courseService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private ScheduleServcie scheduleServcie;
    @Autowired
    private GroupsContext groupsContext;

    @Override
    public GroupClass create(GroupClassDTO groupClassDTO) throws Exception {
        if(this.groupClassRepository.existsGroupClassByNameClass(groupClassDTO.getNameClass().toUpperCase())) throw new BadRequest(Message.MESSAGE_BAD_REQUEST_CREATE_GROUP_CLASS_NAME.formatted(groupClassDTO.getNameClass()));

        groupClassDTO.setSchedules(uniqueValues(groupClassDTO.getSchedules()));
        List<Schedule> schedulesFound=loadSchedules(groupClassDTO.getSchedules());

        verifyHoursPerWeek(schedulesFound,groupClassDTO.getHoursPerWeek());
        verifyTotalHours(groupClassDTO.getTotalHours(),groupClassDTO.getHoursPerWeek(),groupClassDTO.getWeeks());

        groupClassDTO.setRooms(uniqueValues(groupClassDTO.getRooms()));
        List<Room>roomsFound=loadRooms(groupClassDTO.getRooms());

        verifySimilarInformation(schedulesFound,roomsFound);
        Course courseFound=this.courseService.getOne(groupClassDTO.getCourse());

        return this.groupClassRepository.save(
                new GroupClassBuilder()
                        .setCode(groupClassDTO.getCode())
                        .setNameClass(groupClassDTO.getNameClass().toUpperCase())
                        .setTotalHours(groupClassDTO.getTotalHours())
                        .setHoursPerWeek(groupClassDTO.getHoursPerWeek())
                        .setWeeks(groupClassDTO.getWeeks())
                        .setCourse(courseFound)
                        .setSchedules(schedulesFound)
                        .setRooms(roomsFound)
                        .build()
        );
    }

    @Override
    public GroupClass getOne(Long id) {
        Optional<GroupClass> groupFound=this.groupClassRepository.findById(id);
        if(groupFound.isEmpty()) throw new NotFoundException(Message.MESSAGE_NOT_FOUND_GET_ONE_GROUP_CLASS.formatted(id));
        return groupFound.get();
    }

    @Override
    public GroupClassResponse getAll(int numberPage, int pageSize,Long idCondition, String modelCondition) throws Exception {

        GroupsStrategy groupsStrategy=this.groupsContext.LoadStrategy(modelCondition.toUpperCase());
        Pageable pageable=PageRequest.of(numberPage,pageSize);
        Page<GroupClass> allGroupClasses=groupsStrategy.findGroups(pageable,idCondition);

        if(allGroupClasses.getContent().size()==0) throw new NotFoundException(Message.MESSAGE_NOT_FOUND_GET_All_GROUPS);

        return new GroupClassResponseBuilder()
                .setContent(allGroupClasses.getContent())
                .setLastOne(allGroupClasses.isLast())
                .setNumberPage(allGroupClasses.getNumber())
                .setSizePage(allGroupClasses.getSize())
                .setTotalElements(allGroupClasses.getTotalElements())
                .setTotalPages(allGroupClasses.getTotalPages())
                .build();
    }

    @Override
    public GroupClassResponse getByCourse(Long idCourse, int numberPage, int pageSize) {
        return null;
    }

    @Override
    public GroupClassResponse getByRoom(Long idRoom, int numberPage, int pageSize) {
        return null;
    }

    @Override
    public GroupClassResponse getBySchedule(Long idSchedule, int numberPage, int pageSize) {
        return null;
    }

    @Override
    public GroupClass update(Long id, GroupClassDTO groupClassDTO) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    protected List<Long> uniqueValues(List<Long> values){
        return values.stream().distinct().collect(Collectors.toList());
    }
    protected List<Schedule> loadSchedules(List<Long> idSchedules){
        List<Schedule> schedules=new ArrayList<>();
        for(Long schedule:idSchedules){
            schedules.add(this.scheduleServcie.findOne(schedule));
        }
        return  schedules;
    }

    protected  void verifyHoursPerWeek(List<Schedule> schedules, Long hourPerWeek){
        Long totalHours=0L;
        for (Schedule schedule:schedules){
            String[] hours=schedule.getHoursClass().split("-");
            totalHours+=Long.parseLong(hours[1].split(":")[0])-Long.parseLong(hours[0].split(":")[0]);
        }
        if(totalHours!=hourPerWeek) throw  new BadRequest(Message.MESSAGE_BAD_REQUEST_CREATE_GROUP_CLASS_HOURS);
    }

    protected void verifyTotalHours(Long totalHours, Long hoursPerWeek, Long weeks){
        Long hoursCalculated=hoursPerWeek*weeks;
        if(totalHours!=hoursCalculated) throw new BadRequest(Message.MESSAGE_BAD_REQUEST_CREATE_GROUP_CLASS_TOTAL_HOURS);
    }

    protected List<Room> loadRooms(List<Long> idRooms) throws Exception {
        List<Room> rooms=new ArrayList<>();
        for (Long room:idRooms){
            rooms.add(this.roomService.findById(room));
        }
        return rooms;
    }

    protected void verifySimilarInformation(List<Schedule> schedules, List<Room> rooms){
        for (Schedule schedule:schedules){
            for (Room room:rooms){
                if(groupClassRepository.existsGroupClassByRoomsAndSchedules(room,schedule))throw new BadRequest(Message.MESSAGE_BAD_REQUEST_CREATE_GROUP_CLASS_ROOM_SCHEDULE.formatted(room.getRoomName(),schedule.getHoursClass(),schedule.getDayName()));
            }
        }
    }
}
