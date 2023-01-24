package com.app.dojo.services.strategyCourses;

import com.app.dojo.repositories.CourseRepository;
import com.app.dojo.services.Interfaces.LevelService;
import com.app.dojo.services.Interfaces.StudentService;
import com.app.dojo.services.Interfaces.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CoursesContext {
  private CoursesStrategy strategy;
  @Autowired
  private LevelService levelService;
  @Autowired
  private CourseRepository courseRepository;
  @Autowired
  private TeacherService teacherService;
  @Autowired
  private StudentService studentService;

  public CoursesStrategy loadStrategy(String modelCondition){
    switch (modelCondition){
      case "LEVEL":
        return new CoursesLevel(levelService,courseRepository);
      case "TEACHER":
        return new CoursesTeacher(this.teacherService, courseRepository);
      case "STUDENT":
        return new CoursesStudent(this.studentService, this.courseRepository);
      default:
        return new Courses(courseRepository);
    }
  }
}
