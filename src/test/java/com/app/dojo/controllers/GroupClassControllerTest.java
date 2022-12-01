package com.app.dojo.controllers;

import com.app.dojo.builders.builderDTO.CourseDTOBuilder;
import com.app.dojo.builders.builderDTO.GroupClassDTOBuilder;
import com.app.dojo.builders.builderDTO.RoomDTOBuilder;
import com.app.dojo.builders.builderDTO.ScheduleDTOBuilder;
import com.app.dojo.builders.builderModels.*;
import com.app.dojo.dtos.*;
import com.app.dojo.models.Schedule;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles(profiles = "test")
class GroupClassControllerTest {

  @Autowired
  private TestRestTemplate testRestTemplate;
  private String url ="http://localhost:8080/api/dojo-app/groups";
  private GroupClassDTO groupClassDTO;
  @BeforeEach()
  void init() throws ParseException {
    groupClassDTO= new GroupClassDTOBuilder()
        .setCode("2345678")
        .setNameClass("PRINCIPIANTES 01")
        .setHoursPerWeek(2L)
        .setTotalHours(20L)
        .setWeeks(10L)
        .setCourse(1L)
        .setSchedules(List.of(1L))
        .setRooms(List.of(1L))
        .build();
  }
}