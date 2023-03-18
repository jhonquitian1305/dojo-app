package com.app.dojo.services.Interfaces;

import com.app.dojo.dtos.StudentDTO;
import com.app.dojo.dtos.StudentResponse;
import com.app.dojo.models.Student;
import org.springframework.stereotype.Service;

import java.util.List;


public interface StudentService {
    Student saveStudent(StudentDTO studentDTO);
    StudentResponse getAllStudents(int numberPage, int pageSize, String sortBy, String sortDir);
    Student getStudentById(Long id) throws Exception;
    Student getStudentByDni(String dni);
    Student getStudentByEmail(String email);
    Student updateOne(Long id, StudentDTO studentDTO);
    void deleteStudent(Long id) throws Exception;
}
