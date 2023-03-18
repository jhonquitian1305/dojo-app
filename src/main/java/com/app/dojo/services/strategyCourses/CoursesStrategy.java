package com.app.dojo.services.strategyCourses;

import com.app.dojo.models.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CoursesStrategy {
  Page<Course> findCourses(Pageable pageable,Long idCondition) throws Exception;
}
