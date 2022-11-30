package com.app.dojo.repositories;

import com.app.dojo.builders.builderModels.GroupClassBuilder;
import com.app.dojo.builders.builderModels.LevelBuilder;
import com.app.dojo.builders.builderModels.RoomBuilder;
import com.app.dojo.builders.builderModels.ScheduleBuilder;
import com.app.dojo.models.*;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@ActiveProfiles(profiles = "test")
class GroupClassRepositoryTest {

  @Autowired
  private CourseRepository courseRepository;
  @Autowired
  private RoomRepository roomRepository;
  @Autowired
  private ScheduleRepository scheduleRepository;
  @Autowired
  private GroupClassRepository groupClassRepository;
  private Course course;
  private Room room;
  private Schedule schedule;
  private GroupClass group;
  private Level level;

  @BeforeAll()
  void init(){

    room= new RoomBuilder()
        .setRoomName("201")
        .setId(1L)
        .build();

    schedule=new ScheduleBuilder()
        .setDayName("Lunes")
        .setHoursClass("8:00-10:00")
        .setId(1L)
        .build();

    level=new LevelBuilder()
        .setName("CINTA NEGRA")
        .setId(1L)
        .build();

    group= new GroupClassBuilder()
        .setId(1L)
        .setCode("23456789")
        .setNameClass("PRINCIPIANTES 01")
        .setHoursPerWeek(2L)
        .setTotalHours(20L)
        .setWeeks(10L)
        .setId(1L)
        .build();
  }


}