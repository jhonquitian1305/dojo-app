package com.app.dojo.services.implementation;

import com.app.dojo.exception.errors.BadRequest;
import com.app.dojo.models.Teacher;
import com.app.dojo.repositories.TeacherRepository;
import com.app.dojo.services.Interfaces.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImp implements TeacherService {

    @Autowired
    TeacherRepository teacherRepository;

    public void findTeacherByDni(String dni){
        Teacher teacherFound = this.teacherRepository.findTeacherByDni(dni);
        if(teacherFound != null){
            throw new BadRequest("This teacher already exists");
        }
    }
}
