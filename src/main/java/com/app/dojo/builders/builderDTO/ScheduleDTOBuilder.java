package com.app.dojo.builders.builderDTO;

import com.app.dojo.dtos.ScheduleDTO;
import com.app.dojo.models.Day;
import com.app.dojo.models.Hour;

import java.util.ArrayList;
import java.util.List;

public class ScheduleDTOBuilder {
    private Long id;
    private List<Hour> hours;
    private List<Day> days;

    public ScheduleDTOBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public ScheduleDTOBuilder setHours(List<Hour> hours) {
        this.hours = hours;
        return this;
    }

    public ScheduleDTOBuilder setDays(List<Day> days) {
        this.days = days;
        return this;
    }

    public ScheduleDTO build(){
        return  new ScheduleDTO(id,hours,days);
    }
}
