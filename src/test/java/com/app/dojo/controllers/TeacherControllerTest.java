package com.app.dojo.controllers;

import com.app.dojo.builders.builderDTO.TeacherDTOBuilder;
import com.app.dojo.dtos.TeacherDTO;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
}
