package com.app.dojo.repositories;

import com.app.dojo.builders.builderModels.CommentBuilder;
import com.app.dojo.builders.builderModels.CourseBuilder;
import com.app.dojo.builders.builderModels.StudentBuilder;
import com.app.dojo.builders.builderModels.TeacherBuilder;
import com.app.dojo.models.Comment;
import com.app.dojo.models.Course;
import com.app.dojo.models.Student;
import com.app.dojo.models.Teacher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ActiveProfiles(profiles = "test")
public class CommentRepositoryTest {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    private Comment comment;
    private Course course;
    private Teacher teacher;
    private Student student;

    LocalDate localDateObject = LocalDate.now();

    //Formatted date
    String date = "04/02/1995";
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    Date formatDate = format.parse(date);
    Date startDate = format.parse("01/06/2022");
    Date finishDate = format.parse("30/06/2022");


    public CommentRepositoryTest() throws ParseException {
    }

    @BeforeEach
    void begin(){
        comment = new CommentBuilder()
                .setDateComment(localDateObject)
                .setComment("Buen trabajo")
                .build();
        course = new CourseBuilder()
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
        student = new StudentBuilder()
                .setDni("12345678")
                .setNames("Jhon")
                .setLastnames("Quitian")
                .setBirthday(formatDate)
                .setEmail("jhonquitian@mail.com")
                .setPassword("12345678")
                .build();
    }

    @DisplayName("Test repository to create a comment")
    @Test
    void create(){
        Course courseSaved = this.courseRepository.save(course);
        Teacher teacherSaved = this.teacherRepository.save(teacher);
        Student studentSaved = this.studentRepository.save(student);

        comment.setCourse(courseSaved);
        comment.setTeacher(teacherSaved);
        comment.setStudent(studentSaved);
        Comment commentSaved = this.commentRepository.save(comment);

        assertNotNull(commentSaved);
        assertThat(commentSaved.getId()).isGreaterThan(0);
        assertNotNull(commentSaved.getCourse());
        assertNotNull(commentSaved.getTeacher());
        assertNotNull(commentSaved.getStudent());
    }

    @DisplayName("Test repository to get all comments")
    @Test
    void getAll(){
        Course courseSaved = this.courseRepository.save(course);
        Teacher teacherSaved = this.teacherRepository.save(teacher);
        Student studentSaved = this.studentRepository.save(student);
        comment.setCourse(courseSaved);
        comment.setTeacher(teacherSaved);
        comment.setStudent(studentSaved);
        this.commentRepository.save(comment);

        List<Comment> commentFound = this.commentRepository.findAll();

        assertNotNull(commentFound);
        assertThat(commentFound.size()).isEqualTo(1);
    }

    @DisplayName("Test repository to get a comment by id")
    @Test
    void getOneById(){
        Course courseSaved = this.courseRepository.save(course);
        Teacher teacherSaved = this.teacherRepository.save(teacher);
        Student studentSaved = this.studentRepository.save(student);
        comment.setCourse(courseSaved);
        comment.setTeacher(teacherSaved);
        comment.setStudent(studentSaved);
        Comment commentSaved = this.commentRepository.save(comment);

        Optional<Comment> commentFound = this.commentRepository.findById(commentSaved.getId());

        assertNotNull(commentFound);
    }

    @DisplayName("Test repository to get comments by course")
    @Test
    void getByCourse(){
        Course courseSaved = this.courseRepository.save(course);
        Teacher teacherSaved = this.teacherRepository.save(teacher);
        Student studentSaved = this.studentRepository.save(student);
        comment.setCourse(courseSaved);
        comment.setTeacher(teacherSaved);
        comment.setStudent(studentSaved);
        this.commentRepository.save(comment);

        Pageable pageable = PageRequest.of(0, 10);
        Page<Comment> commentsFound = this.commentRepository.findByCourse(courseSaved, pageable);

        assertThat(commentsFound.getContent().size()).isEqualTo(1);
        assertTrue(commentsFound.isLast());
        assertThat(commentsFound.getTotalElements()).isEqualTo(1);
    }

    @DisplayName("Test repository to get comments by teacher")
    @Test
    void getByTeacher(){
        Course courseSaved = this.courseRepository.save(course);
        Teacher teacherSaved = this.teacherRepository.save(teacher);
        Student studentSaved = this.studentRepository.save(student);
        comment.setCourse(courseSaved);
        comment.setTeacher(teacherSaved);
        comment.setStudent(studentSaved);
        this.commentRepository.save(comment);

        Pageable pageable = PageRequest.of(0, 10);
        Page<Comment> commentsFound = this.commentRepository.findByTeacher(teacherSaved, pageable);

        assertThat(commentsFound.getContent().size()).isEqualTo(1);
        assertTrue(commentsFound.isLast());
        assertThat(commentsFound.getTotalElements()).isEqualTo(1);
    }
}
