package com.app.dojo.mappers;

import com.app.dojo.builders.builderDTO.StudentDTOBuilder;
import com.app.dojo.builders.builderModels.StudentBuilder;
import com.app.dojo.dtos.StudentDTO;
import com.app.dojo.models.Student;

public class MapperStudent {
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
