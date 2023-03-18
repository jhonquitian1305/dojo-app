package com.app.dojo.controllers;

import com.app.dojo.builders.builderDTO.ScheduleDTOBuilder;
import com.app.dojo.builders.builderModels.ScheduleBuilder;
import com.app.dojo.dtos.RoomDTO;
import com.app.dojo.dtos.RoomResponse;
import com.app.dojo.dtos.ScheduleDTO;
import com.app.dojo.dtos.ScheduleResponse;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
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
        .setHoursClass("16:00-18:00")
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
  @Order(2)
  @DisplayName("Test schedule Controller, failure when trying to create a schedule that already exists")
  @Test
  void failCreate(){
    ResponseEntity<ScheduleDTO> response=testRestTemplate.postForEntity(url,scheduleDTO,ScheduleDTO.class);
    assertEquals(400,response.getStatusCodeValue());
  }

  @Order(3)
  @DisplayName("Test Schedule Controller, find all schedules")
  @Test
  void findAll(){
    ResponseEntity<ScheduleResponse> response=this.testRestTemplate.getForEntity(url,ScheduleResponse.class);
    assertEquals(200,response.getStatusCodeValue());
    assertEquals(MediaType.APPLICATION_JSON,response.getHeaders().getContentType());
    assertThat(response.getBody()).isNotNull();
    assertThat(response.getBody().getContent().size()).isGreaterThan(0);
  }

  @Order(4)
  @DisplayName("Test Schedule Controller, find one Schedule")
  @Test
  void findOne(){
    ResponseEntity<ScheduleDTO> response=this.testRestTemplate.getForEntity(url+"/1",ScheduleDTO.class);
    assertEquals(200,response.getStatusCodeValue());
    assertEquals(MediaType.APPLICATION_JSON,response.getHeaders().getContentType());
    assertThat(response.getBody()).isNotNull();
    assertEquals(1L,response.getBody().getId());
  }

  @Order(5)
  @DisplayName("Test Schedule Controller, Verify Failure when trying to find a schedule that doesn't exist")
  @Test
  void failFinOne(){
    ResponseEntity<ScheduleDTO> response=this.testRestTemplate.getForEntity(url+"/187",ScheduleDTO.class);
    assertEquals(404,response.getStatusCodeValue());
  }
  @Order(6)
  @Test
  @DisplayName("Test Schedule Controller, test to delete a schedule")
  void delete() throws Exception{
    ResponseEntity<ScheduleResponse> response=testRestTemplate.getForEntity(url,ScheduleResponse.class);
    assertThat(response.getBody().getContent().size()).isGreaterThan(0);

    Map<String,Long> pathVariables= new HashMap<>();
    pathVariables.put("id",1L);

    ResponseEntity<Void> exchange=testRestTemplate.exchange(url+"/1", HttpMethod.DELETE,null,Void.class,pathVariables);

    assertEquals(HttpStatus.NO_CONTENT,exchange.getStatusCode());
    assertEquals(204,exchange.getStatusCodeValue());
  }
}