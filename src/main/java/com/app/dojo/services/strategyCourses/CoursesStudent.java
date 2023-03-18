package com.app.dojo.services.strategyCourses;

import com.app.dojo.models.Course;
import com.app.dojo.models.Student;
import com.app.dojo.repositories.CourseRepository;
import com.app.dojo.services.Interfaces.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class CoursesStudent implements CoursesStrategy {

    private final StudentService studentService;
    private final CourseRepository courseRepository;

    public CoursesStudent(StudentService studentService, CourseRepository courseRepository) {
        this.studentService = studentService;
        this.courseRepository = courseRepository;
    }

    @Override
    public Page<Course> findCourses(Pageable pageable, Long idCondition) throws Exception {
        Student studentFound = this.studentService.getStudentById(idCondition);
        return this.courseRepository.findByStudents(studentFound, pageable);
    }
}
