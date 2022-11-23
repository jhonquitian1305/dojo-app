package com.app.dojo.dtos;

import com.app.dojo.models.Course;
import com.app.dojo.models.Room;
import com.app.dojo.models.Schedule;

import java.util.List;

public class GroupClassDTOResponse {
    private Long id;
    private String code;
    private String nameClass;
    private Long totalHours;
    private Long weeks;
    private Long hoursPerWeek;
    private Course course;
    private List<Room> rooms;
    private List<Schedule> schedules;

    public GroupClassDTOResponse(Long id, String code, String nameClass, Long totalHours, Long weeks, Long hoursPerWeek, Course course, List<Room> rooms, List<Schedule> schedules) {
        this.id = id;
        this.code = code;
        this.nameClass = nameClass;
        this.totalHours = totalHours;
        this.weeks = weeks;
        this.hoursPerWeek = hoursPerWeek;
        this.course = course;
        this.rooms = rooms;
        this.schedules = schedules;
    }

    public GroupClassDTOResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNameClass() {
        return nameClass;
    }

    public void setNameClass(String nameClass) {
        this.nameClass = nameClass;
    }

    public Long getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(Long totalHours) {
        this.totalHours = totalHours;
    }

    public Long getWeeks() {
        return weeks;
    }

    public void setWeeks(Long weeks) {
        this.weeks = weeks;
    }

    public Long getHoursPerWeek() {
        return hoursPerWeek;
    }

    public void setHoursPerWeek(Long hoursPerWeek) {
        this.hoursPerWeek = hoursPerWeek;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }
}
