package com.app.dojo.security;

import com.app.dojo.models.Student;
import com.app.dojo.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class StudentDetailsServiceImp implements UserDetailsService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Student student = this.studentRepository
                .findStudentByEmail(email);
        if(student == null){
            throw new UsernameNotFoundException(String.format("The student with email %s doesn't exists", email));
        }
        return new StudentDetailsImp(student);
    }
}
