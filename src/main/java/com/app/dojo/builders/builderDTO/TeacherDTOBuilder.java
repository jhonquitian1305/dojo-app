package com.app.dojo.builders.builderDTO;

import com.app.dojo.dtos.TeacherDTO;

import java.util.Date;

public class TeacherDTOBuilder {
    private Long id;
    private String dni;
    private String names;
    private String lastnames;
    private Date birthday;
    private String email;
    private String password;

    public TeacherDTOBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public TeacherDTOBuilder setDni(String dni) {
        this.dni = dni;
        return this;
    }

    public TeacherDTOBuilder setNames(String names) {
        this.names = names;
        return this;
    }

    public TeacherDTOBuilder setLastnames(String lastnames) {
        this.lastnames = lastnames;
        return this;
    }

    public TeacherDTOBuilder setBirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }

    public TeacherDTOBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public TeacherDTOBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public TeacherDTO build(){
        return new TeacherDTO(id, dni, names, lastnames, birthday, email, password);
    }
}