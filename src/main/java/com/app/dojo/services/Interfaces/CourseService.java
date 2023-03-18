package com.app.dojo.services.Interfaces;

import com.app.dojo.dtos.CourseDTO;
import com.app.dojo.dtos.CourseResponse;
import com.app.dojo.models.Course;

import java.util.List;

public interface CourseService {
    Course create(CourseDTO courseDTO) throws Exception;
    Course getOne(Long id);
    CourseResponse getAll(int numberPage, int pageSize,String model, Long idCondition) throws Exception;
    Course update(Long id, CourseDTO courseDTO) throws Exception;
    void delete(Long id);
}
