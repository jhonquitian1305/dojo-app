package com.app.dojo.mappers;

import com.app.dojo.builders.builderDTO.GroupClassDTOResponseBuilder;
import com.app.dojo.dtos.GroupClassDTO;
import com.app.dojo.dtos.GroupClassDTOResponse;
import com.app.dojo.models.Course;
import com.app.dojo.models.GroupClass;
import com.app.dojo.models.Room;
import com.app.dojo.models.Schedule;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MapperGroupClass {

    public GroupClassDTOResponse mapperGroupClassDTOResponse(GroupClass groupClass){
        return new GroupClassDTOResponseBuilder()
                .setId(groupClass.getId())
                .setCode(groupClass.getCode())
                .setNameClass(groupClass.getNameClass())
                .setTotalHours(groupClass.getTotalHours())
                .setHoursPerWeek(groupClass.getHoursPerWeek())
                .setWeeks(groupClass.getWeeks())
                .setCourse(groupClass.getCourse())
                .setRooms(groupClass.getRooms())
                .setSchedules(groupClass.getSchedules())
                .build();
    }

    public GroupClass updateInformation(GroupClass groupClass, GroupClassDTO groupClassDTO, List<Schedule> schedules, List<Room> rooms, Course course){
        groupClass.setNameClass(groupClassDTO.getNameClass());
        groupClass.setCode(groupClassDTO.getCode());
        groupClass.setHoursPerWeek(groupClassDTO.getHoursPerWeek());
        groupClass.setTotalHours(groupClassDTO.getTotalHours());
        groupClass.setWeeks(groupClass.getWeeks());
        groupClass.setCourse(course);
        groupClass.setSchedules(schedules);
        groupClass.setRooms(rooms);
        return  groupClass;
    }
}
