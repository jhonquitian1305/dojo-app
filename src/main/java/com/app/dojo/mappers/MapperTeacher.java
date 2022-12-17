package com.app.dojo.mappers;

import com.app.dojo.builders.builderDTO.TeacherDTOBuilder;
import com.app.dojo.builders.builderModels.TeacherBuilder;
import com.app.dojo.dtos.TeacherDTO;
import com.app.dojo.models.Teacher;

public class MapperTeacher {
    public Teacher mapTeacher(TeacherDTO teacherDTO){
        return new TeacherBuilder()
                .setId(teacherDTO.getId())
                .setDni(teacherDTO.getDni())
                .setNames(teacherDTO.getNames())
                .setLastnames(teacherDTO.getLastnames())
                .setBirthday(teacherDTO.getBirthday())
                .setEmail(teacherDTO.getEmail())
                .setPassword(teacherDTO.getPassword())
                .build();
    }

    public TeacherDTO mapTeacherDTO(Teacher teacher){
        return new TeacherDTOBuilder()
                .setId(teacher.getId())
                .setDni(teacher.getDni())
                .setNames(teacher.getNames())
                .setLastnames(teacher.getLastnames())
                .setEmail(teacher.getEmail())
                .setPassword(teacher.getPassword())
                .build();
    }
}
