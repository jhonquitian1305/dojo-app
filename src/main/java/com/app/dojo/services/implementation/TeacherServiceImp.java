package com.app.dojo.services.implementation;

import com.app.dojo.dtos.TeacherDTO;
import com.app.dojo.exception.errors.BadRequest;
import com.app.dojo.mappers.MapperTeacher;
import com.app.dojo.models.Teacher;
import com.app.dojo.repositories.TeacherRepository;
import com.app.dojo.services.Interfaces.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
