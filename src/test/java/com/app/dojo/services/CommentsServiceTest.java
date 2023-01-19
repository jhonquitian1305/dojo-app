package com.app.dojo.services;

import com.app.dojo.builders.builderDTO.CommentDTOBuilder;
import com.app.dojo.builders.builderModels.*;
import com.app.dojo.dtos.CommentDTO;
import com.app.dojo.dtos.CommentResponse;
import com.app.dojo.exception.errors.NotFoundException;
import com.app.dojo.mappers.MapperComment;
import com.app.dojo.models.*;
import com.app.dojo.repositories.CommentRepository;
import com.app.dojo.services.Interfaces.CourseService;
import com.app.dojo.services.Interfaces.StudentService;
import com.app.dojo.services.Interfaces.TeacherService;
import com.app.dojo.services.implementation.CommentServiceImp;
import com.app.dojo.services.strategyComments.CommentsContext;
import com.app.dojo.services.strategyComments.CommentsStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;
import org.springframework.test.context.ActiveProfiles;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles(profiles = "test")
public class CommentsServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentServiceImp commentServiceImp;

    @Mock
    private MapperComment mapperComment;

    @Mock
    private CourseService courseService;

    @Mock
    private TeacherService teacherService;

    @Mock
    private StudentService studentService;

    @Mock
    private CommentsContext commentsContext;

    @Mock
    private CommentsStrategy commentsStrategy;

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

    @DisplayName("Test service to create a comment")
    @Test
    void create() throws Exception {
        given(this.courseService.getOne(anyLong())).willReturn(course);
        given(this.teacherService.getById(anyLong())).willReturn(teacher);
        given(this.studentService.getStudentById(anyLong())).willReturn(student);
        given(this.commentRepository.save(any(Comment.class))).willReturn(comment);

        given(this.mapperComment.createComment(any(CommentDTO.class), any(Course.class), any(Teacher.class), any(Student.class))).willReturn(comment);

        Comment commentSaved = this.commentServiceImp.createOne(commentDTO);

        assertNotNull(commentSaved);
    }

    @DisplayName("Test service to find all comments by one condition")
    @Test
    void findAllByCondition() throws Exception {
        given(this.commentsContext.loadStrategy(anyString())).willReturn(commentsStrategy);
        Page<Comment> commentsFound = new PageImpl<>(List.of(comment));
        Sort sort = Sort.by("id").ascending();
        Pageable pageable = PageRequest.of(1, 1, sort);
        given(this.commentsStrategy.findComments(pageable, 1L)).willReturn(commentsFound);

        CommentResponse allComments = this.commentServiceImp.getAllByCondition(1, 1,"id", "asc", "Course", 1L);

        assertEquals(1, allComments.getSizePage());
        assertEquals(0, allComments.getNumberPage());
        assertEquals(1, allComments.getTotalElements());
        assertEquals(1, allComments.getTotalPages());
        assertTrue(allComments.isLastOne());
        assertThat(allComments.getContent().size()).isEqualTo(1);
    }

    @DisplayName("Test service to get a comment by id")
    @Test
    void getOne(){
        given(this.commentRepository.findById(anyLong())).willReturn(Optional.of(comment));

        Comment commentFound = this.commentServiceImp.getById(anyLong());

        assertNotNull(commentFound);
        assertEquals("Buen trabajo", commentFound.getComment());
        assertNotNull(commentFound.getCourse());
        assertNotNull(commentFound.getTeacher());
        assertNotNull(commentFound.getStudent());
    }

    @DisplayName("Test service to get a comment when doesn't exist")
    @Test
    void failGetOne(){
        given(this.commentRepository.findById(anyLong())).willReturn(Optional.empty());

        NotFoundException commentNotFound = assertThrows(NotFoundException.class, () -> {
            this.commentServiceImp.getById(1L);
        });

        assertEquals("Comment with id %s doesn't exists".formatted(1L), commentNotFound.getMessage());
    }
}
