package com.app.dojo.builders.builderModels;

import com.app.dojo.models.Schedule;

import java.util.ArrayList;
import java.util.List;

public class ScheduleBuilder {
    private Long id;
    private String dayName;
    private String hoursClass;

    public ScheduleBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public ScheduleBuilder setDayName(String dayName) {
        this.dayName = dayName;
        return this;
    }

    public ScheduleBuilder setHoursClass(String hoursClass) {
        this.hoursClass = hoursClass;
        return this;
    }

    public Schedule build(){
        return  new Schedule(id,dayName,hoursClass);
    }
}
