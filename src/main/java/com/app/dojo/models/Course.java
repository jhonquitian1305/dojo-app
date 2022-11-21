package com.app.dojo.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Course {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private Double price;
  private Date startDate;
  private Date finishDate;
  @ManyToMany(cascade ={CascadeType.PERSIST,CascadeType.MERGE})
  @JoinTable(
      name = "courses_levels",
      joinColumns = @JoinColumn(name ="course_id"),
      inverseJoinColumns = @JoinColumn(name = "level_id")
  )
  private List<Level> levels;
  @ManyToMany(cascade ={CascadeType.PERSIST,CascadeType.MERGE})
  @JoinTable(
      name = "courses_rooms",
      joinColumns = @JoinColumn(name ="course_id"),
      inverseJoinColumns = @JoinColumn(name = "room_id")
  )
  private List<Room> rooms;
  @ManyToMany(cascade ={CascadeType.PERSIST,CascadeType.MERGE})
  @JoinTable(
      name = "courses_schedules",
      joinColumns = @JoinColumn(name ="course_id"),
      inverseJoinColumns = @JoinColumn(name = "schedule_id")
  )
  private List<Schedule> schedules;

  public Course(Long id, String name, Double price, Date startDate, Date finishDate,List<Level> levels,List<Room> rooms,List<Schedule> schedules) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.startDate = startDate;
    this.finishDate = finishDate;
    this.levels=levels;
    this.rooms=rooms;
    this.schedules=schedules;
  }

  public Course() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public List<Level> getLevels() {
    return levels;
  }

  public void setLevels(List<Level> levels) {
    this.levels = levels;
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
