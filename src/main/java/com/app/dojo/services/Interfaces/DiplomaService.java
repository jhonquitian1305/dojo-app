package com.app.dojo.services.Interfaces;

import com.app.dojo.dtos.DiplomaById;
import com.app.dojo.dtos.DiplomaDTO;
import com.app.dojo.dtos.DiplomaResponse;
import com.app.dojo.models.Diploma;

public interface DiplomaService {
    Diploma saveDiplomaStudent(Long id, DiplomaDTO diplomaDTO) throws Exception;
    Diploma saveDiplomaTeacher(Long id, DiplomaDTO diplomaDTO);
    DiplomaResponse getDiplomasStudent(Long idStudent, int numberPage, int pageSize, String sortBy, String sortDir) throws Exception;
    DiplomaResponse getDiplomasTeacher(Long idTeacher, int numberPage, int pageSize, String sortBy, String sortDir);
    Diploma getByIdDiplomaStudent(Long idStudent, Long idDiploma) throws Exception;
    Diploma getByIdDiplomaTeacher(Long idTeacher, Long idDiploma);
    void deleteDiplomaStudent(Long idStudent, Long idDiploma) throws Exception;
    void deleteDiplomaTeacher(Long idTeacher, Long idDiploma);
}
