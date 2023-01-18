package com.app.dojo.services.strategyComments;

import com.app.dojo.builders.builderModels.CommentBuilder;
import com.app.dojo.builders.builderModels.CourseBuilder;
import com.app.dojo.builders.builderModels.StudentBuilder;
import com.app.dojo.builders.builderModels.TeacherBuilder;
import com.app.dojo.models.Comment;
import com.app.dojo.models.Course;
import com.app.dojo.models.Student;
import com.app.dojo.models.Teacher;
import com.app.dojo.repositories.CommentRepository;
import com.app.dojo.services.Interfaces.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class CommentsStudentTest {
    @InjectMocks
    private CommentsStudent commentsStudent;
    @Mock
    private CommentRepository commentRepository;
    @Mock
    private StudentService studentService;

    private Comment comment;
    private Student student;

    LocalDate localDateObject = LocalDate.now();

    //Formatted date
    String date = "04/02/1995";
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    Date formatDate = format.parse(date);
    Date startDate = format.parse("01/06/2022");
    Date finishDate = format.parse("30/06/2022");

    public CommentsStudentTest() throws ParseException {
    }

    @BeforeEach
    void begin(){
        Course course = new CourseBuilder()
                .setId(1L)
                .setName("CINTA NEGRA PRINCIPIANTES")
                .setStartDate(startDate)
                .setFinishDate(finishDate)
                .setPrice(200000.0)
                .build();
        Teacher teacher = new TeacherBuilder()
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
    }
}
