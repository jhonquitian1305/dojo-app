package com.app.dojo.services.Interfaces;

import com.app.dojo.dtos.StudentDTO;
import com.app.dojo.dtos.StudentResponse;
import com.app.dojo.models.Student;
import org.springframework.stereotype.Service;

import java.util.List;


public interface StudentService {
    StudentDTO saveStudent(StudentDTO studentDTO);
    StudentResponse getAllStudents(int numberPage, int pageSize, String sortBy, String sortDir);
    Student getStudentById(Long id) throws Exception;
    Student getStudentByDni(StudentDTO studentDTO);
    void deleteStudent(Long id) throws Exception;
}
