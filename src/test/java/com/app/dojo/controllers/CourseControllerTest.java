package com.app.dojo.controllers;

import com.app.dojo.builders.builderDTO.CourseDTOBuilder;
import com.app.dojo.builders.builderDTO.LevelDTOBuilder;
import com.app.dojo.builders.builderDTO.StudentDTOBuilder;
import com.app.dojo.builders.builderDTO.TeacherDTOBuilder;
import com.app.dojo.dtos.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "test")
class CourseControllerTest {
  @Autowired
  private TestRestTemplate testRestTemplate;
  private String urlCourse ="http://localhost:8080/api/dojo-app/courses";
  private String urlLevel ="http://localhost:8080/api/dojo-app/levels";
  private String urlTeacher = "http://localhost:8080/api/dojo-app/teachers";
  private String urlStudent = "http://localhost:8080/api/dojo-app/students";
  private CourseDTO courseDTO;
  private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
  private LevelDTO levelDTO;
  private TeacherDTO teacherDTO;
  private StudentDTO studentDTO;

  ArrayList<Long> teacherId = new ArrayList<>();
  ArrayList<Long> studentId = new ArrayList<>();

  @BeforeEach()
  void init() throws ParseException {

    Date startDate = format.parse("2022-05-01");
    Date finishDate = format.parse("2022-06-30");

    teacherDTO = new TeacherDTOBuilder()
            .setId(1L)
            .setDni("987654321")
            .setNames("Jorge")
            .setLastnames("Ortíz")
            .setBirthday(format.parse("1990-05-02"))
            .setEmail("jorgeortiz@mail.com")
            .setPassword("987654321")
            .build();
    studentDTO = new StudentDTOBuilder()
            .setId(2L)
            .setDni("12345678")
            .setNames("Jhon")
            .setLastnames("Quitian")
            .setBirthday(format.parse("1995-03-05"))
            .setEmail("jhonquitian@mail.com")
            .setPassword("12345678")
            .build();

    teacherId.add(teacherDTO.getId());
    studentId.add(studentDTO.getId());

    courseDTO=new CourseDTOBuilder()
        .setName("CINTA ORANGE AVANZADOS")
        .setStartDate(startDate)
        .setFinishDate(finishDate)
        .setPrice(200000.0)
        .setLevel(1L)
        .setTeachers(teacherId)
        .setStudents(studentId)
        .build();

    levelDTO= new LevelDTOBuilder()
        .setName("CINTA ORANGE")
        .build();
  }

  @Order(1)
  @Test
  @DisplayName("Test CourseController, create a course")
  void create(){
    //Create Level
    ResponseEntity<LevelDTO> levelSaved=this.testRestTemplate.postForEntity(urlLevel,levelDTO, LevelDTO.class);
    courseDTO.setLevel(levelSaved.getBody().getId());

    // create teacher
    ResponseEntity<TeacherDTO> teacherSaved = this.testRestTemplate.postForEntity(urlTeacher, teacherDTO, TeacherDTO.class);
    courseDTO.setTeachers(teacherId);

    // create student
    ResponseEntity<StudentDTO> studentSaved = this.testRestTemplate.postForEntity(urlStudent, studentDTO, StudentDTO.class);
    courseDTO.setStudents(studentId);

    //Create Course
    ResponseEntity<CourseDTOResponse> response=this.testRestTemplate.postForEntity(urlCourse,courseDTO,CourseDTOResponse.class);
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
    ResponseEntity<CourseDTOResponse> response=this.testRestTemplate.postForEntity(urlCourse,courseDTO,CourseDTOResponse.class);
    assertEquals(400,response.getStatusCodeValue());
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
  }

  @Order(3)
  @Test
  @DisplayName("Test CourseController, verify failure when trying to create a course with similar name")
  void failCreateCourseWithSimilarName(){
    ResponseEntity<CourseDTOResponse> response=this.testRestTemplate.postForEntity(urlCourse,courseDTO,CourseDTOResponse.class);
    assertEquals(400,response.getStatusCodeValue());
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
  }

  @Order(4)
  @Test
  @DisplayName("Test CourseController, get all courses")
  void getAllCourses(){
    ResponseEntity<CourseResponse> response=this.testRestTemplate.getForEntity(urlCourse,CourseResponse.class);
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
    ResponseEntity<CourseResponse> response=this.testRestTemplate.getForEntity(urlCourse +"?id=2&model=level",CourseResponse.class);
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
    ResponseEntity<CourseDTOResponse> response=this.testRestTemplate.getForEntity(urlCourse +"/2",CourseDTOResponse.class);
    assertEquals(200,response.getStatusCodeValue());
    assertEquals(HttpStatus.OK,response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(MediaType.APPLICATION_JSON,response.getHeaders().getContentType());
  }

  @Order(7)
  @Test
  @DisplayName("Test CourseController, verify failure when trying to find a course that doesn't exist")
  void failFindOne(){
    ResponseEntity<CourseDTOResponse> response=this.testRestTemplate.getForEntity(urlCourse +"/676",CourseDTOResponse.class);
    assertEquals(404,response.getStatusCodeValue());
    assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
  }

  @Order(8)
  @Test
  @DisplayName("Test CourseController, Test to delete a course")
  void delete(){
    Map<String,Long> pathVariables= new HashMap<>();
    pathVariables.put("id",1L);
    ResponseEntity<Void> exchange=this.testRestTemplate.exchange(urlCourse+"/9", HttpMethod.DELETE,null, Void.class,pathVariables);
    assertEquals(204,exchange.getStatusCodeValue());
    assertEquals(HttpStatus.NO_CONTENT,exchange.getStatusCode());
  }

  @Order(9)
  @DisplayName("Test CourseController, Test to find courses by teacher")
  @Test
  void findByTeacher(){
    ResponseEntity<CourseResponse> response=this.testRestTemplate.getForEntity(urlCourse +"?model=teacher&id=1",CourseResponse.class);
    assertEquals(HttpStatus.OK,response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(MediaType.APPLICATION_JSON,response.getHeaders().getContentType());
    assertThat(response.getBody().getContent().size()).isGreaterThan(0);
  }
}