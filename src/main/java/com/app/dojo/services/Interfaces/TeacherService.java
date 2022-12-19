package com.app.dojo.services.Interfaces;

import com.app.dojo.dtos.TeacherDTO;
import com.app.dojo.models.Teacher;

public interface TeacherService {
    Teacher save(TeacherDTO teacherDTO);
}
