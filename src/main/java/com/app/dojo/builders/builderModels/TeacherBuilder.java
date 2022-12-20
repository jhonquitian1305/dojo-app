package com.app.dojo.builders.builderModels;

import com.app.dojo.models.Teacher;

import java.util.Date;

public class TeacherBuilder {
    private Long id;
    private String dni;
    private String names;
    private String lastnames;
    private Date birthday;
    private String email;
    private String password;

    public TeacherBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public TeacherBuilder setDni(String dni) {
        this.dni = dni;
        return this;
    }

    public TeacherBuilder setNames(String names) {
        this.names = names;
        return this;
    }

    public TeacherBuilder setLastnames(String lastnames) {
        this.lastnames = lastnames;
        return this;
    }

    public TeacherBuilder setBirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }

    public TeacherBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public TeacherBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public Teacher build(){
        return new Teacher(id, dni, names, lastnames, birthday, email, password);
    }
}