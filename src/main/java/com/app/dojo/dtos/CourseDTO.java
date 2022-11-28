package com.app.dojo.dtos;

import com.app.dojo.constants.Message;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

public class CourseDTO {
  @NotNull(message = Message.FIELD_NULL)
  @NotNull(message = Message.FIELD_EMPTY)
  private String name;
  @NotNull(message = Message.FIELD_NULL)
  @NotNull(message = Message.FIELD_EMPTY)
  @Positive(message = Message.COURSES_PRICE)
  private Double price;
  @NotNull(message = Message.FIELD_NULL)
  @NotNull(message = Message.FIELD_EMPTY)
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date startDate;
  @NotNull(message = Message.FIELD_NULL)
  @NotNull(message = Message.FIELD_EMPTY)
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date finishDate;
  @NotNull(message = Message.FIELD_NULL)
  @NotNull(message = Message.FIELD_EMPTY)
  @Positive()
  private Long level;

  public CourseDTO(String name, Double price, Date startDate, Date finishDate, Long level) {
    this.name = name;
    this.price = price;
    this.startDate = startDate;
    this.finishDate = finishDate;
    this.level = level;
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

  public Long getLevel() {
    return level;
  }

  public void setLevel(Long level) {
    this.level = level;
  }
}
