package com.app.dojo.services;

import com.app.dojo.builders.builderDTO.CourseDTOBuilder;
import com.app.dojo.builders.builderModels.CourseBuilder;
import com.app.dojo.builders.builderModels.LevelBuilder;
import com.app.dojo.dtos.CourseDTO;
import com.app.dojo.exception.errors.BadRequest;
import com.app.dojo.mappers.MapperCourse;
import com.app.dojo.models.Course;
import com.app.dojo.models.Level;
import com.app.dojo.repositories.CourseRepository;
import com.app.dojo.services.Interfaces.LevelService;
import com.app.dojo.services.implementation.CourseServiceImp;
import com.app.dojo.services.strategyCourses.CoursesContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles(profiles = "test")
class CourseServiceTest {
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
  private Course course;
  private Level level;
  private CourseDTO courseDTO;
  private static  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
  @BeforeEach
  void init() throws ParseException {
    // Format Date

    Date startDate = format.parse("2022-06-01");
    Date finishDate = format.parse("2022-06-30");

    level = new LevelBuilder()
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

    courseDTO=new CourseDTOBuilder()
        .setName("CINTA NEGRA PRINCIPIANTES")
        .setStartDate(startDate)
        .setFinishDate(finishDate)
        .setPrice(200000.0)
        .setLevel(1L)
        .build();
  }

  @Test
  @DisplayName("Test CourseService, create a course")
  void create() throws Exception {
    //given
    given(this.courseRepository.existsCourseByName(anyString())).willReturn(false);
    given(this.courseRepository.save(any(Course.class))).willReturn(course);
    given(this.levelService.getOne(anyLong())).willReturn(level);
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

}