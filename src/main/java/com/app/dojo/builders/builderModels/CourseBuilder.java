package com.app.dojo.builders.builderModels;

import com.app.dojo.models.Course;
import com.app.dojo.models.Level;
import com.app.dojo.models.Room;
import com.app.dojo.models.Schedule;

import java.util.Date;
import java.util.List;

public class CourseBuilder {
  private Long id;
  private String name;
  private Double price;
  private Date startDate;
  private Date finishDate;
  private Level level;

  public CourseBuilder() {
  }

  public CourseBuilder setId(Long id) {
    this.id = id;
    return this;
  }

  public CourseBuilder setName(String name) {
    this.name = name;
    return this;
  }

  public CourseBuilder setPrice(Double price) {
    this.price = price;
    return this;
  }

  public CourseBuilder setStartDate(Date startDate) {
    this.startDate = startDate;
    return this;
  }

  public CourseBuilder setFinishDate(Date finishDate) {
    this.finishDate = finishDate;
    return this;
  }

  public CourseBuilder setLevel(Level level) {
    this.level = level;
    return this;
  }

  public Course build(){
    return  new Course(id,name,price,startDate,finishDate,level);
  }
}
