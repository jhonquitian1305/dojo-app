package com.app.dojo.dtos;

import com.app.dojo.constants.Message;
import com.app.dojo.models.Course;
import com.app.dojo.models.Room;
import com.app.dojo.models.Schedule;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;

public class GroupClassDTO {
    @NotNull(message = Message.FIELD_NULL)
    @NotNull(message = Message.FIELD_EMPTY)
    @Size(min = 7,max = 7, message = Message.GROUP_CLASSES_CODE)
    private String code;
    @NotNull(message =Message.FIELD_NULL)
    @NotNull(message = Message.FIELD_EMPTY)
    @Size(min = 3,message = Message.GROUP_CLASSES_NAME)
    private String nameClass;
    @NotNull(message =Message.FIELD_NULL)
    @NotNull(message = Message.FIELD_EMPTY)
    @Positive(message = Message.POSITIVE_VALUE)
    private Long totalHours;
    @NotNull(message =Message.FIELD_NULL)
    @NotNull(message = Message.FIELD_EMPTY)
    @Positive(message = Message.POSITIVE_VALUE)
    private Long weeks;
    @NotNull(message =Message.FIELD_NULL)
    @NotNull(message = Message.FIELD_EMPTY)
    @Positive(message = Message.POSITIVE_VALUE)
    private Long hoursPerWeek;
    @NotNull(message =Message.FIELD_NULL)
    @NotNull(message = Message.FIELD_EMPTY)
    private Long course;
    @NotNull(message =Message.FIELD_NULL)
    @NotNull(message = Message.FIELD_EMPTY)
    @Size(min = 1,message = Message.GROUP_CLASSES_ROOM)
    private List<Long> rooms;
    @NotNull(message =Message.FIELD_NULL)
    @NotNull(message = Message.FIELD_EMPTY)
    @Size(min = 1,message = Message.GROUP_CLASSES_SCHEDULE)
    private List<Long> schedules;

    public GroupClassDTO(String code, String nameClass, Long totalHours, Long weeks, Long hoursPerWeek, Long course, List<Long> rooms, List<Long> schedules) {
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

    public Long getCourse() {
        return course;
    }

    public void setCourse(Long course) {
        this.course = course;
    }

    public List<Long> getRooms() {
        return rooms;
    }

    public void setRooms(List<Long> rooms) {
        this.rooms = rooms;
    }

    public List<Long> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Long> schedules) {
        this.schedules = schedules;
    }
}
