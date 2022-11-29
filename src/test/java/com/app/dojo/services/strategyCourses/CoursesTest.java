package com.app.dojo.services.strategyCourses;

import com.app.dojo.builders.builderModels.CourseBuilder;
import com.app.dojo.builders.builderModels.LevelBuilder;
import com.app.dojo.models.Course;
import com.app.dojo.models.Level;
import com.app.dojo.repositories.CourseRepository;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles(profiles = "test")
class CoursesTest {

  @Mock
  private CourseRepository courseRepository;
  @InjectMocks
  private Courses courses;
  private Course course;
  private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

  @BeforeEach()
  void init() throws ParseException {
    Level level= new LevelBuilder()
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
        .build();
  }

  @Test
  @DisplayName("Test CoursesStrategy, get all courses without any condition")
  void findCourses(){
    //given
    Page<Course> courses=new PageImpl<>(List.of(course));
    Pageable pageable=PageRequest.of(1,10);
    given(this.courseRepository.findAll(pageable)).willReturn(courses);
    //when
    Page<Course> coursesFound=this.courses.findCourses(pageable,anyLong());
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