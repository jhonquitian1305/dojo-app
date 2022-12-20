package com.app.dojo.services.implementation;

import com.app.dojo.builders.builderDTO.TeacherResponseBuilder;
import com.app.dojo.dtos.TeacherDTO;
import com.app.dojo.dtos.TeacherResponse;
import com.app.dojo.exception.errors.BadRequest;
import com.app.dojo.exception.errors.NotFoundException;
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
import java.util.Optional;

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

    @Override
    public Teacher getById(Long id) {
        Optional<Teacher> teacherFound = this.teacherRepository.findById(id);
        if(teacherFound.isEmpty()){
            throw new NotFoundException(String.format("Teacher with id %s doesn't exists", id));
        }
        return teacherFound.get();
    }

    @Override
    public Teacher getByDni(TeacherDTO teacherDTO) {
        Teacher teacherFound = this.teacherRepository.findTeacherByDni(teacherDTO.getDni());
        if(teacherFound == null){
            throw new NotFoundException(String.format("Teacher with dni %s doesn't exists", teacherDTO.getDni()));
        }
        return teacherFound;
    }

    @Override
    public Teacher getByEmail(TeacherDTO teacherDTO) {
        Teacher teacherFound = this.teacherRepository.findTeacherByEmail(teacherDTO.getEmail());
        if(teacherFound == null){
            throw new NotFoundException(String.format("Teacher with email %s doesn't exists", teacherDTO.getEmail()));
        }
        return teacherFound;
    }

    @Override
    public Teacher updateOne(Long id, TeacherDTO teacherDTO) {
        Optional<Teacher> teacherFound = this.teacherRepository.findById(id);
        if(teacherFound.isEmpty()){
            throw new NotFoundException(String.format("Teacher with id %s doesn't exists", id));
        }
        Teacher teacherUpdated = updateTeacher(teacherFound.get(), teacherDTO);
        return this.teacherRepository.save(teacherUpdated);
    }

    public void findTeacherByDni(String dni){
        Teacher teacherFound = this.teacherRepository.findTeacherByDni(dni);
        if(teacherFound != null){
            throw new BadRequest("This teacher already exists");
        }
    }

    public void findTeacherByEmail(String email){
        Teacher teacherFound = this.teacherRepository.findTeacherByEmail(email);
        if(teacherFound != null){
            throw new BadRequest("This email already exists");
        }
    }

    private Teacher updateTeacher(Teacher teacher, TeacherDTO teacherDTO){
        teacher.setDni(teacherDTO.getDni());
        teacher.setNames(teacherDTO.getNames());
        teacher.setLastnames(teacherDTO.getLastnames());
        teacher.setBirthday(teacherDTO.getBirthday());
        teacher.setEmail(teacherDTO.getEmail());
        teacher.setPassword(teacherDTO.getPassword());

        return teacher;
    }
}
