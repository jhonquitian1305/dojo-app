package com.app.dojo.services.implementation;

import com.app.dojo.models.Student;
import com.app.dojo.repositories.StudentRepository;
import com.app.dojo.services.Interfaces.EncryptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class EncryptServiceImp implements EncryptService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public String encryptPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @Override
    public boolean verifyPassword(String originalPassword, String hashPassword) {
        return false;
    }

    @Override
    public Student saveStudent(Student student){
        return this.studentRepository.save(student);
    }
}
