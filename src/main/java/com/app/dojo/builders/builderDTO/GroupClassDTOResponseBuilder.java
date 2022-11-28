package com.app.dojo.builders.builderDTO;

import com.app.dojo.dtos.GroupClassDTOResponse;
import com.app.dojo.models.Course;
import com.app.dojo.models.Room;
import com.app.dojo.models.Schedule;

import java.util.List;

public class GroupClassDTOResponseBuilder {
    private Long id;
    private String code;
    private String nameClass;
    private Long totalHours;
    private Long weeks;
    private Long hoursPerWeek;
    private Course course;
    private List<Room> rooms;
    private List<Schedule> schedules;

    public GroupClassDTOResponseBuilder() {
    }

    public GroupClassDTOResponseBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public GroupClassDTOResponseBuilder setCode(String code) {
        this.code = code;
        return this;
    }

    public GroupClassDTOResponseBuilder setNameClass(String nameClass) {
        this.nameClass = nameClass;
        return this;
    }

    public GroupClassDTOResponseBuilder setTotalHours(Long totalHours) {
        this.totalHours = totalHours;
        return this;
    }

    public GroupClassDTOResponseBuilder setWeeks(Long weeks) {
        this.weeks = weeks;
        return this;
    }

    public GroupClassDTOResponseBuilder setHoursPerWeek(Long hoursPerWeek) {
        this.hoursPerWeek = hoursPerWeek;
        return this;
    }

    public GroupClassDTOResponseBuilder setCourse(Course course) {
        this.course = course;
        return this;
    }

    public GroupClassDTOResponseBuilder setRooms(List<Room> rooms) {
        this.rooms = rooms;
        return this;
    }

    public GroupClassDTOResponseBuilder setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
        return this;
    }
    public GroupClassDTOResponse build(){
        return new GroupClassDTOResponse(id,code,nameClass,totalHours,weeks,hoursPerWeek,course,rooms,schedules);
    }
}
