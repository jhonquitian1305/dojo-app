package com.app.dojo.mappers;

import com.app.dojo.builders.builderDTO.StudentDTOBuilder;
import com.app.dojo.builders.builderModels.StudentBuilder;
import com.app.dojo.dtos.StudentDTO;
import com.app.dojo.models.Student;
import org.springframework.stereotype.Component;

@Component
public class MapperStudent {
    public Student mapStudent(StudentDTO studentDTO, String hashPassword){
        return new StudentBuilder()
                .setId(studentDTO.getId())
                .setDni(studentDTO.getDni())
                .setNames(studentDTO.getNames())
                .setLastnames(studentDTO.getLastnames())
                .setBirthday(studentDTO.getBirthday())
                .setEmail(studentDTO.getEmail())
                .setPassword(hashPassword)
                .build();
    }

    public StudentDTO mapStudentDTO(Student student){
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
