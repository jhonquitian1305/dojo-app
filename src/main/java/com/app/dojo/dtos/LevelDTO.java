package com.app.dojo.dtos;

import com.app.dojo.constants.Message;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LevelDTO {
  private Long id;
  @NotNull(message =Message.FIELD_NULL)
  @NotNull(message = Message.FIELD_EMPTY)
  @Size(min = 5,message = Message.LEVEL_NAME_SIZE)
  private String name;

  public LevelDTO(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public LevelDTO() {
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
}
