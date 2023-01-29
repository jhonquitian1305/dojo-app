package com.app.dojo.services.Interfaces;

import com.app.dojo.dtos.DiplomaDTO;
import com.app.dojo.dtos.DiplomaResponse;
import com.app.dojo.dtos.StudentDTO;
import com.app.dojo.models.Diploma;
import com.app.dojo.models.Student;
import com.app.dojo.models.User;

import java.util.List;

public interface DiplomaService {
    Diploma saveDiplomaStudent(Long id, DiplomaDTO diplomaDTO) throws Exception;
    Diploma saveDiplomaTeacher(Long id, DiplomaDTO diplomaDTO);
    DiplomaResponse getDiplomasStudent(Long idStudent, int numberPage, int pageSize, String sortBy, String sortDir) throws Exception;
    DiplomaResponse getDiplomasTeacher(Long idTeacher, int numberPage, int pageSize, String sortBy, String sortDir);
}
