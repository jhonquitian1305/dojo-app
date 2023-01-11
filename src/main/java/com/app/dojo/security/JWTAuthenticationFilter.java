package com.app.dojo.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collections;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        AuthCredentials authCredentials = new AuthCredentials();

        try {
            authCredentials = new ObjectMapper().readValue(request.getReader(), AuthCredentials.class);
        } catch (IOException e){
        }

        UsernamePasswordAuthenticationToken usernamePAT = new UsernamePasswordAuthenticationToken(
                authCredentials.getEmail(),
                authCredentials.getPassword(),
                Collections.emptyList()
        );
        return getAuthenticationManager().authenticate(usernamePAT);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token = "";
        if(authResult.getPrincipal().getClass().equals(StudentDetailsImp.class)){
            StudentDetailsImp studentDetails = (StudentDetailsImp) authResult.getPrincipal();
            token = TokenUtils.createToken(studentDetails.getNombre(), studentDetails.getUsername());
        }else{
            TeacherDetailsImp teacherDetails = (TeacherDetailsImp) authResult.getPrincipal();
            token = TokenUtils.createToken(teacherDetails.getNombre(), teacherDetails.getUsername());
        }

        response.addHeader("Authorization", String.format("Bearer %s", token));
        response.getWriter().flush();

        super.successfulAuthentication(request, response, chain, authResult);
    }
}
