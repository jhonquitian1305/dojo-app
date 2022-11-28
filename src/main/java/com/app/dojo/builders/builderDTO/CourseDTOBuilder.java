package com.app.dojo.builders.builderDTO;

import com.app.dojo.dtos.CourseDTO;

import java.util.Date;

public class CourseDTOBuilder {
  private String name;
  private Double price;
  private Date startDate;
  private Date finishDate;
  private Long level;

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

  public CourseDTO build(){
    return  new CourseDTO(name,price,startDate,finishDate,level);
  }
}
