package com.app.dojo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Level {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  @OneToMany(fetch = FetchType.LAZY,cascade ={CascadeType.MERGE,CascadeType.PERSIST})
  @JoinColumn(name = "level_id")
  @JsonIgnore
  private List<Course> courses;

  public Level(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public Level() {
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

  public List<Course> getCourses() {
    return courses;
  }

  public void setCourses(List<Course> courses) {
    this.courses = courses;
  }
}
