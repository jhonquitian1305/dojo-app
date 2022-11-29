package com.app.dojo.controllers;

import com.app.dojo.builders.builderDTO.CourseDTOBuilder;
import com.app.dojo.dtos.CourseDTO;
import com.app.dojo.dtos.CourseDTOResponse;
import com.app.dojo.dtos.CourseResponse;
import com.app.dojo.dtos.LevelDTO;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "test")
class CourseControllerTest {
  @Autowired
  private TestRestTemplate testRestTemplate;
  private LevelDTO levelDTO;
  private String url="http://localhost:8080/api/dojo-app/levels";
  private CourseDTO courseDTO;
  private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

  @BeforeEach()
  void init() throws ParseException {

    Date startDate = format.parse("2022-06-01");
    Date finishDate = format.parse("2022-06-30");

    courseDTO=new CourseDTOBuilder()
        .setName("CINTA BLANCA PRINCIPIANTES")
        .setStartDate(startDate)
        .setFinishDate(finishDate)
        .setPrice(200000.0)
        .setLevel(3L)
        .build();
  }

  @Order(1)
  @Test
  @DisplayName("Test CourseController, create a course")
  void create(){
    ResponseEntity<CourseDTOResponse> response=this.testRestTemplate.postForEntity(url,courseDTO,CourseDTOResponse.class);
    assertEquals(201,response.getStatusCodeValue());
    assertEquals(HttpStatus.CREATED,response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(MediaType.APPLICATION_JSON,response.getHeaders().getContentType());
  }
  @Order(2)
  @Test
  @DisplayName("Test CourseController, verify failure when trying to create a course with wrong dates")
  void failCreateCourseWithWrongDates() throws ParseException {
    courseDTO.setStartDate(format.parse("2022-12-10"));
    courseDTO.setFinishDate(format.parse("2022-11-10"));
    ResponseEntity<CourseDTOResponse> response=this.testRestTemplate.postForEntity(url,courseDTO,CourseDTOResponse.class);
    assertEquals(400,response.getStatusCodeValue());
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
  }

  @Order(3)
  @Test
  @DisplayName("Test CourseController, verify failure when trying to create a course with similar name")
  void failCreateCourseWithSimilarName(){
    ResponseEntity<CourseDTOResponse> response=this.testRestTemplate.postForEntity(url,courseDTO,CourseDTOResponse.class);
    assertEquals(400,response.getStatusCodeValue());
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
  }

  @Order(4)
  @Test
  @DisplayName("Test CourseController, get all courses")
  void getAllCourses(){
    ResponseEntity<CourseResponse> response=this.testRestTemplate.getForEntity(url,CourseResponse.class);
    assertEquals(200,response.getStatusCodeValue());
    assertEquals(HttpStatus.OK,response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(MediaType.APPLICATION_JSON,response.getHeaders().getContentType());
    assertThat(response.getBody().getContent().size()).isGreaterThan(0);
  }

  @Order(5)
  @Test
  @DisplayName("Test CourseController, Test to find courses by level")
  void findCoursesByLevel(){
    ResponseEntity<CourseResponse> response=this.testRestTemplate.getForEntity(url+"?id=2&model=level",CourseResponse.class);
    assertEquals(200,response.getStatusCodeValue());
    assertEquals(HttpStatus.OK,response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(MediaType.APPLICATION_JSON,response.getHeaders().getContentType());
    assertThat(response.getBody().getContent().size()).isGreaterThan(0);
  }

  @Order(6)
  @Test
  @DisplayName("Test CourseController, Test to find a course")
  void getOne(){
    ResponseEntity<CourseDTOResponse> response=this.testRestTemplate.getForEntity(url+"/2",CourseDTOResponse.class);
    assertEquals(200,response.getStatusCodeValue());
    assertEquals(HttpStatus.OK,response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(MediaType.APPLICATION_JSON,response.getHeaders().getContentType());
  }

  @Order(7)
  @Test
  @DisplayName("Test CourseController, verify failure when trying to find a course that doesn't exist")
  void failFindOne(){
    ResponseEntity<CourseDTOResponse> response=this.testRestTemplate.getForEntity(url+"/676",CourseDTOResponse.class);
    assertEquals(404,response.getStatusCodeValue());
    assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
  }
}