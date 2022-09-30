package com.app.dojo.services.Interfaces;

import com.app.dojo.dtos.StudentDTO;
import com.app.dojo.dtos.StudentResponse;
import com.app.dojo.models.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    StudentDTO saveStudent(StudentDTO studentDTO);
    StudentResponse getAllStudents(int numberPage, int pageSize, String sortBy, String sortDir);
    StudentDTO getStudentById(Long id) throws Exception;
    StudentDTO getStudentByDni(StudentDTO studentDTO);
    void deleteStudent(Long id) throws Exception;
}
