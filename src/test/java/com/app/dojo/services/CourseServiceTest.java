package com.app.dojo.services;

import com.app.dojo.builders.builderDTO.CourseDTOBuilder;
import com.app.dojo.builders.builderModels.CourseBuilder;
import com.app.dojo.builders.builderModels.LevelBuilder;
import com.app.dojo.builders.builderModels.StudentBuilder;
import com.app.dojo.builders.builderModels.TeacherBuilder;
import com.app.dojo.dtos.CourseDTO;
import com.app.dojo.dtos.CourseResponse;
import com.app.dojo.exception.errors.BadRequest;
import com.app.dojo.exception.errors.NotFoundException;
import com.app.dojo.mappers.MapperCourse;
import com.app.dojo.models.Course;
import com.app.dojo.models.Level;
import com.app.dojo.models.Student;
import com.app.dojo.models.Teacher;
import com.app.dojo.repositories.CourseRepository;
import com.app.dojo.services.Interfaces.LevelService;
import com.app.dojo.services.Interfaces.StudentService;
import com.app.dojo.services.Interfaces.TeacherService;
import com.app.dojo.services.implementation.CourseServiceImp;
import com.app.dojo.services.strategyCourses.CoursesContext;
import com.app.dojo.services.strategyCourses.CoursesStrategy;
import org.apache.coyote.Request;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles(profiles = "test")
class CourseServiceTest {

  @Mock
  private CoursesStrategy coursesStrategy;
  @Mock
  private LevelService levelService;
  @Mock
  private MapperCourse mapperCourse;
  @Mock
  private CoursesContext coursesContext;
  @Mock
  private CourseRepository courseRepository;
  @InjectMocks
  private CourseServiceImp courseService;
  @Mock
  private TeacherService teacherService;
  @Mock
  private StudentService studentService;
  private Course course;
  private Level level;
  private CourseDTO courseDTO;
  private Teacher teacher;
  private Student student;
  private static  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
  @BeforeEach
  void init() throws ParseException {
    // Format Date

    Date startDate = format.parse("2022-06-01");
    Date finishDate = format.parse("2022-06-30");
    Date birthday = format.parse("1998-05-15");

    level = new LevelBuilder()
        .setName("CINTA NEGRA")
        .build();

    teacher = new TeacherBuilder()
            .setId(1L)
            .setDni("987654321")
            .setNames("Jorge")
            .setLastnames("Ortíz")
            .setBirthday(birthday)
            .setEmail("jorgeortiz@mail.com")
            .setPassword("987654321")
            .build();
    student = new StudentBuilder()
            .setId(2L)
            .setDni("12345678")
            .setNames("Jhon")
            .setLastnames("Quitian")
            .setBirthday(birthday)
            .setEmail("jhonquitian@mail.com")
            .setPassword("12345678")
            .build();
    ArrayList<Teacher> teachers = new ArrayList<>();
    teachers.add(teacher);
    ArrayList<Student> students = new ArrayList<>();
    students.add(student);
    ArrayList<Long> teacherId = new ArrayList<>();
    teacherId.add(teacher.getId());
    ArrayList<Long> studentId = new ArrayList<>();
    studentId.add(student.getId());

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

    courseDTO=new CourseDTOBuilder()
        .setName("CINTA NEGRA PRINCIPIANTES")
        .setStartDate(startDate)
        .setFinishDate(finishDate)
        .setPrice(200000.0)
        .setLevel(1L)
        .setTeachers(teacherId)
        .setStudents(studentId)
        .build();
  }

  @Test
  @DisplayName("Test CourseService, create a course")
  void create() throws Exception {
    //given
    given(this.courseRepository.existsCourseByName(anyString())).willReturn(false);
    given(this.courseRepository.save(any(Course.class))).willReturn(course);
    given(this.levelService.getOne(anyLong())).willReturn(level);
    given(this.teacherService.getById(anyLong())).willReturn(teacher);
    given(this.studentService.getStudentById(anyLong())).willReturn(student);
    //when
    Course courseSaved=this.courseService.create(courseDTO);
    //then
    assertNotNull(courseSaved);
  }

  @Test
  @DisplayName("Test CourseService, test to verify failre when trying to create a course with wrong dates")
  void failByDatesCreateCourse() throws ParseException {
    //given
    courseDTO.setStartDate(format.parse("2022-05-08"));
    courseDTO.setFinishDate(format.parse("2022-04-31"));
    //when
    BadRequest exception=assertThrows(BadRequest.class,()->{
      this.courseService.create(courseDTO);
    });
    //then
    assertEquals("The end date of the course must be after the start date.",exception.getMessage());
  }

  @Test
  @DisplayName("Test CourseService, Test to verify failure when trying to create a course that is already saved")
  void failCreateCourseExisting(){
    //given
    given(this.courseRepository.existsCourseByName(anyString())).willReturn(true);
    //when
    BadRequest exception=assertThrows(BadRequest.class,()->{
      this.courseService.create(courseDTO);
    });
    //then
    assertEquals("There is already a course saved under that name",exception.getMessage());
  }

