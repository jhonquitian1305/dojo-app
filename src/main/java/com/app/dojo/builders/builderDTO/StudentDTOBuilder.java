package com.app.dojo.builders.builderDTO;

import com.app.dojo.dtos.StudentDTO;

import java.util.Date;

public class StudentDTOBuilder {
    private Long id;
    private String dni;
    private String names;
    private String lastnames;
    private Date birthday;
    private String email;
    private String password;

    public StudentDTOBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public StudentDTOBuilder setDni(String dni) {
        this.dni = dni;
        return this;
    }

    public StudentDTOBuilder setNames(String names) {
        this.names = names;
        return this;
    }

    public StudentDTOBuilder setLastnames(String lastnames) {
        this.lastnames = lastnames;
        return this;
    }

    public StudentDTOBuilder setBirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }

    public StudentDTOBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public StudentDTOBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public StudentDTO build(){
        return new StudentDTO(id, dni, names, lastnames, birthday, email, password);
    }
}
