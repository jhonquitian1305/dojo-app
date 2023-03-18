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
import com.app.dojo.services.Interfaces.TeacherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles(profiles = "test")
public class CommentsTeacherTest {
    @InjectMocks
    private CommentsTeacher comments;
    @Mock
    private CommentRepository commentRepository;
    @Mock
    private TeacherService teacherService;

    private Comment comment;
    private Teacher teacher;

    LocalDate localDateObject = LocalDate.now();

    //Formatted date
    String date = "04/02/1995";
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    Date formatDate = format.parse(date);
    Date startDate = format.parse("01/06/2022");
    Date finishDate = format.parse("30/06/2022");

    public CommentsTeacherTest() throws ParseException {
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
        teacher = new TeacherBuilder()
                .setDni("987654321")
                .setNames("Jorge")
                .setLastnames("Ortíz")
                .setBirthday(formatDate)
                .setEmail("jorgeortiz@mail.com")
                .setPassword("987654321")
                .build();
        Student student = new StudentBuilder()
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

    @DisplayName("Test strategyComments to get all comments by teacher")
    @Test
    void findComments() {
        Page<Comment> comments = new PageImpl<>(List.of(comment));
        Pageable pageable = PageRequest.of(1, 10);
        given(this.commentRepository.findByTeacher(teacher, pageable)).willReturn(comments);
        given(this.teacherService.getById(anyLong())).willReturn(teacher);

        Page<Comment> commentsFound = this.comments.findComments(pageable, anyLong());

        assertAll(
                ()->assertEquals(1, commentsFound.getTotalElements()),
                ()->assertEquals(1, commentsFound.getSize()),
                ()->assertEquals(1, commentsFound.getTotalPages()),
                ()->assertEquals(0, commentsFound.getNumber()),
                ()->assertTrue(commentsFound.isLast()),
                ()->assertNotNull(commentsFound.getContent()),
                ()->assertThat(commentsFound.getContent().size()).isGreaterThan(0)
        );
    }
}
