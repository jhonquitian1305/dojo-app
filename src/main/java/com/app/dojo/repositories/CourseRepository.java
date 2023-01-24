package com.app.dojo.repositories;

import com.app.dojo.models.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    boolean existsCourseByName(String name);
    boolean existsCourseByNameAndIdNot(String name,Long id);
    Page<Course> findByLevel(Level level, Pageable pageable);
    Page<Course> findByTeachers(Teacher teacher, Pageable pageable);
    Page<Course> findByStudents(Student student, Pageable pageable);
}
