package com.app.dojo.mappers;

import com.app.dojo.builders.builderDTO.StudentDTOBuilder;
import com.app.dojo.dtos.StudentDTO;
import com.app.dojo.models.Student;

public class StudentDTOMapper {
    public static StudentDTO mapStudentDTO(Student student){
        return new StudentDTOBuilder()
                .setId(student.getId())
                .setDni(student.getDni())
                .setNames(student.getNames())
                .setLastnames(student.getLastnames())
                .setBirthday(student.getBirthday())
                .setEmail(student.getEmail())
                .setPassword(student.getPassword())
                .build();
    }
}
