package com.app.dojo.services.Interfaces;

import com.app.dojo.dtos.TeacherDTO;
import com.app.dojo.dtos.TeacherResponse;
import com.app.dojo.models.Teacher;

public interface TeacherService {
    Teacher save(TeacherDTO teacherDTO);
    TeacherResponse getAll(int numberPage, int pageSize, String sortBy, String sortDir);
    Teacher getById(Long id);
    Teacher getByDni(String dni);
    Teacher getByEmail(String email);
    Teacher updateOne(Long id, TeacherDTO teacherDTO);
    void deleteOne(Long id);
}
