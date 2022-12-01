package com.app.dojo.controllers;

import com.app.dojo.builders.builderDTO.GroupClassDTOBuilder;
import com.app.dojo.dtos.*;
import com.app.dojo.exception.errors.BadRequest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GroupClassControllerTest {

  @Autowired
  private TestRestTemplate testRestTemplate;
  private String url ="http://localhost:8080/api/dojo-app/groups";
  private GroupClassDTO groupClassDTO;
  @BeforeEach()
  void init() {
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
  @Order(1)
  @Test
  @DisplayName("Test GroupClassController, create a new group")
  void create(){
    ResponseEntity<GroupClassDTOResponse> response=this.testRestTemplate.postForEntity(url,groupClassDTO,GroupClassDTOResponse.class);
    assertEquals(201,response.getStatusCodeValue());
    assertEquals(HttpStatus.CREATED,response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(MediaType.APPLICATION_JSON,response.getHeaders().getContentType());
  }

  @Order(2)
  @Test
  @DisplayName("Test GroupClassController, Test to check when trying to create a group  with an already saved name")
  void failCreateGroupWithWrongName(){
    ResponseEntity<GroupClassDTOResponse> response=this.testRestTemplate.postForEntity(url,groupClassDTO,GroupClassDTOResponse.class);
    assertEquals(400,response.getStatusCodeValue());
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
  }

  @Order(3)
  @Test
  @DisplayName("Test GroupClassService, test to check if there is a failure when trying to create a group with an incorrect hours per week")
  void failCreateGroupWithWrongHoursWeek(){
    groupClassDTO.setNameClass("PRINCIPIANTES 02");
    groupClassDTO.setHoursPerWeek(8L);
    ResponseEntity<GroupClassDTOResponse> response=this.testRestTemplate.postForEntity(url,groupClassDTO,GroupClassDTOResponse.class);
    assertEquals(400,response.getStatusCodeValue());
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
  }
}