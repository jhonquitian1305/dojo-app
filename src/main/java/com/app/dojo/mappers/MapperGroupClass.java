package com.app.dojo.mappers;

import com.app.dojo.builders.builderDTO.GroupClassDTOResponseBuilder;
import com.app.dojo.dtos.GroupClassDTOResponse;
import com.app.dojo.models.GroupClass;
import org.springframework.stereotype.Component;

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
}
