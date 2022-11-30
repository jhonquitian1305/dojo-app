package com.app.dojo.builders.builderDTO;

import com.app.dojo.dtos.GroupClassDTO;

import java.util.List;

public class GroupClassDTOBuilder {
  private String code;
  private String nameClass;
  private Long totalHours;
  private Long weeks;
  private Long hoursPerWeek;
  private Long course;
  private List<Long> rooms;
  private List<Long> schedules;

  public GroupClassDTOBuilder setCode(String code) {
    this.code = code;
    return this;
  }

  public GroupClassDTOBuilder setNameClass(String nameClass) {
    this.nameClass = nameClass;
    return this;
  }

  public GroupClassDTOBuilder setTotalHours(Long totalHours) {
    this.totalHours = totalHours;
    return this;
  }

  public GroupClassDTOBuilder setWeeks(Long weeks) {
    this.weeks = weeks;
    return this;
  }

  public GroupClassDTOBuilder setHoursPerWeek(Long hoursPerWeek) {
    this.hoursPerWeek = hoursPerWeek;
    return this;
  }

  public GroupClassDTOBuilder setCourse(Long course) {
    this.course = course;
    return this;
  }

  public GroupClassDTOBuilder setRooms(List<Long> rooms) {
    this.rooms = rooms;
    return this;
  }

  public GroupClassDTOBuilder setSchedules(List<Long> schedules) {
    this.schedules = schedules;
    return this;
  }

  public GroupClassDTO build(){
    return new GroupClassDTO(code,nameClass,totalHours,weeks,hoursPerWeek,course,rooms,schedules);
  }
}
