package com.app.dojo.services.Interfaces;

import com.app.dojo.models.Student;

public interface EncryptService {
    String encryptPassword(String password);

    boolean verifyPassword(String originalPassword, String hashPassword);

    Student saveStudent(Student student);
}
