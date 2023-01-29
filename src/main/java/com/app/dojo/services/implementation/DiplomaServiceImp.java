package com.app.dojo.services.implementation;

import com.app.dojo.dtos.DiplomaDTO;
import com.app.dojo.exception.errors.BadRequest;
import com.app.dojo.mappers.MapperDiploma;
import com.app.dojo.models.Diploma;
import com.app.dojo.models.User;
import com.app.dojo.repositories.DiplomaRepository;
import com.app.dojo.services.Interfaces.DiplomaService;
import com.app.dojo.services.Interfaces.StudentService;
import com.app.dojo.services.Interfaces.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiplomaServiceImp implements DiplomaService {
    @Autowired
    DiplomaRepository diplomaRepository;

    @Autowired
    MapperDiploma mapperDiploma;

    @Autowired
    StudentService studentService;

    @Autowired
    TeacherService teacherService;

    @Override
    public Diploma saveDiplomaStudent(Long id, DiplomaDTO diplomaDTO) throws Exception {
        if(this.diplomaRepository.existsByDiplomaName(diplomaDTO.getDiplomaName())){
            throw new BadRequest("This diploma whit name %s already exists".formatted(diplomaDTO.getDiplomaName()));
        }

        User studentFound = this.studentService.getStudentById(id);

        Diploma diplomaCreated = this.diplomaRepository.save((this.mapperDiploma.createDiploma(diplomaDTO, studentFound)));

        return diplomaCreated;
    }

    @Override
    public Diploma saveDiplomaTeacher(Long id, DiplomaDTO diplomaDTO) {
        if(this.diplomaRepository.existsByDiplomaName(diplomaDTO.getDiplomaName())){
            throw new BadRequest("This diploma whit name %s already exists".formatted(diplomaDTO.getDiplomaName()));
        }

        User teacherFound = this.teacherService.getById(id);

        Diploma diplomaCreated = this.diplomaRepository.save((this.mapperDiploma.createDiploma(diplomaDTO, teacherFound)));

        return diplomaCreated;
    }
}
