package com.app.dojo.services.strategyCourses;

import com.app.dojo.models.Course;
import com.app.dojo.models.Level;
import com.app.dojo.repositories.CourseRepository;
import com.app.dojo.services.Interfaces.LevelService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class CoursesLevel implements CoursesStrategy {
  private final LevelService levelService;
  private final CourseRepository courseRepository;

  public CoursesLevel(LevelService levelService, CourseRepository courseRepository) {
    this.levelService = levelService;
    this.courseRepository = courseRepository;
  }


  @Override
  public Page<Course> findCourses(Pageable pageable, Long idCondition) {
    Level levelFound=levelService.getOne(idCondition);
    return this.courseRepository.findByLevel(levelFound, pageable);
  }
}
