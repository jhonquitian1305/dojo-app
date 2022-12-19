package com.app.dojo.services.implementation;

import com.app.dojo.builders.builderDTO.TeacherResponseBuilder;
import com.app.dojo.dtos.TeacherDTO;
import com.app.dojo.dtos.TeacherResponse;
import com.app.dojo.exception.errors.BadRequest;
import com.app.dojo.mappers.MapperTeacher;
import com.app.dojo.models.Teacher;
import com.app.dojo.repositories.TeacherRepository;
import com.app.dojo.services.Interfaces.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImp implements TeacherService {

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    MapperTeacher mapperTeacher;

    @Override
    public Teacher save(TeacherDTO teacherDTO) {
        findTeacherByDni(teacherDTO.getDni());
        findTeacherByEmail(teacherDTO.getEmail());

        Teacher teacher = this.mapperTeacher.mapTeacher(teacherDTO);

        Teacher teacherSaved = this.teacherRepository.save(teacher);

        return teacherSaved;
    }

    @Override
    public TeacherResponse getAll(int numberPage, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(numberPage, pageSize, sort);

        Page<Teacher> teachersFound = this.teacherRepository.findAll(pageable);

        List<Teacher> teacherListFound = teachersFound.getContent();
        List<TeacherDTO> teachers = teacherListFound.stream().map(this.mapperTeacher::mapTeacherDTO).toList();


        return new TeacherResponseBuilder()
                .setContent(teachers)
                .setNumberPage(teachersFound.getNumber())
                .setSizePage(teachersFound.getSize())
                .setTotalElements(teachersFound.getTotalElements())
                .setTotalPages(teachersFound.getTotalPages())
                .setLastOne(teachersFound.isLast())
                .build();
    }

    public void findTeacherByDni(String dni){
        Teacher teacherFound = this.teacherRepository.findTeacherByDni(dni);
        if(teacherFound != null){
            throw new BadRequest("This teacher already exists");
        }
    }

    public void findTeacherByEmail(String email){
        Teacher teacherFound = this.teacherRepository.findStudentByEmail(email);
        if(teacherFound != null){
            throw new BadRequest("This email already exists");
        }
    }
}
