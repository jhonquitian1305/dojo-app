package com.app.dojo.controllers;

import com.app.dojo.builders.builderDTO.*;
import com.app.dojo.dtos.*;
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
import java.time.LocalDate;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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

    private String urlComment = "http://localhost:8080/api/dojo-app/comments";
    private String urlCourse = "http://localhost:8080/api/dojo-app/courses";
    private String urlTeacher = "http://localhost:8080/api/dojo-app/teachers";
    private String urlStudent = "http://localhost:8080/api/dojo-app/students";
    private String urlLevel = "http://localhost:8080/api/dojo-app/levels";

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
                .setId(1L)
                .setName("CINTA NEGRA")
                .build();
        courseDTO = new CourseDTOBuilder()
                .setId(1L)
                .setName("CINTA NEGRA PRINCIPIANTES")
                .setStartDate(startDate)
                .setFinishDate(finishDate)
                .setPrice(200000.0)
                .setLevel(1L)
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
        studentDTO = new StudentDTOBuilder()
                .setId(2L)
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
                .setStudent(2L)
                .build();
    }

    @DisplayName("Test controller to create a comment")
    @Test
    @Order(1)
    void createOne(){
        ResponseEntity<LevelDTO> levelSaved= this.testRestTemplate.postForEntity(urlLevel, levelDTO, LevelDTO.class);
        courseDTO.setLevel(levelDTO.getId());

        ResponseEntity<CourseDTOResponse> courseSaved = this.testRestTemplate.postForEntity(urlCourse, courseDTO, CourseDTOResponse.class);
        commentDTO.setCourse(courseDTO.getId());

        ResponseEntity<TeacherDTO> teacherSaved = this.testRestTemplate.postForEntity(urlTeacher, teacherDTO, TeacherDTO.class);
        commentDTO.setTeacher(teacherDTO.getId());

        ResponseEntity<StudentDTO> studentSaved = this.testRestTemplate.postForEntity(urlStudent, studentDTO, StudentDTO.class);
        commentDTO.setStudent(studentDTO.getId());

        ResponseEntity<CommentDTOResponse> response = this.testRestTemplate.postForEntity(urlComment, commentDTO, CommentDTOResponse.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
    }

    @DisplayName("Test Controller to create a comment when the course doesn't exist")
    @Test
    @Order(2)
    void failCreateWhenCourseDoesNotExist(){
        commentDTO.setCourse(50L);

        ResponseEntity<CommentDTOResponse> response = this.testRestTemplate.postForEntity(urlComment, commentDTO, CommentDTOResponse.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
    @DisplayName("Test Controller to create a comment when the teacher doesn't exist")
    @Test
    @Order(3)
    void failCreateWhenTeacherDoesNotExist(){
        commentDTO.setTeacher(50L);

        ResponseEntity<CommentDTOResponse> response = this.testRestTemplate.postForEntity(urlComment, commentDTO, CommentDTOResponse.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @DisplayName("Test Controller to create a comment when the student doesn't exist")
    @Test
    @Order(4)
    void failCreateWhenStudentDoesNotExist(){
        commentDTO.setStudent(50L);

        ResponseEntity<CommentDTOResponse> response = this.testRestTemplate.postForEntity(urlComment, commentDTO, CommentDTOResponse.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @DisplayName("Test Controller to get all comments")
    @Test
    @Order(5)
    void getAll(){
        ResponseEntity<CommentResponse> response = this.testRestTemplate.getForEntity(urlComment, CommentResponse.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.hasBody());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
        assertThat(response.getBody().getContent().size()).isGreaterThan(0);
    }

    @DisplayName("Test Controller to get comments by course")
    @Test
    @Order(6)
    void getCommentsByCourse(){
        ResponseEntity<CommentResponse> response = this.testRestTemplate.getForEntity(urlComment+"?model=course&id=1", CommentResponse.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.hasBody());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
        assertThat(response.getBody().getContent().size()).isGreaterThan(0);
    }

    @DisplayName("Test Controller to get comments by teacher")
    @Test
    @Order(7)
    void getCommentsByTeacher(){
        ResponseEntity<CommentResponse> response = this.testRestTemplate.getForEntity(urlComment+"?model=teacher&id=1", CommentResponse.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.hasBody());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
        assertThat(response.getBody().getContent().size()).isGreaterThan(0);
    }

    @DisplayName("Test Controller to get comments by student")
    @Test
    @Order(8)
    void getCommentsByStudent(){
        ResponseEntity<CommentResponse> response = this.testRestTemplate.getForEntity(urlComment+"?model=student&id=2", CommentResponse.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.hasBody());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
        assertThat(response.getBody().getContent().size()).isGreaterThan(0);
    }

    @DisplayName("Test Controller to get a comment by id")
    @Test
    @Order(9)
    void getOne(){
        ResponseEntity<CommentDTOResponse> response = this.testRestTemplate.getForEntity(urlComment+"/1", CommentDTOResponse.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.hasBody());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
    }
}
