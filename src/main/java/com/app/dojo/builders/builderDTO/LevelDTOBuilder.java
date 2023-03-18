package com.app.dojo.builders.builderDTO;

import com.app.dojo.dtos.LevelDTO;

public class LevelDTOBuilder {
  private Long id;
  private String name;

  public LevelDTOBuilder(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public LevelDTOBuilder() {
  }

  public LevelDTOBuilder setId(Long id) {
    this.id = id;
    return this;
  }

  public LevelDTOBuilder setName(String name) {
    this.name = name;
    return this;
  }

  public LevelDTO build(){
    return new LevelDTO(id,name);
  }
}
