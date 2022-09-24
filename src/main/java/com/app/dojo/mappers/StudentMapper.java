package com.app.dojo.mappers;

import com.app.dojo.builders.builderModels.StudentBuilder;
import com.app.dojo.dtos.StudentDTO;
import com.app.dojo.models.Student;

public class StudentMapper {
    public static Student mapStudent(StudentDTO studentDTO){
        return new StudentBuilder()
                .setId(studentDTO.getId())
                .setDni(studentDTO.getDni())
                .setNames(studentDTO.getNames())
                .setLastnames(studentDTO.getLastnames())
                .setBirthday(studentDTO.getBirthday())
                .setEmail(studentDTO.getEmail())
                .setPassword(studentDTO.getPassword())
                .build();
    }
}
