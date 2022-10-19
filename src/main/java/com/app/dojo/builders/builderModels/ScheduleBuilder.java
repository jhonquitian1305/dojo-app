package com.app.dojo.builders.builderModels;

import com.app.dojo.models.Day;
import com.app.dojo.models.Hour;
import com.app.dojo.models.Schedule;

import java.util.ArrayList;
import java.util.List;

public class ScheduleBuilder {
    private Long id;
    private List<Hour> hours;
    private List<Day> days;

    public ScheduleBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public ScheduleBuilder setHours(List<Hour> hours) {
        this.hours = hours;
        return this;
    }

    public ScheduleBuilder setDays(List<Day> days) {
        this.days = days;
        return this;
    }

    public Schedule build(){
        return  new Schedule(id,hours,days);
    }
}
