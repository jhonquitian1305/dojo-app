package com.app.dojo.services.Interfaces;

import com.app.dojo.dtos.CourseDTO;
import com.app.dojo.dtos.CourseResponse;
import com.app.dojo.models.Course;

public interface CourseService {
    Course create(CourseDTO courseDTO) throws Exception;
    Course getOne(Long id);
    Course getByName(String name);
    CourseResponse getAll();
    Course update(Long id, CourseDTO courseDTO);
    void delete(Long id);
}