  @Test
  @DisplayName("Test CourseService, test to find a course by its id")
  void getOne(){
    //given
    given(this.courseRepository.findById(anyLong())).willReturn(Optional.of(course));
    //when
    Course courseFound=this.courseService.getOne(anyLong());
    //Then
    assertNotNull(courseFound);
    assertEquals(200000.0,courseFound.getPrice());
    assertThat(courseFound.getLevel()).isNotNull();
    assertEquals("CINTA NEGRA PRINCIPIANTES",courseFound.getName());
    assertThat(courseFound.getTeachers()).isNotNull();
    assertThat(courseFound.getStudents()).isNotNull();
  }

  @Test
  @DisplayName("Test CourseService, verify failure in trying to find a course that doesn't exist")
  void failGetOne(){
    //given
    given(this.courseRepository.findById(anyLong())).willReturn(Optional.empty());
    //when
    NotFoundException notFoundException=assertThrows(NotFoundException.class,()->{
        this.courseService.getOne(anyLong());
    });
    //then
    assertEquals("There is no course saved with that id %s".formatted(0L),notFoundException.getMessage());
  }

  @Test
  @DisplayName("Test CourseService, find all courses")
  void findAll() throws Exception {
    //given
    given(coursesContext.loadStrategy(anyString())).willReturn(coursesStrategy);
    Page<Course>coursesFound=new PageImpl<>(List.of(course));
    Pageable pageable= PageRequest.of(1,1);
    given(coursesStrategy.findCourses(pageable,1L)).willReturn(coursesFound);
    //when
    CourseResponse allCourses=this.courseService.getAll(1,1,"LEVEL",1L);
    //then
    assertEquals(1,allCourses.getSizePage());
    assertEquals(0,allCourses.getNumberPage());
    assertEquals(1,allCourses.getTotalElements());
    assertEquals(1,allCourses.getTotalPages());
    assertTrue(allCourses.isLastOne());
    assertThat(allCourses.getContent().size()).isGreaterThan(0);
  }

  @Test
  @DisplayName("Test CourseService, test to update a course")
  void update() throws Exception {
    //given
    given(this.courseRepository.findById(anyLong())).willReturn(Optional.of(course));
    given(this.courseRepository.existsCourseByNameAndIdNot(anyString(),anyLong())).willReturn(false);
    given(this.levelService.getOne(anyLong())).willReturn(level);
    given(this.teacherService.getById(anyLong())).willReturn(teacher);
    given(this.studentService.getStudentById(anyLong())).willReturn(student);
    given(this.courseRepository.save(any(Course.class))).willReturn(course);
    given(this.mapperCourse.updateInformation(course, courseDTO, level, List.of(teacher), List.of(student))).willReturn(course);
    //when
    Course courseUpdated=this.courseService.update(anyLong(),courseDTO);
    //then
    assertNotNull(courseUpdated);
    assertThat(courseUpdated.getLevel()).isNotNull();
    assertThat(courseUpdated.getPrice()).isGreaterThan(0);
    assertThat(courseUpdated.getTeachers()).isNotNull();
    assertThat(courseUpdated.getStudents()).isNotNull();
  }

  @Test
  @DisplayName("Test CourseService, Check for failure when trying to update a course with a name that is already in use")
  void failUpdateCourseName(){
    //given
    given(this.courseRepository.findById(anyLong())).willReturn(Optional.of(course));
    given(this.courseRepository.existsCourseByNameAndIdNot(anyString(),anyLong())).willReturn(true);
    //when
    BadRequest badRequest=assertThrows(BadRequest.class,()->{
      this.courseService.update(anyLong(),courseDTO);
    });
    //then
    assertEquals("There is already a course saved under that name",badRequest.getMessage());
  }

  @Test
  @DisplayName("Test CourseService, Check for failure when trying to update a course with wrong dates")
  void failUpdateCourseWrongDates() throws ParseException {
    //given
    given(this.courseRepository.findById(anyLong())).willReturn(Optional.of(course));
    given(this.courseRepository.existsCourseByNameAndIdNot(anyString(),anyLong())).willReturn(false);
    courseDTO.setFinishDate(format.parse("2022-05-14"));
    courseDTO.setStartDate(format.parse("2022-06-14"));
    //when
    BadRequest badRequest=assertThrows(BadRequest.class,()->{
      this.courseService.update(anyLong(),courseDTO);
    });
    //then
    assertEquals("The end date of the course must be after the start date.",badRequest.getMessage());
  }

  @Test
  @DisplayName("Test CourseService, Delete a course")
  void delete(){
    //given
    given(this.courseRepository.findById(anyLong())).willReturn(Optional.of(course));
    willDoNothing().given(this.courseRepository).deleteById(anyLong());
    //when
    this.courseService.delete(anyLong());
    //then
    verify(this.courseRepository,times(1)).deleteById(anyLong());
  }

  @Test
  @DisplayName("Test CourseService, Check for failure when trying to delete a course that doesn't exist")
  void failDelete(){
    //given
    given(this.courseRepository.findById(anyLong())).willReturn(Optional.empty());
    //when
    NotFoundException notFoundException=assertThrows(NotFoundException.class,()->{
      this.courseService.delete(anyLong());
    });
    //then
    assertEquals("There is no course saved with that id %s".formatted(0),notFoundException.getMessage());
    verify(this.courseRepository,never()).deleteById(anyLong());
  }
}