package com.app.dojo.services;

import com.app.dojo.builders.builderDTO.CommentDTOBuilder;
import com.app.dojo.builders.builderModels.*;
import com.app.dojo.dtos.CommentDTO;
import com.app.dojo.mappers.MapperComment;
import com.app.dojo.models.*;
import com.app.dojo.repositories.CommentRepository;
import com.app.dojo.services.implementation.CommentServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles(profiles = "test")
public class CommentsServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentServiceImp commentServiceImp;

    @Mock
    private MapperComment mapperComment;

    private Comment comment;
    private Course course;
    private Teacher teacher;
    private Student student;

    private CommentDTO commentDTO;

    LocalDate localDateObject = LocalDate.now();

    //Formatted date
    String date = "04/02/1995";
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    Date formatDate = format.parse(date);
    Date startDate = format.parse("01/06/2022");
    Date finishDate = format.parse("30/06/2022");

    public CommentsServiceTest() throws ParseException {
    }

    @BeforeEach()
    void begin(){
        Level level = new LevelBuilder()
                .setName("CINTA NEGRA")
                .build();
        course = new CourseBuilder()
                .setId(1L)
                .setName("CINTA NEGRA PRINCIPIANTES")
                .setStartDate(startDate)
                .setFinishDate(finishDate)
                .setPrice(200000.0)
                .setLevel(level)
                .build();
        teacher = new TeacherBuilder()
                .setDni("987654321")
                .setNames("Jorge")
                .setLastnames("Ortíz")
                .setBirthday(formatDate)
                .setEmail("jorgeortiz@mail.com")
                .setPassword("987654321")
                .build();
        student = new StudentBuilder()
                .setDni("12345678")
                .setNames("Jhon")
                .setLastnames("Quitian")
                .setBirthday(formatDate)
                .setEmail("jhonquitian@mail.com")
                .setPassword("12345678")
                .build();
        comment = new CommentBuilder()
                .setDateComment(localDateObject)
                .setComment("Buen trabajo")
                .setCourse(course)
                .setTeacher(teacher)
                .setStudent(student)
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
