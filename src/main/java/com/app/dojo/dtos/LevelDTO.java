package com.app.dojo.dtos;

public class LevelDTO {
  private Long id;
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
