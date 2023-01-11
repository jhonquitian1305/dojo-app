package com.app.dojo.security;

import com.app.dojo.models.Student;
import com.app.dojo.models.Teacher;
import com.app.dojo.repositories.StudentRepository;
import com.app.dojo.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Primary
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Student student = this.studentRepository
                .findStudentByEmail(email);
        if(student == null){
            Teacher teacher = this.teacherRepository.findTeacherByEmail(email);
            if(teacher == null) throw new UsernameNotFoundException(String.format("The user with email %s doesn't exists", email));
            return new TeacherDetailsImp(teacher);
        }
        return new StudentDetailsImp(student);
    }
}
