package com.app.dojo.builders.builderModels;

import com.app.dojo.models.Course;
import com.app.dojo.models.GroupClass;
import com.app.dojo.models.Room;
import com.app.dojo.models.Schedule;

import java.util.List;

public class GroupClassBuilder {
    private Long id;
    private String code;
    private String nameClass;
    private Long totalHours;
    private Long weeks;
    private Long hoursPerWeek;
    private Course course;
    private List<Room> rooms;
    private List<Schedule> schedules;

    public GroupClassBuilder() {
    }

    public GroupClassBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public GroupClassBuilder setCode(String code) {
        this.code = code;
        return this;
    }

    public GroupClassBuilder setNameClass(String nameClass) {
        this.nameClass = nameClass;
        return this;
    }

    public GroupClassBuilder setTotalHours(Long totalHours) {
        this.totalHours = totalHours;
        return this;
    }

    public GroupClassBuilder setWeeks(Long weeks) {
        this.weeks = weeks;
        return this;
    }

    public GroupClassBuilder setHoursPerWeek(Long hoursPerWeek) {
        this.hoursPerWeek = hoursPerWeek;
        return this;
    }

    public GroupClassBuilder setCourse(Course course) {
        this.course = course;
        return this;
    }

    public GroupClassBuilder setRooms(List<Room> rooms) {
        this.rooms = rooms;
        return this;
    }

    public GroupClassBuilder setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
        return this;
    }

    public GroupClass build(){
        return new GroupClass(id,code,nameClass,totalHours,weeks,hoursPerWeek,course,rooms,schedules);
    }
}
