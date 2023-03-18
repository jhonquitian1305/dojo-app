package com.app.dojo.builders.builderModels;

import com.app.dojo.models.Level;

public class LevelBuilder {
  private Long id;
  private String name;

  public LevelBuilder() {
  }

  public LevelBuilder setId(Long id) {
    this.id = id;
    return this;
  }

  public LevelBuilder setName(String name) {
    this.name = name;
    return this;
  }

  public Level build(){
    return new Level(id, name);
  }
}
