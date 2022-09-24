package com.app.dojo.builders.builderModels;

import com.app.dojo.models.Student;

import java.util.Date;

public class StudentBuilder {
    private Long id;
    private String dni;
    private String names;
    private String lastnames;
    private Date birthday;
    private String email;
    private String password;

    public StudentBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public StudentBuilder setDni(String dni){
        this.dni = dni;
        return this;
    }

    public StudentBuilder setNames(String names){
        this.names = names;
        return this;
    }

    public StudentBuilder setLastnames(String lastNames){
        this.lastnames = lastNames;
        return this;
    }

    public StudentBuilder setBirthday(Date birthday){
        this.birthday = birthday;
        return this;
    }

    public StudentBuilder setEmail(String email){
        this.email = email;
        return this;
    }

    public StudentBuilder setPassword(String password){
        this.password = password;
        return this;
    }

    public Student build(){
        return new Student(id, dni, names, lastnames, birthday, email, password);
    }
}
