package com.app.dojo.repositories;

import com.app.dojo.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findStudentByDni(String dni);
    Student findStudentByEmail(String email);
}
