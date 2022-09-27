package com.app.dojo.services.implementation;

import com.app.dojo.dtos.StudentDTO;
import com.app.dojo.mappers.StudentDTOMapper;
import com.app.dojo.mappers.StudentMapper;
import com.app.dojo.models.Student;
import com.app.dojo.repositories.StudentRepository;
import com.app.dojo.services.Interfaces.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImp implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public StudentDTO saveStudent(StudentDTO studentDTO) {
        Student student = StudentMapper.mapStudent(studentDTO);

        Student studentSaved = studentRepository.save(student);

        StudentDTO studentResponse = StudentDTOMapper.mapStudentDTO(studentSaved);

        return studentResponse;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
