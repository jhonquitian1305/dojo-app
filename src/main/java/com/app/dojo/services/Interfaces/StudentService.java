package com.app.dojo.services.Interfaces;

import com.app.dojo.dtos.StudentDTO;
import com.app.dojo.models.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    StudentDTO saveStudent(StudentDTO studentDTO);
    List<Student> getAllStudents();
    StudentDTO getStudentById(Long id) throws Exception;
    void deleteStudent(Long id) throws Exception;
}
