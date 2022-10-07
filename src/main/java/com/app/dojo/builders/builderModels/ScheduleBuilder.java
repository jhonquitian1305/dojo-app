package com.app.dojo.builders.builderModels;

import com.app.dojo.models.Schedule;

import java.util.ArrayList;
import java.util.List;

public class ScheduleBuilder {
    private Long id;
    private List<String> hours=new ArrayList<String>();
    private List<String> days = new ArrayList<String>();

    public ScheduleBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public ScheduleBuilder setHours(List<String> hours) {
        this.hours = hours;
        return this;
    }

    public ScheduleBuilder setDays(List<String> days) {
        this.days = days;
        return this;
    }

    public Schedule build(){
        return  new Schedule(id,hours,days);
    }
}
