package com.app.dojo.services.implementation;

import com.app.dojo.dtos.StudentDTO;
import com.app.dojo.mappers.StudentDTOMapper;
import com.app.dojo.mappers.StudentMapper;
import com.app.dojo.models.Student;
import com.app.dojo.repositories.StudentRepository;
import com.app.dojo.services.Interfaces.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImp implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public StudentDTO saveStudent(StudentDTO studentDTO) {
        Student student = StudentMapper.mapStudent(studentDTO);

        Student studentSaved = studentRepository.save(student);

        return StudentDTOMapper.mapStudentDTO(studentSaved);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public StudentDTO getStudentById(Long id){
        Optional<Student> studentFound = studentRepository.findById(id);
        //TODO: verificar que no sea nulo
        return StudentDTOMapper.mapStudentDTO(studentFound.get());
    }

    @Override
    public void deleteStudent(Long id) {
        StudentDTO studentFound = this.getStudentById(id);
        this.studentRepository.deleteById(studentFound.getId());
    }
}
