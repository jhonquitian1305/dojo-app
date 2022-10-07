package com.app.dojo.builders.builderDTO;

import com.app.dojo.dtos.ScheduleDTO;

import java.util.ArrayList;
import java.util.List;

public class ScheduleDTOBuilder {
    private Long id;
    private List<String> hours=new ArrayList<String>();
    private List<String> days = new ArrayList<String>();

    public ScheduleDTOBuilder setId(Long id) {
        id = id;
        return this;
    }

    public ScheduleDTOBuilder setHours(List<String> hours) {
        this.hours = hours;
        return this;
    }

    public ScheduleDTOBuilder setDays(List<String> days) {
        this.days = days;
        return this;
    }

    public ScheduleDTO build(){
        return  new ScheduleDTO(id,hours,days);
    }
}
