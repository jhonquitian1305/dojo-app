package com.app.dojo.services.strategyCourses;

import com.app.dojo.models.Course;
import com.app.dojo.repositories.CourseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class Courses implements CoursesStrategy{
  private final CourseRepository courseRepository;

  public Courses(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }


  @Override
  public Page<Course> findCourses(Pageable pageable, Long idCondition) {
    return this.courseRepository.findAll(pageable);
  }
}
