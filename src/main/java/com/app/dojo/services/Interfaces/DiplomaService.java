package com.app.dojo.services.Interfaces;

import com.app.dojo.dtos.DiplomaDTO;
import com.app.dojo.models.Diploma;

public interface DiplomaService {
    Diploma saveDiplomaStudent(Long id, DiplomaDTO diplomaDTO) throws Exception;
    Diploma saveDiplomaTeacher(Long id, DiplomaDTO diplomaDTO);
}
