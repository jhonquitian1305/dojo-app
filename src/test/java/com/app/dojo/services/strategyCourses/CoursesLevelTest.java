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
import com.app.dojo.services.Interfaces.LevelService;
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
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
@ExtendWith(MockitoExtension.class)
@ActiveProfiles(profiles = "test")
class CoursesLevelTest {

  @Mock
  private CourseRepository courseRepository;
  @Mock
  private LevelService levelService;
  @InjectMocks
  private CoursesLevel coursesLevel;
  private Level level;
  private Course course;
  private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

  @BeforeEach()
  void init() throws ParseException {
    Teacher teacher = new TeacherBuilder()
            .setDni("987654321")
            .setNames("Jorge")
            .setLastnames("Ortíz")
            .setBirthday(format.parse("1995-05-15"))
            .setEmail("jorgeortiz@mail.com")
            .setPassword("987654321")
            .build();
    Student student = new StudentBuilder()
            .setDni("12345678")
            .setNames("Jhon")
            .setLastnames("Quitian")
            .setBirthday(format.parse("1998-03-10"))
            .setEmail("jhonquitian@mail.com")
            .setPassword("12345678")
            .build();

    List<Teacher> teachers = new ArrayList<>();
    teachers.add(teacher);

    List<Student> students = new ArrayList<>();
    students.add(student);

    level= new LevelBuilder()
        .setId(1L)
        .setName("CINTA NEGRA")
        .build();
    course= new CourseBuilder()
        .setName("PRINCIPIANTES CINTA NEGRA")
        .setLevel(level)
        .setPrice(500000.0)
        .setStartDate(format.parse("2022-11-10"))
        .setFinishDate(format.parse("2022-12-20"))
        .setId(1L)
        .setTeachers(teachers)
        .setStudents(students)
        .build();
  }

  @Test
  @DisplayName("Test CoursesLevelStrategy, Test to find courses by level")
  void findCourses(){
    //given
    Pageable pageable= PageRequest.of(1,10);
    Page<Course> courses= new PageImpl<>(List.of(course));
    given(this.courseRepository.findByLevel(level,pageable)).willReturn(courses);
    given(this.levelService.getOne(anyLong())).willReturn(level);
    //when
    Page<Course> coursesFound=this.coursesLevel.findCourses(pageable,anyLong());
    //then
    assertEquals(1,coursesFound.getTotalElements());
    assertEquals(1,coursesFound.getSize());
    assertEquals(1,coursesFound.getTotalPages());
    assertEquals(0,coursesFound.getNumber());
    assertTrue(coursesFound.isLast());
    assertNotNull(coursesFound.getContent());
    assertThat(coursesFound.getContent().size()).isGreaterThan(0);
  }

}