package com.app.dojo.services.implementation;

import com.app.dojo.builders.builderDTO.DiplomaResponseBuilder;
import com.app.dojo.dtos.DiplomaDTO;
import com.app.dojo.dtos.DiplomaResponse;
import com.app.dojo.exception.errors.BadRequest;
import com.app.dojo.mappers.MapperDiploma;
import com.app.dojo.models.Diploma;
import com.app.dojo.models.Student;
import com.app.dojo.models.User;
import com.app.dojo.repositories.DiplomaRepository;
import com.app.dojo.services.Interfaces.DiplomaService;
import com.app.dojo.services.Interfaces.StudentService;
import com.app.dojo.services.Interfaces.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

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
        existsDiplomaName(diplomaDTO);

        User studentFound = this.studentService.getStudentById(id);

        Diploma diplomaCreated = this.diplomaRepository.save((this.mapperDiploma.createDiploma(diplomaDTO, studentFound)));

        return diplomaCreated;
    }

    @Override
    public Diploma saveDiplomaTeacher(Long id, DiplomaDTO diplomaDTO) {
        existsDiplomaName(diplomaDTO);

        User teacherFound = this.teacherService.getById(id);

        Diploma diplomaCreated = this.diplomaRepository.save((this.mapperDiploma.createDiploma(diplomaDTO, teacherFound)));

        return diplomaCreated;
    }

    @Override
    public DiplomaResponse getDiplomasStudent(Long idStudent, int numberPage, int pageSize, String sortBy, String sortDir) throws Exception {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(numberPage, pageSize, sort);

        Student studentFound = this.studentService.getStudentById(idStudent);

        Page<Diploma> diplomasFound = this.diplomaRepository.findByUser(studentFound, pageable);

        return new DiplomaResponseBuilder()
                .setContent(diplomasFound.getContent())
                .setNumberPage(diplomasFound.getNumber())
                .setSizePage(diplomasFound.getSize())
                .setTotalElements(diplomasFound.getTotalElements())
                .setTotalPages(diplomasFound.getTotalPages())
                .setLastOne(diplomasFound.isLast())
                .build();
    }

    protected void existsDiplomaName(DiplomaDTO diplomaDTO){
        if(this.diplomaRepository.existsByDiplomaName(diplomaDTO.getDiplomaName())){
            throw new BadRequest("This diploma whit name %s already exists".formatted(diplomaDTO.getDiplomaName()));
        }
    }
}
