package com.app.dojo.controllers;

import com.app.dojo.builders.builderDTO.DiplomaDTOBuilder;
import com.app.dojo.builders.builderDTO.StudentDTOBuilder;
import com.app.dojo.builders.builderDTO.TeacherDTOBuilder;
import com.app.dojo.dtos.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "test")
public class DiplomaControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    private String urlDiplomaStudent = "http://localhost:8080/api/dojo-app/diplomas/students/1";
    private String urlStudent = "http://localhost:8080/api/dojo-app/students";


    private String urlDiplomaTeacher = "http://localhost:8080/api/dojo-app/diplomas/teachers/2";
    private String urlTeacher = "http://localhost:8080/api/dojo-app/teachers";

    private DiplomaDTO diplomaDTO;
    private UserDTO studentDTO;
    private UserDTO teacherDTO;

    //Formatted date
    String date = "23/11/2015";
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    Date formatDate = format.parse(date);

    public DiplomaControllerTest() throws ParseException {
    }

    @BeforeEach
    void begin(){
        studentDTO = new StudentDTOBuilder()
                .setId(1L)
                .setDni("12345678")
                .setNames("Jhon")
                .setLastnames("Quitian")
                .setBirthday(formatDate)
                .setEmail("jhonquitian@mail.com")
                .setPassword("12345678")
                .build();
        teacherDTO = new TeacherDTOBuilder()
                .setId(1L)
                .setDni("987654321")
                .setNames("Jorge")
                .setLastnames("Ortíz")
                .setBirthday(formatDate)
                .setEmail("jorgeortiz@mail.com")
                .setPassword("987654321")
                .build();
        diplomaDTO = new DiplomaDTOBuilder()
                .setDiplomaName("Certificado cinturón verde")
                .build();
    }

    @DisplayName("Test controller to create a diploma for a student")
    @Test
    @Order(1)
    void createDiplomaStudent(){
        ResponseEntity<StudentDTO> studentSaved = this.testRestTemplate.postForEntity(urlStudent, studentDTO, StudentDTO.class);

        ResponseEntity<DiplomaDTOResponse> response = this.testRestTemplate.postForEntity(urlDiplomaStudent, diplomaDTO, DiplomaDTOResponse.class);

        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(MediaType.APPLICATION_JSON,response.getHeaders().getContentType());
    }

    @DisplayName("Test controller to create a diploma for a teacher")
    @Test
    @Order(2)
    void createDiplomaTeacher(){
        ResponseEntity<TeacherDTO> teacherSaved = this.testRestTemplate.exchange(urlTeacher, HttpMethod.POST, new HttpEntity<>(teacherDTO), TeacherDTO.class);


        ResponseEntity<DiplomaDTOResponse> response = this.testRestTemplate.postForEntity(urlDiplomaTeacher, diplomaDTO, DiplomaDTOResponse.class);

        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(MediaType.APPLICATION_JSON,response.getHeaders().getContentType());
    }

    @DisplayName("Test controller to create diploma when name exist")
    @Test
    @Order(3)
    void failCreateDiplomaWhenNameExists(){
        diplomaDTO.setDiplomaName("Certificado cinturón verde");

        ResponseEntity<DiplomaDTOResponse> response = this.testRestTemplate.postForEntity(urlDiplomaTeacher, diplomaDTO, DiplomaDTOResponse.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
