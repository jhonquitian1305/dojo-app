package com.app.dojo.security;

import com.app.dojo.models.Teacher;
import com.app.dojo.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TeacherDetailsServiceImp implements UserDetailsService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Teacher teacher = this.teacherRepository.findTeacherByEmail(email);
        if(teacher == null) throw new UsernameNotFoundException(String.format("The teacher with email %s doesn't email", email));
        return new TeacherDetailsImp(teacher);
    }
}
