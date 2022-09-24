package com.app.dojo.services.Interfaces;

import com.app.dojo.dtos.StudentDTO;
import org.springframework.stereotype.Service;

@Service
public interface StudentService {
    public StudentDTO saveStudent(StudentDTO studentDTO);
}
