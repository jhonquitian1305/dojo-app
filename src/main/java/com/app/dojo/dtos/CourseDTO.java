package com.app.dojo.dtos;

import java.util.Date;
import java.util.List;

public class CourseDTO {
  private String name;
  private Double price;
  private Date startDate;
  private Date finishDate;
  private List<Long> levels;
  private List<Long> rooms;
  private List<Long> schedules;

  public CourseDTO(String name, Double price, Date startDate, Date finishDate, List<Long> levels, List<Long> rooms, List<Long> schedules) {
    this.name = name;
    this.price = price;
    this.startDate = startDate;
    this.finishDate = finishDate;
    this.levels = levels;
    this.rooms = rooms;
    this.schedules = schedules;
  }

  public CourseDTO() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getFinishDate() {
    return finishDate;
  }

  public void setFinishDate(Date finishDate) {
    this.finishDate = finishDate;
  }

  public List<Long> getLevels() {
    return levels;
  }

  public void setLevels(List<Long> levels) {
    this.levels = levels;
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
