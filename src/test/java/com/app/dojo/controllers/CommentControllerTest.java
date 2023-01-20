package com.app.dojo.controllers;

import com.app.dojo.builders.builderDTO.*;
import com.app.dojo.dtos.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "test")
public class CommentControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    private CommentDTO commentDTO;
    private CourseDTO courseDTO;
    private TeacherDTO teacherDTO;
    private StudentDTO studentDTO;
    private LevelDTO levelDTO;

    LocalDate localDateObject = LocalDate.now();

    //Formatted date
    String date = "04/02/1995";
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    Date formatDate = format.parse(date);
    Date startDate = format.parse("01/06/2022");
    Date finishDate = format.parse("30/06/2022");

    public CommentControllerTest() throws ParseException {
    }

    @BeforeEach
    void begin(){
        levelDTO = new LevelDTOBuilder()
                .setName("CINTA NEGRA")
                .build();
        courseDTO = new CourseDTOBuilder()
                .setName("CINTA NEGRA PRINCIPIANTES")
                .setStartDate(startDate)
                .setFinishDate(finishDate)
                .setPrice(200000.0)
                .setLevel(1L)
                .build();
        teacherDTO = new TeacherDTOBuilder()
                .setDni("987654321")
                .setNames("Jorge")
                .setLastnames("Ortíz")
                .setBirthday(formatDate)
                .setEmail("jorgeortiz@mail.com")
                .setPassword("987654321")
                .build();
        studentDTO = new StudentDTOBuilder()
                .setDni("12345678")
                .setNames("Jhon")
                .setLastnames("Quitian")
                .setBirthday(formatDate)
                .setEmail("jhonquitian@mail.com")
                .setPassword("12345678")
                .build();
        commentDTO = new CommentDTOBuilder()
                .setDateComment(localDateObject)
                .setComment("Buen trabajo")
                .setCourse(1L)
                .setTeacher(1L)
                .setStudent(1L)
                .build();
    }
}
