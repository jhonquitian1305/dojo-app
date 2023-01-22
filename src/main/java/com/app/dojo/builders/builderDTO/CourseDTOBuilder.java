package com.app.dojo.builders.builderDTO;

import com.app.dojo.dtos.CourseDTO;

import java.util.Date;
import java.util.List;

public class CourseDTOBuilder {
  private Long id;
  private String name;
  private Double price;
  private Date startDate;
  private Date finishDate;
  private Long level;
  private List<Long> teachers;

  public CourseDTOBuilder setId(Long id) {
    this.id = id;
    return this;
  }

  public CourseDTOBuilder setName(String name) {
    this.name = name;
    return this;
  }

  public CourseDTOBuilder setPrice(Double price) {
    this.price = price;
    return this;
  }

  public CourseDTOBuilder setStartDate(Date startDate) {
    this.startDate = startDate;
    return this;
  }

  public CourseDTOBuilder setFinishDate(Date finishDate) {
    this.finishDate = finishDate;
    return this;
  }

  public CourseDTOBuilder setLevel(Long level) {
    this.level = level;
    return this;
  }

  public CourseDTOBuilder setTeachers(List<Long> teachers) {
    this.teachers = teachers;
    return this;
  }

  public CourseDTO build(){
    return  new CourseDTO(id, name,price,startDate,finishDate,level, teachers);
  }
}
