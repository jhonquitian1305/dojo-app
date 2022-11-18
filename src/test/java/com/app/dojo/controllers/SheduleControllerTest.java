package com.app.dojo.controllers;

import com.app.dojo.builders.builderDTO.ScheduleDTOBuilder;
import com.app.dojo.builders.builderModels.ScheduleBuilder;
import com.app.dojo.dtos.RoomDTO;
import com.app.dojo.dtos.ScheduleDTO;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "test")
class SheduleControllerTest {
  @Autowired
  private TestRestTemplate testRestTemplate;
  private ScheduleDTO scheduleDTO;
  private String url="http://localhost:8080/api/dojo-app/schedules";

  @BeforeEach
  void init(){
    this.scheduleDTO= new ScheduleDTOBuilder()
        .setDayName("Martes")
        .setHoursClass("10:00-12:00")
        .build();
  }

  @DisplayName("Test Schedule Controller, create a schedule")
  @Test
  @Order(1)
  void create()throws Exception {
    ResponseEntity<ScheduleDTO> response=testRestTemplate.postForEntity(url,scheduleDTO,ScheduleDTO.class);
    assertEquals(201,response.getStatusCodeValue());
    assertEquals(MediaType.APPLICATION_JSON,response.getHeaders().getContentType());
    assertNotNull(response.getBody());
  }
}