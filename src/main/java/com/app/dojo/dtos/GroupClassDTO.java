package com.app.dojo.dtos;

import com.app.dojo.constants.Message;
import com.app.dojo.models.Course;
import com.app.dojo.models.Room;
import com.app.dojo.models.Schedule;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

public class GroupClassDTO {
    @NotNull(message = Message.FIELD_NULL)
    @NotNull(message = Message.FIELD_EMPTY)
    private String code;
    @NotNull(message =Message.FIELD_NULL)
    @NotNull(message = Message.FIELD_EMPTY)
    private String nameClass;
    @NotNull(message =Message.FIELD_NULL)
    @NotNull(message = Message.FIELD_EMPTY)
    @Positive
    private Long totalHours;
    @NotNull(message =Message.FIELD_NULL)
    @NotNull(message = Message.FIELD_EMPTY)
    @Positive
    private Long weeks;
    @NotNull(message =Message.FIELD_NULL)
    @NotNull(message = Message.FIELD_EMPTY)
    @Positive
    private Long hoursPerWeek;
    @NotNull(message =Message.FIELD_NULL)
    @NotNull(message = Message.FIELD_EMPTY)
    private Course course;
    @NotNull(message =Message.FIELD_NULL)
    @NotNull(message = Message.FIELD_EMPTY)
    private List<Integer> rooms;
    @NotNull(message =Message.FIELD_NULL)
    @NotNull(message = Message.FIELD_EMPTY)
    private List<Integer> schedules;

    public GroupClassDTO(String code, String nameClass, Long totalHours, Long weeks, Long hoursPerWeek, Course course, List<Integer> rooms, List<Integer> schedules) {
        this.code = code;
        this.nameClass = nameClass;
        this.totalHours = totalHours;
        this.weeks = weeks;
        this.hoursPerWeek = hoursPerWeek;
        this.course = course;
        this.rooms = rooms;
        this.schedules = schedules;
    }
    public GroupClassDTO() {
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

    public List<Integer> getRooms() {
        return rooms;
    }

    public void setRooms(List<Integer> rooms) {
        this.rooms = rooms;
    }

    public List<Integer> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Integer> schedules) {
        this.schedules = schedules;
    }
}
