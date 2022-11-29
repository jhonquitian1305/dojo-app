package com.app.dojo.services.strategyCourses;

import com.app.dojo.builders.builderModels.CourseBuilder;
import com.app.dojo.builders.builderModels.LevelBuilder;
import com.app.dojo.models.Course;
import com.app.dojo.models.Level;
import com.app.dojo.repositories.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

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
}