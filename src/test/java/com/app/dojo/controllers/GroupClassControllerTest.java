package com.app.dojo.controllers;

import com.app.dojo.builders.builderDTO.CourseDTOBuilder;
import com.app.dojo.builders.builderDTO.GroupClassDTOBuilder;
import com.app.dojo.builders.builderDTO.RoomDTOBuilder;
import com.app.dojo.builders.builderDTO.ScheduleDTOBuilder;
import com.app.dojo.builders.builderModels.*;
import com.app.dojo.dtos.CourseDTO;
import com.app.dojo.dtos.GroupClassDTO;
import com.app.dojo.dtos.RoomDTO;
import com.app.dojo.dtos.ScheduleDTO;
import com.app.dojo.models.Schedule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "test")
class GroupClassControllerTest {

  @Autowired
  private TestRestTemplate testRestTemplate;
  private String urlGroups ="http://localhost:8080/api/dojo-app/groups";
  private String urlCourses ="http://localhost:8080/api/dojo-app/courses";
  private String urlRooms="http://localhost:8080/api/dojo-app/rooms";
  private String urlSchedules="http://localhost:8080/api/dojo-app/schedules";
  private GroupClassDTO groupClassDTO;
  private ScheduleDTO scheduleDTO;
  private RoomDTO roomDTO;
  private CourseDTO courseDTO;
  private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

  @BeforeEach()
  void init() throws ParseException {
    Date startDate = format.parse("2022-06-01");
    Date finishDate = format.parse("2022-06-30");

    roomDTO= new RoomDTOBuilder()
        .setRoomName("201")
        .setId(1L)
        .build();

    scheduleDTO=new ScheduleDTOBuilder()
        .setDayName("Lunes")
        .setHoursClass("8:00-10:00")
        .setId(1L)
        .build();

    courseDTO=new CourseDTOBuilder()
        .setPrice(500000.0)
        .setName("CINTA NEGRA PRINCIPIANTES")
        .setStartDate(startDate)
        .setFinishDate(finishDate)
        .build();

    groupClassDTO= new GroupClassDTOBuilder()
        .setCode("23456789")
        .setNameClass("PRINCIPIANTES 01")
        .setHoursPerWeek(2L)
        .setTotalHours(20L)
        .setWeeks(10L)
        .build();
  }

}