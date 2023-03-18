package com.app.dojo.repositories;

import com.app.dojo.models.Comment;
import com.app.dojo.models.Course;
import com.app.dojo.models.Student;
import com.app.dojo.models.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findByCourse(Course course, Pageable pageable);
    Page<Comment> findByTeacher(Teacher teacher, Pageable pageable);
    Page<Comment> findByStudent(Student student, Pageable pageable);
}
