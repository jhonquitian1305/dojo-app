package com.app.dojo.repositories;

import com.app.dojo.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Teacher findTeacherByDni(String dni);
    Teacher findStudentByEmail(String email);
}
