package com.app.dojo.services.strategyCourses;

import com.app.dojo.models.Course;
import com.app.dojo.models.Teacher;
import com.app.dojo.repositories.CourseRepository;
import com.app.dojo.services.Interfaces.TeacherService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class CoursesTeacher implements CoursesStrategy {
    private final TeacherService teacherService;
    private final CourseRepository courseRepository;

    public CoursesTeacher(TeacherService teacherService, CourseRepository courseRepository) {
        this.teacherService = teacherService;
        this.courseRepository = courseRepository;
    }

    @Override
    public Page<Course> findCourses(Pageable pageable, Long idCondition) {
        Teacher teacherFound = this.teacherService.getById(idCondition);
        return this.courseRepository.findByTeachers(teacherFound, pageable);
    }
}
