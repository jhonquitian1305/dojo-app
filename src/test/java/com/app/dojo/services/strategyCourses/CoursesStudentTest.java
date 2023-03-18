package com.app.dojo.services.strategyCourses;

import com.app.dojo.builders.builderModels.CourseBuilder;
import com.app.dojo.builders.builderModels.LevelBuilder;
import com.app.dojo.builders.builderModels.StudentBuilder;
import com.app.dojo.builders.builderModels.TeacherBuilder;
import com.app.dojo.models.Course;
import com.app.dojo.models.Level;
import com.app.dojo.models.Student;
import com.app.dojo.models.Teacher;
import com.app.dojo.repositories.CourseRepository;
import com.app.dojo.services.Interfaces.StudentService;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles(profiles = "test")
public class CoursesStudentTest {
    @InjectMocks
    private CoursesStudent coursesStudent;
    @Mock
    private CourseRepository courseRepository;
    @Mock
    private StudentService studentService;

    private Course course;
    private Student student;

    LocalDate localDateObject = LocalDate.now();

    //Formatted date
    String date = "04/02/1995";
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    Date formatDate = format.parse(date);
    Date startDate = format.parse("01/06/2022");
    Date finishDate = format.parse("30/06/2022");

    public CoursesStudentTest() throws ParseException {
    }

    @BeforeEach
    void begin(){
        Level level= new LevelBuilder()
                .setId(1L)
                .setName("CINTA NEGRA")
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

        List<Teacher> teachers = new ArrayList<>();
        teachers.add(teacher);

        List<Student> students = new ArrayList<>();
        students.add(student);

        course = new CourseBuilder()
                .setId(1L)
                .setName("CINTA NEGRA PRINCIPIANTES")
                .setStartDate(startDate)
                .setFinishDate(finishDate)
                .setPrice(200000.0)
                .setLevel(level)
                .setTeachers(teachers)
                .setStudents(students)
                .build();
    }

    @DisplayName("Test StrategyCourses to get all courses by student")
    @Test
    void findCourses() throws Exception {
        Page<Course> courses = new PageImpl<>(List.of(course));
        Pageable pageable = PageRequest.of(1, 10);
        given(this.courseRepository.findByStudents(student, pageable)).willReturn(courses);
        given(this.studentService.getStudentById(anyLong())).willReturn(student);

        Page<Course> coursesFound = this.coursesStudent.findCourses(pageable, anyLong());

        assertAll(
                ()->assertEquals(1, coursesFound.getTotalElements()),
                ()->assertEquals(1, coursesFound.getSize()),
                ()->assertEquals(1, coursesFound.getTotalPages()),
                ()->assertEquals(0, coursesFound.getNumber()),
                ()->assertTrue(coursesFound.isLast()),
                ()->assertNotNull(coursesFound.getContent()),
                ()->assertThat(coursesFound.getContent().size()).isGreaterThan(0)
        );
    }
}
