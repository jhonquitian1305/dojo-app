package com.app.dojo.mappers;

import com.app.dojo.builders.builderDTO.ScheduleDTOBuilder;
import com.app.dojo.builders.builderModels.ScheduleBuilder;
import com.app.dojo.dtos.ScheduleDTO;
import com.app.dojo.models.Schedule;
import org.springframework.stereotype.Component;

@Component
public class MapperSchedule {

    public Schedule mapperSchedule(ScheduleDTO scheduleDTO){
        return  new ScheduleBuilder()
                .setId(scheduleDTO.getId())
                .setDays(scheduleDTO.getDays())
                .setHours(scheduleDTO.getHours())
                .build();
    }

    public ScheduleDTO mapperScheduleDTO(Schedule schedule){
        return  new ScheduleDTOBuilder()
                .setId(schedule.getId())
                .setDays(schedule.getDays())
                .setHours(schedule.getHours())
                .build();
    }

}
