package com.app.dojo.controllers;

import com.app.dojo.builders.builderDTO.TeacherDTOBuilder;
import com.app.dojo.dtos.TeacherDTO;
import com.app.dojo.dtos.TeacherResponse;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "test")
public class TeacherControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    private TeacherDTO teacherDTO;

    private String url = "http://localhost:8080/api/dojo-app/teachers";

    //Formatted date
    String date = "23/11/2015";
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    Date formatDate = format.parse(date);


    public TeacherControllerTest() throws ParseException {
    }

    @BeforeEach
    void init(){
        teacherDTO = new TeacherDTOBuilder()
                .setId(1L)
                .setDni("987654321")
                .setNames("Jorge")
                .setLastnames("Ortíz")
                .setBirthday(formatDate)
                .setEmail("jorgeortiz@mail.com")
                .setPassword("987654321")
                .build();
    }

    @DisplayName("Test Controller to save a teacher")
    @Test
    @Order(1)
    void create(){
        ResponseEntity<TeacherDTO> response = this.testRestTemplate.postForEntity(url, teacherDTO, TeacherDTO.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());

        TeacherDTO teacherSaved = response.getBody();
        assertNotNull(teacherSaved);

        assertEquals("987654321", teacherSaved.getDni());
        assertEquals("Jorge", teacherSaved.getNames());
        assertEquals("Ortíz", teacherSaved.getLastnames());
        assertEquals(formatDate, teacherSaved.getBirthday());
        assertEquals("jorgeortiz@mail.com", teacherSaved.getEmail());
        assertEquals("987654321", teacherSaved.getPassword());
    }

    @DisplayName("Test controller to save a teacher when exists")
    @Test
    @Order(2)
    void failCreate(){
        ResponseEntity<TeacherDTO> response = this.testRestTemplate.postForEntity(url, teacherDTO, TeacherDTO.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @DisplayName("Test controller to get all teachers")
    @Test
    @Order(3)
    void findAll(){
        ResponseEntity<TeacherResponse> response = this.testRestTemplate.getForEntity(url, TeacherResponse.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getContent()).size().isGreaterThan(0);
    }

    @DisplayName("Test controller to get a teacher by id")
    @Test
    @Order(4)
    void findOne(){
        ResponseEntity<TeacherDTO> response = this.testRestTemplate.getForEntity(url+"/1", TeacherDTO.class);
        TeacherDTO teacher = response.getBody();
        System.out.println(response.getStatusCode());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());

        assertNotNull(teacher);
        assertEquals(1L, teacher.getId());
    }

    @DisplayName("Test controller to get a teacher when doesn't exists")
    @Test
    @Order(5)
    void failGetOne(){
        ResponseEntity<TeacherDTO> response = this.testRestTemplate.getForEntity(url+"/100000", TeacherDTO.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @DisplayName("Test controller to delete a teacher")
    @Test
    @Order(6)
    void deleteOne(){
        ResponseEntity<TeacherResponse> response = this.testRestTemplate.getForEntity(url, TeacherResponse.class);
        assertThat(Objects.requireNonNull(response.getBody()).getContent().size()).isGreaterThan(0);

        Map<String, Long> pathVariables = new HashMap<>();
        pathVariables.put("id", 1L);
        ResponseEntity<Void> exchange = this.testRestTemplate.exchange(url+"/{id}", HttpMethod.DELETE, null, Void.class, pathVariables);

        assertEquals(HttpStatus.OK, exchange.getStatusCode());
        assertFalse(exchange.hasBody());
    }

    @DisplayName("Test controller to delete a teacher when doesn't exists")
    @Test
    @Order(7)
    void failDeleteOne(){
        ResponseEntity<TeacherResponse> response = this.testRestTemplate.getForEntity(url, TeacherResponse.class);
        assertThat(Objects.requireNonNull(response.getBody()).getContent().size()).isGreaterThan(0);

        Map<String, Long> pathVariables = new HashMap<>();
        pathVariables.put("id", 1L);
        ResponseEntity<Void> exchange = this.testRestTemplate.exchange(url+"/{id}", HttpMethod.DELETE, null, Void.class, pathVariables);

        assertEquals(HttpStatus.NOT_FOUND, exchange.getStatusCode());
    }
}
