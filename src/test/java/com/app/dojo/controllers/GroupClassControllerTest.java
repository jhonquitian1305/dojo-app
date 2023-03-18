package com.app.dojo.controllers;

import com.app.dojo.builders.builderDTO.GroupClassDTOBuilder;
import com.app.dojo.dtos.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GroupClassControllerTest {

  @Autowired
  private TestRestTemplate testRestTemplate;
  private String url ="http://localhost:8080/api/dojo-app/groups";
  private GroupClassDTO groupClassDTO;
  private Long id;
  @BeforeEach()
  void init() {
    groupClassDTO= new GroupClassDTOBuilder()
        .setCode("2345678")
        .setNameClass("PRINCIPIANTES 03")
        .setHoursPerWeek(2L)
        .setTotalHours(20L)
        .setWeeks(10L)
        .setCourse(1L)
        .setSchedules(List.of(1L))
        .setRooms(List.of(2L))
        .build();
  }
  @Order(1)
  @Test
  @DisplayName("Test GroupClassController, create a new group")
  void create(){
    ResponseEntity<GroupClassDTOResponse> response=this.testRestTemplate.postForEntity(url,groupClassDTO,GroupClassDTOResponse.class);
    id=response.getBody().getId();
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

  @Order(4)
  @Test
  @DisplayName("Test GroupClassService, test to check if there is a failure when trying to create a group with an incorrect total hours")
  void failCreateGroupWithWrongTotalHours(){
    groupClassDTO.setNameClass("PRINCIPIANTES 02");
    groupClassDTO.setHoursPerWeek(2L);
    groupClassDTO.setTotalHours(66L);
    ResponseEntity<GroupClassDTOResponse> response=this.testRestTemplate.postForEntity(url,groupClassDTO,GroupClassDTOResponse.class);
    assertEquals(400,response.getStatusCodeValue());
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
  }

  @Order(5)
  @Test
  @DisplayName("Test GroupClassService,test to check if there is a failure when trying to create a group with similar information")
  void failCreateGroupWithSimilarInformation(){
    groupClassDTO.setNameClass("PRINCIPIANTES 02");
    groupClassDTO.setHoursPerWeek(2L);
    groupClassDTO.setTotalHours(20L);
    ResponseEntity<GroupClassDTOResponse> response=this.testRestTemplate.postForEntity(url,groupClassDTO,GroupClassDTOResponse.class);
    assertEquals(400,response.getStatusCodeValue());
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
  }

  @Order(6)
  @Test
  @DisplayName("Test GroupClassService, Test to find all groups")
  void findAll() throws Exception {
    ResponseEntity<GroupClassResponse> response=this.testRestTemplate.getForEntity(url,GroupClassResponse.class);
    assertEquals(200,response.getStatusCodeValue());
    assertEquals(HttpStatus.OK,response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(MediaType.APPLICATION_JSON,response.getHeaders().getContentType());
    assertThat(response.getBody().getContent().size()).isGreaterThan(0);
  }

  @Order(7)
  @Test
  @DisplayName("Test GroupClassService, test to find a group")
  void findOne(){
    ResponseEntity<GroupClassDTOResponse> response=this.testRestTemplate.getForEntity(url+"/3",GroupClassDTOResponse.class);
    assertEquals(200,response.getStatusCodeValue());
    assertEquals(HttpStatus.OK,response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(MediaType.APPLICATION_JSON,response.getHeaders().getContentType());
  }

  @Order(8)
  @Test
  @DisplayName("Test GroupClassService, Test to check if there is a failure when trying to find a group that doesn't exist")
  void failFindOne(){
    ResponseEntity<GroupClassDTOResponse> response=this.testRestTemplate.getForEntity(url+"/%s".formatted(1000),GroupClassDTOResponse.class);
    assertEquals(404,response.getStatusCodeValue());
    assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
  }

  @Order(9)
  @Test
  @DisplayName("Test GroupClassService, Test to delete a group")
  void delete(){
    Map<String,Long> pathVariables= new HashMap<>();
    pathVariables.put("id",id);
    ResponseEntity<Void> exchange=this.testRestTemplate.exchange(url+"/3", HttpMethod.DELETE,null, Void.class,pathVariables);
    assertEquals(204,exchange.getStatusCodeValue());
    assertEquals(HttpStatus.NO_CONTENT,exchange.getStatusCode());
  }
}