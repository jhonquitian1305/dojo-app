package com.app.dojo.services.implementation;

import com.app.dojo.builders.builderDTO.StudentResponseBuilder;
import com.app.dojo.dtos.StudentDTO;
import com.app.dojo.dtos.StudentResponse;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImp implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public StudentDTO saveStudent(StudentDTO studentDTO) {
        Student studentFound = this.studentRepository.findStudentByDni(studentDTO.getDni());
        if(studentFound != null){
            throw new BadRequest("This student already exists");
        }

        Student student = StudentMapper.mapStudent(studentDTO);

        Student studentSaved = studentRepository.save(student);

        return StudentDTOMapper.mapStudentDTO(studentSaved);
    }

    @Override
    public StudentResponse getAllStudents(int numberPage, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(numberPage, pageSize, sort);

        Page<Student> studentsFound = studentRepository.findAll(pageable);

        List<Student> studentListFound = studentsFound.getContent();
        List<StudentDTO> students = studentListFound.stream().map(StudentDTOMapper::mapStudentDTO).collect(Collectors.toList());

        return new StudentResponseBuilder()
                .setContent(students)
                .setNumberPage(studentsFound.getNumber())
                .setSizePage(studentsFound.getSize())
                .setTotalElements(studentsFound.getTotalElements())
                .setTotalPages(studentsFound.getTotalPages())
                .setLastOne(studentsFound.isLast())
                .build();
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
