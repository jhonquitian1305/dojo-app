package com.app.dojo.repositories;

import com.app.dojo.builders.builderModels.CourseBuilder;
import com.app.dojo.builders.builderModels.LevelBuilder;
import com.app.dojo.builders.builderModels.StudentBuilder;
import com.app.dojo.builders.builderModels.TeacherBuilder;
import com.app.dojo.models.Course;
import com.app.dojo.models.Level;
import com.app.dojo.models.Student;
import com.app.dojo.models.Teacher;
import org.junit.jupiter.api.BeforeAll;
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
import java.time.Clock;
import java.util.*;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@ActiveProfiles(profiles = "test")
class CourseRepositoryTest {
  @Autowired
  private CourseRepository courseRepository;
  @Autowired
  private LevelRepository levelRepository;
  @Autowired
  private TeacherRepository teacherRepository;
  @Autowired
  private StudentRepository studentRepository;
  private static Course course;
  private static Level level;
  private Teacher teacher;
  private Student student;

  @BeforeEach
  void init() throws ParseException {
    // Format Date
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    Date startDate = format.parse("2022-06-01");
    Date finishDate = format.parse("2022-06-30");
    Date birthday = format.parse("1998-05-15");

    level = new LevelBuilder()
        .setName("CINTA NEGRA")
        .build();
    course = new CourseBuilder()
        .setId(1L)
        .setName("CINTA NEGRA PRINCIPIANTES")
        .setStartDate(startDate)
        .setFinishDate(finishDate)
        .setPrice(200000.0)
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
  }

  @Test
  @DisplayName("Test CourseRepository, create a course")
  void create() {
    //given
    Level levelSaved = this.levelRepository.save(level);

    List<Teacher> teachers = new ArrayList<>();
    Teacher teacherSaved = this.teacherRepository.save(teacher);
    teachers.add(teacherSaved);

    List<Student> students = new ArrayList<>();
    Student studentSaved = this.studentRepository.save(student);
    students.add(studentSaved);
    //when
    course.setLevel(levelSaved);
    course.setTeachers(teachers);
    course.setStudents(students);
    Course courseSaved = this.courseRepository.save(course);
    //then
    assertNotNull(courseSaved);
    assertThat(courseSaved.getId()).isGreaterThan(0);
  }

  @Test
  @DisplayName("Test CourseRepository, find a course")
  void findOne() {
    //given
    Level levelSaved = this.levelRepository.save(level);
    course.setLevel(levelSaved);

    List<Teacher> teachers = new ArrayList<>();
    Teacher teacherSaved = this.teacherRepository.save(teacher);
    teachers.add(teacherSaved);
    course.setTeachers(teachers);

    List<Student> students = new ArrayList<>();
    Student studentSaved = this.studentRepository.save(student);
    students.add(studentSaved);
    course.setStudents(students);

    Course courseSaved = this.courseRepository.save(course);
    //when
    Optional<Course> courseFound = this.courseRepository.findById(courseSaved.getId());
    //then
    assertTrue(courseFound.isPresent());
    assertEquals(course.getName(), courseFound.get().getName());
  }

  @Test
  @DisplayName("Test CourseRepository, verify if a course exists by name")
  void existsCourseByName() {
    //given
    Level levelSaved = this.levelRepository.save(level);
    course.setLevel(levelSaved);

    List<Teacher> teachers = new ArrayList<>();
    Teacher teacherSaved = this.teacherRepository.save(teacher);
    teachers.add(teacherSaved);
    course.setTeachers(teachers);

    List<Student> students = new ArrayList<>();
    Student studentSaved = this.studentRepository.save(student);
    students.add(studentSaved);
    course.setStudents(students);

    Course courseSaved = this.courseRepository.save(course);
    //when
    boolean isExist = this.courseRepository.existsCourseByName(courseSaved.getName());
    //then
    assertTrue(isExist);
  }

  @Test
  @DisplayName("Test CourseRepository, find all courses by level")
  void findByLevel() {
    //given
    Level levelSaved = this.levelRepository.save(level);
    course.setLevel(levelSaved);

    List<Teacher> teachers = new ArrayList<>();
    Teacher teacherSaved = this.teacherRepository.save(teacher);
    teachers.add(teacherSaved);
    course.setTeachers(teachers);

    List<Student> students = new ArrayList<>();
    Student studentSaved = this.studentRepository.save(student);
    students.add(studentSaved);
    course.setStudents(students);

    Course courseSaved = this.courseRepository.save(course);
    //when
    Pageable pageable = PageRequest.of(0, 10);
    Page<Course> coursesFound = this.courseRepository.findByLevel(levelSaved, pageable);
    //then
    assertThat(coursesFound.getContent().size()).isGreaterThan(0);
    assertTrue(coursesFound.isLast());
    assertThat(coursesFound.getTotalElements()).isEqualTo(1);
  }

  @DisplayName("Test CourseRepository, find all courses by teacher")
  @Test
  void findByTeacher(){
    Level levelSaved = this.levelRepository.save(level);
    course.setLevel(levelSaved);

    List<Teacher> teachers = new ArrayList<>();
    Teacher teacherSaved = this.teacherRepository.save(teacher);
    teachers.add(teacherSaved);
    course.setTeachers(teachers);

    List<Student> students = new ArrayList<>();
    Student studentSaved = this.studentRepository.save(student);
    students.add(studentSaved);
    course.setStudents(students);

    this.courseRepository.save(course);

    Pageable pageable = PageRequest.of(0, 10);
    Page<Course> coursesFound = this.courseRepository.findByTeachers(teacherSaved, pageable);

    assertThat(coursesFound.getContent().size()).isGreaterThan(0);
    assertTrue(coursesFound.isLast());
    assertThat(coursesFound.getTotalElements()).isEqualTo(1);
  }

  @Test
  @DisplayName("Test CourseRepository, find all courses")
  void getAll() {
    //given
    Level levelSaved = this.levelRepository.save(level);
    course.setLevel(levelSaved);

    List<Teacher> teachers = new ArrayList<>();
    Teacher teacherSaved = this.teacherRepository.save(teacher);
    teachers.add(teacherSaved);
    course.setTeachers(teachers);

    List<Student> students = new ArrayList<>();
    Student studentSaved = this.studentRepository.save(student);
    students.add(studentSaved);
    course.setStudents(students);

    this.courseRepository.save(course);
    //when
    List<Course> coursesFound = this.courseRepository.findAll();
    //then
    assertNotNull(coursesFound);
    assertThat(coursesFound.size()).isGreaterThan(0);
  }
  @Test
  @DisplayName("Test CourseRepository, test to delete a schedule")
  void delete(){
    //given
    Level levelSaved = this.levelRepository.save(level);
    course.setLevel(levelSaved);

    List<Teacher> teachers = new ArrayList<>();
    Teacher teacherSaved = this.teacherRepository.save(teacher);
    teachers.add(teacherSaved);
    course.setTeachers(teachers);

    List<Student> students = new ArrayList<>();
    Student studentSaved = this.studentRepository.save(student);
    students.add(studentSaved);
    course.setStudents(students);

    Course courseSaved=this.courseRepository.save(course);
    //when
    this.courseRepository.deleteById(courseSaved.getId());
    //then
    Optional<Course> courseFound=this.courseRepository.findById(courseSaved.getId());
    assertTrue(courseFound.isEmpty());
  }
}