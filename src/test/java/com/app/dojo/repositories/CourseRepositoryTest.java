package com.app.dojo.repositories;

import com.app.dojo.builders.builderModels.CourseBuilder;
import com.app.dojo.builders.builderModels.LevelBuilder;
import com.app.dojo.models.Course;
import com.app.dojo.models.Level;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@ActiveProfiles(profiles = "test")
class CourseRepositoryTest {
  @Autowired
  private CourseRepository courseRepository;
  @Autowired
  private LevelRepository levelRepository;
  private static Course course;
  private static Level level;

  @BeforeEach
  void  init() throws ParseException {
    // Format Date
    SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
    Date startDate=format.parse("2022-06-01");
    Date finishDate=format.parse("2022-06-30");

    level= new LevelBuilder()
        .setName("CINTA NEGRA")
        .build();
    course=new CourseBuilder()
        .setId(1L)
        .setName("CINTA NEGRA PRINCIPIANTES")
        .setStartDate(startDate)
        .setFinishDate(finishDate)
        .setPrice(200000.0)
        .build();
  }

  @Test
  @DisplayName("Test CourseRepository, create a course")
  void create(){
    //given
    Level levelSaved=this.levelRepository.save(level);
    //when
    course.setLevel(levelSaved);
    Course courseSaved=this.courseRepository.save(course);
    //then
    assertNotNull(courseSaved);
    assertThat(courseSaved.getId()).isGreaterThan(0);
  }
}