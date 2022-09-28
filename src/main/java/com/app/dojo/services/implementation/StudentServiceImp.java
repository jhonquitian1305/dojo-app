package com.app.dojo.services.implementation;

import com.app.dojo.dtos.StudentDTO;
import com.app.dojo.exception.errors.BadRequest;
import com.app.dojo.exception.errors.NotFoundException;
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
        StudentDTO studentFound = getStudentByDni(studentDTO);
        if(studentFound != null){
            throw new BadRequest("This student already exists");
        }

        Student student = StudentMapper.mapStudent(studentDTO);

        Student studentSaved = studentRepository.save(student);

        return StudentDTOMapper.mapStudentDTO(studentSaved);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public StudentDTO getStudentById(Long id) throws NotFoundException {
        Optional<Student> studentFound = studentRepository.findById(id);
        if(studentFound.isEmpty()){
            throw new NotFoundException(String.format("Student with id %s doesn't exists", id));
        }
        return StudentDTOMapper.mapStudentDTO(studentFound.get());
    }

    @Override
    public StudentDTO getStudentByDni(StudentDTO studentDTO) {
        Student studentFound = studentRepository.findStudentByDni(studentDTO.getDni());
        if(studentFound == null){
            throw new NotFoundException(String.format("Student with dni %s doesn't exists", studentDTO.getDni()));
        }
        return StudentDTOMapper.mapStudentDTO(studentFound);
    }

    @Override
    public void deleteStudent(Long id){
        StudentDTO studentFound = this.getStudentById(id);
        this.studentRepository.deleteById(studentFound.getId());
    }
}
