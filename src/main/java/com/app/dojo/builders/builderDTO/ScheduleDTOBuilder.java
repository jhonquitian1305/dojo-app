package com.app.dojo.builders.builderDTO;

import com.app.dojo.dtos.ScheduleDTO;
import java.util.ArrayList;
import java.util.List;

public class ScheduleDTOBuilder {
    private Long id;
    private String dayName;
    private String hoursClass;

    public ScheduleDTOBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public ScheduleDTOBuilder setDayName(String dayName) {
        this.dayName = dayName;
        return this;
    }

    public ScheduleDTOBuilder setHoursClass(String hoursClass) {
        this.hoursClass = hoursClass;
        return this;
    }

    public ScheduleDTO build(){
        return  new ScheduleDTO(id,dayName,hoursClass);
    }
}
