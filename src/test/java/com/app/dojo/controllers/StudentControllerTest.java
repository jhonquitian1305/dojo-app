package com.app.dojo.controllers;

import com.app.dojo.builders.builderDTO.StudentDTOBuilder;
import com.app.dojo.dtos.StudentDTO;
import com.app.dojo.dtos.StudentResponse;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "test")
public class StudentControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    private StudentDTO studentDTO;

    private String url = "http://localhost:8080/api/dojo-app/students";

    //Formatted date
    String date = "23/11/2015";
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    Date formatDate = format.parse(date);

    public StudentControllerTest() throws ParseException {
    }

    @BeforeEach
    void init(){
        studentDTO = new StudentDTOBuilder()
                .setDni("12345678")
                .setNames("Jhon")
                .setLastnames("Quitian")
                .setBirthday(formatDate)
                .setEmail("jhonquitian@mail.com")
                .setPassword("12345678")
                .build();
    }

    @DisplayName("Test Controller to save a student")
    @Test
    @Order(1)
    void create(){
        ResponseEntity<StudentDTO> response = testRestTemplate.postForEntity(url, studentDTO, StudentDTO.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());

        StudentDTO studentSaved = response.getBody();
        assertNotNull(studentSaved);

        assertEquals("12345678", studentSaved.getDni());
        assertEquals("Jhon", studentSaved.getNames());
        assertEquals("Quitian", studentSaved.getLastnames());
        assertEquals(formatDate, studentSaved.getBirthday());
        assertEquals("jhonquitian@mail.com", studentSaved.getEmail());
        assertEquals("12345678", studentSaved.getPassword());
    }

    @DisplayName("Test controller to save a student when exists")
    @Test
    @Order(2)
    void failCreate(){
        ResponseEntity<StudentDTO> response = testRestTemplate.postForEntity(url, studentDTO, StudentDTO.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @DisplayName("Test controller to get all students")
    @Test
    @Order(3)
    void findAll(){
        ResponseEntity<StudentResponse> response = this.testRestTemplate.getForEntity(url, StudentResponse.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON,response.getHeaders().getContentType());
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getContent().size()).isGreaterThan(0);
    }

    @DisplayName("Test controller to get a student by id")
    @Test
    @Order(4)
    void findOne(){
        ResponseEntity<StudentDTO> response = this.testRestTemplate.getForEntity(url+"/1", StudentDTO.class);
        StudentDTO student = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());

        assertNotNull(student);
        assertEquals(1L, student.getId());
    }

    @DisplayName("Test controller to get a student when doesn't exists")
    @Test
    @Order(5)
    void failGetOne(){
        ResponseEntity<StudentDTO> response = this.testRestTemplate.getForEntity(url+"/10000", StudentDTO.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @DisplayName("Test controller to delete a student")
    @Test
    @Order(6)
    void deleteOne(){
        ResponseEntity<StudentResponse> response = this.testRestTemplate.getForEntity(url, StudentResponse.class);
        assertThat(Objects.requireNonNull(response.getBody()).getContent().size()).isGreaterThan(0);

        Map<String, Long> pathVariables = new HashMap<>();
        pathVariables.put("id", 1L);
        ResponseEntity<Void> exchange = this.testRestTemplate.exchange(url+"/{id}", HttpMethod.DELETE, null, Void.class, pathVariables);

        assertEquals(HttpStatus.NO_CONTENT, exchange.getStatusCode());
        assertFalse(exchange.hasBody());
    }

    @DisplayName("Test controller to delete a student when doesn't exists")
    @Test
    @Order(7)
    void failDeleteOne(){
        ResponseEntity<StudentResponse> response = this.testRestTemplate.getForEntity(url, StudentResponse.class);
        assertThat(Objects.requireNonNull(response.getBody()).getContent().size()).isGreaterThan(0);

        Map<String, Long> pathVariables = new HashMap<>();
        pathVariables.put("id", 1L);
        ResponseEntity<Void> exchange = this.testRestTemplate.exchange(url+"/{id}", HttpMethod.DELETE, null, Void.class, pathVariables);

        assertEquals(HttpStatus.NOT_FOUND, exchange.getStatusCode());
    }
}
